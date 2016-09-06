package uniandes.cumbia.bpel.elements.pick;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a Pick bpel activity
 */
public class Pick extends AbstractPick implements IPick
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Activities to be executed if the pick reacts to a message
     */
    protected List<IOnMessage> onMessages;

    /**
     * Activities to be executed if the pick reacts to an alarm
     */
    protected List<IOnAlarm> onAlarms;

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
     * Activity to be executed
     */
    private IActivity selectedActivity;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Pick( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        onAlarms = new ArrayList<IOnAlarm>( );
        onMessages = new ArrayList<IOnMessage>( );
        selectedActivity = null;
    }

    /**
     * Changes the onMessage element
     * @param onMessage The new onMessage element
     */
    public void addOnMessage( IOnMessage onMessage )
    {
        onMessages.add( onMessage );
        onMessage.setPick(this);
    }

    /**
     * Adds a new onAlarm element
     * @param onAlarm The new element to be added
     */
    public void addOnAlarm( IOnAlarm onAlarm )
    {
        //System.out.println("Pick.addOnAlarm");
        this.onAlarms.add( onAlarm ); 
        onAlarm.setPick(this);
    }

    /**
     * @return the onAlarms
     */
    public List<IOnAlarm> getOnAlarms( )
    {
        return onAlarms;
    }

    /**
     * @return the onMessages
     */
    public List<IOnMessage> getOnMessages( )
    {
        return onMessages;
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
        for( IOnMessage onMessage : onMessages )
            onMessage.stop( );
        for( IOnAlarm onAlarm : onAlarms )
            onAlarm.stop( );
        parentProcess.notifyElementTerminated( this );
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        int counter = 1;
        for( int i = 0; i < onMessages.size( ); i++ )
            counter += onMessages.get( i ).getElementCount( );
        for( int i = 0; i < onAlarms.size( ); i++ )
            counter += onAlarms.get( i ).getElementCount( );
        return counter;
    }

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {

        for( IOnMessage onMessage : onMessages )
        {
            onMessage.initialize( );
        }
        for( IOnAlarm onAlarm : onAlarms )
        {
            onAlarm.initialize( );
        }
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        activatePick( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitPick( );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IPick.TYPE_PICK;
    }

    /**
     * Executes the activity selected for execution
     */
    public void executeSelectedActivity( )
    {
        while(selectedActivity == null)
        {
            
        }
        selectedActivity.execute( );
    }

    /**
     * @param selectedActivity the selectedActivity to set
     */
    public void setSelectedActivity( IActivity selectedActivity )
    {
        synchronized( selectedActivity )
        {
            if(selectedActivity != null)
                this.selectedActivity = selectedActivity;     
        }
    }
}