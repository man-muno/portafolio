package fml.tum.model.statemachine;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.delegates.Action;

import fml.tum.listeners.DSAListener;
import fml.tum.utils.PropertiesValues;

public class TimerAction implements Runnable {
	
	

	private StateMachine<State, Trigger> states;
	private int waitTime;

	public TimerAction(StateMachine<State, Trigger> states, int waitTime) {
		this.states = states;
		this.waitTime = waitTime;
	}

	@Override
	public void run() {
		try {
			wait(waitTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
