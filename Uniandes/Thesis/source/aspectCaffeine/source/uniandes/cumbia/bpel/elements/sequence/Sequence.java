package uniandes.cumbia.bpel.elements.sequence;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


/**
 * Represents a Sequence bpel activity
 */
public class Sequence extends AbstractSequence
{

    public final static String ROLE_ME = "ME";

    public final static String ELEMENT_FINALIZED_EVENT = "finalized";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * List of the sequence activities
     */
    protected List<IActivity> activities;
    
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

    private int activityIndex;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    
    public Sequence(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
        activityIndex = 0;
        activities = new ArrayList<IActivity>();
    }
    
    /**
     * Adds a new activity to the sequence
     * @param element The new activity to be added
     */
    public void addActivity( IActivity activity )
    {
        //System.out.println("Element Added to sequence " + activity.getName( ) + " " + activity);
        activities.add(activity);
    }

    /**
     * @return the activities
     */
    public List<IActivity> getActivities()
    {
        return activities;
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
     * Stops the exit
     */
    public void stop( )
    {
        for(IActivity element: activities)
            element.stop( );
        parentProcess.notifyElementTerminated( this );
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        int counter = 1;
        for(int i=0;i<activities.size( );i++)
            counter+=activities.get( i ).getElementCount( );
        return counter;
    }
    
    /**
     * Executes the next activity. If there are no more activities to execute, then the sequence fires the finalized event
     */
    public void executeNextActivity( )
    {
        if(activityIndex < activities.size( ))
        {
            activities.get( activityIndex ).execute( );
            activityIndex++;
        }
        else
            finalizedSequence();
    }
    
    
    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        
    }

    
    /**
     * Method that begins execution of the activity
     */
    public void execute()
    {
        startSequence();
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitSequence( );
    }
    
    /**
     * Method called when all activities are finished executing
     */
    public void finalizedSequence( )
    {
        activityIndex = 0;
        sequenceFinalized();
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return ISequence.TYPE_SEQUENCE;
    }
}
