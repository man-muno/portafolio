/*******************************************************************************
 * $Id: Sensor.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
package uniandes.cumbia.bpel.instance;

import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

/**
 * Represents a sensor in the running instance
 * 
 * @author Manuel Munoz
 * 
 */
public abstract class Sensor implements IAction
{

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Method called when the action has to be executed
	 * 
	 * @param event Event notification that generated the action
	 * @param element Parent element
	 * @param transition The transition that this action belongs to
	 */
	public void execute(EventNotification event, Transition transition, IOpenObject element)
	{
		notify(event, transition, element);
	}

	/**
	 * Notify that the event happen
	 * 
	 * @param event The event notification received
	 * @param transition The transition caused for the event
	 * @param element The element that take the transition
	 */
	public abstract void notify(EventNotification event, Transition transition, IOpenObject element);

	/**
	 * Installs the sensor in the correct spot of the process or runtime
	 */
	public abstract void deploySensor();

	public abstract void removeSensor();
}
