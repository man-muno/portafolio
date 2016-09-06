package org.openhab.binding.rosie.blackboard.experts.L3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openhab.binding.rosie.blackboard.Blackboard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;

public class KNearestNeighborExpert extends AbstractMLExpert {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -3782958498918292509L;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Current model used
	 */
	private Classifier currentModel;
	
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
	private static final Logger logger = LoggerFactory.getLogger(KNearestNeighborExpert.class);
	
	/**
	 * Name of the expert used to store the tuple on the blackboard
	 */
	public static final String ITEM_NAME = "K_NEAREST_NEIGHBOR_EXPERT";
	
	/**
	 * Constructor of the class. 
	 * @param blackboard Not null
	 */
	public KNearestNeighborExpert(Blackboard blackboard) {
		super(blackboard, true, true, false);
		trainingSet = getFeatures();
		//Create classifier
		// Create a k nearest neighbor classifier
		currentModel = (Classifier)new IBk();
		//Not trained yet
		trained = false;
		startingTime = System.currentTimeMillis();
	}

	@Override
	protected void evaluateKnowledge() {
		try {
			Instance lastInstance = getLastInstance();
			if (!trained){
				//Is on training phase of current model
				trainingSet.add(classifyAsNormal(lastInstance));
				if(trainingTime <= System.currentTimeMillis() - startingTime){
					try {
						currentModel.buildClassifier(trainingSet);
						createArff(trainingSet);
						startingTime = System.currentTimeMillis();
						trained = true;
						trainingSet.clear();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				}
			}else{
				//Check if it is time to retrain. A week has passed and it is time to retrain
				if(trainingTime <= System.currentTimeMillis() - startingTime){
					try {
						currentModel.buildClassifier(trainingSet);
						trainingSet.clear();
						startingTime = System.currentTimeMillis();
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
				} else {
					//No week has passed. Add last instance to instances
					Instance lastInstanceClassified = classifyAsNormal(lastInstance);
					trainingSet.add(lastInstanceClassified);
				}
				try {
					double[] fDistribution = currentModel.distributionForInstance(lastInstance);
					Date date = new Date();
					logger.debug("distribution: " + fDistribution[0] + " " + fDistribution[1]);
					blackboard.addTuple(ITEM_NAME,Level.L3, fDistribution,dateFormat.format(date), false);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
}
