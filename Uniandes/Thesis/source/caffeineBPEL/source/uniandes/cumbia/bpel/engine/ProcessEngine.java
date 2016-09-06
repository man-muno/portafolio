/*******************************************************************************
 * $Id: ProcessEngine.java,v 1.5 2009/01/29 16:09:26 man-muno Exp $
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


import uniandes.cumbia.bpel.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.engine.events.EngineStartedEvent;
import uniandes.cumbia.bpel.engine.events.EngineStoppedEvent;
import uniandes.cumbia.bpel.engine.events.ProcessLoadedEvent;
import uniandes.cumbia.bpel.engine.events.ProcessUnloadedEvent;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.messagecenter.IMessageCenter;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.instantiation.exceptions.ResourcesException;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.IStateMachine;
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
}
