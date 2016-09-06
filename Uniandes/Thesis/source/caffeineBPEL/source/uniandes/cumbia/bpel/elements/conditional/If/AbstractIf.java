package uniandes.cumbia.bpel.elements.conditional.If;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


public abstract class AbstractIf extends NonOpenObject implements IIf
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
 
 
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    
    /**
     * 
     */
    public AbstractIf( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractIf( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_IF);
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
