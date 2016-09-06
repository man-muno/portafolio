package uniandes.cumbia.bpel.elements;

public interface IActivity extends IBasicElement
{

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    void setSuppressJoinFailure( boolean suppressJoinFailure );
    
    /**
     * @return the suppressJoinFailure
     */
    boolean isSuppressJoinFailure( );
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( );
    
    /**
     * Method that begins execution of the activity
     */
    public void execute();
    
    /**
     * Re initializes the element
     */
    public void reInit();
}
