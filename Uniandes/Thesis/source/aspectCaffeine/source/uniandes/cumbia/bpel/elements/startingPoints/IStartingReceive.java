package uniandes.cumbia.bpel.elements.startingPoints;


public interface IStartingReceive extends IStartingPoint
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_STARTING_RECEIVE = "StartingReceive";    

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Changes the receive's port type
     * @param portType The new port type for the element
     */
    public void setPortType( String portType );

    /**
     * Changes the receive's operation 
     * @param operation The new operation for the element
     */
    public void setOperation( String operation );

    /**
     * Changes the message exchange attribute 
     * @param messageExchange The new value for the attribute
     */
    public void setMessageExchange( String messageExchange );

    /**
     * @return the portType
     */
    public String getPortType( );

    /**
     * @return the operation
     */
    public String getOperation( );

    /**
     * @return the messageExchange
     */
    public String getMessageExchange( );
}