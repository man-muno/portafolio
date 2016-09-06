/*******************************************************************************
 * $Id: AspectSpace.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.aspect;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import uniandes.cumbia.aspects.aspect.events.InstanceCreatedEvent;
import uniandes.cumbia.aspects.aspect.events.InstanceDeletedEvent;
import uniandes.cumbia.aspects.containers.HierarchicalMemory;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.aspects.instance.InstanceId;
import uniandes.cumbia.aspects.instance.AspectInstanceSpace;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.data.DataString;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;
import uniandes.cumbia.openobjects.runtime.IRuntime;

/**
 * Aspect Space
 */
public class AspectSpace
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Aspect Id
     */
    private int id;

    /**
     * This is the information about the model
     */
    private ModelInformation modelInformation;

    /**
     * The Engine that contains this aspect space
     */
    private IAspectEngine parent;

    /**
     * Instance Registry
     */
    private Registry instanceRegistry;

    /**
     * Manager of aspect events and sensors
     */
    private AspectSensorAdmin aspectSensorAdmin;

    /**
     * AspectMemory
     */
    private HierarchicalMemory aspectMemory;

    /**
     * The aspect name
     */
    private String aspectName;

    /**
     * The file that defines the model to be loaded
     */
    private File modelFile;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new AspectSpace: it loads all the information about the aspect and stores it in the field called modelInformation.
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param classLoader The class loader that can be used to locate the classes required in the model
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    public AspectSpace( IAspectEngine engine, File modelDescriptionFile, ClassLoader classLoader) throws LoaderException
    {
        this.parent = engine;
        Kernel kernel = parent.getKernel( );
        MetamodelInformation metamodel = parent.getMetamodelInformation( );

        modelInformation = kernel.loadModel( modelDescriptionFile, metamodel, classLoader );
        this.modelFile = modelDescriptionFile;

        this.instanceRegistry = new Registry( );
        this.aspectSensorAdmin = new AspectSensorAdmin( );
        this.aspectMemory = new HierarchicalMemory( engine.getEngineMemory( ) );
        this.aspectName = modelInformation.getModelName( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the aspect Id
     * 
     * @return Aspect Id
     */
    public int getId( )
    {
        return id;
    }

    /**
     * Sets the aspect Id
     * 
     * @param id New Aspect Id
     */
    public void setId( int id )
    {
        this.id = id;
    }

    /**
     * Returns the aspect memory
     * 
     * @return aspect memory
     */
    public HierarchicalMemory getAspectMemory( )
    {
        return aspectMemory;
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
     * Returns the information about the aspect
     * 
     * @return modelInformation
     */
    public ModelInformation getModelInformation( )
    {
        return modelInformation;
    }

    /**
     * Returns the manager of aspect events and sensors
     * 
     * @return Aspect Sensor
     */
    public AspectSensorAdmin getAspectSensorAdmin( )
    {
        return aspectSensorAdmin;
    }

    /**
     * Returns the engine where this aspect space is stored
     * 
     * @return parent
     */
    public IAspectEngine getParent( )
    {
        return parent;
    }

    /**
     * Returns an instance space given its id
     * 
     * @param instanceId The identifier of an instance
     * @return The InstanceSpace that corresponds to the given id
     */
    public AspectInstanceSpace getInstance( int instanceId )
    {
        return ( AspectInstanceSpace )instanceRegistry.get( instanceId );
    }

    /**
     * Creates a new instance space of this aspect
     * 
     * @return New instance space of this aspect
     * @throws CumbiaException Throws this exception if there are problems creating the instance of the aspect
     */
    public AspectInstanceSpace createInstance() throws CumbiaException
    {
        //
        // Create the new Instance space
        AspectInstanceSpace space = new AspectInstanceSpace( this );

        // Register in Registry
        int id = instanceRegistry.add( space );
        space.setId( id );

        // Publish the INSTANCE_CREATED event
        if( this.aspectSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceCreatedEvent event = new InstanceCreatedEvent( instanceId );
            aspectSensorAdmin.publishEvent( event );
        }

        return space;
    }

    /**
     * Loads an instance of the aspect
     * 
     * @param fileForLoading The file that has the persisted information about the instance
     * @return New instance space of this aspect
     * @throws CumbiaException Throws this exception if there are problems creating the instance of the aspect
     */
    public AspectInstanceSpace loadInstance( File fileForLoading ) throws CumbiaException
    {
        //
        // Create the new Instance space
        AspectInstanceSpace space = new AspectInstanceSpace( this, true );

        // Register in Registry
        int id = instanceRegistry.add( space );
        space.setId( id );

        // Publish the INSTANCE_CREATED event
        if( this.aspectSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceCreatedEvent event = new InstanceCreatedEvent( instanceId );
            aspectSensorAdmin.publishEvent( event );
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
     * Adds a new instances space to the aspect
     * 
     * @param space The instance space that is going to be added
     * @return Returns the new InstanceId of the instance space
     */
    public InstanceId addInstance( AspectInstanceSpace space )
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
        if( this.aspectSensorAdmin.hasListeners( ) )
        {
            InstanceId instanceId = new InstanceId( this.id, id );
            InstanceDeletedEvent event = new InstanceDeletedEvent( instanceId );
            aspectSensorAdmin.publishEvent( event );
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
     * Returns the aspect name
     * 
     * @return The aspect name
     */
    public String getAspectName( )
    {
        return aspectName;
    }

    /**
     * Suspends all running instances of the aspect
     */
    public void suspend( )
    {
        ArrayList instances = instanceRegistry.getAll( );
        for( Iterator iter = instances.iterator( ); iter.hasNext( ); )
        {
            AspectInstanceSpace instance = ( AspectInstanceSpace )iter.next( );
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
