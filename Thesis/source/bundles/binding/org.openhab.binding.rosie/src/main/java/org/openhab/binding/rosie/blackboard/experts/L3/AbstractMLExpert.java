package org.openhab.binding.rosie.blackboard.experts.L3;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Tuple;
import org.openhab.binding.rosie.blackboard.experts.L2.EntryPointExpert;
import org.openhab.binding.rosie.blackboard.experts.L2.KnownPresenceExpert;
import org.openhab.binding.rosie.blackboard.experts.L2.MotionExpertIndoors;
import org.openhab.binding.rosie.blackboard.experts.L2.MotionExpertOutdoors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

public abstract class AbstractMLExpert extends AbstractKnowledgeSource {

	/**
	 * Serial version IS
	 */
	private static final long serialVersionUID = 5143009773176161625L;
	
	/**
	 * Logger for the class
	 */
	private static final Logger logger = LoggerFactory.getLogger(AbstractMLExpert.class);

	/**
	 * Date format for the arff file
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

	/**
	 * A week in milliseconds
	 */
	private static final long ONE_WEEK = 7 * 24 * 60 * 60 * 1000;

	/**
	 * Day in milliseconds
	 */
	private static final long ONE_DAY = 7 * 24 * 60 * 60 * 1000;

	/**
	 * One hour in milliseconds
	 */
	private static final long ONE_HOUR = 60 * 60 * 1000;

	/**
	 * One minute in milliseconds
	 */
	private static final long ONE_MINUTE = 1 * 60 * 1000;

	/**
	 * Training time for the machine learning algorithms that extend this class
	 */
	public static final long TRAINING_TIME = 5 * ONE_MINUTE;

	/**
	 * Day in milliseconds
	 */
	protected long trainingTime = TRAINING_TIME;

	/**
	 * Data used to train the machine learning algorithms
	 */
	protected Instances trainingSet;

	/**
	 * Date when the data set was recorded
	 */
	private Attribute date;

	/**
	 * Attribute of the known presence
	 */
	private Attribute knownPresence;

	/**
	 * Attribute of the duration of knownPresence
	 */
	private Attribute knownPresenceDuration;

	/**
	 * Attribute of if open entry point
	 */
	private Attribute openEntryPoint;
	/**
	 * Attribute of the motion indoors
	 */
	private Attribute motionIndoors;

	/**
	 * Attribute of the motion indoor per minute
	 */
	private Attribute indoorPerMinute;

	/**
	 * Attribute of the motion outdoors
	 */
	private Attribute motionOutdoors;

	/**
	 * Attribute of the motion outdoor per minute
	 */
	private Attribute outdoorPerMinute;

	/**
	 * Attribute of the class
	 */
	private Attribute classAttribute;

	/**
	 * Size of the attributes of the data set
	 */
	private int sizeAttributes;

	/**
	 * If the dataset should include a class attribute
	 */
	private boolean withClass;

	/**
	 * If the dataset should include a date attribute
	 */
	private boolean withDate;

	/**
	 * If the data set should include the motion densities
	 */
	private boolean withDensities;

	/**
	 * Constructor for the class. It receives the blackboard. Creates a dataset
	 * including class, dates, and densities attributes
	 * 
	 * @param blackboard
	 *            Not null
	 */
	protected AbstractMLExpert(Blackboard blackboard) {
		super(blackboard);
		this.withClass = true;
		this.withDate = true;
		this.withDensities = true;
	}

	/**
	 * Constructor of the class. Creates the data set depending on the
	 * parameters given
	 * 
	 * @param blackboard
	 *            Not null
	 * @param withClass
	 *            True if the dataset musts have the class attribute
	 * @param withDate
	 *            True if the dataset musts have the date attribute
	 * @param withDensities
	 *            True if the dataset musts have the motion densities attribute
	 */
	protected AbstractMLExpert(Blackboard blackboard, boolean withClass,
			boolean withDate, boolean withDensities) {
		super(blackboard);
		this.withClass = withClass;
		this.withDate = withDate;
		this.withDensities = withDensities;
	}

