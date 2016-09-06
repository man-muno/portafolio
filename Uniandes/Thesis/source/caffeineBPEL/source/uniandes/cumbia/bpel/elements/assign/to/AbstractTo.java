package uniandes.cumbia.bpel.elements.assign.to;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractTo extends NonOpenObject implements ITo
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * 
     */
    public AbstractTo( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractTo( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_TO );
    }
    
    /**
     * Stops the element
     */
    public void stop( )
    {
        // Does nothing
    }

    public void initialize()
    {
        // No events associated with this element
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 0;
    }
    


    @Override
    public IEventListener getEventQueue( )
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void startProcessingEvents( )
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void subscribeToEvents( )
    {
        // TODO Auto-generated method stub
        
    }


    @Override
    public void subscribeToEventsOfNewElement( IKernelElement arg0 )
    {
        // TODO Auto-generated method stub
        
    }
}
