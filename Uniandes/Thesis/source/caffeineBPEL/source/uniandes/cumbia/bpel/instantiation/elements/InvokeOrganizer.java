package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.invoke.IInvoke;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a invoke element
 */
public class InvokeOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ATTRIBUTE_PARTNER_LINK = "partnerLink";
    
    private static final String ATTRIBUTE_PORT_TYPE = "portType";
    
    private static final String ATTRIBUTE_OPERATION = "operation";
    
    private static final String ATTRIBUTE_INPUT_VARIABLE = "inputVariable";
    
    private static final String ATTRIBUTE_OUTPUT_VARIABLE = "outputVariable";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the invoke
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the invoke
     */
    public InvokeOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String invokeName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(invokeName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( invokeName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }
    


    /**
     * Returns the invoke being loaded
     * @return the invoke being loaded
     */
    public IInvoke getActivity( )
    {
        return (IInvoke) element;
    }
    
    /**
     * Loads all the invoke information
     * @throws LoaderException If there is any problem loading the invoke
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IInvoke invoke = null;
        try
        {
            invoke = ( IInvoke )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IInvoke.", e );
        }
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)element).setName(name);
        
        if(((IActivity)element).getName( )==null)
            throw new LoaderException("The invoke element must have a name defined");
        
        String partnerLinkName = getAttribute( ATTRIBUTE_PARTNER_LINK );
        IChannel channel = ( IChannel )modelInstance.getElement(partnerLinkName);
        String inputVariable = getAttribute( ATTRIBUTE_INPUT_VARIABLE );
        channel.setInboundMessage( parentProcess.getData( inputVariable ) );
        String outputVariable = getAttribute( ATTRIBUTE_OUTPUT_VARIABLE );
        if(outputVariable != null)
        {
            channel.setOutboundMessage( parentProcess.getData( outputVariable ) );   
            invoke.setTypeSynch();
        }
        ((IInteraction)invoke).setChannel( channel );
        parentProcess.addChannel(channel);
        String portType = getAttribute( ATTRIBUTE_PORT_TYPE );
        invoke.setPortType( portType );
        String operation = getAttribute( ATTRIBUTE_OPERATION );
        invoke.setOperation( operation );

        
    }
}
