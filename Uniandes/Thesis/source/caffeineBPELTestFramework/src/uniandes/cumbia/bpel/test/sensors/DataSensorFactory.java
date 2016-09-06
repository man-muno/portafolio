package uniandes.cumbia.bpel.test.sensors;

import uniandes.cumbia.testFW.interfaces.ISensor;
import uniandes.cumbia.testFW.interfaces.ISensorFactory;

public class DataSensorFactory implements ISensorFactory
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
    

    @Override
    public ISensor createSensor( String description )
    {
        //System.out.println("createSensor");
        return new DataSensor();
    }
}
