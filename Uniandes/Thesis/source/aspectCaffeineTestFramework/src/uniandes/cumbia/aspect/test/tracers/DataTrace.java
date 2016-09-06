package uniandes.cumbia.aspect.test.tracers;

import java.util.Date;

public class DataTrace implements ITrace
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The date of creation of the trace
     */
    private Date date;

    /**
     * The name of the variable changed
     */
    private String variableName;
    
    /**
     * The name of the part that changed
     */
    private String partName;
    
    /**
     * The value of the part
     */
    private Object value;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public DataTrace( Object[] data )
    {
        super( );
        date = new Date( );
        variableName = ( String )data[0];
        partName = ( String )data[1];
        value = data[2];
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    


    public Date getDate( )
    {
        return date;
    }

    public int compareTo( ITrace arg0 )
    {
        return date.compareTo( ((DataTrace)arg0).getDate() );
    }

    /**
     * @return the variableName
     */
    public String getVariableName( )
    {
        return variableName;
    }

    /**
     * @return the partName
     */
    public String getPartName( )
    {
        return partName;
    }

    /**
     * @return the value
     */
    public Object getValue( )
    {
        return value;
    }
}