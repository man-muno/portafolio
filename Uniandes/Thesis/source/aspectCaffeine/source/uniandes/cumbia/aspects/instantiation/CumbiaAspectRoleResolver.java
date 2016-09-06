package uniandes.cumbia.aspects.instantiation;

import java.util.LinkedList;
import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.containers.EventsAndRolesMappingInformation;
import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.instantiation.RoleResolver;

public class CumbiaAspectRoleResolver extends RoleResolver
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    public static final String ME_ROLE = "ME";
    public static final String TP_ROLE = "TP";
    public static final String ADVICE_ROLE = "ADVICE";
    public static final String INSTRUCTION_ROLE = "INSTRUCTION";
    
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Role id for ME
     */
	protected static int ME;
	
    /**
     * Role id for TP = TransitionPoint
     */
	protected static int TP;
	
    /**
     * Role id for ADVICE
     */
    protected static int ADVICE;
    
    /**
     * Role id for INSTRUCTION
     */
    protected static int INSTRUCTION;
    

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	public CumbiaAspectRoleResolver(ModelInstance modelInstance, EventsAndRolesMappingInformation mapping)
	{
		super(modelInstance, mapping);

		ME = mapping.getRoleId(ME_ROLE);
		TP = mapping.getRoleId(TP_ROLE);
		ADVICE = mapping.getRoleId(ADVICE_ROLE);
		INSTRUCTION = mapping.getRoleId(INSTRUCTION_ROLE);
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Given a baseElement, this method finds the elements that fulfill the given role.
	 * 
	 * @param baseElement The element that acts as frame of reference.
	 * @param roleId The identifier of the role that the elements should fulfill
	 * @return Returns the elements that fullfil the given role
	 */
	public List<? extends IKernelElement> resolveRole(IKernelElement baseElement, int roleId)
	{
		List<? extends IKernelElement> elements = null;

		if (IAspect.class.isInstance(baseElement))
		{
			elements = resolveRoleForAspect((IAspect) baseElement, roleId);
		}
        else if(IAdvice.class.isInstance(baseElement))
        {
            elements = resolveRoleForAdvice((IAdvice)baseElement, roleId);
        }
        else if(ITransitionPoint.class.isInstance(baseElement))
        {
            elements = resolveRoleForTransitionPoint((ITransitionPoint)baseElement, roleId);
        }
        else if(IInstruction.class.isInstance(baseElement))
        {
            elements = resolveRoleForInstruction((IInstruction)baseElement, roleId);
        }
		else
			elements = new LinkedList<IBasicElement>();

		return elements;
	}

    /**
     * Resolves a role relative to an aspect
     * 
     * @param baseElement The aspect
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForAspect(IAspect baseElement, int roleId)
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(TP == roleId)
        {
            elements.addAll(baseElement.getTransitionPoints());
        }

        return elements;
    }
	
    /**
     * Resolves a role relative to an advice
     * 
     * @param baseElement The advice
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForAdvice(IAdvice baseElement, int roleId)
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(INSTRUCTION == roleId)
        {
            elements.addAll( baseElement.getInstructions());
        }

        return elements;
    }
    
    /**
     * Resolves a role relative to a transition point
     * 
     * @param baseElement The transition point
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForTransitionPoint(ITransitionPoint baseElement, int roleId)
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(ADVICE == roleId)
        {
            elements.addAll( baseElement.getAdvices());
        }

        return elements;
    }
    
    /**
     * Resolves a role relative to an instruction
     * 
     * @param baseElement The instruction
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForInstruction(IInstruction baseElement, int roleId)
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

}
