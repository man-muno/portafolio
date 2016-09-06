package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.pick.onalarm.IOnAlarm;
import uniandes.cumbia.bpel.elements.pick.onalarm.OnAlarm;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a onAlarm element
 */
public class OnAlarmOrganizer extends BaseActivityOrganizer
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
     * @param node The node for loading the onAlarm
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the onAlarm
     */
    public OnAlarmOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String onAlarmName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(onAlarmName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( onAlarmName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }


    /**
     * Returns the onAlarm being loaded
     * @return the onAlarm being loaded
     */
    public IOnAlarm getActivity( )
    {
        return ( IOnAlarm )element;
    }

    /**
     * Loads all the onAlarm information
     * @throws LoaderException If there is any problem loading the onAlarm
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IOnAlarm onAlarm = null;
        try
        {
            onAlarm = ( IOnAlarm )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IOnAlarm.", e );
        }

        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ( ( IActivity )element ).setName( name );

        if( ((IActivity)element).getName( ) == null )
            throw new LoaderException( "The onAlarm element must have a name defined" );

        Node element = getChild( ELEMENT_FOR ) != null ? getChild( ELEMENT_FOR ) : getChild( ELEMENT_UNTIL );
        onAlarm.setExpressionLanguage( getAttribute( element, ATTRIBUTE_EXPRESSION_LANGUAGE ) );
        String type = element.getNodeName( );
        Node activity = null;
        if( type.equals( ELEMENT_FOR ) )
        {
            // The onAlarm expression is a for
            onAlarm.setType( OnAlarm.TYPE_FOR );
            Node forNode = getChild( ELEMENT_FOR );
            String expression = forNode.getTextContent( );
            onAlarm.setExpression( expression.split( "'" )[1]);
            activity = getActivity(forNode);
        }
        else if( type.equals( ELEMENT_UNTIL ) )
        {
            // The onAlarm expression is an until
            onAlarm.setType( OnAlarm.TYPE_UNTIL );
            Node untilNode = getChild( ELEMENT_UNTIL );
            String expression = untilNode.getTextContent( );
            onAlarm.setExpression( expression.split( "'" )[1]);
            activity = getActivity(untilNode);
        }

        
        ////System.out.println("Activity " + activity.getNodeName( ) + " " + activity.getNodeValue( ));
        ActivityOrganizer activityOrganizer = new ActivityOrganizer( activity, modelInstance, parentProcess );
        activityOrganizer.organizeInternalStructure( );
        if( activityOrganizer.getActivity( ) != null )
            onAlarm.setActivity( ( IActivity )activityOrganizer.getActivity( ) );

    }


    private Node getActivity( Node node )
    {
        boolean found = false;
        Node resp = null;
        Node temp = node.getNextSibling( );
        while(!found)
        {
            if( temp.getNodeType( ) != Node.TEXT_NODE)
            {
                found = true;
                resp = temp;
            }
            else
            {
                temp = temp.getNextSibling( );
            }
        }
        return resp;
    }
}

