package org.openhab.binding.rosie.blackboard.experts.L3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import org.openhab.binding.rosie.blackboard.Blackboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

public class EMClusterExpert extends AbstractMLExpert {

	private static final int NUM_ATTRIBUTES_TO_CHECK = 4;


	/**
	 * 
	 */
	private static final long serialVersionUID = -2102518308253370157L;


	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	
	private static final Logger logger = LoggerFactory
			.getLogger(EMClusterExpert.class);

	
	private EM clusterer;
	
	private boolean trained;
	
	private long startingTime;

	public static final String ITEM_NAME = "CLUSTERING_EXPERT";

	public static final String NUM_CLUSTERS = "NUM_CLUSTERS";
	
	public EMClusterExpert(Blackboard blackboard) {
		super(blackboard,false,false, true);
		//Create classifier
		trainingSet = getFeatures();
		// Create cluster
		 String[] options = new String[2];
		 options[0] = "-I";                 // max. iterations
		 options[1] = "100";
		 
		String[] options2 = new String[20];
		options2[0] = "-I";
		options2[1] = "100";
		options2[2] = "-N";
		options2[3] = "-1";
		options2[4] = "-X";
		options2[5] = "10";
		options2[6] = "-max";
		options2[7] = "-1";
		options2[8] = "-ll-cv";
		options2[9] = "1.0E-6";
		options2[10] = "-ll-iter";
		options2[11] = "1.0E-6";
		options2[12] = "-M";
		options2[13] = "1.0E-6";
		options2[14] = "-K";
		options2[15] = "10";
		options2[16] = "-num-slots";
		options2[17] = "1";
		options2[18] = "-S";
		options2[19] = "100";
		 
		 clusterer = new EM();   // new instance of clusterer
		 try {
			clusterer.setOptions(options2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Not trained yet
		trained = false;
		startingTime = System.currentTimeMillis();
	}

	@Override
	protected void evaluateKnowledge() {
		if(!trained){
			//Gather the data until the model the time has passed and can be trained
			if(TRAINING_TIME <= System.currentTimeMillis() - startingTime ){
				
			}
		}
		
		
		
		
		
		
		try {
			Instance lastInstance = getLastInstance();
			trainingSet.add(lastInstance);
			if (!trained){
				//Is on training phase of current model
				//trainingSet.add(lastInstance);
				if(TRAINING_TIME <= System.currentTimeMillis() - startingTime ){
					try {
						clusterer.buildClusterer(trainingSet);
						startingTime = System.currentTimeMillis();
						//trainingSet.clear();
						trained = true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
				}
			}else{
				//Check if it is time to retrain. A week has passed and it is time to retrain
				if(TRAINING_TIME <= System.currentTimeMillis() - startingTime){
					try {
						clusterer.buildClusterer(trainingSet);
						startingTime = System.currentTimeMillis();
						trainingSet.clear();
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
				} else {
					//No week has passed. Add last instance to instances
					//trainingSet.add(lastInstance);
				}
				try {
					ClusterEvaluation eval = new ClusterEvaluation();
					eval.setClusterer(clusterer);
					Instances lastInstances = getFeatures();
					lastInstance.setDataset(lastInstances);
					lastInstance.mergeInstance(lastInstance);
					eval.evaluateClusterer(lastInstances);
					
					Date date = new Date();
					//blackboard.addTuple(ITEM_NAME,Level.L3, lastInsanceNormal,dateFormat.format(date), false);
					//System.out.println(eval.clusterResultsToString());
					blackboard.addTuple(ITEM_NAME+NUM_CLUSTERS,Level.L3, clusterer.getNumClusters(),dateFormat.format(date), false);
					blackboard.addTuple(ITEM_NAME,Level.L3, getCusterForInstance(eval.getNumClusters(), lastInstance),dateFormat.format(date), false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}

	
	
	public int[] getCusterForInstance(int numClusters, Instance lastInstance){
		//Get the attributes for the clusters
		double[][][] clusterModelsNumericAtts = clusterer.getClusterModelsNumericAtts();
		int[] response = new int[3];
		int mostLikelyCluster = -1;
		int mostCorrectInstances = 0;
		//Iterate over clusters
		for (int i = 0; i < clusterModelsNumericAtts.length; i++) {
			//Taking the fist 4 attributes as the most important for the cluster comparison
			int correctInstances = 0;
			for (int j = 0; j < NUM_ATTRIBUTES_TO_CHECK; j++) {
				Attribute attribute = lastInstance.attribute(j);
				if (!attribute.isNominal()) {
					//Compare as number
					int attValue = (int) lastInstance.value(attribute);
					//First value for not nominals is mean
					double mean = clusterModelsNumericAtts[i][j][0];
					//Second value is standar dev
					double stdD = clusterModelsNumericAtts[i][j][1];
					//System.out.println("att " + attribute.name() + " " + attValue + " " + mean + " "+ stdD + " " + (Math.abs(attValue - mean) <= stdD));
					if(Math.abs(attValue - mean) <= stdD)
						correctInstances++;
				} else {
					//Compare as nominal
					int pos = (int) lastInstance.value(attribute);
					String attValue = attribute.value(pos);
					//First value is amount for true
					double trueNumber = clusterModelsNumericAtts[i][j][0];
					//Second value is amount for false
					double falseNumber = clusterModelsNumericAtts[i][j][1];
					String biggerValue = trueNumber > falseNumber ? "true" : "false";
				}
				if( correctInstances > mostCorrectInstances ) {
					mostLikelyCluster = i;
					mostCorrectInstances = correctInstances;
				}
			}
		}
		//number of cluster
		response[0]=mostLikelyCluster;
		//number of cluster correct attributes
		response[1]=mostCorrectInstances;
		//Total attributes
		response[2]=NUM_ATTRIBUTES_TO_CHECK;
		logger.debug("mostLikelyCluster " + mostLikelyCluster + " mostCorrectInstances " + mostCorrectInstances);
		return response;
	}
	
	
	
	private boolean[][] isLastInsanceNormal(int numClusters, Instance lastInstance) {
		Enumeration<Attribute> enumerateAttributes = lastInstance.enumerateAttributes();
		//System.out.println(numClusters + " " + lastInstance.numAttributes());
		boolean[][] responses = new boolean[numClusters][lastInstance.numAttributes()];
		int responsePos = 0;
		int cluster = 0;
		//Take the list of attributes for the name
		while (enumerateAttributes.hasMoreElements()) {
			Attribute attribute = (Attribute) enumerateAttributes.nextElement();
			//Get the attributes for the clusters
			double[][][] clusterModelsNumericAtts = clusterer.getClusterModelsNumericAtts();
			for (int i = 0; i < clusterModelsNumericAtts.length; i++) {
				for (int j = 0; j < clusterModelsNumericAtts[i].length; j++) {
					if (!attribute.isNominal()) {
						//Compare as number
						int attValue = (int) lastInstance.value(attribute);
						//First value for not nominals is mean
						double mean = clusterModelsNumericAtts[i][j][0];
						//Second value is standar dev
						double strDev = clusterModelsNumericAtts[i][j][1];
						responses[cluster][responsePos] = Math.abs(attValue - mean) <= strDev;
						logger.debug(attribute.name() + " " + attValue + " " + mean + " " + strDev);
					} else {
						

						logger.debug("Normal Distribution. Mean = "
					            + Utils.doubleToString(clusterModelsNumericAtts[i][j][0], 4) + " StdDev = "
					            + Utils.doubleToString(clusterModelsNumericAtts[i][j][1], 4) + "\n");
						
						
						
						//Compare as nominal
						int pos = (int) lastInstance.value(attribute);
						String attValue = attribute.value(pos);
						//First value is amount for true
						double trueNumber = clusterModelsNumericAtts[i][j][0];
						//Second value is amount for false
						double falseNumber = clusterModelsNumericAtts[i][j][1];
						String biggerValue = trueNumber > falseNumber ? "true" : "false";
						responses[cluster][responsePos] = biggerValue.equals(attValue );
						logger.debug(attribute.name() + " " + attValue + " " + trueNumber + " " + falseNumber);
					}
				}
				responsePos++;
			}
			responsePos=0;
			cluster++;
		}
		return responses;
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
	
	
}
