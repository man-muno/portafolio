package uniandes.cumbia.aspect.test.sensors;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.testFW.interfaces.ISensor;
import uniandes.cumbia.testFW.interfaces.ITracer;

public abstract class AbstractSensor implements ISensor
{
    protected String path;

    protected List<ITracer> tracers;

    public AbstractSensor( )
    {
        //System.out.println( "AbstractSensor" );
        tracers = new ArrayList<ITracer>( );
    }

    public void notifySensor( Object... objects )
    {
        //System.out.println( "AbstractSensor: notifySensor " + ((EventNotification)objects[0]).toString( ));
        for( ITracer tracer : tracers )
            tracer.createTrace( objects );
    }

    public String getPath( )
    {
        //System.out.println( "AbstractSensor: getPath" );
        return path;
    }

    public void setPath( String path )
    {
        //System.out.println( "AbstractSensor: setPath" );
        this.path = path;
    }

    public void addTracer( ITracer tracer )
    {
        //System.out.println( "AbstractSensor: addTracer" );
        tracers.add( tracer );
    }
}