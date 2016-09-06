package uniandes.cumbia.bpel.test.instantiation.utils;

import java.util.Hashtable;

public class WSDLNames
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private static WSDLNames instance;
    
    private Hashtable<String, Integer> table = new Hashtable<String, Integer>();
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    private WSDLNames()
    {
        
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    public static WSDLNames getInstance( )
    {
        if( instance == null )
        {
            synchronized( WSDLNames.class )
            {
                if( instance == null )
                    instance = new WSDLNames( );
            }
        }
        return instance;
    }

    public void setId( int id, String serviceName )
    {
        table.put( serviceName, new Integer(id));
    }

    public int getId( String serviceName )
    {
        return serviceName != null && table.get( serviceName ) != null ? table.get( serviceName ).intValue( ) : 0 ;
    }
}
