package uniandes.cumbia.aspects.elements.transitionPoint.actions;

import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ExecuteAdvice implements IAction
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

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
        //System.out.println("ExcuteAdvice Action");
        ITransitionPoint transitionPoint = (ITransitionPoint) element;
        transitionPoint.executeAdvice();
    }
}
