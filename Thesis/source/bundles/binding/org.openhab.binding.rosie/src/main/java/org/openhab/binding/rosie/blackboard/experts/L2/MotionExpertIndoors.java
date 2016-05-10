package org.openhab.binding.rosie.blackboard.experts.L2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Location;
import org.openhab.binding.rosie.blackboard.Tuple;

/**
 * This expert take into account the motion of the two motion sensors.
 */
public class MotionExpertIndoors extends AbstractKnowledgeSource {
	
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 473087950559590338L;

	/**
	 * Counter for the indoor motion
	 */
	private int indoorCounter = 0;
	
	/**
	 * Old tuples of for motion
	 */
	private Hashtable<String,Tuple> oldTuples = new Hashtable<String, Tuple>();
	
	/**
	 * Name for the expert. Name also used to write on the blackboard
	 */
	public static final String ITEM_NAME = "MOTION_INDOORS";
	
	/**
	 * Postfix for the density of motion indoors
	 */
	public static final String ITEM_NAME_DENSITY = "MOTION_INDOORS_DENSITY";
	
	/**
	 * Time delta to gather the density
	 */
	private static final long DELTA = 30000;
	
	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Start time for motion density
	 */
	private long timerStart;

	/**
	 * Constructor for the expert
	 * @param blackboard Not null
	 */
	public MotionExpertIndoors(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	protected void evaluateKnowledge() {
		Hashtable<String, Tuple> motionMap = blackboard.getMotionTuples();
		Enumeration<String> keys = motionMap.keys();
		

		//Check for new values.
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			Tuple newTuple = motionMap.get(key);
			Tuple oldTuple = oldTuples.get(key);
		
			//If the new tuple is different than the old, means that the sensor's value has changed
			if(oldTuple!=null && newTuple!=null && 
					oldTuple.getPayload()!=null && newTuple.getPayload()!=null &&
					newTuple.getLocation().equals(Location.INDOOR) && 
					//!oldTuple.getPayload().equals(newTuple.getPayload()) &&
					!(oldTuple.getTimeStamp().equals(newTuple.getTimeStamp()))){
				indoorCounter++;
			}
		}
		
		
		//If the delta has passsed, then stop counting the movement
		if(System.currentTimeMillis() - timerStart >= DELTA){
			oldTuples.clear();
			oldTuples.putAll(motionMap);
			Date date = new Date();
			blackboard.addTuple(ITEM_NAME_DENSITY,Level.L2, indoorCounter+"",dateFormat.format(date), false);
			indoorCounter=0;
			timerStart = System.currentTimeMillis();
		}
		
		Date date = new Date();
		blackboard.addTuple(ITEM_NAME,Level.L2, indoorCounter > 0 ? 1 : 0 ,dateFormat.format(date), false);
		
	} 

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
}
