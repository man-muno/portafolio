package uniandes.cumbia.bpel.test.sensors;

import uniandes.cumbia.bpel.elements.Data;
import uniandes.cumbia.bpel.test.tracers.utils.DataSensorTable;

public aspect DataAspect
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
    
    pointcut dataModification():
        execution(* Data.addObject(..));
    
    before(): dataModification()
    {
        DataSensorTable.getInstance( ).getSensor( ((Data)thisJoinPoint.getThis( )).getName()).notifySensor( ((Data)thisJoinPoint.getThis( )).getName(),thisJoinPoint.getArgs( )[0],thisJoinPoint.getArgs( )[1] );
    }
    
}
