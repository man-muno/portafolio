/*******************************************************************************
 * $Id: InstanceSpace.java,v 1.6 2009/02/10 01:45:04 man-muno Exp $
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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import uniandes.cumbia.bpel.containers.HierarchicalMemory;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.instance.sensors.InstanceEndedSensor;
import uniandes.cumbia.bpel.instance.sensors.InstanceStartedSensor;
import uniandes.cumbia.bpel.process.ProcessSpace;
import uniandes.cumbia.exceptions.CumbiaException;
import uniandes.cumbia.openobjects.containers.model.ModelInformation;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.kernel.Kernel;

/**
 * Instance Space
 */
public class InstanceSpace
{

	// -----------------------------------------------------------------
	// Attributes
	// -----------------------------------------------------------------

	/**
	 * Instance Id
	 */
	private int id;
	
    /**
     * The service id of the WSDL process
     */
    private int serviceId;

	/**
	 * Instance of the process that is used in this space
	 */
	private ModelInstance instance;

	/**
	 * Parent process space
	 */
	private ProcessSpace parent;

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
	
    /**
     * The URI for the client
     */
    private String clientURI;



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
	public InstanceSpace(ProcessSpace procSpace) throws CumbiaException
	{
	    dataInitialized = false;
		this.parent = procSpace;
		this.instanceMemory = new HierarchicalMemory(procSpace.getProcessMemory());
		ModelInformation modelInformation = parent.getModelInformation();

		// Create the instance of the process and its runtime
		Kernel kernel = parent.getKernel();
		this.instance = kernel.instanceModel(modelInformation);

		IProcess process = (IProcess)instance.getRoot( );
		process.setEngine( procSpace.getParent( ) );
		
		startedDate = Calendar.getInstance( );
		
		// Set the sensors on the instance
		this.instanceSensorAdmin = new InstanceSensorAdmin();
		
		installSensors();
		
        for(int i=0;i<process.getStartingPoints( ).size( );i++)
        {
            //System.out.println("InstanceSpace : " + process.getStartingPoints( ).get( i ).getName( ));
            //System.out.println("InstanceSpace : " + ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ));
            //System.out.println("InstanceSpace : " + ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ).getInboundMessage( ));
            //System.out.println("InstanceSpace : " + process.getData( ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ).getInboundMessage( ).getName( ) ).getName( ));
        }
		
		//System.out.println("InstanceSpace constructor");

	}

	public void initializeData( ClassLoader loader ) throws CumbiaException
    {
	    try
        {
	        IProcess process = (IProcess)instance.getRoot( );
	        
	        for(int i=0;i<process.getStartingPoints( ).size( );i++)
	        {
	            //System.out.println("Startingpoints : " + process.getStartingPoints( ).get( i ).getName( ));
	            //System.out.println("Startingpoints : " + ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ));
	            //System.out.println("Startingpoints : " + ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ).getInboundMessage( ));
	            //System.out.println("Startingpoints : " + process.getData( ((IInteraction)process.getStartingPoints( ).get( i )).getChannel( ).getInboundMessage( ).getName( ) ).getName( ));
	        }
	        
	        for(int i=0;i<process.getProcessElements( ).size( );i++)
	        {
	            //System.out.println("ProcessElements: " + process.getProcessElements( ).get( i ).getName( ));
	        }
	        
	        
	        
            List<IData> variables = process.getData( );
            //System.out.println("initializeVariables amount " + variables.size( ));
            for(IData variable : variables)
            {
                //System.out.println("initialing variable " + variable.getName( ));
                Hashtable<String,String> partsClassesName= variable.getPartsAndClasses();
                Iterator<String> itPartsClassesName = partsClassesName.keySet( ).iterator( );
                while(itPartsClassesName.hasNext( ))
                {
                    String partName = itPartsClassesName.next( );
                    String className = partsClassesName.get( partName );
                    //System.out.println("PASO INICIALIZAR PROCESO 15 EN EL BPEL PROCESS " + partName);
                    Class clazz= loader.loadClass(className);
                    //System.out.println("PASO INICIALIZAR PROCESO 16 EN EL BPEL PROCESS");
                    Constructor constructor= clazz.getConstructor(new Class[]{});
                    //System.out.println("PASO INICIALIZAR PROCESO 17 EN EL BPEL PROCESS");
                    Object instance= constructor.newInstance(new Object[]{});
                    //System.out.println("PASO INICIALIZAR PROCESO 18 EN EL BPEL PROCESS");
                    variable.addObject(partName,instance);
                    //System.out.println("PASO INICIALIZAR PROCESO 19 EN EL BPEL PROCESS");
                }
            }
            dataInitialized = true;
        }
        catch( SecurityException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( IllegalArgumentException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( ClassNotFoundException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( NoSuchMethodException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( InstantiationException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( IllegalAccessException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        catch( InvocationTargetException e )
        {
            e.printStackTrace();
            throw new CumbiaException(e.getMessage( ));
        }
        
    }

    /**
	 * Install the sensors required to transform the events from the state machines into eventos about the process life-cycle.
	 */
	public void installSensors()
	{
	    //System.out.println("installSensors begin");
		endedSensor = new InstanceEndedSensor(this.instanceSensorAdmin, this.instance);
		endedSensor.deploySensor();
		//System.out.println("InstanceEndedSensor installed");
		startedSensor = new InstanceStartedSensor(this.instanceSensorAdmin, this.instance);
		startedSensor.deploySensor();
		//System.out.println("installSensors end");
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
	public InstanceSpace(ProcessSpace procSpace, boolean empty) throws CumbiaException
	{
		this.parent = procSpace;
		this.instanceMemory = new HierarchicalMemory(procSpace.getProcessMemory());
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
	public ProcessSpace getParent()
	{
		return parent;
	}

	/**
	 * Changes the parent of the process
	 * 
	 * @param parent
	 */
	public void setParent(ProcessSpace parent)
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

		IProcess process = (IProcess) this.instance.getRoot();
		if (process != null)
			process.setInstanceSpaceId(getId());

		if (endedSensor != null)
			endedSensor.setInstanceId(getId());

		if (startedSensor != null)
			startedSensor.setInstanceId(getId());
	}
	
	
	public void setServiceId(int serviceId)
	{
	    this.serviceId = serviceId;
	}
	
	
	public void setClientURI(String clientURI)
	{
	    this.clientURI = clientURI;
	}
	

	/**
	 * Returns the instance identifier
	 * 
	 * @return Instance Identifier
	 */
	public InstanceId getId()
	{
		return new InstanceId(parent.getId(), id, serviceId,clientURI);
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
