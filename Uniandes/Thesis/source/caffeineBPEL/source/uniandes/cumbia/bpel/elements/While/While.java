package uniandes.cumbia.bpel.elements.While;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a While bpel activity
 */
public class While extends AbstractWhile
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The language to evaluate the condition
     */
    private String conditionLanguage;

    /**
     * The condition to be evaluated
     */
    private String condition;

    /**
     * Activity to be executed inside the while
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

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public While( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    /**
     * @return the conditionLanguage
     */
    public String getConditionLanguage( )
    {
        return conditionLanguage;
    }

    /**
     * @param conditionLanguage the conditionLanguage to set
     */
    public void setConditionLanguage( String conditionLanguage )
    {
        this.conditionLanguage = conditionLanguage;
    }

    /**
     * @return the condition
     */
    public String getCondition( )
    {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition( String condition )
    {
        this.condition = condition;
    }

    /**
     * @return the activity
     */
    public IActivity getActivity( )
    {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity( IActivity activity )
    {
        this.activity = activity;
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
    public void initialize( )
    {
        activity.initialize( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitWhile( );
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        startWhile( );
    }
    
    /**
     * Executes the activity once
     */
    public void executeActivity()
    {
        executeWhile();
    }
    
    /**
     * Terminates the execution of the while
     */
    public void finalizing( )
    {
        finalizingWhile();
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IWhile.TYPE_WHILE;
    }
}
