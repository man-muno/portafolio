/*******************************************************************************
 * $Id: EngineStoppedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
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
package uniandes.cumbia.bpel.engine.events;

import uniandes.cumbia.bpel.engine.EngineSensorAdmin;

/**
 * The high-level event that announces that the Engine has been stopped
 */
public class EngineStoppedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Builds the Event
     */
    public EngineStoppedEvent( )
    {
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the type of the event
     * @return ENGINE_STOPPED
     */
    public int getType( )
    {
        return EngineSensorAdmin.ENGINE_STOPPED;
    }

}
