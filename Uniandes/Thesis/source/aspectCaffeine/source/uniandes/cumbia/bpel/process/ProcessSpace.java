/*******************************************************************************
 * $Id: ProcessSpace.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://cumbia.uniandes.edu.co/)
 * 
 * Grupo de Investigación en Construcción de Software
 * Departamento de Ingeniería de Sistemas y Computación
 * Universidad de los Andes
 * Bogotá - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.process;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.bpel.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.instance.InstanceSpace;
import uniandes.cumbia.bpel.process.events.InstanceCreatedEvent;
import uniandes.cumbia.bpel.process.events.InstanceDeletedEvent;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.data.DataString;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.runtime.IRuntime;

/**
 * Process Space
 */
public class ProcessSpace
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Process Id
     */
    private int id;

    /**
     * This is the information about the model
     */
    private ModelInformation modelInformation;

    /**
     * The Engine that contains this process space
     */
    private IProcessEngine parent;

    /**
     * Instance Registry
     */
    private Registry instanceRegistry;

    /**
     * Manager of process events and sensors
     */
    private ProcessSensorAdmin processSensorAdmin;

    /**
     * Process Memory
     */
    private HierarchicalMemory processMemory;

    /**
     * The process name
     */
    private String processName;

    /**
     * The file that defines the model to be loaded
     */
    private File modelFile;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new ProcessSpace: it loads all the information about the process and stores it in the field called modelInformation.
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param classLoader The class loader that can be used to locate the classes required in the model
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    public ProcessSpace( IProcessEngine engine, File modelDescriptionFile, ClassLoader classLoader) throws LoaderException
    {
        this.parent = engine;
        Kernel kernel = parent.getKernel( );
        MetamodelInformation metamodel = parent.getMetamodelInformation( );

        modelInformation = kernel.loadModel( modelDescriptionFile, metamodel, classLoader );
        this.modelFile = modelDescriptionFile;

        this.instanceRegistry = new Registry( );
        this.processSensorAdmin = new ProcessSensorAdmin( );
        this.processMemory = new HierarchicalMemory( engine.getEngineMemory( ) );
        this.processName = modelInformation.getModelName( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the process Id
     * 
     * @return Process Id
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Sets the process Id
     * 
     * @param id New Process Id
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * Returns the process memory
     * 
     * @return process memory
     */
    public HierarchicalMemory getProcessMemory( )
    {
        return processMemory;
    }

    /**
     * Returns the kernel used by the engine
     * 
     * @return
     */
    public Kernel getKernel( )
    {
        return parent.getKernel( );
    }

    /**
     * Returns the information about the process
     * 
     * @return modelInformation
     */
    public ModelInformation getModelInformation( )
    {
        return modelInformation;
    }

    /**
     * Returns the manager of process events and sensors
     * 
     * @return Process Sensor
     */
    public ProcessSensorAdmin getProcessSensorAdmin( )
    {
        return processSensorAdmin;
    }

    /**
     * Returns the engine where this process space is stored
     * 
     * @return parent
     */
    public IProcessEngine getParent( )
    {
        return parent;
    }

    /**
     * Returns an instance space given its id
     * 
     * @param instanceId The identifier of an instance
     * @return The InstanceSpace that corresponds to the given id
     */
    public InstanceSpace getInstance( int instanceId )
    {
        return ( InstanceSpace )instanceRegistry.get( instanceId );
    }

    /**
     * Creates a new instance space of this process
     * 
     * @return New instance space of this process
     * @throws CumbiaException Throws this exception if there are problems creating the instance of the process
     */
    public InstanceSpace createInstance() throws CumbiaException
    {
        //
        // Create the new Instance space
        InstanceSpace space = new InstanceSpace( this );

        // Register in Registry
        int id = instanceRegistry.add( space );
        space.setId( id );

        // Publish the INSTANCE_CREATED event
        if( this.processSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceCreatedEvent event = new InstanceCreatedEvent( instanceId );
            processSensorAdmin.publishEvent( event );
        }
        
        for(AspectSpace aspectSpace : parent.getAspectSpaces( ))
        {
            parent.installAspects( aspectSpace );
        }

        return space;
    }

    /**
     * Loads an instance of the process
     * 
     * @param fileForLoading The file that has the persisted information about the instance
     * @return New instance space of this process
     * @throws CumbiaException Throws this exception if there are problems creating the instance of the process
     */
    public InstanceSpace loadInstance( File fileForLoading ) throws CumbiaException
    {
        //
        // Create the new Instance space
        InstanceSpace space = new InstanceSpace( this, true );

        // Register in Registry
        int id = instanceRegistry.add( space );
        space.setId( id );

        // Publish the INSTANCE_CREATED event
        if( this.processSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceCreatedEvent event = new InstanceCreatedEvent( instanceId );
            processSensorAdmin.publishEvent( event );
        }

        IRuntime instanceRuntime = space.getInstance( ).getRuntime( );
        instanceRuntime.getMemory( ).addData( new DataString( "destinationFile", fileForLoading.getAbsolutePath( ) ) );

        // Load the contents of the instance
        instanceRuntime.load( );

        // Remove the old sensors
        space.removeSensors( );

        // Install the sensors
        space.installSensors( );

        return space;
    }

    /**
     * Adds a new instances space to the process
     * 
     * @param space The instance space that is going to be added
     * @return Returns the new InstanceId of the instance space
     */
    public InstanceId addInstance( InstanceSpace space )
    {
        // Register in Registry
        space.setParent( this );
        int id = instanceRegistry.add( space );
        space.setId( id );

        return space.getId( );
    }

    /**
     * This method deletes an instance after it has finished it's execution
     * 
     * @param id The identifier of the instance
     */
    public void deleteInstance( int id )
    {
        // TODO deleteInstance

        // Publish the INSTANCE_DELETED event
        if( this.processSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceDeletedEvent event = new InstanceDeletedEvent( instanceId );
            processSensorAdmin.publishEvent( event );
        }
    }

    /**
     * Returns the instance registry
     * 
     * @return Instance Registry
     */
    public Registry getInstanceRegistry( )
    {
        return instanceRegistry;
    }

    /**
     * Returns the process name
     * 
     * @return The process name
     */
    public String getProcessName( )
    {
        return processName;
    }

    /**
     * Suspends all running instances of the process
     */
    public void suspend( )
    {
        ArrayList instances = instanceRegistry.getAll( );
        for( Iterator iter = instances.iterator( ); iter.hasNext( ); )
        {
            InstanceSpace instance = ( InstanceSpace )iter.next( );
            instance.getInstance( ).getRuntime( ).suspend( );
        }
    }

    /**
     * @return the modelFile
     */
    public File getModelFile( )
    {
        return modelFile;
    }

    /**
     * @param modelFile the modelFile to set
     */
    public void setModelFile( File modelFile )
    {
        this.modelFile = modelFile;
    }
}
