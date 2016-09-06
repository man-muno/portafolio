package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.empty.IEmpty;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a empty element
 */
public class EmptyOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Element being loaded
     */
    protected IEmpty empty;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the empty
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the empty
     */
    public EmptyOrganizer(Node node, ModelInstance modelInstance, IProcess parentProcess)
    {
        super(node, modelInstance, parentProcess);
        
        String emptyName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(emptyName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( emptyName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        empty = (IEmpty) element;
    }

    /**
     * Returns the empty being loaded
     * @return the empty being loaded
     */
    public IEmpty getActivity( )
    {
        return (IEmpty) element;
    }
    
    /**
     * Loads all the empty information
     * @throws LoaderException If there is any problem loading the empty
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IEmpty empty = null;
        try
        {
            empty = ( IEmpty )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IEmpty.", e );
        }
        
        if(((IActivity)element).getName( )==null)
            throw new LoaderException("The empty element must have a name defined");
    }
}
