package uniandes.cumbia.bpel.elements.pick.onalarm;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;



/**
 * Corresponds to a timer-based alarm
 */
public class OnAlarm extends AbstractOnAlarm
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_FOR = "for";
    
    public static final String TYPE_UNTIL = "until";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The type of onAlarm. It can be or a For or an Until onMessage
     */
    private String type;
    
    /**
     * Expression language for the for clause
     */
    private String expressionLanguage;
    
    /**
     * The time expression
     */
    private String expression;
    
    /**
     * The activity to be executed 
     */
    protected IActivity activity;
    
    /**
     * The parent process
     */
    private IProcess parentProcess;
    
    /**
     * Determines whether the joinFailure fault will be suppressed for all activities in the process. The effect of the attribute at the process level can be overridden by an
     * activity using a different value for the attribute. The default for this attribute is "no" at the process level.
     */
    protected boolean suppressJoinFailure = false;
    
    /**
     * Name of the element 
     */
    protected String name;
    
    /**
     * The element does'nt have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;
    
    /**
     * The pick that this on message belongs to
     */
    private IPick pick;
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    public OnAlarm(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
    }
    
    /**
     * Changes the type of the onAlarm
     * @param typeUntil The new type of the onAlarm
     */
    public void setType( String type )
    {
        this.type = type;
    }
    
    /**
     * Changes the activity to be executed when the onAlarm ends 
     * @param element The new element
     */
    public void setActivity( IActivity activity )
    {
        this.activity = activity;
    }
    
    /**
     * Changes the expression language of the element
     * @param expressionLanguage The new expression language of the element
     */
    public void setExpressionLanguage( String expressionLanguage )
    {
        this.expressionLanguage = expressionLanguage;
    }

    /**
     * @return the expression
     */
    public String getExpression( )
    {
        return expression;
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression( String expression )
    {
        this.expression = expression;
    }

    /**
     * @return the type
     */
    public String getType( )
    {
        return type;
    }

    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( )
    {
        return expressionLanguage;
    }

    /**
     * @return the activity
     */
    public IActivity getActivity( )
    {
        return activity;
    }

    /**
     * Stops the exit
     */
    public void stop( )
    {
        activity.stop( );
        parentProcess.notifyElementTerminated( this );
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1 + activity.getElementCount( );
    }
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }
    
    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
    }

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Sets the pick to the on message
     * @param pick
     */
    public void setPick( IPick pick )
    {
        this.pick = pick;
    }
    
    /**
     * Returns the pick owner of the element
     */
    public IPick getPick()
    {
        return pick;
    }
    
    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return useProcessSuppress ? parentProcess.isSuppressJoinFailure( ) : suppressJoinFailure;
    }
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        useProcessSuppress = false;
    }
    
    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize(  )
    {
        activity.initialize( );
    }

    
    /**
     * Method that begins execution of the activity
     */
    public void execute()
    {
        //System.out.println("Executing on alarm " + this);
        startOnAlarm( );
    }
    
    /**
     * Tells the on alarm that the counter stoped
     */
    public void stopedCounting( )
    {
        counterStoped();
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IOnAlarm.TYPE_ON_ALARM;
    }

    /**
     * Re initializes the element
     */
    public void reInit()
    {
        //Does nothing
    }
}
