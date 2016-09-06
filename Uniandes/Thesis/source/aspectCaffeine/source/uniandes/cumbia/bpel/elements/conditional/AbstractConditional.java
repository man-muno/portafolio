package uniandes.cumbia.bpel.elements.conditional;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class AbstractConditional extends OpenObject implements IConditional
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String START = "start";
    
    public static final String CHECK_ELSE_IF = "checkElseIf";
    
    public static final String CHECK_NEXT_ELSE_IF = "checkNextElseIf";
    
    public static final String EXECUTE_IF = "executeIf";
    
    public static final String EXECUTE_ELSE_IF = "executeElseIf";
    
    public static final String CHECK_ELSE = "checkElse";
    
    public static final String EXECUTE_ELSE = "executeElse";
    
    public static final String FINALIZING = "finalizing";
    
    private static final String REINIT = "reInit";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorStart;   
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorCheckElseIf;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorCheckNextElseIf;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorExecuteIf;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorExecuteElseIf;
    
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorCheckElse;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorExecuteElse;
    
    /**
     * Generator of the event start
     */
    private EventGenerator generatorFinalizing;

    /**
     * Generator of the event reInit
     */
    private EventGenerator generatorReInit;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    
    /**
     * 
     */
    public AbstractConditional( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractConditional( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_CONDITIONAL);
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
        generatorStart = createAndRegisterEventGenerator(START);   
        generatorCheckElseIf = createAndRegisterEventGenerator(CHECK_ELSE_IF);
        generatorCheckNextElseIf = createAndRegisterEventGenerator(CHECK_NEXT_ELSE_IF);
        generatorExecuteIf = createAndRegisterEventGenerator(EXECUTE_IF);
        generatorExecuteElseIf = createAndRegisterEventGenerator(EXECUTE_ELSE_IF);
        generatorCheckElse = createAndRegisterEventGenerator(CHECK_ELSE);
        generatorExecuteElse = createAndRegisterEventGenerator(EXECUTE_ELSE);
        generatorFinalizing = createAndRegisterEventGenerator(FINALIZING);
        generatorReInit = createAndRegisterEventGenerator( REINIT );
    }
    
    /**
     * Fires the start event
     */
    protected void startConditional()
    {
        generatorStart.generate( );
    }
    
    /**
     * Fires the reInit event
     */
    protected void reInitConditional()
    {
        generatorReInit.generate( );
    }
    
    /**
     * fires the executeIf event
     */
    protected void executeIf( )
    {
        generatorExecuteIf.generate( );
    }

    /**
     * Fires the checkElseIf event
     */
    protected void evaluateElseIF( )
    {
        generatorCheckElseIf.generate( );
    }
    
    /**
     * Fires the executeElseIf event
     */
    protected void executeElseIf( )
    {
        generatorExecuteElseIf.generate( );
    }

    /**
     * Fires the checkNextElseIf event
     */
    protected void checkNextElseIf( )
    {
        generatorCheckNextElseIf.generate( );
    }

    /**
     * Fires the checkElse event
     */
    protected void checkElse( )
    {
        generatorCheckElse.generate( );
    }

    /**
     * Fires the executeElse event
     */
    protected void executeElse( )
    {
        generatorExecuteElse.generate( );
    }

    /**
     * Fires the finalizing event
     */
    protected void finalizing( )
    {
        generatorFinalizing.generate( );
    }
}