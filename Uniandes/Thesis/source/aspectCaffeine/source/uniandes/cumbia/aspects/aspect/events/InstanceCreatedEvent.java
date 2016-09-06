/*******************************************************************************
 * $Id: InstanceCreatedEvent.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.aspect.events;

import uniandes.cumbia.aspects.aspect.AspectSensorAdmin;
import uniandes.cumbia.aspects.engine.events.IEvent;
import uniandes.cumbia.aspects.instance.InstanceId;

/**
 * The high level event of the creation of an instance
 */
public class InstanceCreatedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * The full identifier of the instance that was created
     */
    private InstanceId instanceId;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the new event
     * @param instanceId Identifier of the instance
     */
    public InstanceCreatedEvent( InstanceId instanceId )
    {
        this.instanceId = instanceId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the type of the event
     * @return INSTANCE_CREATED
     */
    public int getType( )
    {
        return AspectSensorAdmin.INSTANCE_CREATED;
    }

    /**
     * Returns the identifier of the
     * @return instance
     */
    public InstanceId getInstanceId( )
    {
        return instanceId;
    }
}
