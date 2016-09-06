package org.openhab.binding.rosie.blackboard.experts.L1;

import org.openhab.binding.rosie.blackboard.Blackboard;

public class ContactExpert  extends AbstractDataExpert {

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 5093719272168500736L;
	
	/**
	 * Name of the expert
	 */
	public static final String ITEM_NAME = "ContactExpert";

	/**
	 * Constructor
	 * @param blackboard Not null
	 */
	protected ContactExpert(Blackboard blackboard) {
		super(blackboard,ITEM_NAME);
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	}
	
	
}
