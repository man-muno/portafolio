package uniandes.cumbia.bpel.elements.pick.onalarm;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


public abstract class AbstractOnAlarm extends OpenObject implements IOnAlarm
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String START_ON_ALARM = "start";

    private static final String COUNTER_STOPED_ON_ALARM = "counterStoped";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Generator of the event initialize
     */
    private EventGenerator generatorStart;
    
    /**
     * Generator of the event finalize
     */
    private EventGenerator generatorCounterStoped;
    
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    

    /**
     * 
     */
    public AbstractOnAlarm( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }
    
    /**
     * 
     */
    public AbstractOnAlarm( ModelInstance modelInstance, String elementName )
    {
        super( modelInstance, elementName, TYPE_ON_ALARM);
    }
    
    
    /**
     * This method registers in the EventsManager of the element, all the events that are generated by this entity.<br>
     * This method also creates the generators for the entity's events
     */
    public void registerGeneratedEvents()
    {
        generatorCounterStoped = createAndRegisterEventGenerator(COUNTER_STOPED_ON_ALARM);
        generatorStart = createAndRegisterEventGenerator(START_ON_ALARM);
    }
    
    /**
     * Fires the start event
     */
    protected void startOnAlarm()
    {
        generatorStart.generate( );
    }
    
    /**
     * Fires the counterStoped event
     */
    protected void counterStoped()
    {
        generatorCounterStoped.generate( );
    }
}
