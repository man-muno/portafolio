package uniandes.cumbia.aspects.elements;

import java.io.Serializable;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
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
    public void setName( String name );
    
    /**
     * @return the name of the element
     */
    public String getName( );

	/**
	 * Initialize the element
	 */
    public void initialize();
	
    /**
     * Stops the element
     */
	public void stop( );
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IAspect getParentAspect( );
    
    /**
     * Sets the parent process for this element
     * @param parentAspect Parent process
     */
    public void setParentAspect( IAspect parentAspect );
    
    /**
     * Method that begins execution of the element
     */
    public void execute();
}