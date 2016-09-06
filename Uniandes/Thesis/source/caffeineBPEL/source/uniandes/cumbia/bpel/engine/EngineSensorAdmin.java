/*******************************************************************************
 * $Id: EngineSensorAdmin.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
package uniandes.cumbia.bpel.engine;

import uniandes.cumbia.bpel.engine.events.SensorAdmin;

/**
 * This is the class where the applications interested in receiving events about the state of the engine have to register themselves.
 */
public class EngineSensorAdmin extends SensorAdmin
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final int ENGINE_STARTED = 1;

    public static final int ENGINE_STOPPED = 2;

    public static final int PROCESS_LOADED = 3;

    public static final int PROCESS_UNLOADED = 4;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public EngineSensorAdmin( )
    {
        super( );
    }

}
