package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.exit.IExit;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a exit element
 */
public class ExitOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    /**
     * Element being loaded
     */
    protected IExit exit;
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the exit
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the exit
     */
    public ExitOrganizer(Node node, ModelInstance modelInstance, IProcess parentProcess)
    {
        super(node, modelInstance, parentProcess);
        
        String exitName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(exitName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( exitName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        exit = (IExit) element;
    }

    /**
     * Returns the exit being loaded
     * @return the exit being loaded
     */
    public IExit getActivity( )
    {
        return (IExit) element;
    }
    
    /**
     * Loads all the exit information
     * @throws LoaderException If there is any problem loading the exit
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IExit exit = null;
        try
        {
            exit = ( IExit )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IExit.", e );
        }
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)element).setName(name);
        
        if(((IActivity)element).getName( )==null)
            throw new LoaderException("The exit element must have a name defined");
    }
}
