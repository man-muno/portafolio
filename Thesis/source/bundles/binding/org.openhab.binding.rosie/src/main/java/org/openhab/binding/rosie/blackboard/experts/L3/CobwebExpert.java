package org.openhab.binding.rosie.blackboard.experts.L3;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource.Level;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Cobweb;
import weka.clusterers.EM;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class CobwebExpert extends AbstractMLExpert {

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	private Cobweb clusterer;
	
	private boolean trained;
	
	private long startingTime;

	private static final String ITEM_NAME = "COBWEB_EXPERT";
	
	public CobwebExpert(Blackboard blackboard) {
		super(blackboard,false,false,false);
		//Create classifier
		trainingSet = getFeatures();
		// Create clusterer
		 clusterer = new Cobweb();   // new instance of clusterer
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
				trainingSet.add(lastInstance);
				if(TRAINING_TIME <= System.currentTimeMillis() - startingTime){
					try {
						clusterer.buildClusterer(trainingSet);
						trainingSet.clear();
						startingTime = System.currentTimeMillis();
						trained = true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}else{
				try {
					ClusterEvaluation eval = new ClusterEvaluation();
					eval.setClusterer(clusterer);
					Instances lastInstances = getFeatures();
					eval.evaluateClusterer(lastInstances);
					clusterer.updateClusterer(lastInstance);
					
					Date date = new Date();
					blackboard.addTuple(ITEM_NAME,Level.L3, eval.clusterResultsToString(),dateFormat.format(date), false);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
	
	
}
