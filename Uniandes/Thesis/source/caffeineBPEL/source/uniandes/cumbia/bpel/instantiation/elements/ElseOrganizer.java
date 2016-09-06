package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.conditional.Else.IElse;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a If element
 */
public class ElseOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_CONDITION = "condition";

    private static final String ELEMENT_ELSE_IF = "elseif";

    private static final String ELEMENT_ELSE = "else";

    private static final String ATTRIBUTE_EXPR_LANGUAGE = "expresionLanguage";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the If
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the If
     */
    public ElseOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String ifName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(ifName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( ifName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }


    /**
     * Returns the If being loaded
     * @return the If being loaded
     */
    public IElse getActivity( )
    {
        return ( IElse )element;
    }

    /**
     * Loads all the If information
     * @throws LoaderException If there is any problem loading the If
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IElse elsse = null;
        try
        {
            elsse = ( IElse )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IElse.", e );
        }
        
        Node activity = node.getFirstChild( );
        
        while(activity.getNodeType( ) == Node.TEXT_NODE)
            activity = activity.getNextSibling( );
        
        
        ActivityOrganizer activityLoader = new ActivityOrganizer( activity, modelInstance, parentProcess );
        activityLoader.organizeInternalStructure( );
        if( activityLoader.getActivity( ) != null )
            elsse.setActivity( ( IActivity )activityLoader.getActivity( ) );
    }
}
