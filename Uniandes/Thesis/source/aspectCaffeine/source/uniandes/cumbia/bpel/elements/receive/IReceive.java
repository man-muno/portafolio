package uniandes.cumbia.bpel.elements.receive;

import java.util.List;

import uniandes.cumbia.bpel.elements.ICorrelation;
import uniandes.cumbia.bpel.elements.IInteraction;

public interface IReceive extends IInteraction
{

    public static final String TYPE_RECEIVE = "Receive";
    

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
     * @return the correlations
     */
    public List<ICorrelation> getCorrelations( );

    /**
     * @param correlations the correlations to set
     */
    public void setCorrelations( List<ICorrelation> correlations );

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
     * Notifies that a message has been received
     */
    public void notifyMessageReceived( Object message );
    
    /**
     * Re initializes the element
     */
    public void reInit();

}
