package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.conditional.If.IIf;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a If element
 */
public class IfOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ELEMENT_CONDITION = "condition";

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
    public IfOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
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
    public IIf getActivity( )
    {
        return ( IIf )element;
    }

    /**
     * Loads all the If information
     * @throws LoaderException If there is any problem loading the If
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IIf iif = null;
        try
        {
            iif = ( IIf )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IIf.", e );
        }

        // Gets the condition attributes
        Node condition = getChild( ELEMENT_CONDITION );
        setConditionValues( condition, iif );

        Node caseTrue = condition.getNextSibling( );
        
        while(caseTrue.getNodeType( ) == Node.TEXT_NODE)
            caseTrue = caseTrue.getNextSibling( );
        
        ActivityOrganizer activityLoader = new ActivityOrganizer( caseTrue, modelInstance, parentProcess );
        activityLoader.organizeInternalStructure( );
        if( activityLoader.getActivity( ) != null )
            iif.setActivity( ( IActivity )activityLoader.getActivity( ) );
    }

    /**
     * Obtains the expression language and the boolean expression of the condition node and sets it in the If element
     * @param condition The node that contains the information
     * @param iif The element where the information should be
     */
    private void setConditionValues( Node condition, IIf iif )
    {
        String expressionLanguage = getAttribute( condition, ATTRIBUTE_EXPR_LANGUAGE );

        String booleanExp = null;
        NodeList nodeList = condition.getChildNodes( );
        for( int j = 0; j < nodeList.getLength( ); j++ )
        {
            Node textNode = nodeList.item( j );
            if( textNode.getNodeType( ) == Node.TEXT_NODE )
            {
                booleanExp = textNode.getNodeValue( );
            }
        }
        iif.setConditionLanguage( expressionLanguage );
        iif.setCondition( booleanExp );
    }

}
