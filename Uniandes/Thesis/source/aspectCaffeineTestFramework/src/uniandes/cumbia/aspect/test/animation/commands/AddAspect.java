package uniandes.cumbia.aspect.test.animation.commands;

import java.io.File;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.instantiation.utils.EngineHolder;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.loaders.LoaderException;

public class AddAspect implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;
    
    private String aspectPath;
    private boolean enabled;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    
    public AddAspect( String aspectPath, boolean enabled )
    {
        this.aspectPath = aspectPath;
        this.enabled = enabled;
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
            File aspectFile = new File(aspectPath);
            EngineHolder.getInstance( ).getEngine( ).deployAspect(aspectFile,enabled);
        }
        catch( LoaderException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( CumbiaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
