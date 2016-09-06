/*******************************************************************************
 * $Id: InstanceStartedSensor.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $
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
package uniandes.cumbia.aspects.instance.sensors;

import java.util.Iterator;
import java.util.List;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.instance.InstanceId;
import uniandes.cumbia.aspects.instance.InstanceSensorAdmin;
import uniandes.cumbia.aspects.instance.Sensor;
import uniandes.cumbia.aspects.instance.events.InstanceStartedEvent;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.statemachine.Action;
import uniandes.cumbia.openobjects.statemachine.Transition;

/**
 * This sensor is installed over the transitions of the process state machine and generates the higher level event INSTANCE_STARTED using
 * the InstanceSensorAdmin
 */
public class InstanceStartedSensor extends Sensor
{
	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------
	/**
	 * The InstanceSensorAdmin related to the process instance this Sensor is listening to
	 */
	private InstanceSensorAdmin instanceSensorAdmin;

	/**
	 * The instance of an XPM model where the sensor must be installed
	 */
	private ModelInstance modelInstance;

	/**
	 * Id of the instance that this sensor is associated to
	 */
	private InstanceId instanceId;

	// -----------------------------------------------------------------
	// Constructors
	// -----------------------------------------------------------------
	/**
	 * Builds the sensor with an InstanceSensorAdmin so it can generate the event related to the start of the process
	 * 
	 * @param sensorAdmin The high level event generator
	 * @param modelInstance The instance of an XPM model where the sensor must be installed
	 */
	public InstanceStartedSensor(InstanceSensorAdmin sensorAdmin, ModelInstance modelInstance)
	{
		this.modelInstance = modelInstance;
		this.instanceSensorAdmin = sensorAdmin;
	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * This method should be called when the process starts. <br>
	 * In response to the notification it generates the high level event INSTANCE_STARTED
	 * 
	 * @param event The event notification received
	 * @param transition The transition caused for the event
	 * @param element The element that take the transition
	 */
	public void notify(EventNotification event, Transition transition, IOpenObject element)
	{
		if (this.instanceSensorAdmin.hasListeners())
		{
			IAspect process = (IAspect) element;
			InstanceStartedEvent hEvent = new InstanceStartedEvent(instanceId, process);
			instanceSensorAdmin.publishEvent(hEvent);
		}
	}

	/**
	 * Installs the sensor in the correct spot of the process or runtime
	 */
	public void deploySensor()
	{
		// Deploy the sensor to know if the instance has started
		IAspect rootProcess = (IAspect) this.modelInstance.getRoot();
		//////System.out.println("deploy sensor " + rootProcess);
		Transition tr = rootProcess.getStateMachine().getTransition("ToCalculatingTP");
		tr.getActions().add(new Action("Announce Start to InstanceSpace", this));
	}

	/**
	 * Remove a similar sensor from the process or runtime
	 */
	public void removeSensor()
	{
		IAspect rootProcess = (IAspect) this.modelInstance.getRoot();
		Transition tr = rootProcess.getStateMachine().getTransition("finalized");
		List<Action> actions = tr.getActions();
		for (Iterator iter = actions.iterator(); iter.hasNext();)
		{
			Action action = (Action) iter.next();
			if ("Announce Start to InstanceSpace".equals(action.getName()))
			{
				iter.remove();
			}
		}
	}

	/**
	 * Sets the id of the instance space
	 * 
	 * @param id
	 */
	public void setInstanceId(InstanceId id)
	{
		this.instanceId = id;
	}
}
