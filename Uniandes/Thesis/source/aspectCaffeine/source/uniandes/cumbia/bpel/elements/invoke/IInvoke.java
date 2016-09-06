package uniandes.cumbia.bpel.elements.invoke;

import uniandes.cumbia.bpel.elements.IInteraction;

/**
 * The services provided by the partner link
 */
public interface IInvoke extends IInteraction
{

    public static final String TYPE_INVOKE = "Invoke";
    
   
    /**
     * @return the portType
     */
    public String getPortType( );

    /**
     * @param portType the portType to set
     */
    public void setPortType( String portType );

    /**
     * @return the operation
     */
    public String getOperation( );

    /**
     * @param operation the operation to set
     */
    public void setOperation( String operation );

    /**
     * Sets the element to wait for a message
     */
    public void waitResponse( );

    /**
     * Sets the message received
     * @param outputMessage
     */
    public void messageReceived( Object outputMessage );

    /**
     * Sets that the invoke is finished 
     */
    public void finalized( );

    /**
     * Changes the type of the invoke to synchronous
     */
    public void setTypeSynch( );
    
    /**
     * Return true if the invoke is asynchronous
     * @return
     */
    public boolean isAsynch();
    

    /**
     * Re initializes the element
     */
    public void reInit();

}
