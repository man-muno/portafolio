package uniandes.cumbia.aspect.test.animation.commands;

import java.io.File;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.instantiation.utils.EngineHolder;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.loaders.LoaderException;

public class DisableAspect implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;
    private String name;
    private String processName;
    private String processId;
    private String instanceId;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public DisableAspect( String name, String processName, String processId, String instanceId )
    {
        this.name = name;
        this.processName = processName;
        this.processId = processId;
        this.instanceId = instanceId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes the command
     */
    public void execute( )
    {
        EngineHolder.getInstance( ).getEngine( ).disableAspect(name,processName,Integer.parseInt( processId),Integer.parseInt( instanceId));
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
