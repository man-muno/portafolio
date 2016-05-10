package org.openhab.binding.rosie.blackboard;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openhab.binding.rosie.blackboard.experts.L3.ClusterExpert;
import org.openhab.binding.rosie.blackboard.experts.L3.ClusteringExpert;
import org.openhab.binding.rosie.blackboard.experts.L3.KNearestNeighborExpert;
import org.openhab.binding.rosie.blackboard.experts.L3.RulesOfEngagementExpert;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.OnOffType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 * This class controls how the knowledge sources are evaluated and the actions
 * are executed
 * 
 * @author Manuel Munoz
 * 
 */
public class BasicControl extends AbstractControl {

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

	/**
	 * Date format to be used when saving the result files.
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * Date of the event for the arff file
	 */
	private Attribute date;

	/**
	 * Attribute of the similarity for the arff file
	 */
	private Attribute similarityAttr;

	/**
	 * Attribute of the number the tuple belongs to, for the arff file
	 */
	private Attribute clusterNumberAttr;

	/**
	 * Attribute of the number of correct attributes for that cluster, for the
	 * arff file
	 */
	private Attribute correctClusterAttributesAttr;

	/**
	 * Attribute of the total number of clusters for the arff file
	 */
	private Attribute allClusterAttributesAttr;

	/**
	 * Attribute for the distribution for the arff file
	 */
	private Attribute distributionAttr;

	/**
	 * Attribute if the tuple was detected as abnormal, for the arff file
	 */
	private Attribute classAttributeAttr;

	/**
	 * Data instances of all the tuples received for the arff file.
	 */
	private Instances instances;

	/**
	 * Total size of attributes for the arff file
	 */
	private int sizeAttributes;

	/**
	 * Number of the cluster the instance belongs to
	 */
	private int clusterNumber;

	/**
	 * Number of correct attributes when compared to the clusters
	 */
	private int correctNumberAttributes;

	/**
	 * Number of total attributes in a cluster
	 */
	private int numberAttributesInCluster;

	/**
	 * Value for the similarity of the last tuple
	 */
	private int similarity;

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -2383473109918462328L;

	/**
	 * Instance of the logger
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(BasicControl.class);

	/**
	 * Percentage that must be reached for a tuple to belong to a cluster
	 */
	private static final double CORRECT_CLUSTER_PERCENTAGE = 1;

	/**
	 * The biggest similarity that can is allowed.
	 */
	public static final int SIMILARITY_CUT_OFF = 3;

	/**
	 * Default constructor of the class
	 * 
	 * @param blackboard
	 *            Not null
	 * @param eventPublisher
	 *            Not null
	 */
	public BasicControl(Blackboard blackboard, EventPublisher eventPublisher) {
		super(blackboard, eventPublisher);
		instances = getFeatures();
	}

	/**
	 * Loops over the experts in the blackboard. This loop tells each expert to
	 * check the their knowledge and to execute the actions that are on the
	 * blackboard
	 */
	public void loopExperts() {
		// Tell all knowledge sources to evaluate the knowledge on the
		// blackboard
		for (AbstractKnowledgeSource source : blackboard.getKnowledgeSources())
			source.evaluateKnowledge();
		logger.debug("Evaluated knoledge sources");
		executeActions();
	}

