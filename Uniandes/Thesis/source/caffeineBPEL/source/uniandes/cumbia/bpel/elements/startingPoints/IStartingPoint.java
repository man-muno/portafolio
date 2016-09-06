package uniandes.cumbia.bpel.elements.startingPoints;

import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;


public interface IStartingPoint extends IInteraction
{

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * 
     * @return Parent process of this element
     */
    IProcess getParentProcess();

    /**
     * Sets the parent process for this element
     * 
     * @param parentProcess Parent process
     */
    void setParentProcess(IProcess parentProcess);
    
}
