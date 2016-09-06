/*******************************************************************************
 * $Id: InstanceId.java,v 1.4 2009/01/29 16:09:26 man-muno Exp $
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
package uniandes.cumbia.bpel.instance;

import java.io.Serializable;

/**
 * The full identifier of an instance inside the engine. <br>
 * It contains information about both the Process Space and the Instance Space.
 * 
 * @author Manuel Munoz
 */
public class InstanceId implements Serializable
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Process Id
     */
    private int processId;

    /**
     * Instance Id
     */
    private int instanceId;

    /**
     * Instance Id
     */
    private int serviceId;


    /**
     * The URI for the client
     */
    private String clientURI;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Creates a new Instance Id
     * 
     * @param processId Process Id
     * @param instanceId Instance Id
     */
    public InstanceId( int processId, int instanceId , int serviceId, String clientUri)
    {
        super( );
        this.processId = processId;
        this.instanceId = instanceId;
        this.serviceId = serviceId;
        this.clientURI = null;
    }
    
    /**
     * Creates a new Instance Id
     * 
     * @param processId Process Id
     * @param instanceId Instance Id
     */
    public InstanceId( int processId, int instanceId )
    {
        super( );
        this.processId = processId;
        this.instanceId = instanceId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the instance Id
     * 
     * @return instanceId
     */
    public int getInstanceId( )
    {
        return instanceId;
    }

    /**
     * Returns the process Id
     * 
     * @return processId
     */
    public int getProcessId( )
    {
        return processId;
    }

    public boolean equals( Object obj )
    {
        try
        {
            InstanceId other = ( InstanceId )obj;
            return other.instanceId == instanceId && other.processId == processId;
        }
        catch( ClassCastException cce )
        {
            return false;
        }
    }

    /**
     * Returns the id of the WSDL service
     * @return
     */
    public int getServiceID( )
    {
        return serviceId;
    }

    /**
     * Returns the URI for the client for the process
     * @return
     */
    public String getClientURI( )
    {
        return clientURI;
    }
}
