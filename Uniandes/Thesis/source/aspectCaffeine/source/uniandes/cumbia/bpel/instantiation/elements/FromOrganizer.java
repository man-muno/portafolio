package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a from element
 */
public class FromOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String ELEMENT_QUERY = "query";

    public static final String ATTRIBUTE_QUERY_LANGUATE = "queryLanguage";

    public static final String ELEMENT_LITERAL = "literal";

    public static final String ATTRIBUTE_PARNTER_LINK = "partnerLink";

    public static final String ATTRIBUTE_END_POINT_REFERENCE = "endPointReference";

    public static final String ATTRIBUTE_VARIABLE = "variable";

    public static final String ATTRIBUTE_PART = "part";

    public static final String ATTRIBUTE_PROPERTY = "property";

    public static final String ATTRIBUTE_EXPRESSION_LANGUAGE = "expressionLanguage";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Element being loaded
     */
    protected IFrom from;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    /**
     * Constructor of the loader
     * @param node The node for loading the from
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the from
     */
    public FromOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        ////System.out.println("FromOrganizer constructor begin");
        String fromName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(fromName);
        ////System.out.println("FromOrganizer constructor begin 2 " + fromName);
        element.setParentProcess(parentProcess);
        ////System.out.println("FromOrganizer constructor begin 2a");
        ((IBasicElement)element).setName( fromName );
        ////System.out.println("FromOrganizer constructor begin 2b");
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        ////System.out.println("FromOrganizer constructor begin 3");
        if(suppresed == null)
            ((IFrom)element).useActivitySupressJoinFailure();
       
        ((IFrom)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        from = (IFrom) element;
        ////System.out.println("FromOrganizer constructor");
    }

    /**
     * Returns the from being loaded
     * @return the from being loaded
     */
    public IActivity getActivity( )
    {
        return null;
    }

    /**
     * Loads all the from information
     * @throws LoaderException If there is any problem loading the from
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IFrom from = null;
        try
        {
            from = ( IFrom )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IFrom.", e );
        }

        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        String part = getAttribute( ATTRIBUTE_PART );
        String partnerLink = getAttribute( ATTRIBUTE_PARNTER_LINK );
        String endPointReference = getAttribute( ATTRIBUTE_END_POINT_REFERENCE );
        String property = getAttribute( ATTRIBUTE_PROPERTY );
        String expression = node.getTextContent( );
        Node literalNode = getChild( ELEMENT_LITERAL );

        ////System.out.println("FromOrganizer attribs set");
        
        // The variable variant is used
        if( variable != null && part != null )
        {
            Node queryNode = getChild( ELEMENT_QUERY );
            String queryLanguage = getAttribute( queryNode, ATTRIBUTE_QUERY_LANGUATE );
            String queryContent = queryNode.getFirstChild( ).getNodeValue( );
            from.setQueryContent( queryContent );
            from.setQueryLanguage( queryLanguage );
            from.setVariable( element.getParentProcess( ).getData( variable ) );
            from.setPart( part );
            from.setType( IFrom.VARIABLE_VARIANT );
        }
        else if( partnerLink != null && endPointReference != null )// The partner link variant is used
        {
            from.setChannel( (IChannel)modelInstance.getElement( partnerLink ) );
            from.setEndPointReference( endPointReference );
            from.setType(IFrom.PARTNER_LINK_VARIANT);
        }
        else if( variable != null && property != null )// The property variant
        {
            from.setVariable( element.getParentProcess( ).getData( variable ) );
            from.setProperty( property );
            from.setType(IFrom.PROPERTY_VARIANT);
        }
        else if( expression != null )// The expression variant
        {
            String expressionLanguage = getAttribute( ATTRIBUTE_EXPRESSION_LANGUAGE );      
            from.setExpressionLanguage( expressionLanguage );
            from.setExpression( expression );
            from.setType(IFrom.EXPRESSION_VARIANT);
        }
        else if( literalNode != null )// The literal variant
        {
            from.setLiteralValue( literalNode.getFirstChild( ).getNodeValue( ) );
            from.setType(IFrom.LITERAL_VARIANT);            
            
        }
    }
    
    /**
     * Returns the from being loaded
     * @return the from being loaded
     */
    public IFrom getElement( )
    {
        return ( IFrom )element;
    }
}
