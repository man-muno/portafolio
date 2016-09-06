package uniandes.cumbia.aspects.elements.runtime;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.runtime.listeners.FinalizedElementListener;
import uniandes.cumbia.aspects.elements.runtime.listeners.StartElementListener;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.containers.EventsAndRolesMappingInformation;
import uniandes.cumbia.openobjects.elements.IKernelElement;
import uniandes.cumbia.openobjects.elements.NotificableElement;
import uniandes.cumbia.openobjects.execution.events.EventInformation;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.EventQueue;
import uniandes.cumbia.openobjects.execution.exceptions.PauseExecutionException;
import uniandes.cumbia.openobjects.execution.exceptions.SkipEventException;
import uniandes.cumbia.openobjects.execution.exceptions.WaitAndRetryException;
import uniandes.cumbia.openobjects.runtime.AbstractRuntime;
import uniandes.cumbia.openobjects.runtime.IExpressionEvaluator;
import uniandes.cumbia.openobjects.statemachine.IStateMachine;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class AspectRuntime extends AbstractRuntime
{

    // Constants
    // -----------------------------------------------------------------

    public final static String ROLE_ME = "ME";

    public final static String ELEMENT_START_EVENT = "activate";

    public final static String ELEMENT_FINALIZED_EVENT = "finalized";
    
    public final static String ELEMENT_EXECUTE_EVENT = "execute";
    
    public final static String ELEMENT_PREPARE_EVENT = "prepare";

    public enum AspectElementType {
        Aspect, Advice, TransitionPoint, Instruction
    }

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Indicates if an event can be processed
     */
    private boolean canProcessEvent;

    /**
     * Indicates the last internal event what was invoked
     */
    private int lastAction;

    /**
     * Indicates if the instance was initialized
     */
    private boolean isInitialized;

    /**
     * Indicates if the instance is hydrated
     */
    private boolean isHydrated;

    /**
     * List of active element
     */
    private List<IBasicElement> activeElements;

    /**
     * List of suspended elements
     */
    private List<IBasicElement> suspendedElements;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Constructor of the runtime
     */
    public AspectRuntime( )
    {
        activeElements = new Vector<IBasicElement>( );
        suspendedElements = new Vector<IBasicElement>( );
        canProcessEvent = true;
        lastAction = -1;
        isInitialized = false;
        isHydrated = true;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * 1. Starts the model instance if it is initialized but not running.<br>
     * 2. Executes the plugins associated to the action ACTION_START. <br>
     * 3. Generates the event INSTANCE_STARTED.
     */
    public void start( )
    {
        //////System.out.println( "Start en el Runtime" );
        if( !isRunning( ) )
        {
            installListeners( );
            IAspect aspect = ( IAspect )modelInstance.getRoot( );
            aspect.initializeAspect( );

            runPlugins( ACTION_START );
            lastAction = ACTION_START;

            canProcessEvent = true;
            isInitialized = true;

            generatorInstanceStarted.generate( );
        }

    }

    /**
     * 1. If the model instance is running, this method initiates the process of stopping it. <br>
     * 2. Executes the plugins associated to the action ACTION_STOP. <br>
     * 3. Generates the event INSTANCE_PENDING_STOP.
     */
    public void stop( )
    {
        canProcessEvent = false;

        if( isRunning( ) )
        {
            synchronized( activeElements )
            {
                for( int i = 0; i < activeElements.size( ); i++ )
                {
                    IBasicElement actual = activeElements.get( i );
                    actual.stop( );
                }
            }

            runPlugins( ACTION_STOP );
            lastAction = ACTION_STOP;

            generatorInstancePendingStop.generate( );
        }
    }

    /**
     * 1. If the model instance is running, this method initiates the process of suspending it.<br>
     * 2. Executes the plugins associated to the action ACTION_SUSPEND. <br>
     * 3. Generates the event INSTANCE_PENDING_SUSPEND.
     */
    public void suspend( )
    {
        canProcessEvent = false;

        if( isRunning( ) )
        {
            synchronized( activeElements )
            {
                for( int i = 0; i < activeElements.size( ); i++ )
                {
                    IBasicElement actual = activeElements.get( i );
                    // actual.suspend();
                    // TODO: Como suspender los elementos basicos??
                }
            }

            runPlugins( ACTION_SUSPEND );
            lastAction = ACTION_SUSPEND;

            generatorInstancePendingSuspend.generate( );
        }
    }

    /**
     * 1. Restarts the execution of a suspended model instance. <br>
     * 2. Executes the plugins associated to the action ACTION_RESTART. <br>
     * 3. Generates the event INSTANCE_RESTARTED.
     */
    public void restart( )
    {
        canProcessEvent = true;

        if( isSuspended( ) )
        {
            synchronized( activeElements )
            {
                for( int i = 0; i < activeElements.size( ); i++ )
                {
                    IBasicElement actual = activeElements.get( i );
                    // actual.restart();
                    // TODO: How to restart an basic element
                }
            }

            Collection<IKernelElement> elements = modelInstance.getElements( ).values( );
            for( IKernelElement element : elements )
            {
                ( ( EventQueue )element.getEventQueue( ) ).startProcessingIfNeeded( );
            }

            runPlugins( ACTION_RESTART );
            lastAction = ACTION_RESTART;

            generatorInstanceRestarted.generate( );
        }
    }

    /**
     * 1. If the model instance is suspended, executes the plugins associated to the action ACTION_SAVE. <br>
     * 2. Generates the event INSTANCE_SAVED.
     */
    public void save( )
    {
        if( isSuspended( ) )
        {
            runPlugins( ACTION_SAVE );
            lastAction = ACTION_SAVE;

            generatorInstanceSaved.generate( );
        }
    }

    /**
     * 1. If the model instance has not being initialized, executes the plugins associated to the action ACTION_LOAD. <br>
     * 2. Generates the event INSTANCE_LOADED.
     */
    public void load( )
    {
        if( !isInitialized( ) )
        {
            runPlugins( ACTION_LOAD );
            lastAction = ACTION_LOAD;
            isInitialized = true;

            generatorInstanceLoaded.generate( );
        }
    }

    /**
     * 1. If the model instance is dehydrated, executes the plugins associated to the action ACTION_HYDRATE. <br>
     * 2. Generates the event INSTANCE_HYDRATED.
     */
    public void hydrate( )
    {
        if( isDehydrated( ) )
        {
            runPlugins( ACTION_HYDRATE );
            lastAction = ACTION_HYDRATE;

            isHydrated = true;
            generatorInstanceHydrated.generate( );
        }
    }

    /**
     * 1. If the model instance is suspended, executes the plugins associated to the action ACTION_DEHYDRATE. <br>
     * 2. Generates the event INSTANCE_DEHYDRATED.
     */
    public void dehydrate( )
    {
        if( isSuspended( ) )
        {
            runPlugins( ACTION_DEHYDRATE );
            lastAction = ACTION_DEHYDRATE;

            isHydrated = false;
            generatorInstanceDehydrated.generate( );
        }
    }

    /**
     * Informs if the instance is in execution.<br>
     * An instance is in execution if it is initialized and not suspended or stopped.
     * 
     * @return Returns true if the instance is running.
     */
    public boolean isRunning( )
    {
        return isInitialized( ) && !isStopped( ) && !isSuspended( );
    }

    /**
     * Informs if the instance is stopped. <br>
     * An instance is stopped if somebody requested its stop and there are no elements running in it.
     * 
     * @return Returns true if the instance is stopped.
     */
    public boolean isStopped( )
    {
        synchronized( activeElements )
        {
            return activeElements.size( ) == 0 && elementsInMovement.size( ) == 0 && lastAction == ACTION_STOP;
        }
    }

    /**
     * Informs if the instance is suspended. <br>
     * An instance is suspended if somebody requested its suspension and there are no elements running in it.
     * 
     * @return Returns true if the instance is suspended.
     */
    public boolean isSuspended( )
    {
        synchronized( activeElements )
        {
            return activeElements.size( ) == 0 && elementsInMovement.size( ) == 0 && isInitialized && ( lastAction == ACTION_SUSPEND || lastAction == ACTION_LOAD );
        }
    }

    /**
     * Informs if the instance is initialized.
     * 
     * @return Returns true if the instance is initialized.
     */
    public boolean isInitialized( )
    {
        return isInitialized;
    }

    /**
     * Informs if the instance is dehydrated.
     * 
     * @return Returns true if the instance is dehydrated.
     */
    public boolean isDehydrated( )
    {
        return !isHydrated;
    }

    /**
     * Authorize the processing of an event
     * 
     * @param sm The state machine that received the event
     * @param eventNotification The event to be processed
     * @param transition The transition that will be taken if the event is processed.
     * @throws PauseExecutionException This exception is thrown if processing must be paused
     * @throws SkipEventException This exception is thrown if the current event should be skipped
     * @throws WaitAndRetryException This exception is thrown if it is necessary to wait a certain time before processing the event again
     */
    public void canProcess( IStateMachine sm, EventNotification eventNotification, Transition transition ) throws SkipEventException, PauseExecutionException, WaitAndRetryException
    {
        System.out.println("canProcess Aspect source: " + eventNotification.getSourceElement( ).getElementName( ) + " currentState " + sm.getCurrentState( ).getStateName( )+ " "  + " Role: " + modelInstance.getMappingInformation( ).resolveRoleId( eventNotification.getRole( )) + " eventid " + modelInstance.getMappingInformation( ).resolveEventId( eventNotification.getEventId( )) + " Transition: " + transition );
        if( transition == null )
            throw new SkipEventException( "This event has no effect in the current state", eventNotification );

        if( !canProcessEvent )
        {
            throw new PauseExecutionException( "The event can no be processed", eventNotification );
        }
        
    }

    /**
     * Authorize the processing of an event
     * 
     * @param entity The entity that received the event
     * @param eventNotification The event to be processed
     * @throws PauseExecutionException This exception is thrown if processing must be paused
     * @throws SkipEventException This exception is thrown if the current event should be skipped
     * @throws WaitAndRetryException This exception is thrown if it is necessary to wait a certain time before processing the event again
     */
    public void canProcess( NotificableElement entity, EventNotification eventNotification ) throws SkipEventException, PauseExecutionException, WaitAndRetryException
    {
        if( !canProcessEvent )
        {
            throw new PauseExecutionException( "The event can no be processed", eventNotification );
        }

    }

    /**
     * Intializes the runtime
     */
    public void initialize( )
    {
        isInitialized = true;
    }

    /**
     * Returns the list of active elements
     * 
     * @return The list of active elements
     */
    public List<IBasicElement> getActiveElements( )
    {
        return activeElements;
    }

    /**
     * Adds to the runtime an element that is active
     * 
     * @param be The element that is active
     */
    public void addElementActive( IBasicElement be )
    {
        synchronized( activeElements )
        {
            activeElements.add( be );
        }
    }

    /**
     * Removes the basic element provided
     * 
     * @param be The basic element provided
     */
    public void removeElementActive( IBasicElement be )
    {

        synchronized( activeElements )
        {
            activeElements.remove( be );
        }

        verifyProcessCompletelySuspended( );
        verifyProcessCompletelyStoped( );
    }

    /**
     * Adds to the runtime an basic element that is suspended
     * 
     * @param be The basic element that is suspended
     */
    public void addElementSuspended( IBasicElement be )
    {
        suspendedElements.add( be );
        verifyProcessCompletelySuspended( );
        verifyProcessCompletelyStoped( );
    }

    /**
     * Removes the basic element provided
     * 
     * @param be The basic element to be removed
     */
    public void removeElementSuspended( IBasicElement be )
    {
        boolean deleted = false;

        for( int i = 0; i < suspendedElements.size( ) && !deleted; i++ )
        {
            IBasicElement actual = suspendedElements.get( i );

            if( actual.getElementName( ).equals( be.getElementName( ) ) )
            {
                suspendedElements.remove( i );
                deleted = true;
            }
        }
    }

    /**
     * If the last action requested was ACTION_SUSPEND and there are neither active workspaces nor elements processing events, this method generates the event
     * INSTANCE_SUSPENDED.
     */
    private void verifyProcessCompletelySuspended( )
    {
        if( lastAction == ACTION_SUSPEND )
        {
            synchronized( activeElements )
            {
                if( activeElements.size( ) == 0 && elementsInMovement.size( ) == 0 )
                {
                    generatorInstanceSuspended.generate( );
                }
            }
        }
    }

    /**
     * If the last action requested was ACTION_STOP and there are neither active workspaces nor elements processing events, this method generates the event INSTANCE_STOPPED.
     */
    private void verifyProcessCompletelyStoped( )
    {
        if( lastAction == ACTION_SUSPEND )
        {
            synchronized( activeElements )
            {
                if( activeElements.size( ) == 0 && elementsInMovement.size( ) == 0 )
                {
                    generatorInstanceStoped.generate( );
                }
            }
        }
    }

    /**
     * Generates the event indicating that the instance finished: INSTANCE_FINISHED.
     */
    public void generatesProcessFinishedEvent( )
    {
        generatorInstanceFinished.generate( );
    }

    /**
     * This method returns the expression evaluator that should be used to find elements in the model instance
     * 
     * @return An expression evaluator associated to the instance
     */
    protected IExpressionEvaluator getExpressionEvaluator( )
    {
        return new AspectExpressionEvaluator( this.modelInstance );
    }

    /**
     * Installs the listeners the the runtime requires to get notifications about the state of the process execution. The listeners are installed in the basic elements of the
     * process.
     */
    private void installListeners( )
    {
        IAspect aspect = ( IAspect )modelInstance.getRoot( );

        // Adds the events to all the workspaces of process
        installListenerToElements( aspect );
    }

    /**
     * Adds listeners to all the elements of the process.
     */
    private void installListenerToElements( IAspect aspect )
    {
        List<ITransitionPoint> elements = aspect.getTransitionPoints( );
        for( int i = 0; i < elements.size( ); i++ )
        {
            ITransitionPoint transitionPoint = elements.get( i );
            //Installing in a transition point
            installListenersOnTransitionPoint( transitionPoint );
            
            List<IAdvice> advices = transitionPoint.getAdvices( );
            for( int j = 0; j < advices.size( ); j++ )
            {
                IAdvice advice = advices.get( j );
                installListenersOnAdvice( advice );
                
                List<IInstruction> instructions = advice.getInstructions( );
                for( int k = 0; k < instructions.size( ); k++ )
                {
                    IInstruction instruction = instructions.get( k );
                    installListenersOnInstruction( instruction );
                }
            }
            
        }
    }

    /**
     * Installs the listeners in the specified aspect
     * @param element The element to install the listeners.
     */
    private void installListenersOnAspect( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = modelInstance.getMappingInformation( );
        int startId = mapping.getEventId( ELEMENT_START_EVENT);
        int finalizedId = mapping.getEventId( ELEMENT_FINALIZED_EVENT );
        int roleMe = mapping.getRoleId( ROLE_ME );

        EventInformation beforeStartEventInfo = new EventInformation( startId, roleMe );
        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        StartElementListener activeInsideElementListener = new StartElementListener( this );
        element.getEventsManager( ).addEventListener( beforeStartEventInfo, activeInsideElementListener );

        FinalizedElementListener endedInsideElementListener = new FinalizedElementListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }
    
    /**
     * Installs the listeners in the specified transition point
     * @param element The element to install the listeners.
     */
    private void installListenersOnTransitionPoint( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = modelInstance.getMappingInformation( );
        int startId = mapping.getEventId( ELEMENT_START_EVENT);
        int finalizedId = mapping.getEventId( ELEMENT_FINALIZED_EVENT );
        int roleMe = mapping.getRoleId( ROLE_ME );

        EventInformation beforeStartEventInfo = new EventInformation( startId, roleMe );
        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        StartElementListener activeInsideElementListener = new StartElementListener( this );
        element.getEventsManager( ).addEventListener( beforeStartEventInfo, activeInsideElementListener );

        FinalizedElementListener endedInsideElementListener = new FinalizedElementListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }
    
    /**
     * Installs the listeners in the specified aspect
     * @param element The element to install the listeners.
     */
    private void installListenersOnAdvice( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = modelInstance.getMappingInformation( );
        int startId = mapping.getEventId( ELEMENT_EXECUTE_EVENT);
        int finalizedId = mapping.getEventId( ELEMENT_FINALIZED_EVENT );
        int roleMe = mapping.getRoleId( ROLE_ME );

        EventInformation beforeStartEventInfo = new EventInformation( startId, roleMe );
        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        StartElementListener activeInsideElementListener = new StartElementListener( this );
        element.getEventsManager( ).addEventListener( beforeStartEventInfo, activeInsideElementListener );

        FinalizedElementListener endedInsideElementListener = new FinalizedElementListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }
    
    /**
     * Installs the listeners in the specified aspect
     * @param element The element to install the listeners.
     */
    private void installListenersOnInstruction( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = modelInstance.getMappingInformation( );
        int startId = mapping.getEventId( ELEMENT_PREPARE_EVENT);
        int finalizedId = mapping.getEventId( ELEMENT_FINALIZED_EVENT );
        int roleMe = mapping.getRoleId( ROLE_ME );

        EventInformation beforeStartEventInfo = new EventInformation( startId, roleMe );
        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        StartElementListener activeInsideElementListener = new StartElementListener( this );
        element.getEventsManager( ).addEventListener( beforeStartEventInfo, activeInsideElementListener );

        FinalizedElementListener endedInsideElementListener = new FinalizedElementListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }

}
