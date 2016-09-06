package uniandes.cumbia.aspect.test.animation;

public interface IAnimationCommand extends Runnable
{

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes the commnand
     */
    public void execute( );

    /**
     * Prepare the command for execution
     */
    public void prepare( );

    /**
     * Sets the animation for the command
     * @param animation
     */
    public void setAnimation( IAnimation animation );
}