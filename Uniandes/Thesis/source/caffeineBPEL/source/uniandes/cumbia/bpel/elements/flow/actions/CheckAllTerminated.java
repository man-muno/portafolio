package uniandes.cumbia.bpel.elements.flow.actions;

import uniandes.cumbia.bpel.elements.flow.IFlow;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class CheckAllTerminated implements IAction
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
     * Checks that all activities are terminated
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IFlow flow = (IFlow) element;
        flow.activityTerminated();
        
        if(flow.areAllActivitiesTerminated())
            flow.terminated();
        
    }

}
