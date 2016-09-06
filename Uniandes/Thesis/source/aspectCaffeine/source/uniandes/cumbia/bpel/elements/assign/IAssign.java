package uniandes.cumbia.bpel.elements.assign;

import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;


/**
 * The services provided by an assign
 */
public interface IAssign extends IActivity
{

    public static final String TYPE_ASSIGN = "Assign";
    
    /**
     * Changes the value of validate
     * @param b The new value of the validate
     */
    public void setValidate( boolean b );

    /**
     * Adds an Copy element to the assign
     * @param g
     */
    public void addCopy( ICopy g );

    /**
     * @return the validate
     */
    public boolean isValidate( );

    /**
     * @return the copies
     */
    public List<ICopy> getCopies( );
    
    
    /**
     * Changes the value of the current activity beeing executed
     */
    public void copyExecuted();
    
    /**
     * Returns the position of the next activity to execute
     * @return
     */
    public int getNextCopyToExecute();
    

    /**
     * Method called when all copies are finished executing
     */
    public void finalizedAssign( );
    
    /**
     * Re initializes the element
     */
    public void reInit();

}