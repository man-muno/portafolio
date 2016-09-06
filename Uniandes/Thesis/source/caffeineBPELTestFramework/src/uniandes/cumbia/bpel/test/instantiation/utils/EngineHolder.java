package uniandes.cumbia.bpel.test.instantiation.utils;

import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;

public class EngineHolder
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IProcessEngine engine;
    
    private static EngineHolder instance;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    public static EngineHolder getInstance( )
    {
        if( instance == null )
        {
            synchronized( IProcessEngine.class )
            {
                if( instance == null )
                    instance = new EngineHolder( );
            }
        }
        return instance;
    }
    
    public void setEngine(IProcessEngine engine)
    {
        this.engine = engine;
    }
    
    public IProcessEngine getEngine()
    {
        return engine;
    }
}
