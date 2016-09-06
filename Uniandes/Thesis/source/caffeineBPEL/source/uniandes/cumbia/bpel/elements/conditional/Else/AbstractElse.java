package uniandes.cumbia.bpel.elements.conditional.Else;

import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NonOpenObject;
import uniandes.cumbia.openobjects.execution.events.IEventListener;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractElse extends NonOpenObject implements IElse
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


    public AbstractElse( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    

    public AbstractElse( ModelInstance modelInstance, String elementName)
    {
        super( modelInstance, elementName, TYPE_ELSE);
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
