package uniandes.cumbia.bpel.elements.sequence;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


public abstract class AbstractSequence extends OpenObject implements ISequence
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String START_SEQUENCE = "start";
    
    private static final String FINALIZING_SEQUENCE = "finalizing";
    
    private static final String REINIT = "reInit";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Generator of the event initialize
     */
    private EventGenerator generatorStart;
    
    /**
     * Generator of the event finalized
     */
    private EventGenerator generatorFinalizing;

    /**
     * Generator of the event reInit
     */
    private EventGenerator generatorReInit;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
    /**
     * 
     */
    public AbstractSequence( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractSequence( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_SEQUENCE );
    }
    
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * This method registers in the EventsManager of the element, all the events that are generated by this entity.<br>
     * This method also creates the generators for the entity's events
     */
    public void registerGeneratedEvents()
    {
        generatorFinalizing = createAndRegisterEventGenerator(FINALIZING_SEQUENCE);
        generatorStart = createAndRegisterEventGenerator(START_SEQUENCE);
        generatorReInit = createAndRegisterEventGenerator( REINIT );    
    }

    /**
     * Fires the reInit event
     */
    protected void reInitSequence()
    {
        generatorReInit.generate( );
    }
    
    /**
     * Fires the finalizing event
     */
    protected void sequenceFinalized( )
    {
        generatorFinalizing.generate( );
    }

    /**
     * Fires the start event
     */
    protected void startSequence( )
    {
       generatorStart.generate( );
    }
}