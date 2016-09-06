package uniandes.cumbia.bpel.elements;

import uniandes.cumbia.openobjects.elements.OpenObject;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public abstract class Interaction extends OpenObject implements IInteraction
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    
    /**
     * The channel of communication
     */
    protected IChannel channel;
    
    
    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates an interaction element with the possible channels
     */
    public Interaction(ModelInstance modelInstance, String elementName, String typeName)
    {
        super( modelInstance, elementName, typeName );
        this.channel = null;

    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * @return the channel
     */
    public IChannel getChannel( )
    {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel( IChannel channel)
    {
        this.channel = channel;
    }
}
