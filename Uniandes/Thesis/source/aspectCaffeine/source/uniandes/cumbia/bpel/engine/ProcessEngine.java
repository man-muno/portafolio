/*******************************************************************************
 * $Id: ProcessEngine.java,v 1.4 2009/02/11 18:39:41 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaci�n en Construcci�n de Software
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Universidad de los Andes
 * Bogot� - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.aspectedElement.actions.ExecuteAspect;
import uniandes.cumbia.aspects.elements.instruction.IInnerInstruction;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.aspects.instance.AspectInstanceSpace;
import uniandes.cumbia.bpel.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.engine.events.EngineStartedEvent;
import uniandes.cumbia.bpel.engine.events.EngineStoppedEvent;
import uniandes.cumbia.bpel.engine.events.ProcessLoadedEvent;
import uniandes.cumbia.bpel.engine.events.ProcessUnloadedEvent;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.instantiation.exceptions.ResourcesException;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.statemachine.Action;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.IStateMachine;
import uniandes.cumbia.openobjects.statemachine.State;
import uniandes.cumbia.openobjects.statemachine.Transition;

/**
 * Main class for the process engine
 */
public class ProcessEngine implements IProcessEngine, Serializable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * 
     */
    private static final long serialVersionUID = -7163204854859545960L;

    /**
     * Manager of engine events and sensors
     */
    private EngineSensorAdmin sensorAdmin;

    /**
     * Process Registry
     */
    private Registry processRegistry;

    /**
     * Memory for the engine
     */
    private HierarchicalMemory engineMemory;

    /**
     * This is the information about the metamodel that this engine uses
     */
    private MetamodelInformation metamodelInformation;

    /**
     * This is the open objects kernel used by this engine
     */
    private Kernel openObjectsKernel;

    /**
     * The message center for the web services
     */
    private IMessageCenter messageCenter;

    private IAspectEngine aspectEngine;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new empty engine
     * 
     * @throws IOException This exception is thrown if there are problems loading the configuration
     * @throws LoaderException This exception is thrown if there is a problem loading the metamodel
     * @throws ResourcesException This exception is thrown if there are problems in the creation of the class loader
     */
    public ProcessEngine( File kernelProperties, IMessageCenter messageCenter ) throws LoaderException, ResourcesException, IOException
    {
        // Initialize the kernel and load the metamodel
        initializeKernel( new FileInputStream( kernelProperties ) );

        sensorAdmin = new EngineSensorAdmin( );
        processRegistry = new Registry( );
        engineMemory = new HierarchicalMemory( );
        this.messageCenter = messageCenter;
        messageCenter.setBpelEngine( this );

        // Publish the ENGINE_STARTED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            EngineStartedEvent event = new EngineStartedEvent( );
            sensorAdmin.publishEvent( event );
        }
    }

    /**
     * Creates a new empty engine
     * 
     * @throws IOException This exception is thrown if there are problems loading the configuration
     * @throws LoaderException This exception is thrown if there is a problem loading the metamodel
     * @throws ResourcesException This exception is thrown if there are problems in the creation of the class loader
     */
    public ProcessEngine( InputStream stream, IMessageCenter messageCenter ) throws LoaderException, ResourcesException, IOException
    {
        // Initialize the kernel and load the metamodel
        initializeKernel( stream );

        sensorAdmin = new EngineSensorAdmin( );
        processRegistry = new Registry( );
        engineMemory = new HierarchicalMemory( );
        this.messageCenter = messageCenter;
        messageCenter.setBpelEngine( this );

        // Publish the ENGINE_STARTED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            EngineStartedEvent event = new EngineStartedEvent( );
            sensorAdmin.publishEvent( event );
        }
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Initializes the kernel and loads the metamodel used by this engine.
     * 
     * @param stream This is the file with the configuration that the kernel requires
     * @throws IOException This exception is thrown if there are problems loading the configuration
     * @throws LoaderException This exception is thrown if there is a problem loading the metamodel
     * @throws ResourcesException This exception is thrown if there are problems in the creation of the class loader
     */
    private void initializeKernel( InputStream stream ) throws IOException, LoaderException, ResourcesException
    {
        openObjectsKernel = new Kernel( stream );
        metamodelInformation = openObjectsKernel.loadMetamodel( );
    }

    /**
     * Returns a reference to the kernel that is used by this engine
     * 
     * @return The kernel in use
     */
    public Kernel getKernel( )
    {
        return openObjectsKernel;
    }

    /**
     * Returns the information about the metamodel used by this engine
     * 
     * @return Information on the metamodel
     */
    public MetamodelInformation getMetamodelInformation( )
    {
        return metamodelInformation;
    }

    /**
     * Loads a new process and creates a new process space for it
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param modelClassLoader Classloader with the classes necessary for the process
     * @return The newly created process space
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    public ProcessSpace createProcess( File modelDescriptionFile, ClassLoader modelClassLoader ) throws LoaderException
    {
        openObjectsKernel.loadModel( modelDescriptionFile, metamodelInformation, modelClassLoader );

        // Creates the new process space
        ProcessSpace space = new ProcessSpace( this, modelDescriptionFile, modelClassLoader );

        // Register in the registry
        int id = processRegistry.add( space );

        // Set the Id
        space.setId( id );

        // Publish the PROCESS_LOADED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            ProcessLoadedEvent event = new ProcessLoadedEvent( id );
            sensorAdmin.publishEvent( event );
        }

        // Return the new space
        return space;
    }

    /**
     * Unloads a process from the process space
     * 
     * @param id The identifier of the process that has to be unloaded
     */
    public void unloadProcess( int id )
    {
        // TODO unloadProcess

        // Publish the PROCESS_UNLOADED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            ProcessUnloadedEvent event = new ProcessUnloadedEvent( id );
            sensorAdmin.publishEvent( event );
        }
    }

    /**
     * Returns the process space specified by the id
     * 
     * @param processId Id of the process space
     * @return The process space
     */
    public ProcessSpace getProcessSpace( int processId )
    {
        return ( ProcessSpace )processRegistry.get( processId );
    }

    /**
     * Returns the sensor administration for the engine
     * 
     * @return Sensor Administrator
     */
    public EngineSensorAdmin getSensorAdmin( )
    {
        return sensorAdmin;
    }

    /**
     * Stops the engine
     */
    public void stopEngine( )
    {
        // TODO revisar como se detiene el motor

        // Tell all process instances to suspend
        ArrayList processSpaces = processRegistry.getAll( );
        for( int i = 0; i < processSpaces.size( ); i++ )
        {
            ( ( ProcessSpace )processSpaces.get( i ) ).suspend( );
        }
        processSpaces = new ArrayList( );
        processRegistry = new Registry( );

        // Publish the ENGINE_STOPPED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            EngineStoppedEvent event = new EngineStoppedEvent( );
            sensorAdmin.publishEvent( event );
        }
    }

    /**
     * Returns the engine memory
     * 
     * @return Returns the engine memory
     */
    public HierarchicalMemory getEngineMemory( )
    {
        return engineMemory;
    }

    /**
     * Returns the process registry
     * 
     * @return Process registry
     */
    public Registry getProcessRegistry( )
    {
        return processRegistry;
    }

    /**
     * Returns a process space given it's name
     * @param processName The name of the process
     * @return The process space
     */
    public ProcessSpace getProcessSpaceByName( String processName )
    {
        List<ProcessSpace> processSpaces = processRegistry.getAll( );
        ProcessSpace processSpace = null;
        for( int i = 0; i < processSpaces.size( ) && processSpace == null; i++ )
        {
            if( processSpaces.get( i ).getProcessName( ).equals( processName ) )
                processSpace = processSpaces.get( i );
        }
        return processSpace;
    }

    /**
     * Registry the activity specified like waiting for a message
     * @param partnerLinkName The name of the partner link to send the message
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The name of the port type name
     * @param operationName The name of the operation invoke by the invoke activity associated
     * @param typeMessageExpected The type of message expected
     * @param elementName The name of the element
     * @param instanceID The id of the instance
     * @param beginTime The time when the waiting begins
     */
    public void registerElementExpectingMessage( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, String typeMessageExpected, IInteraction element, InstanceId instanceID, long beginTime )
    {
        messageCenter.registerElementExpectingMessage( partnerLinkName, partnerLinkUri, portTypeName, operationName, typeMessageExpected, element, instanceID, beginTime );
    }

    /**
     * Sends the response to the partner link provided
     * @param partnerLinkName The name of the partner link
     * @param portTypeName The name of the port type
     * @param operationName The name of the operation to be invoked for the answer
     * @param response The response
     * @param name name of the element
     * @param instanceID The id of the instance
     */
    public void sendReplyResponse( String partnerLinkName, String portTypeName, String operationName, Object response, String name, InstanceId instanceID )
    {
        messageCenter.sendReplyResponse( partnerLinkName, portTypeName, operationName, response, name, instanceID );
    }

    /**
     * Invokes the synchronus partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param operationName The operation name to be invoked
     * @param message The message to be sended
     * @return The response of the invocation
     */
    public Object invokeSynchronusPartnerLink( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, InstanceId id, int serviceId )
    {
        Object response = messageCenter.invokeSynchronousCall( serviceId, partnerLinkUri, ( Serializable )message, portTypeName, operationName, id );
        return response;
    }

    /**
     * Invokes the asynchronuos partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param message The message to be sended
     */
    public void invokeAsynchronusPartnerLink( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, IInteraction element, InstanceId instanceID, int serviceId )
    {
        messageCenter.invokeAsynchronusPartnerLink( partnerLinkName, partnerLinkUri, portTypeName, operationName, message, element, instanceID, serviceId );
    }

    // -----------------------------------------------------------------
    // Aspects
    // -----------------------------------------------------------------

    /**
     * Sets the aspect engine
     */
    public void setAspectEngine( IAspectEngine aspectEngine )
    {
        this.aspectEngine = aspectEngine;
    }

    /**
     * Deploys a new aspect
     * @param aspectFile
     * @param enabled
     * @throws LoaderException
     */
    public void deployAspect( File aspectFile, boolean enabled ) throws LoaderException, CumbiaException
    {
        // System.out.println( "AspectFile: " + aspectFile.getAbsolutePath( ) );
        AspectSpace aspectSpace = aspectEngine.createAspect( aspectFile, ClassLoader.getSystemClassLoader( ) );
        installAspects( aspectSpace );
    }

    /**
     * Sets an aspect not to execute
     * @param name
     * @param processName
     * @param processId
     * @param instanceId
     */
    public void disableAspect( String aspectName, String processName, int processId, int instanceId )
    {

        AspectSpace aspectSpace = aspectEngine.getAspectSpaceByName( aspectName );
        ArrayList aspectInstances = aspectSpace.getInstanceRegistry( ).getAll( );

        for( int i = 0; i < aspectInstances.size( ); i++ )
        {
            AspectInstanceSpace aspectInstanceSpace = ( AspectInstanceSpace )aspectInstances.get( i );
            IAspect aspect = ( IAspect )aspectInstanceSpace.getInstance( ).getRoot( );
            IProcess parentProcess = aspect.getAspectedElement( ).getParentProcess( );
            int processInstanceID = parentProcess.getInstanceSpaceId( ).getInstanceId( );
            int processSpaceID = parentProcess.getInstanceSpaceId( ).getProcessId( );

            ProcessSpace processSpace = null;
            InstanceSpace instanceSpace = null;

            if( instanceId != -1 )
            {
                if( processName != null )
                {
                    // Disable the aspect in the specified process instance
                    processSpace = getProcessSpaceByName( processName );
                    instanceSpace = processSpace.getInstance( instanceId );
                }
                else if( processId != -1 )
                {
                    // Disable the aspect in the specified process instance
                    processSpace = getProcessSpace( processId );
                    instanceSpace = processSpace.getInstance( instanceId );
                }

                if( processSpaceID == processSpace.getId( ) && processInstanceID == instanceSpace.getId( ).getInstanceId( ) )
                {
                    aspect.disable();
                }

            }
            else
            {
                if( processName != null )
                {
                    // Disable the aspect in the specified process
                    processSpace = getProcessSpaceByName( processName );
                }
                else if( processId != -1 )
                {
                    // Disable the aspect in the specified process
                    processSpace = getProcessSpace( processId );
                }

                if( processInstanceID == processSpace.getId( ) )
                {
                    aspect.disable();
                }
            }
        }

    }    
    
    
    /**
     * Removes an aspect not to execute
     * @param name
     * @param processName
     * @param processId
     * @param instanceId
     */
    public void removeAspect( String aspectName, String processName, int processId, int instanceId )
    {

        AspectSpace aspectSpace = aspectEngine.getAspectSpaceByName( aspectName );
        ArrayList aspectInstances = aspectSpace.getInstanceRegistry( ).getAll( );

        for( int i = 0; i < aspectInstances.size( ); i++ )
        {
            AspectInstanceSpace aspectInstanceSpace = ( AspectInstanceSpace )aspectInstances.get( i );
            IAspect aspect = ( IAspect )aspectInstanceSpace.getInstance( ).getRoot( );
            IProcess parentProcess = aspect.getAspectedElement( ).getParentProcess( );
            int processInstanceID = parentProcess.getInstanceSpaceId( ).getInstanceId( );
            int processSpaceID = parentProcess.getInstanceSpaceId( ).getProcessId( );

            ProcessSpace processSpace = null;
            InstanceSpace instanceSpace = null;

            if( instanceId != -1 )
            {
                if( processName != null )
                {
                    // Disable the aspect in the specified process instance
                    processSpace = getProcessSpaceByName( processName );
                    instanceSpace = processSpace.getInstance( instanceId );
                }
                else if( processId != -1 )
                {
                    // Disable the aspect in the specified process instance
                    processSpace = getProcessSpace( processId );
                    instanceSpace = processSpace.getInstance( instanceId );
                }

                if( processSpaceID == processSpace.getId( ) && processInstanceID == instanceSpace.getId( ).getInstanceId( ) )
                {
                    aspect.disable();
                }

            }
            else
            {
                if( processName != null )
                {
                    // Disable the aspect in the specified process
                    processSpace = getProcessSpaceByName( processName );
                }
                else if( processId != -1 )
                {
                    // Disable the aspect in the specified process
                    processSpace = getProcessSpace( processId );
                }

                if( processInstanceID == processSpace.getId( ) )
                {
                    aspect.disable();
                }
            }
        }

    }

    /**
     * Returns a list of all the aspectSpaces
     * @return
     */
    public List<AspectSpace> getAspectSpaces( )
    {
        return aspectEngine.getAspectRegistry( ).getAll( );
    }

    /**
     * @param aspectFile
     * @param enabled
     * @param aspectSpace
     * @throws CumbiaException
     */
    public void installAspects( AspectSpace aspectSpace ) throws CumbiaException
    {
        AspectInstanceSpace aspectInstance = aspectSpace.createInstance( );
        IAspect aspectDeployed = ( IAspect )aspectInstance.getInstance( ).getRoot( );
        List<ITransitionPoint> transitonPoints = aspectDeployed.getTransitionPoints( );
        for( int j = 0; j < transitonPoints.size( ); j++ )
        {
            ITransitionPoint transitionPoint = transitonPoints.get( j );
            List<IBasicElement> elements = getAspectedElements( transitionPoint );
            if( elements.size( ) > 0 )
            {
                List<AspectInstanceSpace> instanceSpaces = new ArrayList<AspectInstanceSpace>( );
                instanceSpaces.add( aspectInstance );
                completeAspectSpaces( aspectSpace, instanceSpaces, elements );
                for( int i = 0; i < instanceSpaces.size( ); i++ )
                    installAspectOnElement( instanceSpaces.get( i ), elements.get( i ), j );
            }
            else
                aspectInstance.getInstance( ).getRuntime( ).stop( );
        }
    }

    private void installAspectOnElement( AspectInstanceSpace instanceSpace, IBasicElement element, int transitionPointIndex )
    {
        IAspect aspectDeployed = ( IAspect )instanceSpace.getInstance( ).getRoot( );
        ITransitionPoint transitionPoint = aspectDeployed.getTransitionPoints( ).get( transitionPointIndex );
        // Set the aspected element to the aspect
        aspectDeployed.setAspectedElement( element );
        String expression = transitionPoint.getPointCut( );
        if( expression.indexOf( "->" ) != -1 )
            installInnerJoinPoint( element, transitionPoint, expression );
        else
            installOuterJoinPoint( element, aspectDeployed, transitionPoint );
    }

    /**
     * @param element
     * @param aspectDeployed
     * @param transitionPoint
     */
    private void installOuterJoinPoint( IBasicElement element, IAspect aspectDeployed, ITransitionPoint transitionPoint )
    {
        // The aspect should be located in an element
        List<IAdvice> advices = transitionPoint.getAdvices( );
        IStateMachine sm = ( ( OpenObject )element ).getStateMachine( );
        IAction action = new ExecuteAspect( aspectDeployed );

        for( IAdvice advice : advices )
            if( advice.getType( ).equals( IAdvice.BEFORE_TYPE ) )
                installBeforeAdvice( sm, action );
            else if( advice.getType( ).equals( IAdvice.AFTER_TYPE ) )
                installAfterAdvice( sm, action );
            else if( advice.getType( ).equals( IAdvice.AROUND_TYPE ) )
                installArroundAdvice( sm, action );
    }

    /**
     * @param sm
     * @param action
     */
    private void installArroundAdvice( IStateMachine sm, IAction action )
    {
        State initialState = sm.getInitialState( );
        State finalizingState = null;
        for( State state : sm.getStates( ) )
            for( Transition transition : state.getTransitions( ) )
                if( transition.getSuccessor( ).getStateName( ).equals( "Finalizing" ) )
                    finalizingState = transition.getSuccessor( );

        // Only one transition out from the initial state
        Transition initialTransition = initialState.getTransitions( ).get( 0 );
        initialTransition.setSuccessor( finalizingState );
        initialTransition.getActions( ).set( 0, new Action( ExecuteAspect.EXECUTE_ASPECT, action ) );
    }

    /**
     * @param sm
     * @param action
     */
    private void installAfterAdvice( IStateMachine sm, IAction action )
    {
        for( State state : sm.getStates( ) )
            for( Transition transition : state.getTransitions( ) )
                if( transition.getSuccessor( ).getStateName( ).equals( "Finalizing" ) )
                    if( transition.getActions( ).size( ) > 0 )
                        transition.getActions( ).set( 0, new Action( ExecuteAspect.EXECUTE_ASPECT, action ) );
                    else
                        transition.getActions( ).add( new Action( ExecuteAspect.EXECUTE_ASPECT, action ) );
    }

    /**
     * @param sm
     * @param action
     */
    private void installBeforeAdvice( IStateMachine sm, IAction action )
    {
        for( Transition transition : sm.getInitialState( ).getTransitions( ) )
            transition.getActions( ).set( 0, new Action( ExecuteAspect.EXECUTE_ASPECT, action ) );
    }

    /**
     * @param element
     * @param transitionPoint
     * @param expression
     */
    private void installInnerJoinPoint( IBasicElement element, ITransitionPoint transitionPoint, String expression )
    {
        try
        {
            // The aspect should be located over a transition
            String transitionName = expression.split( "->" )[ 1 ];
            IStateMachine sm = ( ( OpenObject )element ).getStateMachine( );

            for( IAdvice advice : transitionPoint.getAdvices( ) )
                for( IInstruction inst : advice.getInstructions( ) )
                {
                    IInnerInstruction instruction = ( IInnerInstruction )inst;
                    Transition transition = sm.getTransition( transitionName );
                    transition.addAction( instruction.getActionName( ), ( IAction )Class.forName( instruction.getActionClass( ) ).newInstance( ) );
                }
        }
        catch( InstantiationException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }
        catch( IllegalAccessException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }
        catch( ClassNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace( );
        }
    }

    private void completeAspectSpaces( AspectSpace aspectSpace, List<AspectInstanceSpace> instanceSpaces, List<IBasicElement> elements ) throws CumbiaException
    {
        while( instanceSpaces.size( ) != elements.size( ) )
        {
            AspectInstanceSpace aspectInstance = aspectSpace.createInstance( );
            instanceSpaces.add( aspectInstance );
        }
    }

    /**
     * Returns a list of all the aspected elements
     * @param expression
     * @param transitionPoint
     * @return
     */
    private List<IBasicElement> getAspectedElements( ITransitionPoint transitionPoint )
    {

        String expression = transitionPoint.getPointCut( );
        List<IBasicElement> resp = new ArrayList<IBasicElement>( );

        // Si comienza con * es un aspecto que se aplica a muchos elementos dado un tipo
        if( expression.startsWith( "*" ) )
        {
            String noStar = expression.split( "\\*" )[ 1 ];
            // Si la expression contiene "->" es porque se debe colocar en una transicion
            if( expression.indexOf( "->" ) != -1 )
            {
                String transitionName = noStar.split( "->" )[ 1 ];
                // Si tiene "|" entonces debe colocarse en todos los procesos dado un nombre
                if( noStar.indexOf( "|" ) != -1 )
                {
                    String processName = noStar.split( "\\|" )[ 1 ].split( "->" )[ 0 ];
                    ProcessSpace processSpace = getProcessSpaceByName( processName );
                    ArrayList instanceSpaces = processSpace.getInstanceRegistry( ).getAll( );
                    for( int i = 0; i < instanceSpaces.size( ); i++ )
                    {
                        InstanceSpace instanceSpace = ( InstanceSpace )instanceSpaces.get( i );
                        resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression.split( "->" )[ 0 ] ) );
                    }
                }
                else
                // Si no tiene "|" se coloca sobre todos los elementos de todos los procesos
                {
                    ArrayList processSpaces = processRegistry.getAll( );
                    for( int i = 0; i < processSpaces.size( ); i++ )
                    {
                        ProcessSpace processSpace = ( ProcessSpace )processSpaces.get( i );
                        ArrayList instancesSpaces = processSpace.getInstanceRegistry( ).getAll( );
                        for( int j = 0; j < instancesSpaces.size( ); j++ )
                        {
                            InstanceSpace instanceSpace = ( InstanceSpace )instancesSpaces.get( j );
                            resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression.split( "->" )[ 0 ] ) );
                        }
                    }
                }
            }
            else
            // Si no contiene "->" el aspecto se coloca antes, despues o en vez de el elemento
            {
                if( noStar.indexOf( "\\|" ) != -1 )
                {
                    String processName = noStar.split( "\\|" )[ 1 ];
                    ProcessSpace processSpace = getProcessSpaceByName( processName );
                    ArrayList instanceSpaces = processSpace.getInstanceRegistry( ).getAll( );
                    for( int i = 0; i < instanceSpaces.size( ); i++ )
                    {
                        AspectInstanceSpace instanceSpace = ( AspectInstanceSpace )instanceSpaces.get( i );
                        resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression ) );
                    }
                }
                else
                // Si no tiene "|" se coloca sobre todos los elementos de todos los procesos
                {
                    ArrayList processSpaces = processRegistry.getAll( );
                    for( int i = 0; i < processSpaces.size( ); i++ )
                    {
                        ProcessSpace processSpace = ( ProcessSpace )processSpaces.get( i );
                        ArrayList instancesSpaces = processSpace.getInstanceRegistry( ).getAll( );
                        for( int j = 0; j < instancesSpaces.size( ); j++ )
                        {
                            InstanceSpace instanceSpace = ( InstanceSpace )instancesSpaces.get( j );
                            resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression ) );
                        }
                    }
                }
            }
        }
        // Si no comienza con * solo se aplica a un elemento especifico
        else
        {
            int instanceId = transitionPoint.getProcessInstance( );
            String processName = expression.split( "\\|" )[ 1 ].split( ":" )[ 0 ];
            ProcessSpace processSpace = getProcessSpaceByName( processName );

            if( instanceId != -1 )
            {
                InstanceSpace instanceSpace = processSpace.getInstance( instanceId );
                resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression.split( "->" )[ 0 ] ) );
            }
            else
            {
                ArrayList instanceSpaces = processSpace.getInstanceRegistry( ).getAll( );
                for( int i = 0; i < instanceSpaces.size( ); i++ )
                {
                    InstanceSpace instanceSpace = ( InstanceSpace )instanceSpaces.get( i );
                    resp.addAll( instanceSpace.getInstance( ).getRuntime( ).getElement( expression.split( "->" )[ 0 ] ) );
                }
            }
        }
        return resp;
    }

}
