package uniandes.cumbia.bpel.elements;

import java.io.Serializable;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.elements.IKernelElement;

public interface IBasicElement extends IKernelElement, Serializable
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
   
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    
    /**
     * Changes the name of the element
     */
    void setName( String name );
    
    /**
     * @return the name of the element
     */
    String getName( );

	/**
	 * Initialize the element
	 */
	void initialize();
	
    /**
     * Stops the element
     */
    void stop( );
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    int getElementCount( );
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( );
    
    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess );
    
    /**
     * Returns the name type of actual element 
     * @return String different from null
     */
    public String getElementType();
}