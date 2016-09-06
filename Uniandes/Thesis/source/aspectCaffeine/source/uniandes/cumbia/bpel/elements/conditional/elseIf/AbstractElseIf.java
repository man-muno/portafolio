package uniandes.cumbia.bpel.elements.conditional.elseIf;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


public abstract class AbstractElseIf extends NonOpenObject implements IElseIf
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    public AbstractElseIf( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        // TODO Auto-generated constructor stub
    }




    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    
    public void stop()
    {
        
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
    public void subscribeToEventsOfNewElement( IKernelElement newGenerator )
    {
        // TODO Auto-generated method stub
        
    }
    
}
