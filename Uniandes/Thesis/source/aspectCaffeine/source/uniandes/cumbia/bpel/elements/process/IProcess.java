/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id: IProcess.java,v 1.2 2009/02/10 00:55:04 man-muno Exp $ 
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Todos los derechos reservados 2005 
 * 
 * Proyecto CUMBIA
 * Aplicaci�n: JCumbia
 * Autor: Daniel Francisco - 21/01/2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */
package uniandes.cumbia.bpel.elements.process;

import java.util.List;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPoint;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.caffeine.deployer.elements.NameSpace;
import uniandes.cumbia.openobjects.elements.IOpenObject;

/**
 * The services that must be provided by a process
 */
public interface IProcess extends IBasicElement, IOpenObject
{

	static final String TYPE_PROCESS = "Process";


    /**
     * Initializes the element
     */
    public void initializeProcess( );
    
    /**
     * Notifies that an element has terminated
     * @param e The element that stopped
     */
    void notifyElementTerminated( IActivity e );
    
    /**
     * Sets the description for the process
     * @param description The new description
     */
    public void setDescription( String description );
    
    /**
     * Returns the description of the process
     * @return description
     */
    public String getDescription( );
    
    /**
     * Adds a next element in the process. The list is supposed to be in order.
     * @param element The new element to add.
     */
    public void addNextElement( IActivity element );
    
    /**
     * Returns the list of all the activities of the process
     */
    public List<IActivity> getProcessElements();

    /**
     * Returns the list of all the variables related to the process.
     */
    public List<IData> getData( );

    /**
     * @return the queryLanguage
     */
    public String getQueryLanguage( );

    /**
     * @param queryLanguage the queryLanguage to set
     */
    public void setQueryLanguage( String queryLanguage );

    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( );

    /**
     * @param expressionLanguage the expressionLanguage to set
     */
    public void setExpressionLanguage( String expressionLanguage );

    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( );

    /**
     * @param suppressJoinFailure the suppressJoinFailure to set
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure );
    
    /**
     * Initialize the element
     */
    public void initialize();
    
    /**
     * Stops the element
     */
    public void stop( );
    
    /**
     * Returns the exit on standard fault property if the process
     * @return true if the suppress join failure property is true
     */
    public boolean isExitOnStandardFault( );

    /**
     * Changes the value of the exit on standard fault property
     * @param suppressJoinFailure The new value for the mroperty
     */
    public void setExitOnStandardFault( boolean exitOnStandardFault );

    /**
     * Returns a variable given its name
     * @param variableName The name of the variable
     * @return The variable representing the name
     */
    public IData getData( String variableName );

    /**
     * Sets the instance space id
     * 
     * @param isID
     */
    public void setInstanceSpaceId(InstanceId isID);
    
    /**
     * Returns the instance space id
     * @return instanceSpaceId
     */
    public InstanceId getInstanceSpaceId();
    
    /**
     * @return the engine
     */
    public IProcessEngine getEngine( );

    /**
     * @param processEngine the engine to set
     */
    public void setEngine( IProcessEngine processEngine );
    
    /**
     * Returns a list of the starting points of the process
     * @return
     */
    public List<IStartingPoint> getStartingPoints( );

    /**
     * Adds a new starting element to the list
     * @param activity
     */
    public void addStartingElement( IActivity activity );

    /**
     * Executes the next activity. If there are no more activities to execute, the the process fires the finalized event
     */
    public void executeNextActivity( );
    
    public void addNameSpace( NameSpace space );

    public List<NameSpace> getNameSpaces( );

    /**
     * Returns a list of all the channels in the process
     * @return
     */
    public List<IChannel> getChannels( );

    /**
     * Adds a channel to the process
     * @param channel
     */
    public void addChannel( IChannel channel );
    
    /**
     * Sends the execute event for process created inside an advice
     */
    public void sendExecuteEvent( );
}
