package uniandes.cumbia.aspect.test.sensors;

import uniandes.cumbia.testFW.interfaces.ITracer;

public class ServiceSensor extends AbstractSensor
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

    public ServiceSensor( )
    {

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    @Override
    public void notifySensor( Object... objects )
    {
        //System.out.println( "DataSensor: notifySensor " + objects.length + " " + objects[0] + " " + objects[1]+ " " + objects[2]);
        for( ITracer tracer : tracers )
            tracer.createTrace( objects );
    }
}
