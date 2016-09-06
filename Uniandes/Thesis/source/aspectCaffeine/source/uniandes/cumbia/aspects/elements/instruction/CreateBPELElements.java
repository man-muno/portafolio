package uniandes.cumbia.aspects.elements.instruction;

import java.io.File;
import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.listener.FinalizedProcessListener;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.process.actions.Execute;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.EventsAndRolesMappingInformation;
import uniandes.cumbia.openobjects.containers.statemachine.ActionInformation;
import uniandes.cumbia.openobjects.containers.statemachine.StateMachineInformation;
import uniandes.cumbia.openobjects.containers.statemachine.TransitionInformation;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventInformation;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.statemachine.IStateMachine;
import uniandes.cumbia.openobjects.statemachine.State;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class CreateBPELElements extends AbstractInstruction implements IInstruction
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent aspect
     */
    private IAspect parentAspect;
    private File adviceFile;
    private IProcessEngine bpelEngine;
    private IProcess adviceProcess;


    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    /**
     * 
     */
    public CreateBPELElements( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns the parent aspect of this element.
     * @return Parent aspect of this element
     */
    public IAspect getParentAspect( )
    {
        return parentAspect;
    }

    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentAspect( IAspect parentAspect )
    {
        this.parentAspect = parentAspect;
    }
    
    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return elementName;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.elementName = name;
    }

    /**
     * Initializes the transition point
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        super.sendPrepareEvent( );
    }
    
    public void prepareInstruction()
    {
        System.out.println("Preparing instruction");
        try
        {
            createNewTransitionForProcess();
            ProcessSpace space = bpelEngine.createProcess( adviceFile, ClassLoader.getSystemClassLoader( ) );
            
            //Al proceso creado es necesario crear una transicion entre waiting
            //y el estado executing, igual a la existente, pero que se active
            //con un evento lanzado por el proceso.
            
            
            InstanceSpace instance = space.createInstance( );
            adviceProcess = (IProcess)instance.getInstance( ).getRoot( );
            IBasicElement aspectedElement = parentAspect.getAspectedElement( );
            IProcess aspectedProcess = aspectedElement.getParentProcess();
           
            installListenersOnElement(adviceProcess);
            
            List<IData> aspectedVariables = aspectedProcess.getData( );
            List<IData> adviceVariables = adviceProcess.getData( );
            
            prepareData(aspectedVariables, adviceVariables);
            
            List<IChannel> aspectedChannels = aspectedProcess.getChannels( );
            List<IChannel> adviceChannels = adviceProcess.getChannels( );
            
            //Sends the event that the instruction is prepared, and must me executed
            adviceProcess.getModelInstance( ).getRuntime( ).start( );
            super.sendExecuteEvent( );
            
        }
        catch( LoaderException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( CumbiaException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private void createNewTransitionForProcess()
    {
        EventsAndRolesMappingInformation mapping = bpelEngine.getMetamodelInformation( ).getMapping( );
        StateMachineInformation stateMachineInformation = bpelEngine.getMetamodelInformation( ).getStateMachineInformation( "process" );

        // The basic information from the transition
        String transitionName = "NewToExecuting";
        String destinationStateName = "ExecutingActivity";
        String originStateName = "Waiting";


        // The information about the source event
        String sourceEventName = "newExecute";
        String sourceEventRole = "ME";
        int sourceEventId = mapping.storeEventName(sourceEventName);
        int sourceEventRoleId = mapping.storeRoleName(sourceEventRole);

        // The information about the before event
        String beforeEventName = "beforeNewToExecuting";
        int beforeEventId = mapping.storeEventName(beforeEventName);

        // The information about the after event
        String afterEventName = "afterNewToExecuting";
        int afterEventId = mapping.storeEventName(afterEventName);

        // Create and store the transition information holder
        TransitionInformation trInformation = new TransitionInformation(transitionName, beforeEventId, afterEventId, originStateName,
                destinationStateName, sourceEventId, sourceEventRoleId);
        

        // The information about the actions associated to the transition

        String actionName = "NewExecute";
        String actionClass = "uniandes.cumbia.bpel.elements.process.actions.Execute";

        ActionInformation actionInformation = new ActionInformation(actionName, actionClass);
        trInformation.storeActionInformation(actionInformation);
            
        
        stateMachineInformation.storeTranstionInformation(trInformation);
        bpelEngine.getMetamodelInformation( ).storeStateMachineInformation( "process", stateMachineInformation );        
        
        
    }

    private void prepareData( List<IData> aspectedVariables, List<IData> adviceVariables )
    {
        //First check that the data present in the aspected process is not present in the advice process
        //In the case it exist, nothing is done
        
    }

    public void executeInstruction()
    {
        System.out.println("ExecuteInstruction");
        adviceProcess.sendExecuteEvent();
    }
    
    /**
     * Installs the listeners in the specified element
     * @param element The element to install the listeners.
     */
    private void installListenersOnElement( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = adviceProcess.getModelInstance().getMappingInformation( );
        int finalizedId = mapping.getEventId( "finalized" );
        int roleMe = mapping.getRoleId( "ME" );

        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        FinalizedProcessListener endedInsideElementListener = new FinalizedProcessListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }
    
    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        super.sendPrepareEvent( );
    }

    /**
     * Stops the element
     */
    public void stop( )
    {

    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IInstruction.TYPE_INSTRUCTION;
    }

    public void setAdviceFile( File file )
    {
        this.adviceFile = file;
    }

    /**
     * @return the bpelEngine
     */
    public IProcessEngine getBpelEngine( )
    {
        return bpelEngine;
    }

    /**
     * @param bpelEngine the bpelEngine to set
     */
    public void setBpelEngine( IProcessEngine bpelEngine )
    {
        this.bpelEngine = bpelEngine;
    }

    /**
     * @return the adviceFile
     */
    public File getAdviceFile( )
    {
        return adviceFile;
    }

    @Override
    public void setAspectEngine( IAspectEngine aspectEngine )
    {
        this.bpelEngine = aspectEngine.getBPELEngine( );
    }

    public void processEnded( )
    {
        super.sendFinalizedEvent( );
        super.sendReInitEvent( );
    }
}
