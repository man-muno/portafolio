package uniandes.cumbia.bpel.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.animation.commands.ProcessAsynchronusResponse;

public class ProcessAsynchronusResponseLoader extends AbstractLoader
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String PROCESS_ID = "processID";
    private static final String INSTANCE_ID = "instanceID";
    private static final String RESPONSE = "response";
    private static final String SERVICE_NAME = "serviceName";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimationCommand command;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public ProcessAsynchronusResponseLoader( Node node )
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
        String response = getAttribute( RESPONSE );
        
        command = new ProcessAsynchronusResponse(serviceName,processID,instanceID,response);
    }
}
