package org.openhab.binding.simulator.generator;

import org.openhab.core.events.EventPublisher;

public abstract class Generator {

	public abstract void generateUntilNow(int i) ;

	public abstract void isGenerated();

	public abstract void tick(EventPublisher eventPublisher);

}
