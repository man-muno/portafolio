package org.openhab.binding.rosie.blackboard.experts.L2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Tuple;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;

public class EntryPointExpert extends AbstractKnowledgeSource {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -1980295982615468011L;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Name for the expert. Name also used to write on the blackboard
	 */
	public static final String ITEM_NAME = "OPEN_ENTRY_POINT_EXPERT";
	
	/**
	 * Name for the duration time postfix
	 */
	public static final String DURATION_NAME = "_DURATION";
	
	/**
	 * Amount of last entry elements detected
	 */
	private int lastEntry = 0;

	/**
	 * Start time of some known presence in the house	
	 */
	private long entryTimeStart;

	/**
	 * Stop time of some known presence in the house
	 */
	private long entryTimeStop;

	/**
	 * Calculated duration of presence
	 */
	private long duration;
	
	/**
	 * Creates the expert
	 * @param blackboard
	 */
	public EntryPointExpert(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	protected void evaluateKnowledge() {
		Hashtable<String, Tuple> entryMap = blackboard.getPointOfEntryTuples();
		Enumeration<String> keys = entryMap.keys();
		int entry = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			entry += getValue(entryMap.get(key).getPayload());
		}
	
		// If no entry point is currently open and the last 
		if(entry == 0 && lastEntry > 0){
			//Take the time to when a known presence left the home
			entryTimeStop = System.currentTimeMillis();
			duration = entryTimeStop- entryTimeStart;
		}
			
		//if old presence is 0 and new is greater, then someone came home
		if(lastEntry == 0 && entry > 0){
			//Take the time when someone came home
			entryTimeStart = System.currentTimeMillis();
			duration = 0;
		}
		
		lastEntry = entry;
		
		if (duration!=0){
			Date date = new Date();
			blackboard.addTuple(ITEM_NAME+DURATION_NAME,Level.L2, duration/1000+"",dateFormat.format(date), false);	
			duration = 0;
		}
			
		Date date = new Date();
		//Write if any entry point is accessible 
		blackboard.addTuple(ITEM_NAME,Level.L2, entry ,dateFormat.format(date), false);

	}
	
	/**
	 * Returns 1 the if the tuple contains an open or on presence item
	 * @param payload not null
	 * @return
	 */
	private int getValue(Object payload){
		int entryPoints = 0; 
		if (payload instanceof OnOffType && payload.equals(OnOffType.ON)){
			entryPoints++;
		} else if(payload instanceof OpenClosedType  && payload.equals(OpenClosedType.OPEN)){
			entryPoints++;
		}
		
		return entryPoints;
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
}
