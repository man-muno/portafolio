package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.wait.IWait;
import uniandes.cumbia.bpel.elements.wait.Wait;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a wait element
 */
public class WaitOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ELEMENT_FOR = "for";
    
    private static final String ELEMENT_UNTIL = "until";
    
    private static final String ATTRIBUTE_EXPRESSION_LANGUAGE = "expressionLanguage";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the wait
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the wait
     */
    public WaitOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String waitName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(waitName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( waitName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

    }


    /**
     * Returns the wait being loaded
     * @return the wait being loaded
     */
    public IWait getActivity( )
    {
        return (IWait) element;
    }
    
    /**
     * Loads all the wait information
     * @throws LoaderException If there is any problem loading the wait
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IWait wait = null;
        try
        {
            wait = ( IWait )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IWait.", e );
        }

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)element).setName(name);
        
        if(((IActivity)element).getName( )==null)
            throw new LoaderException("The wait element must have a name defined");
        
        Node forNode = getChild( ELEMENT_FOR );
        Node untilNode = getChild( ELEMENT_UNTIL );
        String expressionLanguage = forNode != null ? getAttribute( forNode, ATTRIBUTE_EXPRESSION_LANGUAGE ) : getAttribute( untilNode, ATTRIBUTE_EXPRESSION_LANGUAGE ); 
        wait.setExpressionLanguage( expressionLanguage );
        String expression = forNode != null ? forNode.getFirstChild( ).getNodeValue( ) : untilNode.getFirstChild( ).getNodeValue( );
        wait.setExpression( expression.split( "'" )[1] );
        wait.setType( forNode != null ? Wait.TYPE_FOR : Wait.TYPE_UNTIL );
        
    }



}
