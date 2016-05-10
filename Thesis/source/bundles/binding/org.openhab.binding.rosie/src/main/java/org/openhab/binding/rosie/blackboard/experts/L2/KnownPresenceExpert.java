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

public class KnownPresenceExpert extends AbstractKnowledgeSource {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 4708657103625904103L;

	/**
	 * Date formatter
	 */
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	/**
	 * Name for the expert. Name also used to write on the blackboard
	 */
	public static final String ITEM_NAME = "PRESENCE_EXPERT";
	
	/**
	 * Name for the duration time postfix
	 */
	public static final String DURATION_NAME = "_DURATION";
	
	/**
	 * Constructor for the class. 
	 * @param blackboard Not Null
	 */
	public KnownPresenceExpert(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	protected void evaluateKnowledge() {
		Hashtable<String, Tuple> presenceMap = blackboard.getPresenceTuples();
		Enumeration<String> keys = presenceMap.keys();
		int presence = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
			presence += getValue(presenceMap.get(key).getPayload());
		}
			
		Date date = new Date();
		blackboard.addTuple(ITEM_NAME,Level.L2, presence > 0 ? 1 : 0 ,dateFormat.format(date), false);

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
