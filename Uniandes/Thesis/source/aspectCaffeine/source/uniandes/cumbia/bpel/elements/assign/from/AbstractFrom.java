package uniandes.cumbia.bpel.elements.assign.from;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractFrom extends NonOpenObject implements IFrom
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
    public AbstractFrom( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractFrom( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_FROM );
    }
    
    /**
     * Stops the element
     */
    public void stop( )
    {
        //Does nothing
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 0;
    }
    

    public void initialize( )
    {
        // TODO Auto-generated method stub
        
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
