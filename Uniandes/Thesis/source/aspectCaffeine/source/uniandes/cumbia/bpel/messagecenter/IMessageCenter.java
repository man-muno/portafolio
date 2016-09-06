package uniandes.cumbia.bpel.messagecenter;

import java.io.Serializable;
import java.util.List;

import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.engine.client.BPELClient;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.exceptions.CumbiaException;



public interface IMessageCenter extends Serializable
{

    /**
     * Creates a new web services in the message center
     * @param processName The name of the process
     * @return The id of the web service create
     */
    public int createNewService(IWsdlService service);
    
    /**
     * Execute an asynchronous call  
     * @param id The id of the services associated with the call
     * @param message The message to be processed
     * @param loader The classloader for process the call 
     * @param instanceId The instance id
     * @param processId The process id
     * @param clientUri Uri of the client
     * @return The response
     */
    public void executeAsynchronousCall(int id, Object message, ClassLoader loader, int instanceId, int processId, String clientUri);
    
    /**
     * Executes an asynchronous call
     * @param processName The process name
     * @param message The message
     * @param loader The classloader for process the call
     * @param serviceId the id of the service
     * @param instanceId The instance id
     * @param clientUri Uri of the client
     * @return The response The response
     * @throws InstantiationException
     */
    public void executeAsynchronousCall( String processName, Object message, ClassLoader loader, int serviceId, InstanceId instanceId, String clientUri );    
    
    /**
     * Execute an synchronous call  
     * @param id The id of the services associated with the call
     * @param message The message to be processed
     * @param loader The classloader for process the call
     * @param instanceId The instance id
     * @param processId The process id
     * @return The response     
     */
    public Object executeSynchronousCall(int id, Object message, ClassLoader loader, int instanceId, int processId);
    
    /**
     * Executes an synchronous call
     * @param processName The process name
     * @param message The message
     * @param loader The classloader for process the call
     * @param serviceId the id of the service
     * @param instanceId The instance id
     * @return The response
     * @throws InstantiationException
     */
    public Object executeSynchronousCall( String processName, Object message, ClassLoader loader, int serviceId, InstanceId instanceId );
    
    /**
     * Processes asynchronous response  
     * @param id The id of the services associated with the response
     * @param response The response     
     * @param instanceId The instance id
     * @param processId The process id
     */
    public void processAsynchronousResponse(int id, Object response, int instanceId, int processId);    
    
    /**
     * Register a message receive for a synchronous process
     * @param processName The process name
     * @param message The message
     * @param instanceId The instance id
     */
    public boolean registerMessageReceivedProcess( String processName, Object message, InstanceId instanceId );
    
    /**
     * Execute a synchronous call  
     * @param serviceId The service id
     * @param partnerLinkUri The uri of the partner link
     * @param message The message to be sent     
	 * @param portTypeName The port for making the invocation
	 * @param operationName The operation name to be invoked
	 * @param id The id of the process instance
     * @return The response of the invocation     
     */
    public Object invokeSynchronousCall(int serviceId, String partnerLinkUri, Object message, String portTypeName, String operationName, InstanceId id);
    
    /**
     * Returns the bpel engine
     * @return The bpel engine
     */
    public IProcessEngine getBpelEngine(); 
	

	/**
	 * Changes the bpel engine
	 * @param bEngine The new bpel engine
	 */
    public void setBpelEngine(IProcessEngine bEngine);
	
	/**
	 * Returns the process name associated with the bpel client
	 * @param processName The process name
	 * @return The bpel client
	 */
    public BPELClient getBpelClient(String processName, String url); 
	
	/**
	 * Returns the list of process instance
	 * @param processName The name of the process
	 * @return The list of process instances
	 */
    public List<InstanceSpace> getProcessInstances(String processName);
	
	/**
	 * Returns the process space with the specified
	 * @param processName The process name
	 * @return The process with the name specified
	 */
    public ProcessSpace getProcessSpace(String processName);
	
	/**
	 *  Creates the process space of the specified process
	 * @param processName The name of the process 
	 * @return The process space created
	 */
    public ProcessSpace createProcessSpace(String processName, ClassLoader loader);
	
	/**
     * Invokes an asynchronous call  
     * @param id The id of the process instance
     * @param partnerLinkUri The uri of the partner link
     * @param message The message to be sent
     * @param portTypeName The port for making the invocation
	 * @param operationName The operation name to be invoked 
	 * @param id The id of the process instance    
     */
    public void invokeAsynchronousCall(int serviceId, String partnerLinkUri, Object message, String portTypeName, String processName, InstanceId id);
    
	/**
	 * Returns the names of bpel process published
	 * @return The names of the bpel process
	 * @throws CumbiaException 
	 */
    public List<String> getBPELProcessesNamesPublished() throws CumbiaException;
	
    /**
     * Registry the activity specified like waiting for a message
     * @param partnerLinkName The name of the partner link to send the message
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The name of the port type name
     * @param operationName The name of the operation invoke by the invoke activity associated
     * @param typeMessageExpected The type of message expected
     * @param elementName The name of the element
     * @param instanceID The id  of the instance
     *  @param beginTime The time when the waiting begins
     */
    public void registerElementExpectingMessage(String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, String typeMessageExpected, IInteraction element, InstanceId instanceID, long beginTime);
    
    /**
     * Sends the response to the partner link provided
     * @param partnerLinkName The name of the partner link
     * @param portTypeName The name of the port type
     * @param operationName The name of the operation to be invoked for the answer
     * @param response The response 
     * @param name name of the element
     * @param instanceID The id of the instance
     */
    public void sendReplyResponse(String partnerLinkName, String portTypeName, String operationName, Object response, String name, InstanceId instanceID);
    
    /**
     * Invokes the asynchronuos partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param message The message to be sent   
     */
    public void invokeAsynchronusPartnerLink(String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, IInteraction elemenet, InstanceId instanceID, int serviceId  );
}
