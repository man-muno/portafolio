package uniandes.cumbia.bpel.elements.pick.actions;

import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;

public class OnMessageExecutor implements Runnable
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
    private IOnMessage onMessage;
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------


    public OnMessageExecutor( IOnMessage onMessage )
    {
        this.onMessage = onMessage;
    }
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------


    public void run( )
    {
        onMessage.execute( );
    }
}
