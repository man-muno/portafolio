package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPick;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a pick element
 */
public class StartingPickOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_ON_MESSAGE = "onMessage";
    
    private static final String ATTRIBUTE_PARTNER_LINK = "partnerLink";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the pick
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the pick
     */
    public StartingPickOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );
        
        String pickName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( pickName );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( pickName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );

        if( suppresed == null )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );

        ( ( IActivity )element ).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }

    /**
     * Returns the pick being loaded
     * @return the pick being loaded
     */
    public IStartingPick getActivity( )
    {
        return ( IStartingPick )element;
    }

    /**
     * Loads all the pick information
     * @throws LoaderException If there is any problem loading the pick
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IStartingPick pick = null;
        try
        {
            pick = ( IStartingPick )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPick.", e );
        }

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ( ( IActivity )element ) == null )
            throw new LoaderException( "The pick element must have a name defined" );

        Node onMessageNode = getChild( ELEMENT_ON_MESSAGE );
        OnMessageOrganizer onMessageLoader = new OnMessageOrganizer( onMessageNode, modelInstance, parentProcess );
        onMessageLoader.organizeInternalStructure( );
        IOnMessage onMessage = ( IOnMessage )onMessageLoader.getActivity( );
        pick.setMessageExchange( onMessage.getMessageExchange( ) );
        pick.setOnMessage( onMessage.getActivity( ) );
        pick.setOperation( onMessage.getOperation( ));
        pick.setPortType( onMessage.getOperation( ));
        String partnerLinkName = getAttribute( onMessageNode,ATTRIBUTE_PARTNER_LINK );
        pick.setChannel( ( IChannel )modelInstance.getElement( partnerLinkName ) );
        parentProcess.addChannel(( IChannel )modelInstance.getElement( partnerLinkName ) );
    }
}
