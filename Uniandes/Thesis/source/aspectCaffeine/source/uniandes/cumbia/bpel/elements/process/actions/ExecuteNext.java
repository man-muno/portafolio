package uniandes.cumbia.bpel.elements.process.actions;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ExecuteNext implements IAction
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // ConstructorsS
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Register an element to receive a message
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        //System.out.println( "ExecuteNext Action called" );
        IProcess process = ( IProcess )element;
        process.executeNextActivity( );
    }
}
