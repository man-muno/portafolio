package uniandes.cumbia.aspect.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspect.test.animation.commands.InvokeSynchronusCall;

public class InvokeSynchronusCallLoader extends AbstractLoader
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String PROCESS_ID = "processID";
    private static final String INSTANCE_ID = "instanceID";
    private static final String MESSAGE = "message";
    private static final String PARTNER_LINK_URI = "partnerLinkUri";
    private static final String PORT_TYPE = "portType";
    private static final String OPERATION = "operation";
    private static final String SERVICE_NAME = "serviceName";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public InvokeSynchronusCallLoader( Node node )
    {
        super(node);
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns the loaded element.
     * @return The loaded element.
     */
    public IAnimationCommand getElement( )
    {
        return command;
    }
    
    /**
     * Loads the animation.
     * @throws LoaderException If there is any problem loading the element.
     */
    public void loadElement( ) throws LoaderException
    {
        String serviceName = getAttribute( SERVICE_NAME );
        String processID = getAttribute( PROCESS_ID );
        String instanceID = getAttribute( INSTANCE_ID );
        String message = getAttribute( MESSAGE );
        String partnerLinkUri = getAttribute( PARTNER_LINK_URI );
        String portType = getAttribute( PORT_TYPE );
        String operation = getAttribute( OPERATION );
        
        command = new InvokeSynchronusCall(serviceName,processID, instanceID, message, partnerLinkUri,portType, operation);
    }
}
