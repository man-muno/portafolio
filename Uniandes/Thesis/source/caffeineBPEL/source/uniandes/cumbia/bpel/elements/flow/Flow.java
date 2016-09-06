package uniandes.cumbia.bpel.elements.flow;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a Flow bpel activity
 */
public class Flow extends AbstractFlow
{

    /**
     * List of all the links names
     */
    private List<String> links;

    /**
     * List of the flow elements
     */
    protected List<IActivity> activities;

    /**
     * The parent process
     */
    private IProcess parentProcess;

    /**
     * Determines whether the joinFailure fault will be suppressed for all elements in the process. The effect of the attribute at the process level can be overridden by an
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
     * The counter of activities terminated
     */
    private int activitiesTerminated;

    /**
     * The counter of activities waiting
     */
    private int activitiesWaiting;


    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Creates the new flow
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type
     */
    public Flow( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        activities = new ArrayList<IActivity>( );
        activitiesTerminated = 0;
        activitiesWaiting = 0;
    }

    /**
     * Adds an activity to be executed in the flow
     * @param activity The activity to be executed
     */
    public void addActivity( IActivity activity )
    {
        activities.add( activity );
    }

    /**
     * @return the elements
     */
    public List<IActivity> getActivities( )
    {
        return activities;
    }
    
    /**
     * Adds one to the counter of activities terminated
     */
    public synchronized void activityTerminated( )
    {
        activitiesTerminated++;
    }
    
    /**
     * Returns true if the number of activities terminated is equal to the amount of activities 
     * @return
     */
    public synchronized boolean areAllActivitiesTerminated( )
    {
        return activitiesTerminated == activities.size( ) ? true : false;
    }
    
    /**
     * Adds one to the counter of activities waiting
     */
    public synchronized void addActivityWaiting( )
    {
        activitiesWaiting++;
    }
    
    /**
     * Subtract one to the counter of activities waiting
     */
    public synchronized void subtractActivityWaiting( )
    {
        activitiesWaiting--;
        if(activitiesWaiting == 0)
            super.backToExecute();
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        int counter = 1;
        for( int i = 0; i < activities.size( ); i++ )
            counter += activities.get( i ).getElementCount( );
        return counter;
    }

    /**
     * Stops the process
     */
    public void stop( )
    {
        for( int i = 0; i < activities.size( ); i++ )
        {
            IActivity element = activities.get( i );
            element.stop( );
        }

        parentProcess.notifyElementTerminated( this );
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
        for( int i = 0; i < activities.size( ); i++ )
        {
            IActivity element = activities.get( i );
            element.initialize( );
            //System.out.println( "initializing " + element.getName( ) );
        }
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        startFlow( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitFlow( );
    }
    
    /**
     * Indicates that the flow is terminated
     */
    public void terminated( )
    {
        finalizeFlow( );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IFlow.TYPE_FLOW;
    }
}