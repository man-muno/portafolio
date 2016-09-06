package uniandes.cumbia.bpel.engine.utils.xpath;

import uniandes.cumbia.bpel.elements.process.IProcess;

public class BPELContext 
{
    // -----------------------------------------------------------------
    // Constanst
    // -----------------------------------------------------------------
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	/**
	 * The memory of the element
	 */
	private IProcess process;
	

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
	/**
	 * Constructor of the context
	 * @param em The element memory
	 * @param pu The process utils
	 */
	public BPELContext(IProcess process)
	{
		this.process= process;
	}
	
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	/**
	 * Returns the element memory
	 * @return elementMemory
	 */
	public IProcess getElement() 
	{
		return process;
	}
}
