package uniandes.cumbia.bpel.test.tracers;

import uniandes.cumbia.bpel.test.sensors.AbstractSensor;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.IEventListener;

public class EventListenerSensor extends AbstractSensor implements IEventListener
{
    private static final long serialVersionUID = -2582519770935766177L;

    public void notifyEvent( EventNotification event )
    {
        //System.out.println( "EventListenerSensor: notifyEvent" );
        notifySensor( event );
    }

}