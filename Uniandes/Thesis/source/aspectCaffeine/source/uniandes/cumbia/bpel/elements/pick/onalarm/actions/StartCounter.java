package uniandes.cumbia.bpel.elements.pick.onalarm.actions;

import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onalarm.OnAlarm;
import uniandes.cumbia.bpel.engine.utils.xpath.DateParser;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class StartCounter implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Starts the counter of the on alarm
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {

        IOnAlarm onAlarm = ( IOnAlarm )element;
        long startTime = System.currentTimeMillis( );

        String timeExpression = onAlarm.getExpression( );
        String durationType = onAlarm.getType( );

        long duration = 0;
        long sleep = 0;

        if( durationType.equals( OnAlarm.TYPE_UNTIL ) )
        {
            duration = DateParser.parseDeadlineExpression( timeExpression );
            sleep = duration - startTime;
        }
        else
        {
            duration = DateParser.parseDurationExpression( timeExpression );
            sleep = duration;
        }

        if( sleep > 0 )
        {
            try
            {
                Thread.sleep( sleep );
                onAlarm.stopedCounting();
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }
        }
    }
}
