package org.openhab.binding.rosie.blackboard.actions;

import org.openhab.binding.rosie.blackboard.AbstractAction;
import org.openhab.binding.rosie.blackboard.Blackboard;

/**
 * This class is called when an intrusion is detected by Rosie
 * @author manuel
 */
public class PostOpenHABUpdate extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5200094899820554346L;

	/**
	 * Default constructor for the action
	 * @param blackboard Not null
	 */
	public PostOpenHABUpdate(Blackboard blackboard) {
		super(blackboard);
	}

	@Override
	public void execute() {
		
	}

}
