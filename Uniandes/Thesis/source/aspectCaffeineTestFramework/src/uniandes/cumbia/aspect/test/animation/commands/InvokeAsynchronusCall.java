package uniandes.cumbia.aspect.test.animation.commands;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.WSDLNames;

public class InvokeAsynchronusCall implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimation animation;
    
    /**
     * The name of the process
     */
    private String processName;
    
    /**
     * The name of the process
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
     * The port type
     */
    private String portType;
    
    /**
     * The partner link name
     */
    private String partnerLinkUri;
    
    /**
     * The message center
     */
    private IMessageCenter messageCenter;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public InvokeAsynchronusCall( String processName,String serviceName, String processID, String instanceID, String portType, String partnerLinkUri)
    {
        this.processName = processName;
        this.serviceName = serviceName;
        this.instanceID = instanceID;
        this.processID = processID;
        this.portType = portType;
        this.partnerLinkUri = partnerLinkUri;
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
            messageCenter.invokeAsynchronousCall( WSDLNames.getInstance( ).getId(serviceName), partnerLinkUri, id, portType, processName, id);
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
