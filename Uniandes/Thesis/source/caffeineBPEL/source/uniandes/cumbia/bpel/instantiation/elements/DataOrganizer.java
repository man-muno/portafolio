package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a variable
 */
public class DataOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String ATTRIBUTE_NAME = "name";
    
    private static final String ATTRIBUTE_MESSAGE_TYPE = "messageType";

    private static final String ATTRIBUTE_TYPE = "type";

    private static final String ATTRIBUTE_ELEMENT = "element";
    
    private static final String ELEMENT_MESSAGE =  "message";
    
    private static final String ELEMENT_PART = "part";
    
    private static final String ATTRIBUTE_CLASS_NAME = "className";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the variable
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the variable
     */
    public DataOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super( node, modelInstance, parentProcess );

        String variableName = getAttribute( "name" );
        element = ( IBasicElement )modelInstance.getElement( variableName );
        element.setParentProcess( parentProcess );
        ((IData)element).setName( variableName );
    }

    /**
     * Loads all the variable information
     * @throws LoaderException If there is any problem loading the variable
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IData variable = null;
        try
        {
            variable = ( IData )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IVariable.", e );
        }

        // Sets the attributes
        String name = getAttribute( ATTRIBUTE_NAME );
        variable.setName( name );
        String type = getAttribute( ATTRIBUTE_TYPE );
        variable.setType( type );
        int variableType = IData.TYPE;

        if( type == null )
        {
            // Message type
            String messageType = getAttribute( ATTRIBUTE_MESSAGE_TYPE);
            String[] typeParts = messageType.split( ":" );

            if( typeParts.length > 1 )
                messageType = typeParts[ 1 ];
            
            variable.setMessageType( messageType );
            variableType = IData.MESSAGE_TYPE;
            Node messageNode = getChild( ELEMENT_MESSAGE );
            List<Node> partNodes = getChilds( messageNode, ELEMENT_PART );
            for(int i=0;i<partNodes.size( );i++)
            {
                Node partNode = partNodes.get( i );
                String partName = getAttribute( partNode, ATTRIBUTE_NAME );
                String className = getAttribute( partNode,ATTRIBUTE_CLASS_NAME );
                variable.addPart(partName,className);
            }

            if( messageType == null )
            {
                String element = getAttribute( ATTRIBUTE_ELEMENT );
                variable.setElement( element );
                variableType = IData.ELEMENT;
            }
        }
        variable.setVariableType(variableType);
        ////System.out.println("PartnerLink " + variable.getName( ) + " orgaixed");
    }

    /**
     * Returns the variable being loaded
     * @return the variable being loaded
     */
    public IData getElement( )
    {
        return ( IData )element;
    }

    @Override
    public IActivity getActivity( )
    {
        return null;
    }

}
