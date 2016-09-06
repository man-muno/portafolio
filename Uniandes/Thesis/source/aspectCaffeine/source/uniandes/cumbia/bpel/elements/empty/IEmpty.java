package uniandes.cumbia.bpel.elements.empty;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IEmpty extends IActivity
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_EMPTY = "Empty";
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Re initializes the element
     */
    public void reInit();
    
}