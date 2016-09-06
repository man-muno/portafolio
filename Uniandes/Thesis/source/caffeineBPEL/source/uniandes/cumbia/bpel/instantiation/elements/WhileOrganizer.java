package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.While.IWhile;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a while element
 */
public class WhileOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private static final String ELEMENT_CONDITION = "condition";

    private static final String ATTRIBUTE_EXPR_LANGUAGE = "expresionLanguage";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the while
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the while
     */
    public WhileOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String whileName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(whileName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( whileName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

    }


    /**
     * Returns the while being loaded
     * @return the while being loaded
     */
    public IWhile getActivity( )
    {
        return ( IWhile )element;
    }

    /**
     * Loads all the while information
     * @throws LoaderException If there is any problem loading the while
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IWhile whillie = null;
        try
        {
            whillie = ( IWhile )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IWhile.", e );
        }

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ((IActivity)element).getName( ) == null )
            throw new LoaderException( "The while element must have a name defined" );

        Node conditionNode = getChild( ELEMENT_CONDITION );
        String expressionLanguage = getAttribute( conditionNode, ATTRIBUTE_EXPR_LANGUAGE );
        whillie.setConditionLanguage( expressionLanguage );
        whillie.setCondition( conditionNode.getFirstChild( ).getNodeValue( ) );

        Node activity = conditionNode.getNextSibling( );
        
        while(activity.getNodeType( ) == Node.TEXT_NODE)
            activity = activity.getNextSibling( );
        
        ActivityOrganizer activityElseOrganizer = new ActivityOrganizer( activity, modelInstance, parentProcess );
        activityElseOrganizer.organizeInternalStructure( );
        if( activityElseOrganizer.getActivity( ) != null )
            whillie.setActivity( ( IActivity)activityElseOrganizer.getActivity( ) );

    }
}
