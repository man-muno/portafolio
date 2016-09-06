package org.openhab.binding.rosie.ml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sun.org.mozilla.javascript.internal.ast.WithStatement;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;

public class Cluster {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	// Date
	private Attribute date;

	private Attribute knownPresence;
	// Duration of knownPresence
	private Attribute knownPresenceDuration;
	// open Entry point
	private Attribute openEntryPoint;
	// Motion indoors
	private Attribute motionIndoors;
	// Motion indoor per minute
	private Attribute indoorPerMinute;
	// Motion outdoors
	private Attribute motionOutdoors;
	// Motion outdoor per minute
	private Attribute outdoorPerMinute;
	private Attribute classAttribute;

	private int sizeAttributes = 5;

	private boolean withClass = false;

	private boolean withDate = false;

	private boolean withDensities = false;

	public static void main(String[] args) throws Exception {

		Cluster c = new Cluster();
		c.test();
	}

	public void test() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"D:/Dropbox/1.7.1/data/last.arff"));
			Instances data = new Instances(reader);
			reader.close();
			EM clusterer = new EM(); // new instance of clusterer
			String[] options = new String[2];
			options[0] = "-I"; // max. iterations
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

			clusterer.setOptions(options2);
			// build the clusterer
			clusterer.buildClusterer(data);

			ClusterEvaluation eval = new ClusterEvaluation();
			eval.setClusterer(clusterer);
			// Instance lastInstance = getLastInstance();
			Instances lastInstances = getFeatures();
			// lastInstance.setDataset(lastInstances);
			// lastInstance.mergeInstance(lastInstance);
			eval.evaluateClusterer(lastInstances);
			System.out.println(eval.clusterResultsToString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected Instance getLastInstance() throws ParseException {
		// Create empty instance with three attribute values
		Instance inst = new DenseInstance(sizeAttributes);
		// Known Presences4
		inst.setValue(knownPresence, 0);
		// Known Presences duration
		// inst.setValue(knownPresenceDuration, getKnowPresenceDuration());
		// Open entry point
		inst.setValue(openEntryPoint, 0);
		// Motion indoors
		inst.setValue(motionIndoors, 0);
		// Motion outdoors
		inst.setValue(motionOutdoors, 0);

		return inst;
	}

	protected Instances getFeatures() {
		// Declare a nominal attribute along with its values

		// Date
		date = new Attribute("date", DATE_FORMAT);

		// amount of knwong presences
		knownPresence = new Attribute("knownPresence");

		// duration amount of knwong presences
		knownPresenceDuration = new Attribute("knownPresenceDuration");

		// entry point
		// ArrayList<String> listValuesOpenEntryPoint = new ArrayList<String>();
		// listValuesOpenEntryPoint.add("true");
		// listValuesOpenEntryPoint.add("false");
		// openEntryPoint = new
		// Attribute("openEntryPoint",listValuesOpenEntryPoint);
		openEntryPoint = new Attribute("openEntryPoint");

		// Motion indoors
		// ArrayList<String> listValuesMotionIndoors = new ArrayList<String>();
		// listValuesMotionIndoors.add("true");
		// listValuesMotionIndoors.add("false");
		// motionIndoors = new
		// Attribute("motionIndoors",listValuesMotionIndoors);
		motionIndoors = new Attribute("motionIndoors");

		// Declare two numeric attributes
		// Motion indoor per minute
		indoorPerMinute = new Attribute("motionIndoorPerMinute");

		// Motion outdoors
		// ArrayList<String> listValuesMotionOutdoors = new ArrayList<String>();
		// listValuesMotionOutdoors.add("true");
		// listValuesMotionOutdoors.add("false");
		// motionOutdoors = new
		// Attribute("motionOutdoors",listValuesMotionOutdoors);
		motionOutdoors = new Attribute("motionOutdoors");

		// Declare two numeric attributes
		// Motion outdoor per minute
		outdoorPerMinute = new Attribute("outdoorPerMinute");

		// Declare the class attribute along with its values
		ArrayList<String> fvClassVal = new ArrayList<String>(2);
		fvClassVal.add("true");
		fvClassVal.add("false");
		classAttribute = new Attribute("normal", fvClassVal);

		// Declare the feature vector
		ArrayList<Attribute> fvWekaAttributes = new ArrayList<Attribute>();
		if (withDate)
			fvWekaAttributes.add(date);
		fvWekaAttributes.add(knownPresence);
		// fvWekaAttributes.add(knownPresenceDuration);
		fvWekaAttributes.add(openEntryPoint);
		fvWekaAttributes.add(motionIndoors);
		fvWekaAttributes.add(motionOutdoors);
		if (withDensities) {
			fvWekaAttributes.add(indoorPerMinute);
			fvWekaAttributes.add(outdoorPerMinute);
		}
		if (withClass)
			fvWekaAttributes.add(classAttribute);

		// create an empty training set
		Instances features = new Instances("rel", fvWekaAttributes,
				fvWekaAttributes.size());
		// Set class index
		sizeAttributes = features.numAttributes();
		if (withClass)
			features.setClassIndex(sizeAttributes - 1);

		return features;
	}

}
