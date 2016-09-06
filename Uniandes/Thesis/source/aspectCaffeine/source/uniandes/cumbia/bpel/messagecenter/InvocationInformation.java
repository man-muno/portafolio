package uniandes.cumbia.bpel.messagecenter;

import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.instance.InstanceId;


public class InvocationInformation
{
	// -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
	    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	/**
	 * The id of the process
	 */
	private InstanceId instanceId;
	
	/**
	 * The type of message expected
	 */
	private String messageExpectedType;
	
	/**
	 * The type of message expected
	 */
	private String messageSendedType;  
	
	/**
	 * The time when the expected begins
	 */
	private long beginTimeWaiting; 
	
	/**
	 * The id of the correlation
	 */
	private String correlationId; 
	
	/**
	 * The name of the element
	 */
	private IInteraction element; 
	
	/**
	 * The partner link uri
	 */
	private String partnerLinkUri;
	
	/**
	 * The operation name
	 */
	private String operationName; 
	
	/**
	 * The partner link name
	 */
	private String partnerLinkName; 
	
	/**
	 * The port type name
	 */
	private String portTypeName; 
	
	/**
	 * The response of the invocation
	 */
	private Object response; 
							
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
	/**
	 * Constructor of the invocation information
	 */
	public InvocationInformation() 
	{
	
	}
		
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

	/**
	 * Returns the time when the waiting begun
	 * @return The time when the waiting begun 
	 */
	public long getBeginTimeWaiting() 
	{
		return beginTimeWaiting;
	}

	/**
	 * Changes the time when the waiting begun
	 * @param bTExpected The new time when the waiting begun 
	 */
	public void setBeginTimeWaiting(long bTExpected) 
	{
		beginTimeWaiting = bTExpected;
	}

	/**
	 * Returns the correlation id
	 * @return The new correlation id
	 */
	public String getCorrelationId() 
	{
		return correlationId;
	}

	/**
	 * Changes the correlation id
	 * @param cId The new correlation Id
	 */
	public void setCorrelationId(String cId) 
	{
		correlationId = cId;
	}

	/**
	 * Returns the instance id
	 * @return The new instance id
	 */
	public InstanceId getInstanceId() 
	{
		return instanceId;
	}

	/**
	 * Changes the instance id  
	 * @param instId The new instance id
	 */
	public void setInstanceId(InstanceId instId) 
	{
		instanceId = instId;
	}

	/**
	 * Returns the expected message type
	 * @return The new message type expected
	 */
	public String getMessageExpectedType() 
	{
		return messageExpectedType;
	}

	/**
	 * Changes the message type expected
	 * @param mExpectedType The new messaje expected type
	 */
	public void setMessageExpectedType(String mExpectedType) 
	{
		messageExpectedType = mExpectedType;
	}
	
	/**
	 * Returns the activity
	 * @return The activity
	 */
	public IInteraction getElement() 
	{
		return element;
	}

	/**
	 * Changes the id of the activity
	 * @param elementName The element
	 */
	public void setElement(IInteraction element) 
	{
		this.element = element;
	}

	/**
	 * Returns the message sended type
	 * @return The message sended type
	 */
	public String getMessageSendedType() 
	{
		return messageSendedType;
	}

	/**
	 * Changes the message sended type 
	 * @param messageSended The new message sended type
	 */
	public void setMessageSendedType(String messageSended) 
	{
		messageSendedType = messageSended;
	}

	/**
	 * Returns the operation name
	 * @return The operation name
	 */
	public String getOperationName() 
	{
		return operationName;
	}

	/**
	 * Changes the operation name
	 * @param opName The new operation name
	 */
	public void setOperationName(String opName) 
	{
		operationName = opName;
	}

	/**
	 * Returns the partner link uri
	 * @return the partner link uri
	 */
	public String getPartnerLinkUri() 
	{
		return partnerLinkUri;
	}

	/**
	 * Changes the partner link uri
	 * @param pLinkUri The new partner link uri
	 */
	public void setPartnerLinkUri(String pLinkUri) 
	{
		partnerLinkUri = pLinkUri;
	}

	/**
	 * Returns the partner link name
	 * @return The partner link name
	 */
	public String getPartnerLinkName() 
	{
		return partnerLinkName;
	}

	/**
	 * Changes the partner link name
	 * @param pLinkName The new partner link name
	 */
	public void setPartnerLinkName(String pLinkName) 
	{
		partnerLinkName = pLinkName;
	}

	/**
	 * Returns the port type name 
	 * @return The new port type name
	 */
	public String getPortTypeName() 
	{
		return portTypeName;
	}

	/**
	 * Changes the port type name 
	 * @param pTypeName The new port type name
	 */
	public void setPortTypeName(String pTypeName) 
	{
		portTypeName = pTypeName;
	}

	/**
	 * Returns the response
	 * @return The response
	 */
	public Object getResponse() 
	{
		return response;
	}

	/**
	 * Changes the response 
	 * @param resp The new response 
	 */
	public void setResponse(Object resp) 
	{
		response = resp;
	}
}
