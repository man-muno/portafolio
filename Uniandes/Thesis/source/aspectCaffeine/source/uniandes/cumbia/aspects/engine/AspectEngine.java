/*******************************************************************************
 * $Id: AspectEngine.java,v 1.3 2009/02/11 18:11:58 man-muno Exp $
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
package uniandes.cumbia.aspects.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.aspects.containers.HierarchicalMemory;
import uniandes.cumbia.aspects.engine.events.EngineStartedEvent;
import uniandes.cumbia.aspects.engine.events.EngineStoppedEvent;
import uniandes.cumbia.aspects.engine.events.ProcessLoadedEvent;
import uniandes.cumbia.aspects.engine.events.ProcessUnloadedEvent;
import uniandes.cumbia.aspects.instantiation.ConflictingInstructionsLoader;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.instantiation.exceptions.ResourcesException;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Main class for the process engine
 */
public class AspectEngine implements IAspectEngine, Serializable
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
    
    private IProcessEngine bpelEngine;
    
    private File conflictingFile;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates a new empty engine
     * @param processEngine 
     * 
     * @throws IOException This exception is thrown if there are problems loading the configuration
     * @throws LoaderException This exception is thrown if there is a problem loading the metamodel
     * @throws ResourcesException This exception is thrown if there are problems in the creation of the class loader
     */
    public AspectEngine( File kernelProperties, File conflictingFile, IProcessEngine bpelEngine ) throws LoaderException, ResourcesException, IOException
    {
        // Initialize the kernel and load the metamodel
        initializeKernel( new FileInputStream( kernelProperties ) );

        this.bpelEngine= bpelEngine;
        this.conflictingFile = conflictingFile;
        
        sensorAdmin = new EngineSensorAdmin( );
        processRegistry = new Registry( );
        engineMemory = new HierarchicalMemory( );

        // Publish the ENGINE_STARTED event
        if( this.sensorAdmin.hasListeners( ) )
        {
            EngineStartedEvent event = new EngineStartedEvent( );
            sensorAdmin.publishEvent( event );
        }
        loadConflicts( );
    }

    /**
     * Creates a new empty engine
     * 
     * @throws IOException This exception is thrown if there are problems loading the configuration
     * @throws LoaderException This exception is thrown if there is a problem loading the metamodel
     * @throws ResourcesException This exception is thrown if there are problems in the creation of the class loader
     */
    public AspectEngine( InputStream stream ) throws LoaderException, ResourcesException, IOException
    {
        // Initialize the kernel and load the metamodel
        initializeKernel( stream );

        sensorAdmin = new EngineSensorAdmin( );
        processRegistry = new Registry( );
        engineMemory = new HierarchicalMemory( );

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
    
    public IProcessEngine getBPELEngine()
    {
        return bpelEngine;
    }

    /**
     * Loads a new process and creates a new process space for it
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param modelClassLoader Classloader with the classes necessary for the process
     * @return The newly created process space
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    public AspectSpace createAspect( File modelDescriptionFile, ClassLoader modelClassLoader ) throws LoaderException
    {
        openObjectsKernel.loadModel( modelDescriptionFile, metamodelInformation, modelClassLoader );

        // Creates the new process space
        AspectSpace space = new AspectSpace( this, modelDescriptionFile, modelClassLoader );

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

        // Returns the new space
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
     * @param aspectId Id of the process space
     * @return The process space
     */
    public AspectSpace getAspectSpace( int aspectId )
    {
        return ( AspectSpace )processRegistry.get( aspectId );
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
            ( ( AspectSpace )processSpaces.get( i ) ).suspend( );
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
    public Registry getAspectRegistry( )
    {
        return processRegistry;
    }

    /**
     * Returns a process space given it's name
     * @param aspectName The name of the process
     * @return The process space
     */
    public AspectSpace getAspectSpaceByName( String aspectName )
    {
        List<AspectSpace> processSpaces = processRegistry.getAll( );
        AspectSpace processSpace = null;
        for( int i = 0; i < processSpaces.size( ) && processSpace == null; i++ )
        {
            if( processSpaces.get( i ).getAspectName( ).equals( aspectName ) )
                processSpace = processSpaces.get( i );
        }
        return processSpace;
    }
    
    private void loadConflicts( ) throws LoaderException
    {
        try
        {
            // Creates a DOM Parser
            DOMParser domParser = new DOMParser( );
            // Parses the DOM Tree
            InputStream xmlStream = new FileInputStream( conflictingFile );
            domParser.parse( new InputSource( xmlStream ) );
            // Saca el documento
            Document doc = domParser.getDocument( );
            ConflictingInstructionsLoader loader = new ConflictingInstructionsLoader( doc );
            loader.load(  );

        }
        catch( FileNotFoundException e )
        {
            throw new LoaderException( "Unable to load conflicting instructions file.", e );
        }
        catch( SAXException e )
        {
            throw new LoaderException( "Unable to load conflicting instructions file.", e );
        }
        catch( IOException e )
        {
            throw new LoaderException( "Unable to load conflicting instructions file.", e );
        }
    }
}
