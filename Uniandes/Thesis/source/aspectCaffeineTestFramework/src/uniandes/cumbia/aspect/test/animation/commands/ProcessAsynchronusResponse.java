package uniandes.cumbia.aspect.test.animation.commands;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.WSDLNames;

public class ProcessAsynchronusResponse implements IAnimationCommand, Runnable
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
     * The object
     */
    private Object response;
    
    /**
     * The message center
     */
    private IMessageCenter messageCenter;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public ProcessAsynchronusResponse( String serviceName, String processID, String instanceID, Object response )
    {
        this.serviceName = serviceName;
        this.instanceID = instanceID;
        this.processID = processID;
        this.response = response;
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
            messageCenter.processAsynchronousResponse( WSDLNames.getInstance( ).getId(serviceName), response, Integer.parseInt( instanceID), Integer.parseInt( processID ));
            
        }
        catch( Exception e )
        {
            e.printStackTrace( );
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
        execute( );
    }
}
