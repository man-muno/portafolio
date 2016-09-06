package uniandes.cumbia.bpel.elements;

public interface IInteraction extends IActivity
{

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * @return the channel
     */
    public IChannel getChannel( );

    /**
     * @param channel the channel to set
     */
    public void setChannel( IChannel channel);

    /**
     * Notifies the element that a message was received
     * @param message
     */
    public void notifyMessageReceived( Object message );
    
}
