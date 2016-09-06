package uniandes.cumbia.aspect.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspect.test.animation.commands.DisableAspect;
import uniandes.cumbia.aspect.test.animation.commands.SetConflictFile;

public class DisableAspectLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String NAME = "name";
    
    private static final String PROCESS_NAME = "processName";
    
    private static final String PROCESS_ID = "processID";
    
    private static final String INSTANCE_ID = "instanceID";
    
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public DisableAspectLoader( Node node )
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
        String name = getAttribute( NAME );
        String processName = getAttribute( PROCESS_NAME );
        String processId = getAttribute( PROCESS_ID );
        String instanceId = getAttribute( INSTANCE_ID );
     
        
        command = new DisableAspect(name,processName,processId,instanceId);
        
        
        
    }
}
