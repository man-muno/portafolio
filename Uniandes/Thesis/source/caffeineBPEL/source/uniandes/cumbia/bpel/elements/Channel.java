package uniandes.cumbia.bpel.elements;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class Channel extends AbstractChannel implements IChannel
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Name of the partner link
     */
    private String name;

    /**
     * Characterizes the conversational relationship between two services
     */
    private String partnerLinkType;

    /**
     * The role of the business process
     */
    private String myRole;

    /**
     * Role of the partner
     */
    private String partnerRole;

    /**
     * The initializePartnerRole attribute specifies whether the WS-BPEL processor is required to initialize a <partnerLink>'s partnerRole value.
     */
    private boolean initializeParnterRole;

    /**
     * The partner link URI
     */
    private String partnerLinkURI;

    /**
     * The message for the channel
     */
    private IData inboundMessage;

    /**
     * The message for the channel
     */
    private IData outboundMessage;

    /**
     * The parent process
     */
    private IProcess parentProcess;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public Channel( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Returns the message for the channel
     * @return The message for the channel
     */
    public IData getInboundMessage( )
    {
        return inboundMessage;
    }

    /**
     * Returns the message for the channel
     * @return The message for the channel
     */
    public IData getOutboundMessage( )
    {
        return outboundMessage;
    }

    /**
     * Changes the message for the channel
     * @param message The new message for the channel
     */
    public void setInboundMessage( IData inboundMessage )
    {
        //System.out.println( "Setting inboundMessage in channel " + this + " " + inboundMessage + " " + inboundMessage.getName( ) );
        this.inboundMessage = inboundMessage;
    }

    /**
     * Changes the message for the channel
     * @param message The new message for the channel
     */
    public void setOutboundMessage( IData outboundMessage )
    {
        //System.out.println( "Setting exitMessage in channel " + this + " " + outboundMessage + " " + outboundMessage.getName( ) );
        this.outboundMessage = outboundMessage;
    }

    /**
     * Changes the initializePartnerRole attribute
     * @param b The new value for the initializePartnerRole attribute
     */
    public void setInitializePartnerRole( boolean b )
    {
        initializeParnterRole = b;
    }

    /**
     * Changes the name of the partner link
     * @param name The new name for the partner link
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Changes the value of the partnerRole attribute
     * @param The new value for the partnerRole attribute
     */
    public void setPartnerRole( String partnerRole )
    {
        this.partnerRole = partnerRole;
    }

    /**
     * @return the parentProcess
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }

    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return parentProcess.isSuppressJoinFailure( );
    }
    /**
     * The new value for the partnerLinkType attribute
     * @param type The new partner link type
     */
    public void setType( String type )
    {
        this.partnerLinkType = type;
    }

    /**
     * Returns the name of the partner link
     * @return The name of the partner link
     */
    public String getPartnerLinkName( )
    {
        return name;
    }

    /**
     * @return the partnerLinkType
     */
    public String getPartnerLinkType( )
    {
        return partnerLinkType;
    }

    /**
     * @param partnerLinkType the partnerLinkType to set
     */
    public void setPartnerLinkType( String partnerLinkType )
    {
        this.partnerLinkType = partnerLinkType;
    }

    /**
     * @return the myRole
     */
    public String getMyRole( )
    {
        return myRole;
    }

    /**
     * @param myRole the myRole to set
     */
    public void setMyRole( String myRole )
    {
        this.myRole = myRole;
    }

    /**
     * @return the initializeParnterRole
     */
    public boolean isInitializeParnterRole( )
    {
        return initializeParnterRole;
    }

    /**
     * @param initializeParnterRole the initializeParnterRole to set
     */
    public void setInitializeParnterRole( boolean initializeParnterRole )
    {
        this.initializeParnterRole = initializeParnterRole;
    }

    /**
     * @return the name
     */
    public String getName( )
    {
        return name;
    }

    /**
     * @return the partnerRole
     */
    public String getPartnerRole( )
    {
        return partnerRole;
    }

    /**
     * Changes the value of the partner link uri
     * @param partnerLinkURI
     */
    public void setPartnerLinkURI( String partnerLinkURI )
    {
        this.partnerLinkURI = partnerLinkURI;
    }

    /**
     * Returns the partner link URI
     * @return The partner Link URI
     */
    public String getPartnerLinkURI( )
    {
        return partnerLinkURI;
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 0;
    }

    /**
     * Initialize the element
     */
    public void initialize( )
    {
        // Does nothing
    }

    /**
     * Establishes the parent process associated with this partner link
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }

    /**
     * Stops the element
     */
    public void stop( )
    {
        // Does nothing
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IChannel.TYPE_CHANNEL;
    }
}
