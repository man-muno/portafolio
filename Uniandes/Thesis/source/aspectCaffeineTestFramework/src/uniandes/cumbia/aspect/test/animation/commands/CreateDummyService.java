package uniandes.cumbia.aspect.test.animation.commands;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.bpel.test.instantiation.utils.DummyService;
import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.instantiation.utils.WSDLNames;

public class CreateDummyService implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;
    
    private String processName;
    private String serviceName;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    
    public CreateDummyService( String processName, String serviceName )
    {
        this.processName = processName;
        this.serviceName = serviceName;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------


    /**
     * Executes the command
     */
    public void execute( )
    {
        WSDLNames.getInstance().setId(MessageCenter.getInstance( ).createNewService( new DummyService(processName) ),serviceName);
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
