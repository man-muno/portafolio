package uniandes.cumbia.bpel.elements.startingPoints;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IStartingPick extends IStartingPoint
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String TYPE_STARTING_PICK = "StartingPick";

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Changes the onMessage element
     * @param element The new onMessage element
     */
    public void setOnMessage( IActivity element );

    /**
     * @return the onMessages
     */
    public IActivity getOnMessage( );

    /**
     * Changes the port type for the onMessage
     * @param portType The new port type
     */
    public void setPortType( String portType );

    /**
     * Changes the operation defined for the onMessage
     * @param operation The new operation defined for the onMessage
     */
    public void setOperation( String operation );

    /**
     * Changes the message exchange attribute
     * @param messageExchange The new message exchange attribute
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

    /**
     * @return the activity
     */
    public IActivity getActivity( );

    /**
     * @param activity the activity to set
     */
    public void setActivity( IActivity activity );
}