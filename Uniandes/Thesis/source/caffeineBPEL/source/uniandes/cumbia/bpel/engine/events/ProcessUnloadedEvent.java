/*******************************************************************************
 * $Id: ProcessUnloadedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
 * The high-level event that announces that a Process has been unloaded
 */
public class ProcessUnloadedEvent implements IEvent
{
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    /**
     * Identifier of the unloaded process
     */
    private int processId;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Builds the event
     * @param processId Identifier of the unloaded process
     */
    public ProcessUnloadedEvent( int processId )
    {
        this.processId = processId;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Returns the type of the event
     * @return PROCESS_UNLOADED
     */
    public int getType( )
    {
        return EngineSensorAdmin.PROCESS_UNLOADED;
    }

    /**
     * Returns the identifier of the process that was unloaded
     * @return processId
     */
    public int getProcessId( )
    {
        return processId;
    }

}
