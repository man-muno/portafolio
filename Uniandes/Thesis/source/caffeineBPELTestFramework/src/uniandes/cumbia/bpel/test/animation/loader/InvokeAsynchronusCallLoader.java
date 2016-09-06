package uniandes.cumbia.bpel.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.animation.commands.InvokeAsynchronusCall;

public class InvokeAsynchronusCallLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String PROCESS_NAME = "processName";
    private static final String SERVICE_NAME = "serviceName";
    private static final String PROCESS_ID = "processID";
    private static final String INSTANCE_ID = "instanceID";
    private static final String PORT_TYPE = "portType";
    private static final String PARTNER_LINK_URI = "partnerLinkUri";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public InvokeAsynchronusCallLoader( Node node )
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
        String processName = getAttribute( PROCESS_NAME );
        String serviceName = getAttribute( SERVICE_NAME);
        String processID = getAttribute( PROCESS_ID );
        String instanceID = getAttribute( INSTANCE_ID );
        String portType = getAttribute( PORT_TYPE );
        String partnerLinkUri = getAttribute( PARTNER_LINK_URI );
        
        command = new InvokeAsynchronusCall(processName,serviceName,processID,instanceID,portType,partnerLinkUri);
    }
}
