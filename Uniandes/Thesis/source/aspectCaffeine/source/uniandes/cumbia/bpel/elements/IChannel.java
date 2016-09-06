package uniandes.cumbia.bpel.elements;


public interface IChannel extends IBasicElement
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_CHANNEL = "Channel";
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns the message for the channel
     * @return The message for the channel
     */
    public IData getInboundMessage();
    
    /**
     * Returns the message for the channel
     * @return The message for the channel
     */
    public IData getOutboundMessage();
    
    /**
     * Changes the message for the channel
     * @param message The new message for the channel
     */
    public void setInboundMessage(IData message);
    
    /**
     * Changes the message for the channel
     * @param message The new message for the channel
     */
    public void setOutboundMessage(IData message);
    
    /**
     * Changes the initializePartnerRole attribute
     * @param b The new value for the initializePartnerRole attribute
     */
    public void setInitializePartnerRole( boolean b );

    /**
     * Changes the value of the partnerRole attribute
     * @param The new value for the partnerRole attribute
     */
    public void setPartnerRole( String partnerRole );

    /**
     * The new value for the partnerLinkType attribute
     * @param type The new partner link type
     */
    public void setType( String type );

    /**
     * Returns the name of the partner link
     * @return The name of the partner link
     */
    public String getPartnerLinkName( );

    /**
     * @return the partnerLinkType
     */
    public String getPartnerLinkType( );

    /**
     * @param partnerLinkType the partnerLinkType to set
     */
    public void setPartnerLinkType( String partnerLinkType );

    /**
     * @return the myRole
     */
    public String getMyRole( );

    /**
     * @param myRole the myRole to set
     */
    public void setMyRole( String myRole );

    /**
     * @return the initializeParnterRole
     */
    public boolean isInitializeParnterRole( );

    /**
     * @param initializeParnterRole the initializeParnterRole to set
     */
    public void setInitializeParnterRole( boolean initializeParnterRole );

    /**
     * @return the partnerRole
     */
    public String getPartnerRole( );

    /**
     * Changes the value of the partner link uri
     * @param partnerLinkURI
     */
    public void setPartnerLinkURI( String partnerLinkURI );
    
    /**
     * Returns the partner link URI
     * @return The partner Link URI
     */
    public String getPartnerLinkURI();
}
