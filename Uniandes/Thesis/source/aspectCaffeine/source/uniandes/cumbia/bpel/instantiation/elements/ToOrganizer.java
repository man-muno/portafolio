package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.assign.to.ITo;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a to element
 */
public class ToOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String ELEMENT_QUERY = "query";

    public static final String ATTRIBUTE_QUERY_LANGUATE = "queryLanguage";

    public static final String ATTRIBUTE_PARNTER_LINK = "partnerLink";

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
    protected ITo to;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the to
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the to
     */
    public ToOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        ////System.out.println("ToOrganizer constructor begin");
        String toName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(toName);
        ////System.out.println("ToOrganizer constructor begin 2 " + toName);
        element.setParentProcess(parentProcess);
        ////System.out.println("ToOrganizer constructor begin 2a");
        ((IBasicElement)element).setName( toName );
        ////System.out.println("FromOrganizer constructor begin 2b");
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        ////System.out.println("ToOrganizer constructor begin 3");
        if(suppresed == null)
            ((ITo)element).useActivitySupressJoinFailure();
       
        ((ITo)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        to = (ITo) element;
        ////System.out.println("ToOrganizer constructor");
    }

    /**
     * Returns the to being loaded
     * @return the to being loaded
     */
    public IActivity getActivity( )
    {
        return null;
    }
    
    /**
     * Loads all the to information
     * @throws LoaderException If there is any problem loading the to
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        ITo to = null;
        try
        {
            to = ( ITo )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement ITo.", e );
        }


        String variable = getAttribute( ATTRIBUTE_VARIABLE );
        String part = getAttribute( ATTRIBUTE_PART );
        String partnerLink = getAttribute( ATTRIBUTE_PARNTER_LINK );
        String property = getAttribute( ATTRIBUTE_PROPERTY );
        String expression = node.getTextContent( );
        
        ////System.out.println("ToOrganizer attribs set");
        
        // The variable variant is used
        if( variable != null && part != null )
        {
            Node queryNode = getChild( ELEMENT_QUERY );
            String queryLanguage = getAttribute( queryNode, ATTRIBUTE_QUERY_LANGUATE );
            String queryContent = queryNode != null && queryNode.getFirstChild( ) != null ? queryNode.getFirstChild( ).getNodeValue( ) : null;
            to.setQuery( queryContent );
            to.setQueryLanguage( queryLanguage );
            to.setData( element.getParentProcess( ).getData( variable ) );
            to.setPart( part );
            to.setType(ITo.VARIABLE_VARIANT);
        }
        else if( partnerLink != null ) // The partner link variant is used
        {
            to.setChannel( (IChannel)modelInstance.getElement( partnerLink ) );
            to.setType( ITo.PARTNER_LINK_VARIANT );
        }
        else if( variable != null && property != null )// The property variant
        {
            to.setData( element.getParentProcess( ).getData( variable ) );
            to.setProperty( property );
            to.setType( ITo.PROPERTY_VARIANT );
        }
        else if( expression != null ) // The expression variant
        {
            String expressionLanguage = getAttribute( ATTRIBUTE_EXPRESSION_LANGUAGE );
            to.setExpressionLanguage( expressionLanguage );
            to.setExpression( expression );
            to.setType( ITo.EXPRESSION_VARIANT);
        }
    }
    
    /**
     * Returns the to being loaded
     * @return the to being loaded
     */
    public ITo getElement( )
    {
        return (ITo) element;
    }
}
