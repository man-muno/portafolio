package uniandes.cumbia.bpel.elements.pick.actions;

import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;

public class OnAlarmExecutor implements Runnable
{

    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The activity to execute
     */
    private IOnAlarm onAlarm;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------


    public OnAlarmExecutor( IOnAlarm onAlarm )
    {
        this.onAlarm = onAlarm;
    }
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------


    public void run( )
    {
        onAlarm.execute( );
    }
}
