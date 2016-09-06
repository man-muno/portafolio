/*******************************************************************************
 * $Id: LoaderException.java,v 1.1 2009/01/29 21:48:51 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigación en Construcción de Software
 * Departamento de Ingeniería de Sistemas y Computación
 * Universidad de los Andes
 * Bogotá - Colombia
 * 
 * Copyright (c) 2006
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.test.animation.loader;

import uniandes.cumbia.exceptions.CumbiaException;


/**
 * Exception thrown when an error ocurrs while loading a process instance
 */
public class LoaderException extends CumbiaException
{
	private static final long serialVersionUID = 88712794417789476L;

	// -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor with messange
     * @param messange Message for the exception
     */
    public LoaderException( String messange )
    {
        super( messange );
    }

    /**
     * Constructor with messange and inner exception
     * @param messange Message for the exception
     * @param inner Inner exception
     */
    public LoaderException( String messange, Exception inner )
    {
        super( messange, inner );
    }
}