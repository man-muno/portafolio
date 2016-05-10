package org.openhab.binding.rosie.blackboard;

import java.io.Serializable;

/**
 * Class that represents the actions taken by Rosie when a hypothesis is reached.
 */
public abstract class AbstractAction implements Serializable{

	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 8517109724673389934L;
	
	/**
	 * Instance of the blackboard class. It should not be null.
	 */
	protected Blackboard blackboard;

	/**
	 * Constructor for the AbsctractAction class.
	 * @param blackboard Not null
	 */
	public AbstractAction(Blackboard blackboard) {
		this.blackboard = blackboard;
	}
	
	/**
	 * Method that is called when the action is to be executed
	 */
	abstract public void execute();
	
}
