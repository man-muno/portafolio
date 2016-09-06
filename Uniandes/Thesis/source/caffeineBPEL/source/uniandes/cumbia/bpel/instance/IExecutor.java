/*******************************************************************************
 * $Id: IExecutor.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaci�n en Construcci�n de Software
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Universidad de los Andes
 * Bogot� - Colombia
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
