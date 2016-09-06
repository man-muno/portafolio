/*******************************************************************************
 * $Id: ProcessSensorAdmin.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.bpel.process;

import uniandes.cumbia.bpel.engine.events.SensorAdmin;

/**
 * This class manages the high level events generated by a process space
 */
public class ProcessSensorAdmin extends SensorAdmin
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final int INSTANCE_CREATED = 1;

    public static final int INSTANCE_DELETED = 2;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Builds the events manager
     */
    public ProcessSensorAdmin( )
    {
        super( );
    }

}