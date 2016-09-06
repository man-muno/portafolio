package uniandes.cumbia.bpel.elements.pick.onalarm.actions;

import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class SetActivityOnPick implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Adds an activity waiting
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IOnAlarm onAlarm = (IOnAlarm) element;
        //System.out.println("SetActivityOnPick " + onAlarm.getActivity( ).getName( ));
        onAlarm.getPick( ).setSelectedActivity( onAlarm.getActivity( ) );
    }

}
