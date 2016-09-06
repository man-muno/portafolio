package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.AbstractLoader;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a partner link
 */
public class ChannelOrganizer extends AbstractLoader
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String ATTRIBUTE_NAME = "name";

    public static final String ATTRIBUTE_SUPPRESED_JOIN_FAILURE = "suppresJoinFailure";

    private static final String ATTRIBUTE_PARTNER_LINK_TYPE = "partnerLinkType";

    private static final String ATTRIBUTE_MY_ROLE = "myRole";

    private static final String ATTRIBUTE_PARTNER_ROLE = "partnerRole";

    private static final String ATTRIBUTES_INIT_PARNTER_ROLE = "initializePartnerRole";

    private static final String ATTRIBUTE_PARTNER_LINK_URI = "partnerLinkUri";
    
    // -----------------------------------------------------------------
    // Atributes
    // -----------------------------------------------------------------
    
    /**
     * Element being loaded
     */
    protected IBasicElement element;

    /**
     * The instance where the elements are being organized
     */
    protected ModelInstance modelInstance;
    
    /**
     * The process owner of the element. Null if the element is the process 
     */
    protected IProcess parentProcess;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the partner link
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the partner link
     */
    public ChannelOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node);
        this.modelInstance = modelInstance;
        this.parentProcess = parentProcess;

        String partnerLinkName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( partnerLinkName );
        element.setParentProcess( parentProcess );
        ( ( IChannel )element ).setName( partnerLinkName );

    }

    /**
     * Loads all the partner link information
     * @throws LoaderException If there is any problem loading the partner link
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IChannel channel = null;
        try
        {
            channel = ( IChannel )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPartnerLink.", e );
        }

        // Sets the attributes
        String name = getAttribute( ATTRIBUTE_NAME );
        channel.setName( name );

        String type = getAttribute( ATTRIBUTE_PARTNER_LINK_TYPE );
        channel.setType( type );

        String myRole = getAttribute( ATTRIBUTE_MY_ROLE );
        channel.setMyRole( myRole );

        String partnerRole = getAttribute( ATTRIBUTE_PARTNER_ROLE );
        channel.setPartnerRole( partnerRole );

        String partnerLinkURI = getAttribute( ATTRIBUTE_PARTNER_LINK_URI );
        channel.setPartnerLinkURI( partnerLinkURI );

        String initializePartnerRole = getAttribute( ATTRIBUTES_INIT_PARNTER_ROLE );
        if( initializePartnerRole != null )
            channel.setInitializePartnerRole( Boolean.parseBoolean( initializePartnerRole ) );
        ////System.out.println( "PartnerLink " + channel.getName( ) + " orgaixed" );
    }

    /**
     * Returns the partner link being loaded
     * @return the partner link being loaded
     */
    public IChannel getChannel( )
    {
        return ( IChannel )element;
    }

}
