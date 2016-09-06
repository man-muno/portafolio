package uniandes.cumbia.bpel.elements;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractChannel extends NonOpenObject implements IChannel
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    
    public AbstractChannel( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------


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
