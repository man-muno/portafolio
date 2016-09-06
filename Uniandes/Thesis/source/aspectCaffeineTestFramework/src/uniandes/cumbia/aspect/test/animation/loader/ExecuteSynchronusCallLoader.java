package uniandes.cumbia.aspect.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspect.test.animation.commands.ExecuteSynchronusCall;

public class ExecuteSynchronusCallLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String PROCESS_NAME = "processName";
    private static final String PROCESS_ID = "processID";
    private static final String INSTANCE_ID = "instanceID";
    private static final String MESSAGE = "message";
    private static final String SERVICE_NAME = "serviceName";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimationCommand command;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public ExecuteSynchronusCallLoader( Node node )
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
        String serviceName = getAttribute( SERVICE_NAME );
        String processID = getAttribute( PROCESS_ID );
        String instanceID = getAttribute( INSTANCE_ID );
        String message = getAttribute( MESSAGE );
        
        command = new ExecuteSynchronusCall(processName,serviceName,processID,instanceID,message);
    }
}