package uniandes.cumbia.aspect.test.animation;

import java.util.ArrayList;
import java.util.List;

public class Animation implements IAnimation
{
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Index of the command
     */
    private int actionCommandsExecuted;

    /**
     * List of the commands
     */
    private List<IAnimationCommand> commands;

    /**
     * The animator
     */
    private Animator animator;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public Animation( )
    {
        super( );
        commands = new ArrayList<IAnimationCommand>( );
        actionCommandsExecuted = 0;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns all the animation commands
     */
    public List<IAnimationCommand> getAnimationCommands( )
    {
        return commands;
    }

    /**
     * Adds an animation command
     */
    public void addAnimationCommand( IAnimationCommand command )
    {
        commands.add( command );
    }

    /**
     * Notifies that the action command was executed
     */
    public void notifyActionCommandExecuted( )
    {
        if( ++actionCommandsExecuted >= commands.size( ) )
            animator.notifyAnimationExecuted( );
    }

    /**
     * @param animator The animator to set.
     */
    public void setAnimator( Animator animator )
    {
        this.animator = animator;
    }
}