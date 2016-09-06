package uniandes.cumbia.bpel.elements.conditional.actions;

import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ExecuteElse implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Executes the activity in the else element
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        IConditional conditional = ( IConditional )element;
        if( conditional.getElse( ) != null )
            conditional.getElse( ).getActivity( ).execute( );
        else
            conditional.finalizeElement();
    }
}
