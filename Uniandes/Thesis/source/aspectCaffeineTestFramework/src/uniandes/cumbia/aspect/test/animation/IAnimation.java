package uniandes.cumbia.aspect.test.animation;

import java.util.List;

public interface IAnimation
{
    /**
     * Returns the animation commands
     */
    public List<IAnimationCommand> getAnimationCommands( );

    /**
     * Adds an animation command to the list
     */
    public void addAnimationCommand( IAnimationCommand command );

    /**
     * Notifies that the animation command was executed
     */
    public void notifyActionCommandExecuted( );
}