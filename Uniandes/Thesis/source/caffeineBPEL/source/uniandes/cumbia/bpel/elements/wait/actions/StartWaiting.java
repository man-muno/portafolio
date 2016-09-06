package uniandes.cumbia.bpel.elements.wait.actions;

import uniandes.cumbia.bpel.elements.wait.IWait;
import uniandes.cumbia.bpel.elements.wait.Wait;
import uniandes.cumbia.bpel.engine.utils.xpath.DateParser;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class StartWaiting implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Register an element to receive a message
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        IWait wait = ( IWait )element;
        long startTime = System.currentTimeMillis( );
        String durationType = wait.getType( );
        long duration = 0;

        long sleep = 0;

        if( durationType.equals( Wait.TYPE_UNTIL ) )
        {
            duration = DateParser.parseDeadlineExpression( wait.getExpression( ) );
            sleep = duration - startTime;

        }
        else
        {
            duration = DateParser.parseDurationExpression( wait.getExpression( ) );
            sleep = duration;
        }

        if( sleep > 0 )
        {
            try
            {
                Thread.sleep( sleep );
                wait.terminate();
            }
            catch( InterruptedException e )
            {
                e.printStackTrace( );
            }
        }
    }

}
