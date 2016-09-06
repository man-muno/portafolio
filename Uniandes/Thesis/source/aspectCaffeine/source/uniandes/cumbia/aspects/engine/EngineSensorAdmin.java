/*******************************************************************************
 * $Id: EngineSensorAdmin.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.engine;

import uniandes.cumbia.aspects.engine.events.SensorAdmin;

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
