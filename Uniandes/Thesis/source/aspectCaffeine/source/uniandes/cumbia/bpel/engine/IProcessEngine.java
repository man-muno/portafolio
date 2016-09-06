/*******************************************************************************
 * $Id: IProcessEngine.java,v 1.3 2009/02/11 18:39:41 man-muno Exp $
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
import java.io.Serializable;
import java.util.List;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.bpel.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.datastructures.Registry;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.metamodel.MetamodelInformation;
import uniandes.cumbia.openobjects.kernel.Kernel;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Interface with the services offered by the process engine
 * 
 * @author Manuel Munoz
 */
public interface IProcessEngine extends Serializable
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
     * Loads a new process and creates a new process space for it
     * 
     * @param modelDescriptionFile This is the file that contains the description of the model
     * @param modelClassLoader Classloader with the classes necessary for the process
     * @return The newly created process space
     * @throws LoaderException This exception is thrown if there is a problem loading the model
     */
    ProcessSpace createProcess( File modelDescriptionFile, ClassLoader modelClassLoader ) throws LoaderException;

    /**
     * Returns the process space specified by the id
     * 
     * @param processId Id of the process space
     * @return The process space
     */
    ProcessSpace getProcessSpace( int processId );

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
    Registry getProcessRegistry( );

    /**
     * Returns a process space given it's name
     * @param processName The name of the process
     * @return The process space
     */
    ProcessSpace getProcessSpaceByName( String processName );

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
    public void registerElementExpectingMessage( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, String typeMessageExpected, IInteraction element, InstanceId instanceID, long beginTime );
    
    /**
     * Sends the response to the partner link provided
     * @param partnerLinkName The name of the partner link
     * @param portTypeName The name of the port type
     * @param operationName The name of the operation to be invoked for the answer
     * @param response The response 
     * @param name name of the element
     * @param instanceID The id of the instance
     */
    public void sendReplyResponse(String partnerLinkName, String portTypeName, String operationName, Object response, String name, InstanceId instanceID);
    
    /**
     * Invokes the synchronus partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param operationName The operation name to be invoked
     * @param message The message to be sended
     * @return The response of the invocation
     */
    public Object invokeSynchronusPartnerLink(String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, InstanceId id, int serviceId );
    
    /**
     * Invokes the asynchronuos partner link specified
     * @param partnerLinkName The partner link name
     * @param partnerLinkUri The uri of the partner link
     * @param portTypeName The port for making the invocation
     * @param message The message to be sended   
     */
    public void invokeAsynchronusPartnerLink( String partnerLinkName, String partnerLinkUri, String portTypeName, String operationName, Object message, IInteraction element, InstanceId instanceID, int serviceId );
    
    // -----------------------------------------------------------------
    // Aspects
    // -----------------------------------------------------------------
    
    /**
     * Sets the aspect engine
     */
    public void setAspectEngine(IAspectEngine aspectEngine);
    
    /**
     * Deploys a new aspect
     * @param aspectFile
     * @param enabled
     */
    public void deployAspect(File aspectFile, boolean enabled) throws LoaderException, CumbiaException;
    
    /**
     * Sets an aspect not to execute
     * @param name
     * @param processName
     * @param processId
     * @param instanceId
     */
    public void disableAspect(String name, String processName, int processId, int instanceId );
    
    /**
     * Removes an aspect not to execute
     * @param name
     * @param processName
     * @param processId
     * @param instanceId
     */
    public void removeAspect(String name, String processName, int processId, int instanceId );
    
    /**
     * Returns a list of all the aspectSpaces
     * @return
     */
    public List<AspectSpace> getAspectSpaces();
    
    
    /**
     * @param aspectFile
     * @param enabled
     * @param aspectSpace
     * @throws CumbiaException
     */
    public void installAspects( AspectSpace aspectSpace ) throws CumbiaException;

}
