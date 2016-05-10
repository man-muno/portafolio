package org.openhab.binding.rosie.blackboard;

import java.io.Serializable;


/**
 * The knowledge source parent
 * 
 *   @author manuel.munoz
 */
public abstract class AbstractKnowledgeSource implements Serializable{
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 1268566669057127970L;

	/**
	 * Level of the knowledge source or expert
	 */
	public enum Level {
		L1, L2, L3, L4
	}
	
	/**
	 * A reference to the blackboard that uses this knowledge source
	 */
	protected Blackboard blackboard;
	

	/**
	 * Level of the knowledge source
	 */
	protected Level level;

	/**
	 * Constructor for the knowledge source
	 * @param blackboard Instance of the blackboard to read and write to.
	 */
	protected AbstractKnowledgeSource(Blackboard blackboard) {
		this.blackboard = blackboard;
	}

	/**
	 * Evaluates the knowledge that is on the expert/source
	 */
	protected abstract void evaluateKnowledge();
	
	/**
	 * Returns the name of the expert. Usually, the name for the tuple written on the blackbord
	 * @return Name of the expert. Not null
	 */
	protected abstract String getExpertName();

}
