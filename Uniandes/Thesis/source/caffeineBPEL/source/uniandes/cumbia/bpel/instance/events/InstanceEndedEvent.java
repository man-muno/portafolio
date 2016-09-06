/*******************************************************************************
 * $Id: InstanceEndedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
package uniandes.cumbia.bpel.instance.events;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.engine.events.IEvent;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.bpel.instance.InstanceSensorAdmin;

/**
 * The high level event of the end of an instance
 */
public class InstanceEndedEvent implements IEvent
{
	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------
	/**
	 * Identifier of the instance that ended
	 */
	private InstanceId id;

	/**
	 * The process that was ended
	 */
	private IProcess process;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------
	/**
	 * Builds the event with information and references of the process that ended
	 * 
	 * @param id Identifier of the instance space that contains the process
	 * @param process The process that generated the low-level event
	 */
	public InstanceEndedEvent(InstanceId id, IProcess process)
	{
		this.id = id;
		this.process = process;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	/**
	 * Returns the identifier of the instanceSpace that contains the process that ended
	 * 
	 * @return id
	 */
	public InstanceId getId()
	{
		return id;
	}

	/**
	 * Returns the process that generated the low-level event
	 * 
	 * @return process
	 */
	public IProcess getProcess()
	{
		return process;
	}

	/**
	 * Returns the type of the event
	 * 
	 * @return INSTANCE_ENDED
	 */
	public int getType()
	{
		return InstanceSensorAdmin.INSTANCE_ENDED;
	}

}
