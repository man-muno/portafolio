package uniandes.cumbia.bpel.elements.While;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractWhile extends OpenObject implements IWhile
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String START_WHILE = "start";

    private static final String EXECUTING_WHILE = "executing";

    private static final String FINALIZING_WHILE = "finalizing";
    
    private static final String REINIT = "reInit";


    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Generator of the event initialize
     */
    private EventGenerator generatorStart;

    /**
     * Generator of the event activate
     */
    private EventGenerator generatorExecuting;

    /**
     * Generator of the event finalize
     */
    private EventGenerator generatorFinalizing;
    
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
    public AbstractWhile( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    /**
     * 
     */
    public AbstractWhile( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_WHILE );
    }

    /**
     * This method registers in the EventsManager of the element, all the events that are generated by this entity.<br>
     * This method also creates the generators for the entity's events
     */
    public void registerGeneratedEvents( )
    {
        generatorExecuting = createAndRegisterEventGenerator( EXECUTING_WHILE);
        generatorFinalizing = createAndRegisterEventGenerator( FINALIZING_WHILE );
        generatorStart = createAndRegisterEventGenerator( START_WHILE );
        generatorReInit = createAndRegisterEventGenerator( REINIT );
    }
    
    /**
     * Fires the reInit event
     */
    protected void reInitWhile()
    {
        generatorReInit.generate( );
    }
    
    /**
     * Fires the start event
     */
    protected void startWhile()
    {
        generatorStart.generate( );
    }
    
    /**
     * Fires the executing event
     */
    protected void executeWhile()
    {
        generatorExecuting.generate( );
    }
    
    /**
     * Fires the finalizing event
     */
    protected void finalizingWhile()
    {
        generatorFinalizing.generate( );
    }
}
