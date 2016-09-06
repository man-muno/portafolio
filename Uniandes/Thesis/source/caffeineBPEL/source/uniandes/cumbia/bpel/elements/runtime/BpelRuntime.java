package uniandes.cumbia.bpel.elements.runtime;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.While.While;
import uniandes.cumbia.bpel.elements.conditional.Conditional;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.flow.Flow;
import uniandes.cumbia.bpel.elements.pick.Pick;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.runtime.listeners.ActiveElementListener;
import uniandes.cumbia.bpel.elements.runtime.listeners.FinalizedElementListener;
import uniandes.cumbia.bpel.elements.sequence.Sequence;
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

public class BpelRuntime extends AbstractRuntime
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public final static String ROLE_ME = "ME";

    public final static String ELEMENT_ACTIVATE_EVENT = "activate";

    public final static String ELEMENT_FINALIZED_EVENT = "finalized";

    public enum BpelElementType {
        Assign, Copy, From, To, Conditional, Else, 
        ElseIf, If, Empty, Exit, Flow, Invoke, Pick, 
        OnAlarm, OnMessage, Process, Receive, Reply, 
        Sequence, StartingReceive, StartingPick, Wait, 
        While, Data, Channel
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
    public BpelRuntime( )
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
        //System.out.println( "Start en el Runtime" );
        if( !isRunning( ) )
        {
            installListeners( );
            IProcess process = ( IProcess )modelInstance.getRoot( );
            process.initializeProcess( );

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
        //System.out.println("canProcess source: " + eventNotification.getSourceElement( ).getElementName( ) + " Role: " + modelInstance.getMappingInformation( ).resolveRoleId( eventNotification.getRole( )) + " eventid " + modelInstance.getMappingInformation( ).resolveEventId( eventNotification.getEventId( )) );
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
        return new BpelExpressionEvaluator( this.modelInstance );
    }

    /**
     * Installs the listeners the the runtime requires to get notifications about the state of the process execution. The listeners are installed in the basic elements of the
     * process.
     */
    private void installListeners( )
    {
        IProcess process = ( IProcess )modelInstance.getRoot( );

        // Adds the events to all the workspaces of process
        installListenerToElements( process );
    }

    /**
     * Adds listeners to all the elements of the process.
     */
    private void installListenerToElements( IProcess process )
    {
        List<IActivity> elements = process.getProcessElements( );
        for( int i = 0; i < elements.size( ); i++ )
        {
            IBasicElement element = elements.get( i );
            installListenersOnElement( element );
            locateElementToInstallListener( element );
        }
    }

    /**
     * Because inner elements possibly have "complex" elements some extra work is needed to successfully place all listeners
     * @param element The element that may have more elements inside it.
     */
    private void locateElementToInstallListener( IBasicElement element )
    {
        if( element instanceof Flow )
        {
            List<IActivity> insideElements = ( ( Flow )element ).getActivities( );
            for( int j = 0; j < insideElements.size( ); j++ )
            {
                locateElementToInstallListener( insideElements.get( j ) );
            }
        }
        else if( element instanceof Sequence )
        {
            List<IActivity> insideElements = ( ( Sequence )element ).getActivities( );
            for( int j = 0; j < insideElements.size( ); j++ )
            {
                locateElementToInstallListener( insideElements.get( j ) );
            }
        }
        else if( element instanceof Pick )
        {
            List<IOnAlarm> insideOnAlarms = ( ( Pick )element ).getOnAlarms( );
            for( int j = 0; j < insideOnAlarms.size( ); j++ )
            {
                IOnAlarm onAlarm = insideOnAlarms.get( j );
                installListenersOnElement( onAlarm );
                locateElementToInstallListener( onAlarm.getActivity( ) );
            }

            List<IOnMessage> insideOnMessages = ( ( Pick )element ).getOnMessages( );
            for( int j = 0; j < insideOnMessages.size( ); j++ )
            {
                IOnMessage onMessage = insideOnMessages.get( j );
                installListenersOnElement( onMessage );
                locateElementToInstallListener( onMessage.getActivity( ) );
            }
        }
        else if( element instanceof While )
        {
            locateElementToInstallListener( ( ( While )element ).getActivity( ) );
        }
        else if( element instanceof Conditional )
        {
            locateElementToInstallListener( ( ( Conditional )element ).getElse( ).getActivity( ) );
            locateElementToInstallListener( ( ( Conditional )element ).getIf( ).getActivity( ) );

            List<IElseIf> insideElements = ( ( Conditional )element ).getElseIfs( );
            for( int j = 0; j < insideElements.size( ); j++ )
            {
                locateElementToInstallListener( ( ( IElseIf )insideElements.get( j ) ).getActivity( ) );
            }
        }
    }

    /**
     * Installs the listeners in the specified element
     * @param element The element to install the listeners.
     */
    private void installListenersOnElement( IBasicElement element )
    {
        EventsAndRolesMappingInformation mapping = modelInstance.getMappingInformation( );
        int activateId = mapping.getEventId( ELEMENT_ACTIVATE_EVENT );
        int finalizedId = mapping.getEventId( ELEMENT_FINALIZED_EVENT );
        int roleMe = mapping.getRoleId( ROLE_ME );

        EventInformation beforeActivateEventInfo = new EventInformation( activateId, roleMe );
        EventInformation executionEndedInfo = new EventInformation( finalizedId, roleMe );
        ActiveElementListener activeInsideElementListener = new ActiveElementListener( this );
        element.getEventsManager( ).addEventListener( beforeActivateEventInfo, activeInsideElementListener );

        FinalizedElementListener endedInsideElementListener = new FinalizedElementListener( this );
        element.getEventsManager( ).addEventListener( executionEndedInfo, endedInsideElementListener );
    }

}
