package org.openhab.binding.rosie.blackboard.experts.L3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

import org.openhab.binding.rosie.blackboard.Blackboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

public class ClusteringExpert extends AbstractMLExpert {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 4710992886616775745L;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * States if the algorithm has been trained for the first time
	 */
	private boolean trained;

	/**
	 * Time when the class started execution
	 */
	private long startingTime;
	
	/**
	 * Logger for the class
	 */
	private static final Logger logger = LoggerFactory.getLogger(ClusteringExpert.class);

	/**
	 * Set of the clusters
	 */
	private Instances[] clusters;

	/**
	 * Delta to check the similarity
	 */
	private int delta = 1;

	/**
	 * Amount of random instances to select
	 */
	private static final int K = 16;

	/**
	 * Name of the expert used to store the tuple on the blackboard
	 */
	public static final String ITEM_NAME = "KPOINT_EXPERT";

	/**
	 * Constructor for the class
	 * @param blackboard Not Null
	 */
	public ClusteringExpert(Blackboard blackboard) {
		super(blackboard);
		// Create classifier
		trainingSet = getFeatures();
		clusters = new Instances[K];
		createEmptyClusters();
		
		// Not trained yet
		trained = false;
		startingTime = System.currentTimeMillis();
	}

	/**
	 * Initializes the cluster container
	 */
	private void createEmptyClusters() {
		for (int i = 0; i < clusters.length; i++) {
			clusters[i] = getFeatures();
		}
	}

	@Override
	protected void evaluateKnowledge() {
		try {
			Instance lastInstance = getLastInstance();
			if (!trained) {
				// Is on training phase of current model
				trainingSet.add(lastInstance);
				if (trainingSet.size() > K && trainingTime <= System.currentTimeMillis() - startingTime) {
					try {
						// Training time has passed. Select k elements for
						// clustering
						Random random = new Random();
						for (int i = 0; i < K; i++) {
							int randomIndex = random.nextInt((K-i) - 0 + 1) + 0;
							clusters[i].add(trainingSet.get(randomIndex));
							trainingSet.remove(randomIndex);
							for (int j = 0; j < clusters.length; j++) {
								if (0 == similarityClusterInstance(clusters[j],	lastInstance)) {
									clusters[j].add(lastInstance);
								}
							}
						}
						//trainingSet.clear();
						startingTime = System.currentTimeMillis();
						trained = true;
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			} else {
				// Check if it is time to retrain. A week has passed and it is
				// time to retrain
				if (trainingSet.size() > K && trainingTime <= System.currentTimeMillis() - startingTime) {
					try {
						// Training time has passed. Select k elements for
						// clustering
						Random random = new Random();
						for (int i = 0; i < K; i++) {
							int randomIndex = random.nextInt((K-i) - 0 + 1) + 0;
							clusters[i].add(trainingSet.get(randomIndex));
							trainingSet.remove(randomIndex);
							for (int j = 0; j < clusters.length; j++) {
								if (0 == similarityClusterInstance(clusters[j], lastInstance)) {
									clusters[j].add(lastInstance);
								}
							}

						}
						//trainingSet.clear();
						startingTime = System.currentTimeMillis();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					// No week has passed. Add last instance to instances
					trainingSet.add(lastInstance);
				}
				try {
					// Evaluate current instance and publish result
					Instances largestCluster = getLargestCluster();
					int similarity = similarityClusterInstance(largestCluster, lastInstance);
					Date date = new Date();
					blackboard.addTuple(ITEM_NAME, Level.L3, similarity,dateFormat.format(date), false);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * Returns the instance of the larger cluster
	 * @return May be null
	 */
	private Instances getLargestCluster() {
		Instances response = null;
		int largestSize = 0;
		for (int i = 0; i < clusters.length; i++) {
			if(clusters[i] != null && largestSize < clusters[i].size()){
				largestSize = clusters[i].size();
				response = clusters[i];
			}
				
		}
		return response;
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	}

	/**
	 * Calculates the similarity between an instance and a set of instances. Takes the first element of the instances as the centroid for comparison.
	 * @param instances Not null
	 * @param instance Not null
	 * @return
	 */
	public int similarityClusterInstance(Instances instances, Instance instance) {
		int result = 1;
		if(instances != null && instances.size() > 0){
			Instance clusterInstance = instances.get(0);
			result = similarityInstanceInstance(clusterInstance, instance);			
		}
		return result;
	}

	/**
	 * Calculates the similarity between two instances
	 * @param firstInstance Not null
	 * @param secondInstance Not null
	 * @return
	 */
	public int similarityInstanceInstance(Instance firstInstance, Instance secondInstance) {
		int result = 0;
		
		Enumeration<Attribute> enumerateAttributesFirstInstance = firstInstance.enumerateAttributes();
		Enumeration<Attribute> enumerateAttributesSecondInstance = secondInstance.enumerateAttributes();
		
		while (enumerateAttributesFirstInstance.hasMoreElements()) {
			Attribute attributeFirst = (Attribute) enumerateAttributesFirstInstance.nextElement();
			Attribute attributeSecond = (Attribute) enumerateAttributesSecondInstance.nextElement();
			result += similarity(firstInstance, secondInstance, attributeFirst, attributeSecond);
		}
		return result;
	}

	/**
	 * Compares two instances using euclidian distance for the values
	 * @param firstInstance Complete first instance to compare. Used to get the value of the attribute. Not null
	 * @param secondInstance Complete second instance to compare. Used to get the value of the attribute.  Not null
	 * @param attributeFirst First attribute to compare. Not null
	 * @param attributeSecond Second attribute to compare. Not null
	 * @return
	 */
	private int similarity(Instance firstInstance, Instance secondInstance, Attribute attributeFirst, Attribute attributeSecond) {
		int result =0 ;
		if (attributeFirst.isDate()){
			//How to measure distances for dates?
		} else if(attributeFirst.isNominal()){
			//get nominal values
			int firstPos = (int) firstInstance.value(attributeFirst);
			int secondPos = (int) secondInstance.value(attributeSecond);
			result = attributeFirst.value(firstPos).equals(attributeSecond.value(secondPos)) ? 0 : 1; 
		} else if(attributeFirst.isNumeric()){
			int fistInt = (int) firstInstance.value(attributeFirst);
			int secondInt = (int) secondInstance.value(attributeSecond);
			result = (int) euclidianDistances(fistInt, 0, secondInt,0);
		} else {
			result =  firstInstance.stringValue(attributeFirst).equals(secondInstance.stringValue(attributeSecond)) ? 0 : 1; 
		}
		return result < delta ? 0 : 1;
	}

	/**
	 * Calculates the euclidian distance of the given points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public double euclidianDistances(double x1, double y1, double x2, double y2) {
		double xcoord = Math.abs(x1 - x2);
		double ycoord = Math.abs(y1 - y2);
		double distance = Math.sqrt((ycoord) * (ycoord) + (xcoord) * (xcoord));
		return distance;
	}

}
