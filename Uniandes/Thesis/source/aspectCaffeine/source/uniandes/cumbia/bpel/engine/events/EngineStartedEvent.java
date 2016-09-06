/*******************************************************************************
 * $Id: EngineStartedEvent.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.bpel.engine.events;

import uniandes.cumbia.bpel.engine.EngineSensorAdmin;

/**
 * The high-level event that announces that the Engine has been started
 */
public class EngineStartedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the event
     */
    public EngineStartedEvent( )
    {
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the type of the event
     * @return ENGINE_STARTED
     */
    public int getType( )
    {
        return EngineSensorAdmin.ENGINE_STARTED;
    }

}
