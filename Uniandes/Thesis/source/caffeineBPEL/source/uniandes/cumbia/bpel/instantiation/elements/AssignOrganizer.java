package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.assign.IAssign;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a assign element
 */
public class AssignOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ATTRIBUTE_VALIDATE = "validate";

    private static final String ELEMENT_COPY = "copy";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Element being loaded
     */
    protected IAssign assign;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the assign
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the assign
     */
    public AssignOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess )
    {
        super( node, modelInstance, parentProcess );

        String assignName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( assignName );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( assignName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );

        if( suppresed == null )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );

        ( ( IActivity )element ).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        assign = ( IAssign )element;
        ////System.out.println("Assign constructor");
    }

    /**
     * Returns the assign being loaded
     * @return the assign being loaded
     */
    public IAssign getActivity( )
    {
        return ( IAssign )element;
    }

    /**
     * Loads all the assign information
     * @throws LoaderException If there is any problem loading the assign
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        ////System.out.println("Assign organizeInternalStructure");
        // Fist cast the object for easier use and validation
        IAssign assign = null;
        try
        {
            assign = ( IAssign )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IAssign.", e );
        }

        assign.setParentProcess( parentProcess );

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ( ( IActivity )element ).getName( ) == null )
            throw new LoaderException( "The assign element must have a name defined" );

        // Sets the attributes
        String validate = getAttribute( ATTRIBUTE_VALIDATE );
        if( validate != null )
            assign.setValidate( Boolean.parseBoolean( validate ) );

        ////System.out.println("Assign attrbs set");
        
        List<Node> children = getChilds( ELEMENT_COPY );
        for( int i = 0; i < children.size( ); i++ )
        {
            Node child = children.get( i );
            ////System.out.println("AssignLoader node: " + child);
            CopyOrganizer copyOrganizer = new CopyOrganizer( child, modelInstance, element.getParentProcess( ) );
            copyOrganizer.organizeInternalStructure( );
            assign.addCopy( ( ICopy )copyOrganizer.getElement( ) );
        }
    }
}
