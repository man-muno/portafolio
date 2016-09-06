package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.conditional.IConditional;
import uniandes.cumbia.bpel.elements.conditional.Else.IElse;
import uniandes.cumbia.bpel.elements.conditional.If.IIf;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a If element
 */
public class ConditionalOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_ELSE_IF = "elseif";

    private static final String ELEMENT_ELSE = "else";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IConditional conditional;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the If
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the If
     */
    public ConditionalOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );

        String ifName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( "Conditional"+ifName );
        element.setParentProcess( parentProcess );
        ( ( IActivity )element ).setName( "Conditional"+ifName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );

        if( suppresed == null )
            ( ( IActivity )element ).useActivitySupressJoinFailure( );

        ( ( IActivity )element ).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }

    /**
     * Returns the If being loaded
     * @return the If being loaded
     */
    public IConditional getActivity( )
    {
        return ( IConditional )element;
    }

    /**
     * Loads all the If information
     * @throws LoaderException If there is any problem loading the If
     */
    public void organizeInternalStructure( ) throws LoaderException
    {

        // Fist cast the object for easier use and validation
        IConditional conditional = null;
        try
        {
            conditional = ( IConditional )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IIf.", e );
        }

        IfOrganizer ifOrganizer = new IfOrganizer(node, modelInstance, parentProcess);
        ifOrganizer.organizeInternalStructure( );
        IIf ipf = ifOrganizer.getActivity( );                        
        conditional.setIf( ipf );

        List<Node> elseifs = getChilds( ELEMENT_ELSE_IF );
        for( int i = 0; i < elseifs.size( ); i++ )
        {
            // Inside the else-if statement there's another if element
            ElseIfOrganizer elseIfOrganizer = new ElseIfOrganizer( elseifs.get( i ), modelInstance, parentProcess );
            elseIfOrganizer.organizeInternalStructure( );
            if((IElseIf)elseIfOrganizer.getActivity( )  != null)
                conditional.addElseIf( (IElseIf)elseIfOrganizer.getActivity( ) );
        }

        Node elsse = getChild( ELEMENT_ELSE );
        // Inside the else element is an activity
        if( elsse != null )
        {
            // Inside the else statement ther's another if element
            ElseOrganizer elseOrganizer = new ElseOrganizer( elsse, modelInstance, parentProcess );
            elseOrganizer.organizeInternalStructure( );
            conditional.setElse((IElse)elseOrganizer.getActivity( ) );
        }
        else
            conditional.setElse( null );

    }
}
