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
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;

public class HumanPresenceOutdoorsExpert extends AbstractKnowledgeSource {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -4333587098552541970L;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Name for the expert. Name also used to write on the blackboard
	 */
	public static final String ITEM_NAME = "HUMAN_PRESENCE_OUTDOORS_EXPERT";
	
	/**
	 * Name for the duration time postfix
	 */
	public static final String DURATION_NAME = "_DURATION";
	
	/**
	 * Time when the last presence was detected
	 */
	private int lastPresence = 0;
	
	/**
	 * Start time of some known presence in the house	
	 */
	private long outdoorPresenceTimeStart;

	/**
	 * Stop time of some known presence in the house
	 */
	private long outdoorPresenceTimeStop;

	/**
	 * Calculated duration of presence
	 */
	private long duration;
	
	/**
	 * Constructor for the class. 
	 * @param blackboard Not Null
	 */
	public HumanPresenceOutdoorsExpert(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	protected void evaluateKnowledge() {
		Hashtable<String, Tuple> presenceMap = blackboard.getPresenceTuples();
		Enumeration<String> keys = presenceMap.keys();
		int presence = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			Tuple tuple = presenceMap.get(key);
			if(tuple.getLocation().equals(Location.OUTDOOR))
			presence += getValue(tuple.getPayload());
		}
	
		
		//if new presence is 0 and old was not, then no one is at home, but someone left
		//Measure the duration of the time someone known was at home
		if(presence == 0 && lastPresence > 0){
			//Take the time to when a known presence left the home
			outdoorPresenceTimeStop = System.currentTimeMillis();
			duration = outdoorPresenceTimeStop- outdoorPresenceTimeStart;
		}
			
		//if old presence is 0 and new is greater, then someone came home
		if(lastPresence == 0 && presence > 0){
			//Take the time when someone came home
			outdoorPresenceTimeStart = System.currentTimeMillis();
			duration = 0;
		}
		
		lastPresence = presence;
		
		if (duration!=0){
			Date date = new Date();
			blackboard.addTuple(ITEM_NAME+DURATION_NAME,Level.L2, duration/1000+"",dateFormat.format(date), false);	
		}
			
		Date date = new Date();
		blackboard.addTuple(ITEM_NAME,Level.L2, presence!=0,dateFormat.format(date), false);

	}
	
	/**
	 * Returns 1 the if the tuple contains an open or on presence item
	 * @param payload not null
	 * @return
	 */
	private int getValue(Object payload){
		int presence = 0; 
		if (payload instanceof OnOffType && payload.equals(OnOffType.ON)){
			presence++;
		} else if(payload instanceof OpenClosedType  && payload.equals(OpenClosedType.OPEN)){
			presence++;
		}
		
		return presence;
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
}
