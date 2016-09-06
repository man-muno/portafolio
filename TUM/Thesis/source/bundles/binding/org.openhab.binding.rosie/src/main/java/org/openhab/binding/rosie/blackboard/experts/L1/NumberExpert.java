package org.openhab.binding.rosie.blackboard.experts.L1;

import org.openhab.binding.rosie.blackboard.Blackboard;

public class NumberExpert extends AbstractDataExpert {

	/**
	 * Serial version ID 
	 */
	private static final long serialVersionUID = 5364146636979983459L;
	
	/**
	 * Name of the expert
	 */
	public static final String ITEM_NAME = "NumberExpert";

	/**
	 * Constructor
	 * @param blackboard Not null
	 */
	protected NumberExpert(Blackboard blackboard) {
		super(blackboard,ITEM_NAME);
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	}

}
