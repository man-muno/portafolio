/*******************************************************************************
 * $Id: FinalizedProcessListener.java,v 1.1 2009/02/10 00:55:04 man-muno Exp $
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
package uniandes.cumbia.aspects.elements.instruction.listener;

import uniandes.cumbia.aspects.elements.instruction.CreateBPELElements;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.events.IEventListener;

public class FinalizedProcessListener implements IEventListener
{
	// -----------------------------------------------
	// Attributes
	// -----------------------------------------------

	/**
	 * Reference to the process
	 */
	private CreateBPELElements createBPELElements;

	// -----------------------------------------------
	// Methods
	// -----------------------------------------------

	/**
	 * Constructor of the listener
	 * 
	 * @param responsible The responsible to set.
	 */
	public FinalizedProcessListener(CreateBPELElements createBPELElements)
	{
		this.createBPELElements = createBPELElements;
	}

	/**
	 * Notfies the event
	 */
	public void notifyEvent(EventNotification event)
	{
	    createBPELElements.processEnded();
	    System.out.println("FinalizedProcessListener Listener");
	}
}
