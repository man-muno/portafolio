package uniandes.cumbia.bpel.elements.pick.onmessage;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Activity similar to the receive. It waits for the receipt of a message
 */
public class OnMessage extends AbstractOnMessage
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
     * Activity to be executed if a message is received
     */
    protected IActivity activity;

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

    /**
     * The pick that this on message belongs to
     */
    private IPick pick;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public OnMessage( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        pick = null;
    }

    /**
     * Changes the port type for the onMessage
     * @param portType The new port type
     */
    public void setPortType( String portType )
    {
        this.portType = portType;
    }

    /**
     * Changes the operation defined for the onMessage
     * @param operation The new operation defined for the onMessage
     */
    public void setOperation( String operation )
    {
        this.operation = operation;
    }

    /**
     * Changes the message exchange attribute
     * @param messageExchange The new message exchange attribute
     */
    public void setMessageExchange( String messageExchange )
    {
        this.messageExchange = messageExchange;
    }

    /**
     * Sets the activity to be executed when the messages arrives
     * @param The new activity
     */
    public void setActivity( IActivity activity )
    {
        this.activity = activity;
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
     * @return the activity
     */
    public IActivity getActivity( )
    {
        return activity;
    }

    /**
     * Stops the exit
     */
    public void stop( )
    {
        activity.stop( );
        parentProcess.notifyElementTerminated( this );
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1 + activity.getElementCount( );
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
     * Sets the pick to the on message
     * @param pick
     */
    public void setPick( IPick pick )
    {
        this.pick = pick;
    }
    
    /**
     * Returns the pick owner of the element
     */
    public IPick getPick()
    {
        return pick;
    }
    
    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        activity.initialize( );
    }

    /**
     * Notifies the element that a message was received
     * @param message
     */
    public void notifyMessageReceived( Object message )
    {
        channel.getInboundMessage( ).addObject( "payload", message );
        messageReceived( );
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        activateOnMessage( );
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IOnMessage.TYPE_ON_MESSAGE;
    }

    /**
     * Re initializes the element
     */
    public void reInit()
    {
        //Does nothing
    }
}
