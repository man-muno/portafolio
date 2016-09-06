package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a elseIf element
 */
public class ElseIfOrganizer extends BaseActivityOrganizer
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
     * @param node The node for loading the elseIf
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the elseIf
     */
    public ElseIfOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String elseIfName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(elseIfName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( elseIfName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }


    /**
     * Returns the elseIf being loaded
     * @return the elseIf being loaded
     */
    public IElseIf getActivity( )
    {
        return (IElseIf) element;
    }
    
    /**
     * Loads all the else-if information
     * @throws LoaderException If there is any problem loading the If
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IElseIf elseIf = null;
        try
        {
            elseIf = ( IElseIf )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IElseIf.", e );
        }
        
        //Gets the condition attributes
        Node condition = getChild( ELEMENT_CONDITION );
        setConditionValues(condition, elseIf);
                
        Node activity = condition.getNextSibling( );
        
        while(activity.getNodeType( ) == Node.TEXT_NODE)
            activity = activity.getNextSibling( );
        
        ActivityOrganizer activityLoader = new ActivityOrganizer( activity, modelInstance, parentProcess );
        activityLoader.organizeInternalStructure( );
        if(activityLoader.getActivity( )!=null)
            elseIf.setActivity( ( IActivity )activityLoader.getActivity( ) );
    }

    /**
     * Obtains the expression language and the boolean expression of the condition node
     * and sets it in the If element
     * @param condition The node that contains the information
     * @param elseIf The element where the information should be
     */
    private void setConditionValues( Node condition, IElseIf elseIf )
    {
        String expressionLanguage = getAttribute( condition, ATTRIBUTE_EXPR_LANGUAGE );
        
        String booleanExp = null;
        NodeList nodeList = condition.getChildNodes( );
        for(int j=0;j<nodeList.getLength( );j++)
        {
            Node textNode = nodeList.item( j );
            if(textNode.getNodeType( ) == Node.TEXT_NODE)
            {
                booleanExp = textNode.getNodeValue( );
            }
        }
        elseIf.setConditionLanguage(expressionLanguage);
        elseIf.setCondition(booleanExp);
    }



}
