package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.reply.IReply;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a reply element
 */
public class ReplyOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ATTRIBUTE_PARTNER_LINK = "partnerLink";

    private static final String ATTRIBUTE_PORT_TYPE = "portType";

    private static final String ATTRIBUTE_OPERATION = "operation";

    private static final String ATTRIBUTE_VARIABLE = "variable";

    private static final String ATTRIBUTE_FAULT_NAME = "faultName";

    private static final String ATTRIBUTE_MESSAGE_EXCHANGE = "messageExchange";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the reply
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the reply
     */
    public ReplyOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );
        String replyName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( replyName );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( replyName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        if( suppresed == null || suppresed.equalsIgnoreCase( "no" ) )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );
        else if( suppresed.equalsIgnoreCase( "yes" ) )
            ( ( IActivity )element ).setSuppressJoinFailure( true );
    }

    /**
     * Returns the reply being loaded
     * @return the reply being loaded
     */
    public IReply getActivity( )
    {
        return ( IReply )element;
    }

    /**
     * Loads all the reply information
     * @throws LoaderException If there is any problem loading the reply
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IReply reply = null;
        try
        {
            reply = ( IReply )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IReply.", e );
        }

        reply.setParentProcess( parentProcess );

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ( ( IActivity )element ).getName( ) == null )
            throw new LoaderException( "The reply element must have a name defined" );

        String partnerLinkName = getAttribute( ATTRIBUTE_PARTNER_LINK );
        IChannel channel = ( IChannel )modelInstance.getElement( partnerLinkName );
        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        channel.setOutboundMessage( parentProcess.getData( variable ) );
        ////System.out.println("ReplyOrganizer variable name " + variable);
        ////System.out.println("ReplyOrganizer variable name " +  parentProcess.getData( variable ) );
        ((IInteraction)reply).setChannel( channel );
        parentProcess.addChannel(channel);
        String portType = getAttribute( ATTRIBUTE_PORT_TYPE );
        reply.setPortType( portType );
        String operation = getAttribute( ATTRIBUTE_OPERATION );
        reply.setOperation( operation );
        String faultName = getAttribute( ATTRIBUTE_FAULT_NAME );
        reply.setFaultName( faultName );
        String messageExchange = getAttribute( ATTRIBUTE_MESSAGE_EXCHANGE );
        reply.setMessageExchange( messageExchange );
        ////System.out.println("ReplyOrganizer : " + ((IInteraction)reply).getChannel( ));
        ////System.out.println("ReplyOrganizer : " + ((IInteraction)reply).getChannel( ).getOutboundMessage( ));
        ////System.out.println("ReplyOrganizer : " + parentProcess.getData( ((IInteraction)reply).getChannel( ).getOutboundMessage( ).getName( ) ).getName( ));
    }
}
