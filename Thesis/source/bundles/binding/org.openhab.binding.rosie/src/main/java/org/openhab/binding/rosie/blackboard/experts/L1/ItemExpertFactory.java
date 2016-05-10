package org.openhab.binding.rosie.blackboard.experts.L1;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;

public class ItemExpertFactory {

	/**
	 * Constant for the switch type
	 */
	public static final Object SWITCH_TYPE = "Switch";
	
	/**
	 * Constant for the contact type
	 */	
	public static final Object CONTACT_TYPE = "Contact";
	
	/**
	 * Constant for the number type
	 */
	public static final Object NUMBER_TYPE = "Number";

	/**
	 * Creates an expert given the parameters
	 * @param expertName Name of the expert to get
	 * @param sensorType 
	 * @param blackboard not null
	 * @return Null if no expert is found
	 */ 
	public AbstractKnowledgeSource getExpert(String expertName, String sensorType, Blackboard blackboard){
		AbstractKnowledgeSource response = null;
		if (expertName.equals(SWITCH_TYPE))
			response = new SwitchExpert(blackboard);
		else if (expertName.equals(CONTACT_TYPE))
			response = new ContactExpert(blackboard);
		else if (expertName.equals(NUMBER_TYPE))
			response = new NumberExpert(blackboard);
		
		return response;
	}
}
