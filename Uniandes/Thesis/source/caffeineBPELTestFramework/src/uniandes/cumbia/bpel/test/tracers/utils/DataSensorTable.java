package uniandes.cumbia.bpel.test.tracers.utils;

import java.util.Hashtable;

import uniandes.cumbia.bpel.test.instantiation.utils.MessageCenter;
import uniandes.cumbia.bpel.test.sensors.DataSensor;

public class DataSensorTable
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private Hashtable<String, DataSensor> dataAndSensorRelation;

    private static DataSensorTable instance;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    private DataSensorTable( )
    {
        dataAndSensorRelation = new Hashtable<String, DataSensor>( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public static DataSensorTable getInstance( )
    {
        if( instance == null )
        {
            synchronized( DataSensorTable.class )
            {
                if( instance == null )
                    instance = new DataSensorTable( );
            }
        }
        return instance;

    }

    public DataSensor getSensor( String dataName )
    {
        return dataAndSensorRelation.get( dataName );
    }

    public void setSensor( String dataName, DataSensor dataSensor )
    {
        //System.out.println("DataSensorTable.setSensor " + dataName);
        dataAndSensorRelation.put( dataName, dataSensor );
    }
}
