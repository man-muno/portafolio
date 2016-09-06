package uniandes.cumbia.bpel.elements.invoke;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents an Invoke bpel activity
 */
public class Invoke extends AbstractInvoke
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The <invoke> activity allows the business process to invoke a one-way or request-response operation on a portType offered by a partner.
     */
    private String portType;

    /**
     * The operation defined for this invoke
     */
    private String operation;

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
     * The type of the invoke
     */
    private boolean isAsynch = true;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Invoke( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    /**
     * @return the portType
     */
    public String getPortType( )
    {
        return portType;
    }

    /**
     * @param portType the portType to set
     */
    public void setPortType( String portType )
    {
        this.portType = portType;
    }

    /**
     * @return the operation
     */
    public String getOperation( )
    {
        return operation;
    }

    /**
     * @param operation the operation to set
     */
    public void setOperation( String operation )
    {
        this.operation = operation;
    }
    
    /**
     * Changes the type of the invoke a synchronous
     */
    public void setTypeSynch( )
    {
        isAsynch = false;
    }
    
    /**
     * Return true if the invoke is asynchronous
     * @return
     */
    public boolean isAsynch()
    {
        return isAsynch;
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
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1;
    }

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {

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
        sendMessage( );
    }
    
    /**
     * Sets the element to wait for a message
     */
    public void waitResponse( )
    {
        waitResponseInvoke();
    }

    /**
     * Sets the message received
     * @param outputMessage
     */
    public void messageReceived( Object outputMessage )
    {
        parentProcess.getData( channel.getInboundMessage( ).getName( ) ).addObject( "payload", outputMessage );
        messageReceived( );
    }

    /**
     * Sets that the invoke is finished 
     */
    public void finalized( )
    {
        finalizedInvoke( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitInvoke(  );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IInvoke.TYPE_INVOKE;
    }
}
