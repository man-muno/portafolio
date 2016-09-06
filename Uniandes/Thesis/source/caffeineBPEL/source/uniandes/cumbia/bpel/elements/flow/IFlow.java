package uniandes.cumbia.bpel.elements.flow;

import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IStructured;


/**
 * The services provided by the Flow element
 */
public interface IFlow extends IStructured
{

    public static final String TYPE_FLOW = "Flow";
    
    /**
     * Adds an activity to be executed in the flow
     * @param activity The activity to be executed
     */
    public void addActivity( IActivity activity );
    
    /**
     * @return the activities
     */
    public List<IActivity> getActivities( );

    /**
     * Adds one to the counter of activities terminated
     */
    public void activityTerminated( );

    /**
     * Returns true if the number of activities terminated is equal to the amount of activities 
     * @return
     */
    public boolean areAllActivitiesTerminated( );

    /**
     * Indicates that the flow is terminated
     */
    public void terminated( );

    /**
     * Adds one to the counter of activities waiting
     */
    public void addActivityWaiting( );
    
    /**
     * Subtract one to the counter of activities waiting
     */
    public void subtractActivityWaiting( );
    
    /**
     * Re initializes the element
     */
    public void reInit();

}