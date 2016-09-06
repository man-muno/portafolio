package uniandes.cumbia.bpel.test.animation.commands;

import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.test.animation.IAnimation;
import uniandes.cumbia.bpel.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;

public class Wait implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;
   
    /**
     * The time to wait
     */
    private long delay;

    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    

    public Wait(long delay)
    {
        this.delay = delay;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Executes the command
     */
    public void execute( )
    {
        //System.out.println("Executing wait");
        try
        {
            //System.out.println("enter waiting");
            Thread.sleep(delay);
        }
        catch( InterruptedException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("end waiting");
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
