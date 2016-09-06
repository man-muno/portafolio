package uniandes.cumbia.bpel.instantiation;

import java.util.LinkedList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.While.IWhile;
import uniandes.cumbia.bpel.elements.assign.IAssign;
import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.empty.IEmpty;
import uniandes.cumbia.bpel.elements.exit.IExit;
import uniandes.cumbia.bpel.elements.flow.IFlow;
import uniandes.cumbia.bpel.elements.invoke.IInvoke;
import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.receive.IReceive;
import uniandes.cumbia.bpel.elements.reply.IReply;
import uniandes.cumbia.bpel.elements.sequence.ISequence;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPick;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingReceive;
import uniandes.cumbia.bpel.elements.wait.IWait;
import uniandes.cumbia.openobjects.containers.EventsAndRolesMappingInformation;
import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.instantiation.RoleResolver;

public class BpelRoleResolver extends RoleResolver
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    public static final String ME_ROLE = "ME";
    public static final String SP_ROLE = "SP";
    public static final String ONALARM_ROLE = "onAlarm";
    public static final String ONMESSAGE_ROLE = "onMessage";
    public static final String ACT_ROLE = "ACT";
    public static final String INNER_ROLE  = "INNER";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Role id for ME
     */
	protected static int ME;
	
    /**
     * Role id for SP = StartingPoints
     */
	protected static int SP;
    
    /**
     * Role id for ACT = ACTIVITY
     */
	protected static int ACT;
    
    /**
     * Role id for ONALARM
     */
	protected static int ONALARM;
    
    /**
     * Role id for ONMESSAGE
     */
	protected static int ONMESSAGE;
    
    /**
     * Role id for INNER
     */
	protected static int INNER;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------

	public BpelRoleResolver(ModelInstance modelInstance, EventsAndRolesMappingInformation mapping)
	{
		super(modelInstance, mapping);

		ME = mapping.getRoleId(ME_ROLE);
		SP = mapping.getRoleId(SP_ROLE);
		ACT = mapping.getRoleId(ACT_ROLE);
		ONALARM = mapping.getRoleId(ONALARM_ROLE);
		ONMESSAGE = mapping.getRoleId(ONMESSAGE_ROLE);
		INNER = mapping.getRoleId(INNER_ROLE);
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

		if (IProcess.class.isInstance(baseElement))
		{
			elements = resolveRoleForProcess((IProcess) baseElement, roleId);
		}
        else if(ISequence.class.isInstance(baseElement))
        {
            elements = resolveRoleForSequence((ISequence)baseElement, roleId);
        }
        else if(IReceive.class.isInstance(baseElement))
        {
            elements = resolveRoleForReceive((IReceive)baseElement, roleId);
        }
        else if(IReply.class.isInstance(baseElement))
        {
            elements = resolveRoleForReply((IReply)baseElement, roleId);
        }
        else if(IAssign.class.isInstance(baseElement))
        {
            elements = resolveRoleForAssign((IAssign)baseElement, roleId);
        }
        else if(IStartingReceive.class.isInstance(baseElement))
        {
            elements = resolveRoleForStartingReceive((IStartingReceive)baseElement, roleId);
        }
        else if(IStartingPick.class.isInstance(baseElement))
        {
            elements = resolveRoleForStartingPick((IStartingPick)baseElement, roleId);
        }
        else if(IPick.class.isInstance(baseElement))
        {
            elements = resolveRoleForPick((IPick)baseElement, roleId);
        }
        else if(IOnAlarm.class.isInstance(baseElement))
        {
            elements = resolveRoleForOnAlarm((IOnAlarm)baseElement, roleId);
        }
        else if(IOnMessage.class.isInstance(baseElement))
        {
            elements = resolveRoleForOnMessage((IOnMessage)baseElement, roleId);
        }
        else if(IInvoke.class.isInstance(baseElement))
        {
            elements = resolveRoleForInvoke((IInvoke)baseElement, roleId);
        }
        else if(IFlow.class.isInstance(baseElement))
        {
            elements = resolveRoleForFlow((IFlow)baseElement, roleId);
        }
        else if(IWhile.class.isInstance(baseElement))
        {
            elements = resolveRoleForWhile((IWhile)baseElement, roleId);
        }
        else if(IConditional.class.isInstance(baseElement))
        {
            elements = resolveRoleForConditional((IConditional)baseElement, roleId);
        }
        else if(IExit.class.isInstance(baseElement))
        {
            elements = resolveRoleForExit((IExit)baseElement, roleId);
        }
        else if(IEmpty.class.isInstance(baseElement))
        {
            elements = resolveRoleForEmpty((IEmpty)baseElement, roleId);
        }
        else if(IWait.class.isInstance(baseElement))
        {
            elements = resolveRoleForWait((IWait)baseElement, roleId);
        }
		else
			elements = new LinkedList<IBasicElement>();

		return elements;
	}

    /**
     * Resolves a role relative to a Wait
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
	private List<? extends IKernelElement> resolveRoleForWait( IWait baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Empty
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForEmpty( IEmpty baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Exit
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForExit( IExit baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Conditional
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForConditional( IConditional baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(ACT == roleId)
        {
            elements.add(baseElement.getIf( ).getActivity( ));
            List<IElseIf> elseIfs = baseElement.getElseIfs( );
            for(IElseIf elseIf : elseIfs)
            {
                elements.add(elseIf.getActivity( ));    
            }
            if(baseElement.getElse( ) != null)
                elements.add( baseElement.getElse( ).getActivity( ) );
        }

        return elements;
    }

    /**
     * Resolves a role relative to a While
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForWhile( IWhile baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(ACT == roleId)
        {
            elements.add(baseElement.getActivity( ));
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Flow
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForFlow( IFlow baseElement, int roleId )
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(INNER == roleId)
        {
            elements.addAll(baseElement.getActivities( ));
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Invoke
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForInvoke( IInvoke baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a OnMessage
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForOnMessage( IOnMessage baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a OnAlarm
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForOnAlarm( IOnAlarm baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
     * Resolves a role relative to a Pick
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    private List<? extends IKernelElement> resolveRoleForPick( IPick baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(ONALARM == roleId)
        {
            elements.addAll( baseElement.getOnAlarms( ));
        }
        else if(ONMESSAGE == roleId)
        {
            elements.addAll( baseElement.getOnMessages( ));
        }
        else if(ACT == roleId)
        {
            List<IOnMessage> onMessages = baseElement.getOnMessages( );
            for(IOnMessage onMessage : onMessages)
                elements.add( onMessage.getActivity( ) );
            
            List<IOnAlarm> onAlarms = baseElement.getOnAlarms( );
            for(IOnAlarm onAlarm : onAlarms)
                elements.add( onAlarm.getActivity( ) );            
        }

        return elements;
    }

    /**
	 * Resolves the roles for the StartingPick activity
	 * @param baseElement
	 * @param roleId
	 * @return
	 */
	private List<? extends IKernelElement> resolveRoleForStartingPick( IStartingPick baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(ACT == roleId)
        {
            elements.add( baseElement.getActivity( ));
        }

        return elements;
    }

	/**
	 * Resolves the roles for the StartingReceive activity
	 * @param baseElement
	 * @param roleId
	 * @return
	 */
    private List<? extends IKernelElement> resolveRoleForStartingReceive( IStartingReceive baseElement, int roleId )
    {
        List<IKernelElement> elements = new LinkedList<IKernelElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }

    /**
	 * Resolves a role relative to a process
	 * 
	 * @param baseElement The process
	 * @param roleId The identifier of the role
	 * @return
	 */
	protected List<? extends IKernelElement> resolveRoleForProcess(IProcess baseElement, int roleId)
	{
		List<IKernelElement> elements = new LinkedList<IKernelElement>();

		if (ME == roleId)
		{
			elements.add(baseElement);
		}
        else if(SP == roleId)
        {
            elements.addAll( baseElement.getStartingPoints());
        }
        else if(ACT == roleId)
        {
            elements.addAll( baseElement.getProcessElements( ));
        }

		return elements;
	}
	
	/**
     * Resolves a role relative to a sequence
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForSequence(ISequence baseElement, int roleId)
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }
        else if(INNER == roleId)
        {
            elements.addAll(baseElement.getActivities( ));
        }

        return elements;
    }
    
    /**
     * Resolves a role relative to a receive
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForReceive(IReceive baseElement, int roleId)
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }
    
    /**
     * Resolves a role relative to a assign
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForAssign(IAssign baseElement, int roleId)
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }
    
    /**
     * Resolves a role relative to a Reply
     * 
     * @param baseElement The process
     * @param roleId The identifier of the role
     * @return
     */
    protected List<? extends IKernelElement> resolveRoleForReply(IReply baseElement, int roleId)
    {
        List<IBasicElement> elements = new LinkedList<IBasicElement>();

        if (ME == roleId)
        {
            elements.add(baseElement);
        }

        return elements;
    }
}
