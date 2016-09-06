/*******************************************************************************
 * $Id: FinalizedElementListener.java,v 1.1 2009/01/29 21:38:43 man-muno Exp $
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
package uniandes.cumbia.aspects.elements.runtime.listeners;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.runtime.AspectRuntime;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.IEventListener;

public class FinalizedElementListener implements IEventListener
{
	// -----------------------------------------------
	// Attributes
	// -----------------------------------------------

	/**
	 * Reference to the runtime
	 */
	private AspectRuntime runtime;

	// -----------------------------------------------
	// Methods
	// -----------------------------------------------

	/**
	 * Constructor of the listener
	 * 
	 * @param responsible The responsible to set.
	 */
	public FinalizedElementListener(AspectRuntime rt)
	{
		runtime = rt;
	}

	/**
	 * Notfies the event
	 */
	public void notifyEvent(EventNotification event)
	{
		//
		// Element that generated the event
		IBasicElement element = (IBasicElement) event.getSourceElement();
		//
		// Removes the work space active
		runtime.removeElementActive(element);
	}
}
