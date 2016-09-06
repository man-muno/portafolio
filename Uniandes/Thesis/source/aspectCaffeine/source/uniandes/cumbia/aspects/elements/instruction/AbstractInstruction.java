package uniandes.cumbia.aspects.elements.instruction;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractInstruction extends OpenObject implements IInstruction
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String PREPARE = "prepare";
    
    public static final String EXECUTE = "execute";
    
    public static final String FINALIZED = "finalized";
    
    public static final String REINIT = "reinit";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------


    /**
     * Generator of the event start
     */
    private EventGenerator generatorPrepare;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorExecute;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorReInit;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorFinalized;

    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    /**
     * 
     */
    public AbstractInstruction( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractInstruction( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_INSTRUCTION);
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
        
        generatorReInit = createAndRegisterEventGenerator(REINIT);
        generatorFinalized = createAndRegisterEventGenerator(FINALIZED);
        generatorPrepare = createAndRegisterEventGenerator(PREPARE);
        generatorExecute = createAndRegisterEventGenerator(EXECUTE);
    }

    protected void sendReInitEvent()
    {
        generatorReInit.generate( );
    }
    
    protected void sendFinalizedEvent()
    {
        generatorFinalized.generate( );
    }

    protected void sendPrepareEvent()
    {
        generatorPrepare.generate( );
    }

    protected void sendExecuteEvent()
    {
        generatorExecute.generate( );
    }
}
