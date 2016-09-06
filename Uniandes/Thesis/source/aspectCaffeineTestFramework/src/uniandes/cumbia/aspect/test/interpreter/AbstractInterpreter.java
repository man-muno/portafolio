package uniandes.cumbia.aspect.test.interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uniandes.cumbia.aspect.test.tracers.AbstractTracer;
import uniandes.cumbia.aspect.test.tracers.DataTrace;
import uniandes.cumbia.aspect.test.tracers.EventTrace;
import uniandes.cumbia.aspect.test.tracers.ITrace;
import uniandes.cumbia.testFW.interfaces.IInterpreter;
import uniandes.cumbia.testFW.interfaces.ITracer;

public class AbstractInterpreter implements IInterpreter
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * List of the tracers
     */
    protected List<ITracer> tracers;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public AbstractInterpreter( )
    {
        tracers = new ArrayList<ITracer>( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public void addTracer( ITracer tracer )
    {
        tracers.add( tracer );
    }

    public List<ITracer> getTracers( )
    {
        return tracers;
    }

    protected void sortByDate()
    {
        for(ITracer iTracer :tracers)
        {
            AbstractTracer tracer = (AbstractTracer) iTracer;
            try
            {
                Collections.sort(tracer.getTraces( ));
            }
            catch( Exception e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    protected boolean isValue( String variableName, Object value )
    {
        boolean found = false;
        for( int i = 0; i < tracers.size( ) && !found; i++ )
        {
            ITracer iTracer = tracers.get( i );
            if( iTracer instanceof AbstractTracer )
            {
                AbstractTracer tracer = ( AbstractTracer )iTracer;
                List<ITrace> traces = tracer.getTraces( );
                for( int j = 0; j < traces.size( ) && !found; j++ )
                {
                    ITrace iTrace = traces.get( j );
                    if( iTrace instanceof DataTrace )
                    {
                        DataTrace trace = ( DataTrace )iTrace;
                        if( trace.getValue( ) != null && trace.getValue( ).toString( ) != null && trace.getValue( ).toString( ).equals( value.toString( ) ))
                            found = true;
                        else if(value == null && trace.getValue( ).toString( ) == null )
                            found = true;
                    }
                }
            }
        }
        return found;

    }
    

    protected void elementOrder()
    {
        for( int i = 0; i < tracers.size( ); i++ )
        {
            ITracer iTracer = tracers.get( i );
            if( iTracer instanceof AbstractTracer )
            {
                AbstractTracer tracer = ( AbstractTracer )iTracer;
                List<ITrace> traces = tracer.getTraces( );
                for( int j = 0; j < traces.size( ); j++ )
                {
                    ITrace iTrace = traces.get( j );
                    if( iTrace instanceof EventTrace )
                    {
                        EventTrace trace = ( EventTrace )iTrace;
                        //System.out.println( "elementOrder" + " SourceName " + trace.getSourceName( ) + " " + "pos " + j );
                    }
                }
            }
        }
    }
    
    protected boolean isElementExecuted( String elementName, int pos )
    {
        boolean found = false;
        for( int i = 0; i < tracers.size( ) && !found; i++ )
        {
            ITracer iTracer = tracers.get( i );
            if( iTracer instanceof AbstractTracer )
            {
                AbstractTracer tracer = ( AbstractTracer )iTracer;
                List<ITrace> traces = tracer.getTraces( );
                for( int j = 0; j < traces.size( ) && !found; j++ )
                {
                    ITrace iTrace = traces.get( j );
                    if( iTrace instanceof EventTrace )
                    {
                        EventTrace trace = ( EventTrace )iTrace;
                        //System.out.println( "isElementExecuted" + " SourceName " + trace.getSourceName( ) + " " + "pos " + pos );
                        if( trace.getSourceName( ).equalsIgnoreCase( elementName ) && j == pos-1)
                            found = true;
                    }
                }
            }
        }
        return found;
    }
    
    /**
     * Returns the number of times that a given event was traced.
     * @param sourceName Name of the source of the event.
     * @param sourceEventName Name of the event.
     * @return The number of times that the given event was traced in the interpreter's tracers.
     */
    protected int getNumberOfTimesTraced( String sourceName, String sourceEventName )
    {
        int counter = 0;
        for( ITracer iTracer : tracers )
        {
            if( iTracer instanceof AbstractTracer )
            {
                AbstractTracer tracer = ( AbstractTracer )iTracer;
                List<ITrace> traces = tracer.getTraces( );
                for( ITrace itrace : traces )
                {
                    if( itrace instanceof EventTrace )
                    {
                        EventTrace trace = ( EventTrace )itrace;
                        //System.out.println( "getNumberOfTimesTraced" + " SourceName " + trace.getSourceName( ) + " " + "sourceEventName " + trace.getSourceEventName( ) );
                        if( trace.getSourceEventName( ).equalsIgnoreCase( sourceEventName ) && trace.getSourceName( ).equalsIgnoreCase( sourceName ) )
                            counter++;
                    }
                }
            }
        }
        return counter;
    }

    @Override
    public String toString( )
    {
        String string = new String( );
        for( ITracer iTracer : tracers )
        {
            if( iTracer instanceof AbstractTracer )
            {
                AbstractTracer tracer = ( AbstractTracer )iTracer;
                List<ITrace> traces = tracer.getTraces( );
                for( ITrace itrace : traces )
                {
                    EventTrace trace = ( EventTrace )itrace;
                    string += "sourceName: " + trace.getSourceName( ) + " - " + "sourceEventName: " + trace.getSourceEventName( ) + " - " + "date: " + trace.getDate( ) + " - " + "nanoTime: " + trace.getNanoTime( ) + "\n";
                }
            }
        }
        return string;
    }

}