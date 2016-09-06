package uniandes.cumbia.aspect.test.tracers;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.testFW.interfaces.ISensor;
import uniandes.cumbia.testFW.interfaces.ITracer;

public abstract class AbstractTracer implements ITracer
{
    protected List<ISensor> sensors;
    protected List<ITrace> traces;

    /**
     * Default constructor. Initializes the sensor and traces lists.
     */
    public AbstractTracer( )
    {
        //System.out.println( "AbstractTracer" );
        sensors = new ArrayList<ISensor>( );
        traces = new ArrayList<ITrace>( );
    }

    /**
     * Adds a sensor to the tracer.
     * @param is Sensor to be added to the tracer.
     */
    public void addSensor( ISensor is )
    {
        //System.out.println( "AbstractTracer: addSensor" );
        sensors.add( is );
    }

    /**
     * Returns the sensors of the tracer.
     * @return The sensors of the tracers.
     */
    public List<ISensor> getSensors( )
    {
        //System.out.println( "AbstractTracer: getSensors" );
        return sensors;
    }

    /**
     * Returns the traces of the tracer.
     * @return The traces of the tracers.
     */
    public List<ITrace> getTraces( )
    {
        //System.out.println( "AbstractTracer: getTraces" );
        return traces;
    }
}