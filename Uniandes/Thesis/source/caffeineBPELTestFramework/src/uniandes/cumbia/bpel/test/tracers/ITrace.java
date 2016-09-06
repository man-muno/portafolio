package uniandes.cumbia.bpel.test.tracers;

import java.util.Date;

/**
 * Common interface for traces.
 */
public interface ITrace extends Comparable<ITrace> {

	
	/**
	 * Gets the date of the generation of the trace.
	 * @return The date of the generation of the trace.
	 */
	public Date getDate();
}