/*******************************************************************************
 * $Id: AspectInstanceSpace.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $
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
package uniandes.cumbia.aspects.instance;

import java.util.Calendar;

import uniandes.cumbia.aspects.aspect.AspectSpace;
import uniandes.cumbia.aspects.containers.HierarchicalMemory;
import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.instance.sensors.InstanceEndedSensor;
import uniandes.cumbia.aspects.instance.sensors.InstanceStartedSensor;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.kernel.Kernel;

/**
 * Instance Space
 */
public class AspectInstanceSpace
{

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * Instance Id
	 */
	private int id;

	/**
	 * Instance of the process that is used in this space
	 */
	private ModelInstance instance;

	/**
	 * Parent process space
	 */
	private AspectSpace parent;

	/**
	 * Manager of instance events and sensors
	 */
	private InstanceSensorAdmin instanceSensorAdmin;

	/**
	 * Instance Memory
	 */
	private HierarchicalMemory instanceMemory;

	/**
	 * The ended sensor 
	 */
	private InstanceEndedSensor endedSensor;

	/**
	 * The started sensor
	 */
	private InstanceStartedSensor startedSensor;
	
	/**
	 * The calendar that has the started date of the process
	 */
	private Calendar startedDate;
	
	/**
	 * The calendar that has the finished date of the process
	 */
	private Calendar finishedDate;
	
	/**
	 * Establishes that the data of the process has been initialized
	 */
	private boolean dataInitialized;



	// -----------------------------------------------------------------
	// Constrcutors
	// -----------------------------------------------------------------

	/**
	 * Creates a new instance space and creates the instance of a model
	 * 
	 * @param procSpace The process space where the instance space is located
	 * @param loader 
	 * @throws CumbiaException This exception is thrown if there are problems creating the instance
	 */
	public AspectInstanceSpace(AspectSpace procSpace) throws CumbiaException
	{
	    dataInitialized = false;
		this.parent = procSpace;
		this.instanceMemory = new HierarchicalMemory(procSpace.getAspectMemory());
		ModelInformation modelInformation = parent.getModelInformation();

		// Create the instance of the process and its runtime
		Kernel kernel = parent.getKernel();
		this.instance = kernel.instanceModel(modelInformation);

		IAspect aspect = (IAspect)instance.getRoot( );
		aspect.setEngine( procSpace.getParent( ) );
		
		startedDate = Calendar.getInstance( );
		
		// Set the sensors on the instance
		this.instanceSensorAdmin = new InstanceSensorAdmin();
		
		installSensors();
	}

    /**
	 * Install the sensors required to transform the events from the state machines into eventos about the process life-cycle.
	 */
	public void installSensors()
	{
	    //////System.out.println("installSensors begin");
		endedSensor = new InstanceEndedSensor(this.instanceSensorAdmin, this.instance);
		endedSensor.deploySensor();
		//////System.out.println("InstanceEndedSensor installed");
		startedSensor = new InstanceStartedSensor(this.instanceSensorAdmin, this.instance);
		startedSensor.deploySensor();
		//////System.out.println("installSensors end");
	}

	/**
	 * Removes the sensors from the instance, in the case they were installed previously
	 */
	public void removeSensors()
	{
		new InstanceEndedSensor(this.instanceSensorAdmin, this.instance).removeSensor();
		new InstanceStartedSensor(this.instanceSensorAdmin, this.instance).removeSensor();
	}

	/**
	 * Creates a new instance space and creates the instance of a model
	 * 
	 * @param procSpace The process space where the instance space is located
	 * @param empty Indicates if the instance must be empty when created
	 * @throws CumbiaException This exception is thrown if there are problems creating the instance
	 */
	public AspectInstanceSpace(AspectSpace procSpace, boolean empty) throws CumbiaException
	{
		this.parent = procSpace;
		this.instanceMemory = new HierarchicalMemory(procSpace.getAspectMemory());
		ModelInformation modelInformation = parent.getModelInformation();

		// Create the instance of the process and its runtime
		Kernel kernel = parent.getKernel();
		if (!empty)
			this.instance = kernel.instanceModel(modelInformation);

		else
			this.instance = kernel.instanceEmptyModel(modelInformation);

		// Set the sensors on the instance
		this.instanceSensorAdmin = new InstanceSensorAdmin();

	}

	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------

	/**
	 * Returns the process instance running in this space
	 * 
	 * @return instance
	 */
	public ModelInstance getInstance()
	{
		return instance;
	}

	/**
	 * Returns the parent process space
	 * 
	 * @return Parent process space
	 */
	public AspectSpace getParent()
	{
		return parent;
	}

	/**
	 * Changes the parent of the process
	 * 
	 * @param parent
	 */
	public void setParent(AspectSpace parent)
	{
		this.parent = parent;
	}

	/**
	 * Executes the executor given
	 * 
	 * @param executor
	 */
	public void execute(IExecutor executor)
	{
		executor.execute();
	}

	/**
	 * Sets the instance Id
	 * 
	 * @param id The new instance id
	 */
	public void setId(int id)
	{
		this.id = id;

		IAspect process = (IAspect) this.instance.getRoot();
		if (process != null)
			process.setInstanceSpaceId(getId());

		if (endedSensor != null)
			endedSensor.setInstanceId(getId());

		if (startedSensor != null)
			startedSensor.setInstanceId(getId());
	}

	/**
	 * Returns the instance identifier
	 * 
	 * @return Instance Identifier
	 */
	public InstanceId getId()
	{
		return new InstanceId(parent.getId(), id);
	}

	/**
	 * Returns the manager of instance events and sensors
	 * 
	 * @return Instance Sensor Admin
	 */
	public InstanceSensorAdmin getInstanceSensorAdmin()
	{
		return instanceSensorAdmin;
	}

	/**
	 * Returns the instance memory
	 * 
	 * @return instance memory
	 */
	public HierarchicalMemory getInstanceMemory()
	{
		return instanceMemory;
	}

	/**
	 * Sets the instance memory for the instance
	 * 
	 * @param instanceMemory The new instance memory
	 */
	public void setInstanceMemory(HierarchicalMemory instanceMemory)
	{
		this.instanceMemory = instanceMemory;
	}

	/**
	 * Changes the instance
	 * 
	 * @param inst The new instance
	 */
	public void setInstance(ModelInstance instance)
	{
		this.instance = instance;
	}

    /**
     * @return the startedSensor
     */
    public InstanceStartedSensor getStartedSensor( )
    {
        return startedSensor;
    }

    /**
     * @return the startedDate
     */
    public Calendar getStartedDate( )
    {
        return startedDate;
    }

    public void setFinishedDate( )
    {
        finishedDate = Calendar.getInstance( );
    }

    /**
     * @return the finishedDate
     */
    public Calendar getFinishedDate( )
    {
        return finishedDate;
    }

    /**
     * @return the dataInitialized
     */
    public boolean isDataInitialized( )
    {
        return dataInitialized;
    }
}
