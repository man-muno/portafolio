package uniandes.cumbia.bpel.elements.pick.actions;

import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;
import uniandes.cumbia.utils.ThreadPool;

public class InitElements implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Initiates the inner elements of the pick
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IPick pick = (IPick) element;
        ThreadPool pool = ThreadPool.getInstance( );
        for(int i=0;i<pick.getOnAlarms( ).size( );i++)
        {
            OnAlarmExecutor onAlarmExecutor = new OnAlarmExecutor(pick.getOnAlarms( ).get( i ));
            pool.execute( onAlarmExecutor );
        }
        for(int i=0;i<pick.getOnMessages( ).size( );i++)
        {
            OnMessageExecutor onMessageExecutor = new OnMessageExecutor(pick.getOnMessages( ).get( i ));
            pool.execute( onMessageExecutor );
        }
    }

}
