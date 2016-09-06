package uniandes.cumbia.bpel.elements.pick.onmessage;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.pick.IPick;

public interface IOnMessage extends IInteraction
{
    public static final String TYPE_ON_MESSAGE = "OnMessage";
    
    
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
     * Sets the activity to be executed when the messages arrives
     * @param The new activity
     */
    public void setActivity( IActivity activity );

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
     * Sets the pick to the on message
     * @param pick
     */
    public void setPick( IPick pick );
    
    /**
     * Returns the pick owner of the element
     */
    public IPick getPick();
}
