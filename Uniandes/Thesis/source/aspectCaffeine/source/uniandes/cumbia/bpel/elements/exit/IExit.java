package uniandes.cumbia.bpel.elements.exit;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IExit extends IActivity
{

    // -----------------------------------------------------------------
    // Contstants
    // -----------------------------------------------------------------
    
    public static final String TYPE_EXIT = "Exit";
    

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Re initializes the element
     */
    public void reInit();
}