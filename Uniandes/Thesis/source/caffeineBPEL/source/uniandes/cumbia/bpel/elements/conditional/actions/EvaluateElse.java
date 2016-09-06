package uniandes.cumbia.bpel.elements.conditional.actions;

import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class EvaluateElse implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Fires the event to execute the else element. If the else element doesn't exists the next action should take into consideration
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        System.out.println("EvaluateElse action");
        IConditional conditional = ( IConditional )element;
        conditional.executeElseActivity( );
    }

}
