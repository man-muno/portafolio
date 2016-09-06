package uniandes.cumbia.bpel.messagecenter;

import uniandes.cumbia.bpel.instance.InstanceId;


public class ExecuteSynchronousServices extends Thread
{
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	/**
	 * The id of the instance
	 */
	private InstanceId instanceId;
	
	/**
	 * The response
	 */
	private Object response;
	
	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	/**
	 * Constructor of the runnable
	 * @param insId Id of the instance
	 */
	public ExecuteSynchronousServices(InstanceId insId)
	{
		instanceId= insId;
		response= null;
		
	}
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

	/**
	 * Waits the the response of a synchronous processes
	 */
	public void run() 
	{	
		while(response==null)
		{
			try 
			{
				//System.out.println("ENTRO A ESPERAR");
				Thread.sleep(10000);
				//System.out.println("SALIO DE ESPERAR");
			} 
			catch (InterruptedException e) 
			{
				
				e.printStackTrace();
			}
		}
		
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
		response= resp;
	}

	/**
	 * Returns the instance id
	 * @return The instance id
	 */
	public InstanceId getInstanceId() 
	{
		return instanceId;
	}
	
	

}
