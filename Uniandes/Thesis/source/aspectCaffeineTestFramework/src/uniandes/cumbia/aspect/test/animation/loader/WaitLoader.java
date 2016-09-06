package uniandes.cumbia.aspect.test.animation.loader;

import org.w3c.dom.Node;

import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspect.test.animation.commands.Wait;

public class WaitLoader extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String AMOUNT = "amount";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IAnimationCommand command;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public WaitLoader( Node node )
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
        long delay = Long.parseLong( getAttribute( AMOUNT ));
        
        command = new Wait(delay);
        //System.out.println("wait loaded");
    }
}
