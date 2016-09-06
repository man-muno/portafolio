/*******************************************************************************
 * $Id: Process.java,v 1.8 2009/02/10 01:45:04 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaciï¿½n en Construcciï¿½n de Software
 * Departamento de Ingenierï¿½a de Sistemas y Computaciï¿½n
 * Universidad de los Andes
 * Bogotï¿½ - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.elements.process;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPoint;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.caffeine.deployer.elements.NameSpace;
import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.events.generators.EventGenerator;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Abstract Process
 */
public class Process extends OpenObject implements IProcess
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String PROCESS_ACTIVATE = "activate";

    private static final String PROCESS_FINALIZED = "finalized";

    // -----------------------------------------------------------------
    // BPEL Attributes
    // -----------------------------------------------------------------

    /**
     * Description of the process
     */
    protected String description;

    /**
     * This attribute specifies the query language used in the process for selection of nodes in assignment.
     */
    protected String queryLanguage;

    /**
     * This attribute specifies the expression language used in the <process>. The default value for this attribute is: "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0", which
     * represents the usage of [XPath 1.0] within WS-BPEL 2.0.
     */
    protected String expressionLanguage = "urn:oasis:names:tc:wsbpel:2.0:sublang:xpath1.0";

    /**
     * Determines whether the joinFailure fault will be suppressed for all activities in the process. The effect of the attribute at the process level can be overridden by an
     * activity using a different value for the attribute. The default for this attribute is "no" at the process level.
     */
    protected boolean suppressJoinFailure = false;

    /**
     * If the value of this attribute is set to “yes”, then the process MUST exit immediately as if an <exit> activity has been reached
     */
    protected boolean exitOnStandardFault = false;

    /**
     * List of the process's variables
     */
    protected List<IData> variables;
    
    /**
     * List of all the channels of the process
     */
    private List<IChannel> channels;

    /**
     * List of all the activities contained in the process.
     */
    protected List<IActivity> processElements;
    
    /**
     * Name of the element 
     */
    protected String name;
    
    /**
     * The element does'nt have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;
    
    /**
     * The process engine
     */
    private IProcessEngine engine;
    
    /**
     * List of all the elements contained in the process.
     */
    protected List<NameSpace> nameSpaces;
    
    // -----------------------------------------------------------------
    // OpenObject Attributes
    // -----------------------------------------------------------------

    /**
     * Generator of the event activate
     */
    private EventGenerator generatorProcessActivate;

    /**
     * Generator of the event finalized
     */
    private EventGenerator generatorProcessFinalized;
    
    /**
     * This is the number of activities and subprocesses that have not yet terminated in a given moment after the signal for stop has been
     * given
     */
    private int notYetTerminated;

    /**
     * Identifier of the instance space where this process is used
     */
    private InstanceId instanceSpaceId;

    /**
     * The list of starting elements
     */
    private List<IStartingPoint> startingElements;
    
    /**
     * The index of the activity that in in line for execution
     */
    private int activityIndex;
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Creates the new abstract process
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     */
    public Process(ModelInstance modelInstance, String elementName)
    {
        super(modelInstance, elementName, TYPE_PROCESS);
        activityIndex = 0;
        startingElements = new ArrayList<IStartingPoint>();
        variables = new ArrayList<IData>();
        channels = new ArrayList<IChannel>();
        processElements = new ArrayList<IActivity>( );
        nameSpaces = new ArrayList<NameSpace>();
    }
    
    /**
     * Creates the new abstract process
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type in the metamodel
     */
    public Process(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
        
        variables = new ArrayList<IData>();
        processElements = new ArrayList<IActivity>( );
    }
    
    /**
     * Returns the description of the process
     * @return description
     */
    public String getDescription( )
    {
        return description;
    }

    /**
     * Sets the description for the process
     * @param description The new description
     */
    public void setDescription( String description )
    {
        this.description = description;
    }

    /**
     * Adds a next element in the process. The list is supposed to be in execution order.
     * @param element The new element to add.
     */
    public void addNextElement( IActivity element )
    {
        //System.out.println("Element added to process " + element.getName( ) + " " + element);
        processElements.add( element );
    }

    /**
     * Returns the list of all the activities of the process
     */
    public List<IActivity> getProcessElements( )
    {
        return processElements;
    }

    /**
     * Returns the list of all the data related to the process.
     */
    public List<IData> getData( )
    {
        return variables;
    }
    
    /**
     * Returns a list of all the channels in the process
     * @return
     */
    public List<IChannel> getChannels( )
    {
        return channels;
    }
    
    /**
     * Adds a channel to the process
     * @param channel
     */
    public void addChannel( IChannel channel )
    {
        if(!channels.contains( channel ))
        {
            channels.add( channel );
            //System.out.println("Channel added " + channel.getName( ) );
        }
            
    }

    /**
     * Returns the exit on standard fault property if the process
     * @return true if the suppress join failure property is true
     */
    public boolean isExitOnStandardFault( )
    {
        return exitOnStandardFault;
    }

    /**
     * Changes the value of the exit on standard fault property
     * @param suppressJoinFailure The new value for the mroperty
     */
    public void setExitOnStandardFault( boolean exitOnStandardFault )
    {
        this.exitOnStandardFault = exitOnStandardFault;
    }


    /**
     * @return the queryLanguage
     */
    public String getQueryLanguage( )
    {
        return queryLanguage;
    }

    /**
     * @param queryLanguage the queryLanguage to set
     */
    public void setQueryLanguage( String queryLanguage )
    {
        this.queryLanguage = queryLanguage;
    }

    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( )
    {
        return expressionLanguage;
    }

    /**
     * @param expressionLanguage the expressionLanguage to set
     */
    public void setExpressionLanguage( String expressionLanguage )
    {
        this.expressionLanguage = expressionLanguage;
    }

    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return suppressJoinFailure;
    }
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        useProcessSuppress = false;
    }

    /**
     * @param suppressJoinFailure the suppressJoinFailure to set
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
    }
    
    /**
     * Returns the variable identified with the respective name
     */
    public IData getData( String variableName )
    {
        IData variable = null;
        for(int i=0;i<variables.size( ) && variable == null;i++)
        {
            IData temp = variables.get( i );
            variable = temp.getName( ).equals( variableName ) ? temp : null;   
        }
        return variable;
    }
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( )
    {
        return null;
    }
    
    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess )
    {
        //Does nothing
    }
    
    /**
     * Executes the next activity. If there are no more activities to execute, the the process fires the finalized event
     */
    public void executeNextActivity( )
    {
        if(activityIndex < processElements.size( ))
        {
            IActivity activity = processElements.get( activityIndex ); 
            activity.execute( );
            activityIndex++;
        }
        else
            finalizedProcess();
    }
    
    /**
     * Adds a new starting element to the list
     * @param activity
     */
    public void addStartingElement( IActivity activity )
    {
        startingElements.add((IStartingPoint)activity);
    }
    
    /**
     * Returns a list of the starting points of the process
     * @return
     */
    public List<IStartingPoint> getStartingPoints( )
    {
        return startingElements;
    }
    
    /**
     * Sets the instance space id
     * 
     * @param isID
     */
    public void setInstanceSpaceId(InstanceId isID)
    {
        this.instanceSpaceId = isID;
    }

    /**
     * Returns the instance space id
     * @return instanceSpaceId
     */
    public InstanceId getInstanceSpaceId()
    {
        return this.instanceSpaceId;
    }
    
    /**
     * This method registers in the EventsManager of the element, all the events that are generated by this entity.<br>
     * This method also creates the generators for the entity's events
     */
    public void registerGeneratedEvents()
    {
        generatorProcessActivate = createAndRegisterEventGenerator(PROCESS_ACTIVATE);
        generatorProcessFinalized = createAndRegisterEventGenerator(PROCESS_FINALIZED);

    }
    
    public void initialize()
    {

    }

    
    /**
     * Initializes the element
     */
    public void initializeProcess()
    {
        initiateProcess();
    }

    /**
     * Initializes the element
     */
    protected void initiateProcess()
    {
        generatorProcessActivate.generate( );
    }

    /**
     * Stops the process
     */
    public void stop( )
    {
        notYetTerminated = 0;
        for( int i = 0; i < processElements.size( ); i++ )
        {
            notYetTerminated += processElements.get( i ).getElementCount( );
        }
        for( int i = 0; i < processElements.size( ); i++ )
        {
            IBasicElement element = processElements.get( i );
            element.stop( );
        }

        if( notYetTerminated == 0 )
        {
            terminate( );
        }
    }

    /**
     * Returns notYetTerminated
     * 
     * @return notYetTerminated
     */
    public int getNotYetTerminated()
    {
        return notYetTerminated;
    }

    /**
     * Terminates the process
     */
    protected void terminate( )
    {
        processEnded( );
        finalizedProcess( );
    }

    /**
     * Method called when all activities are finished executing
     */
    protected void finalizedProcess( )
    {
        activityIndex = 0;
        generatorProcessFinalized.generate( );
        engine.getProcessSpace( instanceSpaceId.getProcessId( ) ).getInstance( instanceSpaceId.getInstanceId( ) ).setFinishedDate( );
        getModelInstance( ).getRuntime( ).stop( );
    }

    
    /**
     * Notifies that the process ended
     */
    private void processEnded()
    {

    }
    
    /**
     * Notifies that an element has terminated
     * @param e The element that stopped
     */
    public void notifyElementTerminated( IActivity e )
    {
        notYetTerminated--;
        if( notYetTerminated == 0 )
        {
            terminate( );
        }
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1 + processElements.size( );
    }

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the engine
     */
    public IProcessEngine getEngine( )
    {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine( IProcessEngine engine )
    {
        this.engine = engine;
    }
    
    public void addNameSpace( NameSpace space )
    {
        nameSpaces.add(space);
    }
    
    public List<NameSpace> getNameSpaces( )
    {
        return nameSpaces;
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IProcess.TYPE_PROCESS;
    }
}
