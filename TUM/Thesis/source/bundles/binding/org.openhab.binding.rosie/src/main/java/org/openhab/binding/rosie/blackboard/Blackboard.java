package org.openhab.binding.rosie.blackboard;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource.Level;
import org.openhab.binding.rosie.blackboard.experts.L1.AbstractDataExpert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stores the data and the knowledge sources
 * 
 * @author manuel.munoz
 */

public class Blackboard implements Serializable {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -7300798232019745380L;

	/**
	 * Logger
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(Blackboard.class);

	/**
	 * Map where all the generic tuples are stored
	 */
	private Hashtable<String, Tuple> tupleMap;

	/**
	 * Map where all the presence tuples are stored
	 */
	private Hashtable<String, Tuple> presenceMap;

	/**
	 * Map where all the environmental tuples are stored
	 */
	private Hashtable<String, Tuple> environmentalMap;

	/**
	 * Map where all the electrical tuples are stored
	 */
	private Hashtable<String, Tuple> electricalMap;

	/**
	 * Map where all the motion tuples are stored
	 */
	private Hashtable<String, Tuple> motionMap;

	/**
	 * Map where all the point of entry tuples are stored
	 */
	private Hashtable<String, Tuple> pointOfEntryMap;

	/**
	 * Instance of the blackboard for the Singleton pattern
	 */
	private static Blackboard instance;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * This map of knowledge sources contains the current set of experts,
	 * identified by name
	 */
	private HashMap<String, AbstractKnowledgeSource> knowledgeSources;

	/**
	 * Default constructor for the blackboard.
	 */
	private Blackboard() {
		tupleMap = new Hashtable<String, Tuple>();
		presenceMap = new Hashtable<String, Tuple>();
		environmentalMap = new Hashtable<String, Tuple>();
		electricalMap = new Hashtable<String, Tuple>();
		motionMap = new Hashtable<String, Tuple>();
		pointOfEntryMap = new Hashtable<String, Tuple>();
		knowledgeSources = new HashMap<String, AbstractKnowledgeSource>();
	}

	/**
	 * Returns the instance of the blackboard, for the singleton pattern
	 * 
	 * @return not null
	 */
	public static synchronized Blackboard getInstance() {
		return instance = instance == null ? new Blackboard() : instance;
	}

	/**
	 * Inserts tuples in the corresponding map, according to the corresponding
	 * key. The map is configured when creating the OpenHAB's items. If the
	 * tuple is new, or it cannot be located on the other maps, it will be stored on the generic one.
	 * 
	 * @param key represents the name of the tuple. Usually is the item name of the experts
	 */
	public void addTuple(String key, Level level, Object payload,
			String timeStamp, boolean read) {
		Location location = setTupleItemLocation(key);
		Tuple tuple = new Tuple(level, payload, timeStamp, location, read);
		if (this.presenceMap.containsKey(key)) {
			this.presenceMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on presenceMap");
		} else if (this.electricalMap.containsKey(key)) {
			this.electricalMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on electricalMap");
		} else if (this.environmentalMap.containsKey(key)) {
			this.environmentalMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on environmentalMap");
		} else if (this.motionMap.containsKey(key)) {
			this.motionMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on motionmap");
		} else if (this.pointOfEntryMap.containsKey(key)) {
			this.pointOfEntryMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on pointOfEntryMap");
		} else {
			this.tupleMap.put(key, tuple);
			logger.debug("Key = " + key + " # tuple = " + tuple.getPayload() + " on generic Map");
		}
	}

