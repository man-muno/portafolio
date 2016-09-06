package uniandes.cumbia.bpel.elements.receive;

import uniandes.cumbia.bpel.elements.Interaction;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractReceive extends Interaction implements IReceive
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ACTIVATE_RECEIVE = "activate";

    private static final String MESSAGE_RECEIVED_RECEIVE = "messageReceived";
    
    private static final String REINIT = "reInit";


    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Generator of the event activate
     */
    private EventGenerator generatorActivate;

    /**
     * Generator of the event finalize
     */
    private EventGenerator generatorMessageReceived;
    
    /**
     * Generator of the event reInit
     */
    private EventGenerator generatorReInit;


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * 
     */
    public AbstractReceive( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    /**
     * 
     */
    public AbstractReceive( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_RECEIVE );
    }

    /**
     * This method registers in the EventsManager of the element, all the events that are generated by this entity.<br>
     * This method also creates the generators for the entity's events
     */
    public void registerGeneratedEvents( )
    {
        generatorActivate = createAndRegisterEventGenerator( ACTIVATE_RECEIVE );
        generatorMessageReceived = createAndRegisterEventGenerator( MESSAGE_RECEIVED_RECEIVE );
        generatorReInit = createAndRegisterEventGenerator( REINIT );
    }

    /**
     * Fires the reInit event
     */
    protected void reInitReceive()
    {
        generatorReInit.generate( );
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1;
    }

    /**
     * Generates the activated event
     */
    protected void activateReceive( )
    {
        generatorActivate.generate( );
    }

    /**
     * Generates the message received event
     */
    protected void messageReceived()
    {
        generatorMessageReceived.generate( );
    }
}