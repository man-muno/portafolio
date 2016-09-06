package uniandes.cumbia.bpel.test.tracers;

import java.util.List;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.runtime.BpelRuntime;
import uniandes.cumbia.bpel.instantiation.BpelRoleResolver;
import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.testFW.interfaces.ISensor;

/**
 * Implementation of ITracer. This tracer is supposed to create traces based on events listened by an IEventListener.
 */
public class EventListenerTracer extends AbstractTracer
{
    /**
     * Creates a trace.
     * @param event Event listened by an EventListenedSensor.
     * @return The created trace.
     */
    public Object createTrace( Object... event )
    {
        //System.out.println("EventListenerTracer: createTrace");
        EventNotification eventReceived = ( EventNotification )event[ 0 ];
        // Get the source.
        IKernelElement source = eventReceived.getSourceElement( );
        // Get the event name.
        
        String sourceEventName = source.getModelInstance( ).getMappingInformation().resolveEventId(eventReceived.getEventId( ));
        // Create the trace.
        //System.out.println("Creates trace " + source.getElementName( ));
        EventTrace trace = new EventTrace( source.getElementName( ), sourceEventName );
        // Add it to the list.
        traces.add( trace );
        return trace;
    }

    /**
     * Installs the sensor in the given path. It is supposed to install the sensor as a listener to the before event of a given transition, or to all transitions of an
     * element.
     * @param expression Expression indicating where the sensor must be installed. It has to be the path to an element (timeRuleName or timeRuleName.elementName), followed by
     *        "." and a name of a transition or the wildcard character "*".
     * @param runtime Runtime of the TimeModel in which the elements to be traced are.
     * @param sensor Sensor to be installed. It must be an object from a class implementing IEventListener.
     */
    public void installSensors( String expression, Object runtime, ISensor sensor )
    {

        // Get the open object
        List<IBasicElement> elements = ((BpelRuntime)runtime).getElement( expression );
        
        for(IBasicElement element : elements)
        {
            IOpenObject openObject = (IOpenObject) element;
            EventGenerator eventGenerator = openObject.getStateMachine( ).getInitialState( ).getExitEventGenerator( );
            int id = openObject.getModelInstance( ).getMappingInformation( ).getRoleId( BpelRoleResolver.ME_ROLE);
            eventGenerator.addListener( ( IEventListener )sensor, id);
        }
        //System.out.println("EventListenerTracer: installSensors");
    }
}