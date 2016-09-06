package uniandes.cumbia.bpel.messagecenter;

import java.io.Serializable;

public interface IWsdlService  extends Serializable
{
	
	/**
	 * Invokes the synchronous service specified
	 * @param message The message to be sended
	 * @param uri The uri of the service
	 * @param operation The operation to be invoked
	 * @param instanceId The instance id
	 * @param processId The process id
	 * @return The response
	 */
	Object invokeSynchronusService(Object message, String uri, String operation, int instanceId, int processId);
	
	/**
	 * Invokes the synchronous service specified
	 * @param message The message to be sended
	 * @param uri The uri of the service
	 * @param operation The operation to be invoked
	 * @param instanceId The instance id
	 * @param processId The process id	
	 */
	void invokeAsynchronusService(Object message, String uri, String operation, int instanceId, int processId);
	
	/**
	 * Returns the process name
	 * @return The process name
	 */
	String getProcessName();
	
	/**
	 * Changes the id 
	 * @param id The new id
	 */
	void setId(int id);
	
}
