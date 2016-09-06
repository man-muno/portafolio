/*******************************************************************************
 * $Id: InstanceStartedEvent.java,v 1.2 2008/11/12 19:42:11 man-muno Exp $
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
 * The high level event of the start of an instance
 */
public class InstanceStartedEvent implements IEvent
{
	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------
	/**
	 * Identifier of the instance that was started
	 */
	private InstanceId id;

	/**
	 * The process that was started
	 */
	private IProcess process;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------
	/**
	 * Builds the event with information and references of the process that was started
	 * 
	 * @param id Identifier of the instance space that contains the process
	 * @param process The process that generated the low-level event
	 */
	public InstanceStartedEvent(InstanceId id, IProcess process)
	{
		this.id = id;
		this.process = process;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	/**
	 * Returns the identifier of the instanceSpace that contains the process that was started
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
	 * @return INSTANCE_STARTED
	 */
	public int getType()
	{
		return InstanceSensorAdmin.INSTANCE_STARTED;
	}

}
