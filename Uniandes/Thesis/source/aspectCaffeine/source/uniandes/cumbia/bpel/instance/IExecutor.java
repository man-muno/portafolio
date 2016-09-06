/*******************************************************************************
 * $Id: IExecutor.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.bpel.instance;

import java.io.Serializable;

/**
 * An executor is an object that is intended to execute certain actions inside the engine
 */
public interface IExecutor extends Serializable
{

    /**
     * This method is called to execute the action this Executor was built for
     */
    void execute( );

}
