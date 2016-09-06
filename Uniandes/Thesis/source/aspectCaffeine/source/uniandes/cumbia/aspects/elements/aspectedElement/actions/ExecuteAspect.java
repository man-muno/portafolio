package uniandes.cumbia.aspects.elements.aspectedElement.actions;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ExecuteAspect implements IAction
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String EXECUTE_ASPECT = "ExecuteAspect";
    private IAspect aspect;

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public ExecuteAspect(IAspect aspect)
    {
        this.aspect = aspect;
    }
    

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Calculates the next transition point to be executed
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        System.out.println("ExecuteAspect Action");
        aspect.initializeAspect();
    }
}
