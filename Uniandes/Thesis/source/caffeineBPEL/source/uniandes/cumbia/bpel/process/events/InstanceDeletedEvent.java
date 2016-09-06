/*******************************************************************************
 * $Id: InstanceDeletedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
package uniandes.cumbia.bpel.process.events;

import uniandes.cumbia.bpel.engine.events.IEvent;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.process.ProcessSensorAdmin;

/**
 * The high level event of the deletion of an instance
 */
public class InstanceDeletedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Full identifier of the instance
     */
    private InstanceId instanceId;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the new event
     * @param instanceId Identifier of the instance
     */
    public InstanceDeletedEvent( InstanceId instanceId )
    {
        this.instanceId = instanceId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the type of the event
     * @return INSTANCE_DELETED
     */
    public int getType( )
    {
        return ProcessSensorAdmin.INSTANCE_DELETED;
    }

    /**
     * Returns the identifier of the instance
     * @return instanceId
     */
    public InstanceId getInstanceId( )
    {
        return instanceId;
    }
}
