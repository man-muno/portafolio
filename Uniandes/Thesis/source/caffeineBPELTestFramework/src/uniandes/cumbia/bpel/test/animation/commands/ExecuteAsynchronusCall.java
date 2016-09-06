package uniandes.cumbia.bpel.test.animation.commands;

import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.test.animation.IAnimation;
import uniandes.cumbia.bpel.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.WSDLNames;

public class ExecuteAsynchronusCall implements IAnimationCommand, Runnable
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimation animation;
    
    /**
     * The name of the process
     */
    private String processName;
    
    /**
     * The name of the service
     */
    private String serviceName;
    
    /**
     * The id of the process
     */
    private String processID;
    
    /**
     * The id of the process instance
     */
    private String instanceID;
    
    /**
     * The message
     */
    private String message;
    
    /**
     * The message center
     */
    private IMessageCenter messageCenter;

    /**
     * The client uri
     */
    private String clientUri;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public ExecuteAsynchronusCall( String processName,String serviceName, String processID, String instanceID, String message, String clientUri )
    {
        this.processName = processName;
        this.serviceName = serviceName;
        this.instanceID = instanceID;
        this.processID = processID;
        this.message = message;
        this.clientUri = clientUri;
        this.messageCenter = (IMessageCenter)MessageCenter.getInstance( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Executes the command
     */
    public void execute( )
    {
        try
        {
            InstanceId id = new InstanceId(Integer.parseInt( instanceID ), Integer.parseInt( processID ) );
            messageCenter.executeAsynchronousCall(  processName, message, ClassLoader.getSystemClassLoader( ), WSDLNames.getInstance( ).getId(serviceName), id, clientUri  );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
        animation.notifyActionCommandExecuted( );
    }

    /**
     * Prepare the command for execution
     */
    public void prepare( )
    {
        
    }

    /**
     * Sets the animation for the command
     * @param animation
     */
    public void setAnimation( IAnimation animation )
    {
        this.animation = animation;
    }

    @Override
    public void run( )
    {
        execute();
    }
}
