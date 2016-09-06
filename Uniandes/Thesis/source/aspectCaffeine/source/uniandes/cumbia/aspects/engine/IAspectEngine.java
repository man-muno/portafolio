/*******************************************************************************
 * $Id: IAspectEngine.java,v 1.2 2009/02/10 00:55:03 man-muno Exp $
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
import java.io.Serializable;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.aspects.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Interface with the services offered by the aspect engine
 */
public interface IAspectEngine extends Serializable
{

    /**
     * Returns a reference to the kernel that is used by this engine
     * 
     * @return The kernel in use
     */
    Kernel getKernel( );

    /**
     * Returns the information about the metamodel used by this engine
     * 
     * @return Information on the metamodel
     */
    MetamodelInformation getMetamodelInformation( );

    /**
     * Loads a new aspect and creates a new aspect space for it
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param modelClassLoader Classloader with the classes necessary for the process
     * @return The newly created process space
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    AspectSpace createAspect( File modelDescriptionFile, ClassLoader modelClassLoader ) throws LoaderException;

    /**
     * Returns the aspect space specified by the id
     * 
     * @param aspectId Id of the process space
     * @return The process space
     */
    AspectSpace getAspectSpace( int aspectId );
    
    public IProcessEngine getBPELEngine();

    /**
     * Returns the sensor administration for the engine
     * 
     * @return Sensor Administrator
     */
    EngineSensorAdmin getSensorAdmin( );

    /**
     * Stops the engine
     */
    void stopEngine( );

    /**
     * Returns the engine memory
     * 
     * @return Returns the engine memory
     */
    HierarchicalMemory getEngineMemory( );

    /**
     * Returns the process registry
     * 
     * @return Process registry
     */
    Registry getAspectRegistry( );

    /**
     * Returns a aspect space given it's name
     * @param aspectName The name of the aspect
     * @return The aspect space
     */
    AspectSpace getAspectSpaceByName( String aspectName );
}
