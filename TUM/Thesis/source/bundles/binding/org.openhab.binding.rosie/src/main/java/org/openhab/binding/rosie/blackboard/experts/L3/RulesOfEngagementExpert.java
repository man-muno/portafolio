package org.openhab.binding.rosie.blackboard.experts.L3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Tuple;
import org.openhab.binding.rosie.blackboard.experts.L2.EntryPointExpert;
import org.openhab.binding.rosie.blackboard.experts.L2.KnownPresenceExpert;
import org.openhab.binding.rosie.blackboard.experts.L2.MotionExpertIndoors;

public class RulesOfEngagementExpert extends AbstractKnowledgeSource {

	/**
	 * Name of the expert used to store the tuple on the blackboard
	 */
	public static final String ITEM_NAME = "RULES_OF_ENGAGEMENT";

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -5265971363875606279L;
	
	/**
	 * Constructor for the class
	 * @param blackboard Not null
	 */
	public RulesOfEngagementExpert(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	public void evaluateKnowledge() {
		int presence = getKnowPresence();
		boolean  inDoorMotion = getMotionIndoors();
		int pointOfEntry = getPointOfEntry();
		if (presence==0 && (inDoorMotion || pointOfEntry==0)){
			Date date = new Date();
			blackboard.addTuple(ITEM_NAME,Level.L3, true ,dateFormat.format(date), false);
		} else	{
			Date date = new Date();
			blackboard.addTuple(ITEM_NAME,Level.L3, false ,dateFormat.format(date), false);			
		}
	}

	/**
	 * Returns the amount of open entry points
	 * @return Positive integer or zero
	 */
	private int getPointOfEntry() {
		Tuple tuple = blackboard.getTupleValue(EntryPointExpert.ITEM_NAME);
		if (tuple!=null){
			Object payload = tuple.getPayload();
			int answ = Integer.parseInt(payload != null ? payload.toString() : "0");
			return answ;
		}
		return 0;
	}

	/**
	 * Returns the amount of known presences
	 * @return Positive integer or zero
	 */
	private int getKnowPresence() {
		Tuple tuple = blackboard.getTupleValue(KnownPresenceExpert.ITEM_NAME);
		if (tuple!=null){
			Object payload = tuple.getPayload();
			int answ = Integer.parseInt(payload != null ? payload.toString() : "0");
			return answ;
		}
		return 0;
	}
	
	/**
	 * Returns the amount of motion indoors
	 * @return Positive integer or zero
	 */
	private boolean getMotionIndoors() {
		Tuple tuple = blackboard.getTupleValue(MotionExpertIndoors.ITEM_NAME);
		if (tuple!=null){
			Object payload = tuple.getPayload();
			int density = Integer.parseInt(payload != null ? payload.toString() : "0");
			return density!=0;
		}
		return false;
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	} 
}
