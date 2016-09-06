package uniandes.cumbia.bpel.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.animation.commands.CreateCounterService;
import uniandes.cumbia.bpel.test.animation.commands.CreateDummyService;

public class CreateTestWSDLLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String PROCESS_NAME = "processName";
    private static final String SERVICE_NAME = "serviceName";
    private static final String TYPE = "type";
    
    private static final String DUMMY = "dummy";
    private static final String COUNTER = "counter";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public CreateTestWSDLLoader( Node node )
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
        String type = getAttribute( TYPE );
        
        
        command = type.equals( DUMMY ) ? new CreateDummyService(processName,serviceName) : new CreateCounterService(processName,serviceName) ;
    }
}
