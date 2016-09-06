package uniandes.cumbia.bpel.elements.receive;

import java.util.List;

import uniandes.cumbia.bpel.elements.ICorrelation;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


/**
 * Represents a Receive bpel activity
 */
public class Receive extends AbstractReceive
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------


    /**
     * The portType (optional) for the receive
     */
    private String portType;

    /**
     * The operation that it expects the partner to invoke
     */
    private String operation;

    /**
     * The optional messageExchange attribute is used to disambiguate the relationship between inbound message activities (IMA) and <reply> activities. The explicit use of
     * messageExchange is needed only where the execution can result in multiple IMA-<reply> pairs (e.g. <receive>-<reply> pair) on the same partnerLink and operation being
     * executed simultaneously.
     */
    private String messageExchange;

    /**
     * List of correlations
     */
    private List<ICorrelation> correlations;

    /**
     * The parent process
     */
    private IProcess parentProcess;
    
    /**
     * Determines whether the joinFailure fault will be suppressed for all activities in the process. The effect of the attribute at the process level can be overridden by an
     * activity using a different value for the attribute. The default for this attribute is "no" at the process level.
     */
    protected boolean suppressJoinFailure = false;
    
    /**
     * Name of the element 
     */
    protected String name;
    
    /**
     * The element does'nt have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    
    public Receive(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
    }


    /**
     * Changes the receive's port type
     * @param portType The new port type for the element
     */
    public void setPortType( String portType )
    {
        this.portType = portType;
    }

    /**
     * Changes the receive's operation
     * @param operation The new operation for the element
     */
    public void setOperation( String operation )
    {
        this.operation = operation;
    }

    /**
     * Changes the message exchange attribute
     * @param messageExchange The new value for the attribute
     */
    public void setMessageExchange( String messageExchange )
    {
        this.messageExchange = messageExchange;
    }

    /**
     * @return the correlations
     */
    public List<ICorrelation> getCorrelations( )
    {
        return correlations;
    }

    /**
     * @param correlations the correlations to set
     */
    public void setCorrelations( List<ICorrelation> correlations )
    {
        this.correlations = correlations;
    }

    /**
     * @return the portType
     */
    public String getPortType( )
    {
        return portType;
    }

    /**
     * @return the operation
     */
    public String getOperation( )
    {
        return operation;
    }

    /**
     * @return the messageExchange
     */
    public String getMessageExchange( )
    {
        return messageExchange;
    }
    
    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }
    
    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
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
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return useProcessSuppress ? parentProcess.isSuppressJoinFailure( ) : suppressJoinFailure;
    }
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        useProcessSuppress = false;
    }
    
    
    /**
     * Stops the exit
     */
    public void stop( )
    {
        parentProcess.notifyElementTerminated( this );
    }
    

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        
    }

    public void notifyMessageReceived( Object message )
    {
        channel.getInboundMessage( ).addObject( "payload", message);
        messageReceived();
    }

    
    /**
     * Method that begins execution of the activity
     */
    public void execute()
    {
        activateReceive( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitReceive( );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IReceive.TYPE_RECEIVE;
    }
}
