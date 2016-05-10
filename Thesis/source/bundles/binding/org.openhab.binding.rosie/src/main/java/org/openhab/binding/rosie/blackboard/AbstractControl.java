package org.openhab.binding.rosie.blackboard;

import java.io.Serializable;

import org.openhab.core.events.EventPublisher;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;

public abstract class AbstractControl implements Serializable {
	
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -2131163500512831437L;

	/**
	 * Instance of the blackboard
	 */
	protected Blackboard blackboard;
	
	/**
	 * State machine that represents the security status of the home
	 */
	public StateMachine<States, Triggers> securityStatus = null;

	/**
	 * Definition of the states for the state machine
	 */
	public enum States {
		START, NORMAL, ELEVATED, ABNORMAL, ALERTED
	}

	/**
	 * Definition of the triggers for the state changes of the state machine
	 */
	public enum Triggers {
		INITIALIZED, UNCOMMON, NORMAL, SUSPICIOUS, ALERT, OVERRIDEN
	}
	
	/**
	 * Instance of OpenHAB's publisher of bus events 
 	 */
	protected EventPublisher eventPublisher;
	
	/**
	 * Default constructor of the control component.
	 * @param blackboard Not null
	 * @param eventPublisher Not null
	 */
	protected AbstractControl(Blackboard blackboard, EventPublisher eventPublisher) {
		this.blackboard = blackboard;
		configureSteMachine();
		securityStatus.fire(Triggers.INITIALIZED);
		this.eventPublisher = eventPublisher;
	}

	/**
	 * This method is called when a new message is received by the binding from OpenHAB, or the refresh interval is reached.
	 */
	public abstract void loopExperts();
	
	/**
	 * Calls the actios of the class to be executed.
	 */
	public abstract void executeActions();
	
	/**
	 * Returns the current state of the security status of the home
	 * @return
	 */
	public States getState() {
		return securityStatus.getState();
	}

	/**
	 * Sends a trigger to the state machine. If it cannot be triggered it will not send it
	 * @param trigger Trigger to be sent
	 * @return True in case the trigger was fired. False otherwise
	 */
	protected boolean trigger(Triggers trigger) {
		boolean response = false;
		if (securityStatus.canFire(trigger)){
			securityStatus.fire(trigger);
		}
		return response;
	}
	

	/**
	 * Configures the state machine.
	 */
	private void configureSteMachine() {
		// Create configuiration object
		StateMachineConfig<States, Triggers> securityConfiguration = new StateMachineConfig<States, Triggers>();

		// Configurate Start state
		securityConfiguration.configure(States.START).permit(
				Triggers.INITIALIZED, States.NORMAL);

		// Configure Normal state
		securityConfiguration
				.configure(States.NORMAL)
				.permit(Triggers.UNCOMMON, States.ELEVATED)
				.permit(Triggers.SUSPICIOUS, States.ABNORMAL);

		// Configure Elevated state
		securityConfiguration
				.configure(States.ELEVATED)
				.permit(Triggers.NORMAL, States.NORMAL)
				.permit(Triggers.SUSPICIOUS, States.ABNORMAL);

		// Configure abnormal state
		securityConfiguration
				.configure(States.ABNORMAL)
				.permit(Triggers.NORMAL, States.NORMAL)
				.permit(Triggers.UNCOMMON, States.ELEVATED);

		// Configure Alert state
		securityConfiguration
				.configure(States.ALERTED)
				.permit(Triggers.ALERT, States.ABNORMAL)
				.permit(Triggers.OVERRIDEN, States.NORMAL);
		
		
		securityStatus = new StateMachine<States, Triggers>(States.START,
				securityConfiguration);

	}

	/**
	 * Changes the event publisher for the control
	 * @param eventPublisher Not null
	 */
	public void setEventPlubisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	/**
	 * Method called when the binding is removed from OpenHAB. It performs tear down activities, like closing opened files.
	 */
	public abstract void teardown();
	
}
