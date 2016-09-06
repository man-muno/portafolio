package uniandes.cumbia.bpel.instantiation.elements;

import java.util.Iterator;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.receive.IReceive;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a receive element
 */
public class ReceiveOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ATTRIBUTE_PARTNER_LINK = "partnerLink";
    
    private static final String ATTRIBUTE_PORT_TYPE = "portType";
    
    private static final String ATTRIBUTE_OPERATION = "operation";
    
    private static final String ATTRIBUTE_VARIABLE = "variable";
    
    private static final String ATTRIBUTE_MESSAGE_EXCHANGE = "messageExchange";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    private IReceive receive;
    
    
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the receive
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the receive
     */
    public ReceiveOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String receiveName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(receiveName);
        Iterator<String> it = modelInstance.getElements( ).keySet( ).iterator( );
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( receiveName );
        
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        if(suppresed == null || suppresed.equalsIgnoreCase( "no" ))
            ((IActivity)element).useActivitySupressJoinFailure();
        else if(suppresed.equalsIgnoreCase( "yes" ))
            ((IActivity)element).setSuppressJoinFailure( true );   
    }


    /**
     * Returns the receive being loaded
     * @return the receive being loaded
     */
    public IReceive getActivity( )
    {
        return receive;
    }
    
    /**
     * Loads all the receive information
     * @throws LoaderException If there is any problem loading the receive
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        
        try
        {
            receive = ( IReceive )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPartnerLink.", e );
        }
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)receive).setName(name);

        receive.setParentProcess( parentProcess );
        
        if(receive.getName( )==null)
            throw new LoaderException("The receive element must have a name defined");
        
        String partnerLinkName = getAttribute( ATTRIBUTE_PARTNER_LINK );
        IChannel channel = ( IChannel )modelInstance.getElement(partnerLinkName);
        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        channel.setInboundMessage( parentProcess.getData( variable ) );
        ((IInteraction)receive).setChannel( channel );
        parentProcess.addChannel(channel);
        String portType = getAttribute( ATTRIBUTE_PORT_TYPE);
        receive.setPortType(portType);
        String operation = getAttribute( ATTRIBUTE_OPERATION );
        receive.setOperation(operation);
        String messageExchange = getAttribute( ATTRIBUTE_MESSAGE_EXCHANGE );
        receive.setMessageExchange(messageExchange);
        
    }
}

