package uniandes.cumbia.bpel.elements.pick;

import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IStructured;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;

public interface IPick extends IStructured
{

    public static final String TYPE_PICK = "Pick";
    
    /**
     * Changes the onMessage element
     * @param element The new onMessage element
     */
    public void addOnMessage( IOnMessage element );
    
    /**
     * Adds a new onAlarm element
     * @param element The new element to be added
     */
    public void addOnAlarm( IOnAlarm element );

    /**
     * @return the onAlarms
     */
    public List<IOnAlarm> getOnAlarms( );

    /**
     * @return the onMessages
     */
    public List<IOnMessage> getOnMessages( );
    
    /**
     * @param selectedActivity the selectedActivity to set
     */
    public void setSelectedActivity( IActivity selectedActivity );

    /**
     * Executes the activity selected for execution
     */
    public void executeSelectedActivity( );
    
    /**
     * Re initializes the element
     */
    public void reInit();
}