	@Override
	public void executeActions() {
		Tuple rulesResultTuple = blackboard
				.getTupleValue(RulesOfEngagementExpert.ITEM_NAME);

		Tuple knearestNTuple = blackboard
				.getTupleValue(KNearestNeighborExpert.ITEM_NAME);

		Object rulesResultPayload = rulesResultTuple != null ? rulesResultTuple
				.getPayload() : null;

		Object knearestNPayload = knearestNTuple != null ? knearestNTuple
				.getPayload() : null;
		double distribution = knearestNPayload != null ? ((double[]) knearestNPayload)[0]
				: 0;

		boolean isBreakInWithRules = false;

		if (rulesResultPayload != null) {
			isBreakInWithRules = (boolean) rulesResultPayload;
		}

		boolean isIntrusionBySimilarity = isIntrusionBySimilarity();
		boolean isIntrusionByCluster = isIntrusionByCluster();
		boolean isIntrusionByDistribution = isIntrusionByDistribution(distribution);

		boolean intrusion = false;

		if (isBreakInWithRules || isIntrusionByDistribution
				|| isIntrusionByCluster || isIntrusionBySimilarity) {
			escalate();
			System.out.println("after escalate " + securityStatus.getState());
			if (securityStatus.isInState(States.ABNORMAL)) {
				eventPublisher.postCommand("INTRUSION_DETECTED", OnOffType.ON);
				trigger(Triggers.ALERT);
			}
			intrusion = true;
		} else {
			descalate();
			System.out.println("after descalate " + securityStatus.getState());
			if (!securityStatus.isInState(States.ABNORMAL))
				eventPublisher.postCommand("INTRUSION_DETECTED", OnOffType.OFF);
			intrusion = false;
		}

		try {
			instances.add(getLastInstance(similarity, clusterNumber,
					correctNumberAttributes, numberAttributesInCluster,
					distribution, String.valueOf(intrusion)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Evaluates the similarity value for the last tuple. Returns true if it is
	 * considered to be an intrusion. An intrusion occurs if the similarity is
	 * larger than the value set on the constant
	 * 
	 * @return True in case of intrusion, false otherwise.
	 */
	private boolean isIntrusionBySimilarity() {
		Tuple kPointExpertTuple = blackboard
				.getTupleValue(ClusteringExpert.ITEM_NAME);
		Object kPointExpertPayload = kPointExpertTuple != null ? kPointExpertTuple
				.getPayload() : null;
		similarity = kPointExpertPayload != null ? (int) kPointExpertPayload
				: 0;

		return similarity > SIMILARITY_CUT_OFF;
	}

	/**
	 * States if an intrusion has happened, according to the evaluation of the
	 * last tuple on the clusters. An intrusion has ocurred if the porcentage of
	 * correct attributes is lower than that set on the constant
	 * 
	 * @return True in case of intrusion, false otherwise.
	 */
	private boolean isIntrusionByCluster() {
		Tuple clusterExpertTuple = blackboard
				.getTupleValue(ClusterExpert.ITEM_NAME);
		boolean response = false;
		if (clusterExpertTuple != null) {
			Object clusterExpertPayload = clusterExpertTuple != null ? clusterExpertTuple
					.getPayload() : null;
			clusterNumber = clusterExpertPayload != null ? ((int[]) clusterExpertPayload)[0]
					: 0;
			correctNumberAttributes = clusterExpertPayload != null ? ((int[]) clusterExpertPayload)[1]
					: 0;
			numberAttributesInCluster = clusterExpertPayload != null ? ((int[]) clusterExpertPayload)[2]
					: 0;

			double percentage = numberAttributesInCluster != 0 ? correctNumberAttributes
					/ numberAttributesInCluster
					: 0;
			response = percentage != CORRECT_CLUSTER_PERCENTAGE;
		}

		return response;
	}

	/**
	 * Lowers the security status of the home
	 */
	private void descalate() {
		if (securityStatus.isInState(States.ABNORMAL)) {
			trigger(Triggers.UNCOMMON);
		} else if (securityStatus.isInState(States.ELEVATED)) {
			trigger(Triggers.NORMAL);
		}

	}

	/**
	 * Increases the security status of the home
	 */
	private void escalate() {
		if (securityStatus.isInState(States.NORMAL)) {
			trigger(Triggers.UNCOMMON);
		} else if (securityStatus.isInState(States.ELEVATED)) {
			trigger(Triggers.SUSPICIOUS);
		}
	}

	/**
	 * Evaluates the distribution and states if there has been an intrusion
	 * 
	 * @param distribution
	 *            0 < distribution <= 1
	 * @return True if an intrusion has occurred
	 */
	private boolean isIntrusionByDistribution(double distribution) {
		boolean response = false;

		if (distribution == 0) {
			response = false;
		} else if (distribution < 0.9999486607852931) {
			response = true;
		} else if (distribution < 0.9999969045354826) {
			response = false;
		} else if (distribution < 0.9999975545406073) {
			response = true;
		} else if (distribution >= 0.9999975545406073) {
			response = false;
		}
		return response;
	}

	/**
	 * Returns the feature list of the attributes to be written on the arff file
	 * 
	 * @return List of attributes, not null
	 */
	protected Instances getFeatures() {

		// Date
		date = new Attribute("date", DATE_FORMAT);

		// similarity
		similarityAttr = new Attribute("similarity");

		// correctClusterNumber
		clusterNumberAttr = new Attribute("clusterNumber");

		// correct amount of attributes of that tuple
		correctClusterAttributesAttr = new Attribute("correctClusterAttributes");

		// Number of attributes
		allClusterAttributesAttr = new Attribute("allClusterAttributes");

		// Distribution value
		distributionAttr = new Attribute("distribution");

		// Declare the class attribute along with its values
		ArrayList<String> fvClassVal = new ArrayList<String>(2);
		fvClassVal.add("true");
		fvClassVal.add("false");
		classAttributeAttr = new Attribute("intrusion", fvClassVal);

		// Declare the feature vector
		ArrayList<Attribute> fvWekaAttributes = new ArrayList<Attribute>();
		fvWekaAttributes.add(date);
		fvWekaAttributes.add(similarityAttr);
		fvWekaAttributes.add(clusterNumberAttr);
		fvWekaAttributes.add(correctClusterAttributesAttr);
		fvWekaAttributes.add(allClusterAttributesAttr);
		fvWekaAttributes.add(distributionAttr);
		fvWekaAttributes.add(classAttributeAttr);

		// create an empty training set
		Instances features = new Instances("rel", fvWekaAttributes,
				fvWekaAttributes.size());
		// Set class index
		features.setClassIndex(fvWekaAttributes.size() - 1);
		sizeAttributes = fvWekaAttributes.size();
		return features;
	}

	@Override
	public void teardown() {
		try {
			ArffSaver saver = new ArffSaver();
			saver.setInstances(instances);
			Date date = new Date();
			saver.setFile(new File("./data/control/" + dateFormat.format(date)
					+ ".arff"));
			saver.writeBatch();
			System.out.println(saver.retrieveDir());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates and returns an Instance object with the values for the last
	 * tuple.
	 * 
	 * @param similarityValue
	 *            -1 if missing
	 * @param clusterNumberValue
	 *            -1 if missing
	 * @param correctClusterAttributesValue
	 *            -1 if missing
	 * @param allClusterAttributesValue
	 *            -1 if missing
	 * @param distribution
	 *            -1 if missing
	 * @param intrusionValue
	 *            null if missing
	 * @return The instance with the tuple values
	 * @throws ParseException
	 */
	protected Instance getLastInstance(int similarityValue,
			int clusterNumberValue, int correctClusterAttributesValue,
			int allClusterAttributesValue, double distribution,
			String intrusionValue) throws ParseException {
		// Create empty instance with three attribute values
		Instance inst = new DenseInstance(sizeAttributes);
		inst.setValue(date, date.parseDate(getDate()));
		// Similarity
		if (similarityValue != -1)
			inst.setValue(similarityAttr, similarityValue);
		else
			inst.setMissing(similarityAttr);
		// correct cluster
		if (clusterNumberValue != -1)
			inst.setValue(clusterNumberAttr, clusterNumberValue);
		else
			inst.setMissing(clusterNumberAttr);

		// Correct cluster attributes
		if (correctClusterAttributesValue != -1)
			inst.setValue(correctClusterAttributesAttr,
					correctClusterAttributesValue);
		else
			inst.setMissing(correctClusterAttributesAttr);

		// Total amount of attributes
		if (allClusterAttributesValue != -1)
			inst.setValue(allClusterAttributesAttr, allClusterAttributesValue);
		else
			inst.setMissing(allClusterAttributesValue);

		// value for the distribution
		if (distribution != -1)
			inst.setValue(distributionAttr, distribution);
		else
			inst.setMissing(distributionAttr);

		// Set class
		if (intrusionValue != null)
			inst.setValue(classAttributeAttr, intrusionValue);
		else
			inst.setMissing(classAttributeAttr);

		inst.setDataset(instances);
		return inst;
	}

	/**
	 * Returns the formated date in String
	 * 
	 * @return
	 */
	private String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		return formatter.format(new Date(System.currentTimeMillis()));
	}
}
