package uniandes.cumbia.bpel.elements.flow.actions;

import uniandes.cumbia.bpel.elements.IActivity;

public class FlowActivityExecutor implements Runnable
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
    private IActivity activity;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public FlowActivityExecutor(IActivity activity)
    {
        this.activity = activity;
    }
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    public void run( )
    {
        activity.execute( );
    }
}
