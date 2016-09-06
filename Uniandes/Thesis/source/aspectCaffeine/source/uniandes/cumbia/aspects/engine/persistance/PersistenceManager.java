package uniandes.cumbia.aspects.engine.persistance;

import java.io.File;
import java.util.List;

import uniandes.cumbia.caffeine.deployer.deposit.BPELProcessInfo;
import uniandes.cumbia.deposit.interfaces.IDeposit;

public class PersistenceManager implements IPersistenceManager 
{
	// -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
	private final static String BPEL_TYPE= "bpel";
	
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
	
	/**
	 * Reference to the deposit
	 */
	private IDeposit deposit;

	
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
	
	public PersistenceManager(IDeposit dep)
	{
		deposit= dep;
	}
	
	
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
	/**
	 * Returns the process stored in the cumbia deposit 
	 */
	public List getStoredProcesses()
	{
		return deposit.getStoredElements(BPEL_TYPE);
	}
	
	
	/**
	 * Return the information associated with the process specified 
	 * @param name The name of the process
	 * @return The process info
	 */
	public BPELProcessInfo getProcessInfo(String name)
	{
		return (BPELProcessInfo) deposit.getStoredElement(name, "bpel"); 
	}
	
    /**
     * Stores the bpel definition in the deposit
     * @param bpelInformation The bpel information of the process
     */
    public void storeBPELDefinition( File bpelInformation )
    {
        deposit.storeElement( bpelInformation );
    }
}
