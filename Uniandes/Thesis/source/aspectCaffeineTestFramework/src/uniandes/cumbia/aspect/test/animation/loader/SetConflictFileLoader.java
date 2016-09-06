package uniandes.cumbia.aspect.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspect.test.animation.commands.SetConflictFile;

public class SetConflictFileLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ASPECT_FILE = "file";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public SetConflictFileLoader( Node node )
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
        String conflictFile = getAttribute( ASPECT_FILE  );
        command = new SetConflictFile(conflictFile);
        
        
        
    }
}
