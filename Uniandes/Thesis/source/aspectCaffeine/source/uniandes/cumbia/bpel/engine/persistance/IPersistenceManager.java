package uniandes.cumbia.bpel.engine.persistance;

import java.io.Serializable;
import java.util.List;

import uniandes.cumbia.caffeine.deployer.deposit.BPELProcessInfo;

public interface IPersistenceManager extends Serializable
{
	/**
	 * Returns the process stored in the cumbia deposit 
	 */
	List getStoredProcesses();
	
	/**
	 * Return the information associated with the process specified 
	 * @param name The name of the process
	 * @return The process info
	 */
	BPELProcessInfo getProcessInfo(String name);
}
