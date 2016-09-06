package uniandes.cumbia.aspect.test.animation.commands;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.WSDLNames;

public class InvokeSynchronusCall implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;
   
    
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
     * The partner link uri
     */
    private String partnerLinkUri;
    
    /**
     * The operation
     */
    private String operation;
    
    /**
     * The message center
     */
    private IMessageCenter messageCenter;

    /**
     * The port type
     */
    private String portType;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public InvokeSynchronusCall(String serviceName, String processID, String instanceID, String message, String partnerLinkUri, String portType,String operation )
    {
        this.serviceName = serviceName;
        this.instanceID = instanceID;
        this.processID = processID;
        this.message = message;
        this.partnerLinkUri = partnerLinkUri;
        this.operation = operation;
        this.portType = portType;
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
            messageCenter.invokeSynchronousCall( WSDLNames.getInstance( ).getId(serviceName), partnerLinkUri, message, portType, operation, id);
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