	/**
	 * Create the instances object created to train the machine learning
	 * algotihms. Depending on how the class is created some attributes are not
	 * included
	 * 
	 * @return Valid empty instance of the instances object.
	 */
	protected Instances getFeatures() {
		// Declare a nominal attribute along with its values

		// Date
		date = new Attribute("date", DATE_FORMAT);

		// amount of knwong presences
		knownPresence = new Attribute("knownPresence");

		// duration amount of knwong presences
		knownPresenceDuration = new Attribute("knownPresenceDuration");

		// entry point
		openEntryPoint = new Attribute("openEntryPoint");

		// Motion indoors
		motionIndoors = new Attribute("motionIndoors");

		// Declare two numeric attributes
		// Motion indoor per minute
		indoorPerMinute = new Attribute("motionIndoorPerMinute");

		// Motion outdoors
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

	/**
	 * Returns the training time. It can be configured on the configurations
	 * file. If not configured, then the default will be used
	 * 
	 * @return positive different from 0
	 */
	public long getTrainingTime() {
		return trainingTime;
	}

	/**
	 * Changes the training time
	 * 
	 * @param trainingTime
	 *            positive different from 0
	 */
	public void setTrainingTime(long trainingTime) {
		this.trainingTime = trainingTime;
	}

	/**
	 * Returns the instance for the state of the last values for the tuples.
	 * 
	 * @return Not null
	 * @throws ParseException
	 */
	protected Instance getLastInstance() throws ParseException {
		// Create empty instance with three attribute values
		Instance inst = new DenseInstance(sizeAttributes);
		if (withDate)
			inst.setValue(date, date.parseDate(getDate()));
		// Known Presences
		inst.setValue(knownPresence, getKnowPresence());
		// Known Presences duration
		// inst.setValue(knownPresenceDuration, getKnowPresenceDuration());
		// Open entry point
		inst.setValue(openEntryPoint, getOpenEntryPoints());
		// Motion indoors
		inst.setValue(motionIndoors, getMotionIndoors());
		// Motion outdoors
		inst.setValue(motionOutdoors, getMotionOutdoors());
		if (withDensities) {
			// Motion indoor per minute
			inst.setValue(indoorPerMinute, getMotionDesityIndoors());
			// Motion outdoor per minute
			inst.setValue(outdoorPerMinute, getMotionDesityOutdoors());
		}
		// Set as normal
		if (withClass) {
			inst.setValue(classAttribute, getNormalClass());
			inst.setDataset(trainingSet);
		}
		return inst;
	}

	/**
	 * Returns the number of total open entry points.
	 * 
	 * @return zero or positive number
	 */
	private int getOpenEntryPoints() {
		Tuple tuple = blackboard.getTupleValue(EntryPointExpert.ITEM_NAME);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return density > 0 ? 1 : 0;
		}
		return 0;
	}

	/**
	 * Classifies the given instance as a normal instance. If the class is
	 * configured as not to use class attributes, then nothing will happen
	 * 
	 * @param instance Not null
	 * @return
	 */
	protected Instance classifyAsNormal(Instance instance) {
		Instance newInstance = (Instance) instance.copy();
		if (withClass)
			newInstance.setValue(classAttribute, getNormalClass());
		return newInstance;
	}

	/**
	 * Returns the value of what it means when a class is normal
	 * @return Not null
	 */
	private String getNormalClass() {
		return new String("true");
	}

	/**
	 * Returns the motion density for the outdoor values
	 * @return positive integer or 0
	 */
	private int getMotionDesityOutdoors() {
		Tuple tuple = blackboard
				.getTupleValue(MotionExpertOutdoors.ITEM_NAME_DENSITY);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return density;
		}
		return 0;
	}

	/**
	 * Returns the number of motion events
	 * @return positive integer or 0
	 */
	private int getMotionOutdoors() {
		Tuple tuple = blackboard.getTupleValue(MotionExpertOutdoors.ITEM_NAME);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return density > 0 ? 1 : 0;
		}
		return 0;
	}

	/**
	 * Returns the motion density for the indoors values
	 * @return positive integer or 0
	 */
	private int getMotionDesityIndoors() {
		Tuple tuple = blackboard
				.getTupleValue(MotionExpertIndoors.ITEM_NAME_DENSITY);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return density;
		}
		return 0;
	}

	/**
	 * Returns the number of motion events
	 * @return positive integer or 0
	 */
	private int getMotionIndoors() {
		Tuple tuple = blackboard.getTupleValue(MotionExpertIndoors.ITEM_NAME);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return density > 0 ? 1 : 0;
		}
		return 0;
	}


	/**
	 * Returns the amount of known presences on the blackboard
	 * @return positive integer or 0
	 */
	private int getKnowPresence() {
		Tuple tuple = blackboard.getTupleValue(KnownPresenceExpert.ITEM_NAME);
		if (tuple != null) {
			Object payload = tuple.getPayload();
			int answ = Integer.parseInt(payload != null ? payload.toString()
					: "0");
			return answ > 0 ? 1 : 0;
		}
		return 0;
	}

	/**
	 * Returns the date with the format predefined
	 * @return
	 */
	private String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * Creates the arff file with the given data set.
	 * @param instances 
	 */
	protected void createArff(Instances instances) {
		try {
			ArffSaver saver = new ArffSaver();
			saver.setInstances(instances);
			Date date = new Date();
			saver.setFile(new File("./data/" + dateFormat.format(date)
					+ ".arff"));
			saver.writeBatch();
			System.out.println(saver.retrieveDir());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

}
