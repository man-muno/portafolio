/*******************************************************************************
 * $Id: ProcessLoadedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
 * The high-level event that announces that a Process has been loaded
 */
public class ProcessLoadedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Identifier of the loaded process
     */
    private int processId;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the event
     * @param processId The identifier of the process that was loaded
     */
    public ProcessLoadedEvent( int processId )
    {
        this.processId = processId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the type of the event
     * @return PROCESS_LOADED
     */
    public int getType( )
    {
        return EngineSensorAdmin.PROCESS_LOADED;
    }

    /**
     * Returns the identifier of the loaded process
     * @return processId
     */
    public int getProcessId( )
    {
        return processId;
    }

}
