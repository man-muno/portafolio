package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.flow.IFlow;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a flow element
 */
public class FlowOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ELEMENT_LINKS = "links";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    
    /**
     * Element being loaded
     */
    protected IFlow flow;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the exit
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the exit
     */
    public FlowOrganizer(Node node, ModelInstance modelInstance, IProcess parentProcess)
    {
        super(node, modelInstance, parentProcess);
        
        String flowName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(flowName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( flowName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        flow = (IFlow) element;
    }

    /**
     * Returns the flow being loaded
     * @return the flow being loaded
     */
    public IFlow getActivity( )
    {
        return (IFlow) element;
    }
    
    /**
     * Loads all the flow information
     * @throws LoaderException If there is any problem loading the flow
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IFlow flow = null;
        try
        {
            flow = ( IFlow )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IFlow.", e );
        }
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)element).setName(name);
        
        if(((IActivity)element).getName( )==null)
            throw new LoaderException("The flow element must have a name defined");
        
        NodeList elements = node.getChildNodes( );
        for(int i=0;i<elements.getLength( );i++)
        {
            Node element = elements.item( i );
            if( !element.getNodeName( ).equals( ELEMENT_LINKS ))
            {
                ActivityOrganizer elementOrganizer = new ActivityOrganizer( element, modelInstance, parentProcess );
                elementOrganizer.organizeInternalStructure( );
                if( elementOrganizer.getActivity( ) != null )
                {
                    if( elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_PICK ) || elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_RECEIVE ) )
                    {
                        parentProcess.addStartingElement( elementOrganizer.getActivity( ) );
                    }
                    else
                        flow.addActivity( elementOrganizer.getActivity( ) );
                    ////System.out.println( "Element " + elementOrganizer.getActivity( ).getName( ) + " added" );
                }
            }
            //else
                //Add the links
        }
    }
}
