/*******************************************************************************
 * $Id: InstanceId.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.instance;

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
     * Aspect Id
     */
    private int aspectId;

    /**
     * Instance Id
     */
    private int instanceId;


    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Creates a new Instance Id
     * 
     * @param aspectId Aspect Id
     * @param instanceId Instance Id
     */
    public InstanceId( int aspectId, int instanceId )
    {
        super( );
        this.aspectId = aspectId;
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
     * Returns the aspect Id
     * 
     * @return aspectId
     */
    public int getAspectId( )
    {
        return aspectId;
    }

    public boolean equals( Object obj )
    {
        try
        {
            InstanceId other = ( InstanceId )obj;
            return other.instanceId == instanceId && other.aspectId == aspectId;
        }
        catch( ClassCastException cce )
        {
            return false;
        }
    }
}
