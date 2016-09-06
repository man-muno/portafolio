package uniandes.cumbia.aspect.test.tracers;

import java.util.List;

import uniandes.cumbia.aspect.test.sensors.DataSensor;
import uniandes.cumbia.aspect.test.tracers.utils.DataSensorTable;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.runtime.BpelRuntime;
import uniandes.cumbia.testFW.interfaces.ISensor;

/**
 * Implementation of ITracer. This tracer is supposed to create traces based on events listened by an IEventListener.
 */
public class DataTracer extends AbstractTracer
{
    /**
     * Creates a trace.
     * @param event Event listened by an EventListenedSensor.
     * @return The created trace.
     */
    public Object createTrace( Object... data )
    {
        //System.out.println("DataTracer: createTrace " + data.length);
       
        DataTrace trace = new DataTrace(data);  
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
        //System.out.println("DataTracer: installSensors " + sensor);
        
        // Get the open object
        List<IBasicElement> elements = ((BpelRuntime)runtime).getElement( expression );
        
        for(int i=0;i<elements.size( );i++)
        {
            IData data = (IData) elements.get(i);
            DataSensorTable.getInstance().setSensor( data.getElementName( ), ( DataSensor )sensor );
        }
    }
}