package uniandes.cumbia.bpel.instantiation.elements;

import java.util.Iterator;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingReceive;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a receive element
 */
public class StartingReceiveOrganizer extends BaseActivityOrganizer
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

    private IStartingReceive startingReceive;

    

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the receive
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the receive
     */
    public StartingReceiveOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );

        String receiveName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( receiveName );
        Iterator<String> it = modelInstance.getElements( ).keySet( ).iterator( );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( receiveName );

        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        if( suppresed == null || suppresed.equalsIgnoreCase( "no" ) )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );
        else if( suppresed.equalsIgnoreCase( "yes" ) )
            ( ( IActivity )element ).setSuppressJoinFailure( true );

        ////System.out.println("StartingReceiveOrganizer constructor");
        
    }

    /**
     * Returns the receive being loaded
     * @return the receive being loaded
     */
    public IStartingReceive getActivity( )
    {
        return startingReceive;
    }

    /**
     * Loads all the receive information
     * @throws LoaderException If there is any problem loading the receive
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        
        try
        {
            startingReceive = ( IStartingReceive )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPartnerLink.", e );
        }
        
        ////System.out.println("StartingReceiveOrganizer organizeInternalStructure");
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )startingReceive ).setName( name );

        startingReceive.setParentProcess( parentProcess );
        ////System.out.println("setting the process " + name);
        if( startingReceive.getName( ) == null )
            throw new LoaderException( "The receive element must have a name defined" );

        String partnerLinkName = getAttribute( ATTRIBUTE_PARTNER_LINK );
        ////System.out.println("Channel name " + partnerLinkName);
        IChannel channel = ( IChannel )modelInstance.getElement( partnerLinkName );
        ////System.out.println("Channel " + channel + " " + channel.getInboundMessage( ));
        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        ////System.out.println("StartingReceiveOrganizer variable name " + variable);
        ////System.out.println("StartingReceiveOrganizer variable name " +  parentProcess.getData( variable ) );
        channel.setInboundMessage( parentProcess.getData( variable ) );
        ( ( IInteraction )startingReceive ).setChannel( channel );
        parentProcess.addChannel(channel);
        ////System.out.println("StartingReceiveOrganizer organizeInternalStructure");
        String portType = getAttribute( ATTRIBUTE_PORT_TYPE );
        startingReceive.setPortType( portType );
        String operation = getAttribute( ATTRIBUTE_OPERATION );
        startingReceive.setOperation( operation );
        String messageExchange = getAttribute( ATTRIBUTE_MESSAGE_EXCHANGE );
        startingReceive.setMessageExchange( messageExchange );
        
        
        ////System.out.println("StartingReceiveOrganizer : " + ((IInteraction)startingReceive).getChannel( ));
        ////System.out.println("StartingReceiveOrganizer : " + ((IInteraction)startingReceive).getChannel( ).getInboundMessage( ));
        ////System.out.println("StartingReceiveOrganizer : " + parentProcess.getData( ((IInteraction)startingReceive).getChannel( ).getInboundMessage( ).getName( ) ).getName( ));
        
        ////System.out.println("StartingReceiveOrganizer organizeInternalStructure");

    }
}
