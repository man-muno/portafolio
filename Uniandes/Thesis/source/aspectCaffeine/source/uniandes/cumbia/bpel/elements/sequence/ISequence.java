package uniandes.cumbia.bpel.elements.sequence;

import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IStructured;


public interface ISequence extends IStructured
{

    public static final String TYPE_SEQUENCE = "Sequence";
    
    /**
     * Adds a new activity to the sequence
     * @param element The new activity to be added
     */
    public void addActivity( IActivity activity );
    
    /**
     * @return the activities
     */
    public List<IActivity> getActivities( );

    /**
     * Method called when all activities are finished executing
     */
    public void finalizedSequence( );

    /**
     * Executes the next activity. If there are no more activities to execute, then the sequence fires the finalized event
     */
    public void executeNextActivity( );
    

    /**
     * Re initializes the element
     */
    public void reInit();

}
