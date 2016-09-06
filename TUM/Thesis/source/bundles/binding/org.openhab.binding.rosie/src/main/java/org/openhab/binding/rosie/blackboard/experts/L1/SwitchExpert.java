package org.openhab.binding.rosie.blackboard.experts.L1;

import org.openhab.binding.rosie.blackboard.Blackboard;

public class SwitchExpert  extends AbstractDataExpert{

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -207677679281961223L;
	
	/**
	 * Name of the expert
	 */
	public static final String ITEM_NAME = "SwitchExpert";

	/**
	 * Constructor
	 * @param blackboad Not null
	 */
	protected SwitchExpert(Blackboard blackboard) {
		super(blackboard,ITEM_NAME);
	}

	@Override
	protected String getExpertName() {
		return ITEM_NAME;
	}
}