	/**
	 * Create and adds a new tuple, depending of the type of sensor it was defined to.
	 * @param sensorType Type of sensor depending on the configuration of the item. Not null
	 * @param name Not null
	 * @param level Not null
	 * @param payload Not null
	 * @param timeStamp Not null
	 * @param location Not null
	 * @param read 
	 */
	public void addTuple(SensorType sensorType, String name, Level level,
			Object payload, String timeStamp, Location location, boolean read) {
		Tuple tuple = new Tuple(level, payload, timeStamp, location, read);
		switch (sensorType) {
		case PRESENCE:
			this.presenceMap.put(name, tuple);
			break;
		case ENVIRONMENTAL:
			this.environmentalMap.put(name, tuple);
			break;
		case ELECTRICAL:
			this.electricalMap.put(name, tuple);
			break;
		case MOTION:
			this.motionMap.put(name, tuple);
			break;
		case POINT_OF_ENTRY:
			this.pointOfEntryMap.put(name, tuple);
			break;
		}
	}

	/**
	 * Returns the location for the tuple, given it's experts
	 * @param key Not null
	 * @return If not found it will return null
	 */
	private Location setTupleItemLocation(String key) {
		Location response = null;
		AbstractKnowledgeSource kSource = getKnowledgeSource(key);
		if (kSource instanceof AbstractDataExpert) {
			response = ((AbstractDataExpert) kSource).getLocation();
		}
		return response;
	}

	/**
	 * knowledge sources getter
	 */
	public Collection<AbstractKnowledgeSource> getKnowledgeSources() {
		return knowledgeSources.values();
	}

	/**
	 * adds a knowledge source to the blackboard
	 */
	public void addKnowledgeSource(String name, SensorType sensorType,
			AbstractDataExpert ks) {
		Date date = new Date();
		addTuple(sensorType, name, Level.L1, null, dateFormat.format(date),
				ks.getLocation(), false);
		knowledgeSources.put(name, ks);
	}

	/**
	 * checks whether the blackboard contains some key
	 */
	public boolean containsTupleKey(String key) {
		return this.tupleMap.containsKey(key)
				|| this.presenceMap.containsKey(key)
				|| this.environmentalMap.containsKey(key)
				|| this.electricalMap.containsKey(key)
				|| this.motionMap.containsKey(key)
				|| this.pointOfEntryMap.containsKey(key);
	}

	/**
	 * Tuple getter
	 */
	public Tuple getTupleValue(String key) {
		Tuple response = this.tupleMap.get(key);
		if (response == null)
			response = this.presenceMap.get(key);
		if (response == null)
			response = this.environmentalMap.get(key);
		if (response == null)
			response = this.electricalMap.get(key);
		if (response == null)
			response = this.motionMap.get(key);
		if (response == null)
			response = this.pointOfEntryMap.get(key);
		return response;
	}

	@Override
	public String toString() {
		return this.tupleMap.toString() + " " + this.presenceMap.toString()
				+ " " + this.electricalMap.toString() + " "
				+ this.environmentalMap.toString() + " "
				+ this.motionMap.toString() + " "
				+ this.pointOfEntryMap.toString();
	}

	public void addKnowledgeSource(String name,
			AbstractKnowledgeSource knowledgeSource) {
		this.knowledgeSources.put(name, knowledgeSource);
	}

	/**
	 * Returns the knowledge source given its name 
	 * @param simpleName Not null
	 * @return May be null
	 */
	public AbstractKnowledgeSource getKnowledgeSource(String simpleName) {
		return knowledgeSources.get(simpleName);
	}

	/**
	 * Returns the map of the presence map
	 * @return Not null
	 */
	public Hashtable<String, Tuple> getPresenceTuples() {
		return presenceMap;
	}

	/**
	 * Returns the motion map
	 * @return Not null
	 */
	public Hashtable<String, Tuple> getMotionTuples() {
		return motionMap;
	}

	/**
	 * Returns the point of entry map
	 * @return Not null
	 */
	public Hashtable<String, Tuple> getPointOfEntryTuples() {
		return pointOfEntryMap;
	}

	/**
	 * Returns the generic map
	 * @return Not null
	 */
	public Hashtable<String, Tuple> getGenericTuples() {
		return tupleMap;
	}

	/**
	 * Returns the environmental map 
	 * @return Not Null
	 */
	public Hashtable<String, Tuple> getEnvorinmentalTuples() {
		return environmentalMap;
	}

}
