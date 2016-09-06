package org.openhab.binding.simulator;

import org.openhab.core.events.EventPublisher;

public class Simulator {

	private EventPublisher eventPublisher;

	public Simulator(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

}
