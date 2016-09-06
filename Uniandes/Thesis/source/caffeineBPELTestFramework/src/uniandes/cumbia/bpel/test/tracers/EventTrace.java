package uniandes.cumbia.bpel.test.tracers;

import java.util.Date;

public class EventTrace implements ITrace
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private String sourceName;

    private String sourceEventName;

    private Date date;

    private long nanoTime;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    public EventTrace( String sourceName, String sourceEventName )
    {
        super( );
        //System.out.println("GenericTrace");
        this.sourceName = sourceName;
        this.sourceEventName = sourceEventName;
        date = new Date( );
        nanoTime = System.nanoTime( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Gets the name of the source of the trace (e.g. the element that
     * generated the event to be traced).
     * @return Name of the source of the trace.
     */
    public String getSourceName()
    {
        //System.out.println("GenericTrace: getSourceName");
        return sourceName;
    }

    public Date getDate( )
    {
        //System.out.println("GenericTrace: getDate");
        return date;
    }

    /**
     * Gets the name of the specific event (state, transition, event, ...)
     * in the source, that generated the trace (e.g. the transition of the
     * source element to be traced).
     * @return Name of the source event.
     */
    public String getSourceEventName()
    {
        //System.out.println("GenericTrace: getSourceEventName");
        return sourceEventName;
    }

    public int compareTo( ITrace arg0 )
    {
        //System.out.println("GenericTrace: compareTo");
        return date.compareTo( ((EventTrace)arg0).getDate() );
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString( )
    {
        //System.out.println("GenericTrace: toString");
        return "sourceName: " + sourceName + " - " + "sourceEventName: " + sourceEventName + " - " + "date: " + date + " - " + "nanoTime: " + nanoTime;
    }

    /**
     * Returns the time of the generation of the trace in nanosecond
     * precision.
     * @return The time of the generation of the trace in nanosecond
     * precision.
     */
    public long getNanoTime()
    {
        //System.out.println("GenericTrace: getNanoTime");
        return nanoTime;
    }
}