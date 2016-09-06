package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a onMessage element
 */
public class OnMessageOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ATTRIBUTE_PARTNER_LINK = "partnerLink";

    private static final String ATTRIBUTE_PORT_TYPE = "portType";

    private static final String ATTRIBUTE_OPERATION = "operation";

    private static final String ATTRIBUTE_VARIABLE = "variable";

    private static final String ATTRIBUTE_MESSAGE_EXCHANGE = "messageExchange";

    private static final String ELEMENT_CORRELATIONS = "correlations";

    private static final String ELEMENT_CORRELATION = "correlation";

    private static final String ELEMENT_FROM_PARTS = "fromParts";

    private static final String ELEMENT_FROM_PART = "fromPart";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the onMessage
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the onMessage
     */
    public OnMessageOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );

        String onMessageName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( onMessageName );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( onMessageName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );

        if( suppresed == null )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );

        ( ( IActivity )element ).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }

    /**
     * Returns the onMessage being loaded
     * @return the onMessage being loaded
     */
    public IOnMessage getActivity( )
    {
        return ( IOnMessage )element;
    }

    /**
     * Loads all the onMessage information
     * @throws LoaderException If there is any problem loading the onMessage
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IOnMessage onMessage = null;
        try
        {
            onMessage = ( IOnMessage )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IOnMessage.", e );
        }

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ( ( IActivity )element ).getName( ) == null )
            throw new LoaderException( "The onMessage element must have a name defined" );

        String partnerLinkName = getAttribute( ATTRIBUTE_PARTNER_LINK );
        IChannel channel = (IChannel)modelInstance.getElement( partnerLinkName );
        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        channel.setInboundMessage( parentProcess.getData( variable ) );
        ( ( IInteraction )onMessage ).setChannel( channel );
        parentProcess.addChannel(channel);

        String portType = getAttribute( ATTRIBUTE_PORT_TYPE );
        onMessage.setPortType( portType );

        String operation = getAttribute( ATTRIBUTE_OPERATION );
        onMessage.setOperation( operation );

        String messageExchange = getAttribute( ATTRIBUTE_MESSAGE_EXCHANGE );
        onMessage.setMessageExchange( messageExchange );

        List<Node> correlations = getChilds( ELEMENT_CORRELATION );

        List<Node> fromParts = getChilds( ELEMENT_FROM_PART );

        NodeList children = node.getChildNodes( );
        for( int i = 0; i < children.getLength( ); i++ )
        {
            Node child = children.item( i );
            if( !child.getNodeName( ).equals( ELEMENT_CORRELATION ) || !child.getNodeName( ).equals( ELEMENT_FROM_PARTS ) )
            {
                ActivityOrganizer activityOrganizer = new ActivityOrganizer( child, modelInstance, parentProcess );
                activityOrganizer.organizeInternalStructure( );
                if( activityOrganizer.getActivity( ) != null )
                    onMessage.setActivity( ( IActivity )activityOrganizer.getActivity( ) );
            }

        }

    }
}
