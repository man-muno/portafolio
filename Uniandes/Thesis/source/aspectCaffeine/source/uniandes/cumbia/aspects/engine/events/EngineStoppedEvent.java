/*******************************************************************************
 * $Id: EngineStoppedEvent.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigacion en Construccion de Software
 * Departamento de Ingenieria de Sistemas y Computacion
 * Universidad de los Andes
 * Bogota - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.aspects.engine.events;

import uniandes.cumbia.aspects.engine.EngineSensorAdmin;

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
