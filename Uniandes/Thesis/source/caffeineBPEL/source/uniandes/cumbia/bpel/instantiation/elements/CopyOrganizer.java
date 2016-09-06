package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;
import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.assign.to.ITo;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a copy element
 */
public class CopyOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ATTRIBUTE_KEEP_SRC_ELEMENT_NAME = "keepSrcElementName";
    
    private static final String ATTRIBUTE_IGNORE_MISSING_FROM_DATA = "ignoreMissingFromData";
    
    private static final String ELEMENT_FROM = "from";
    
    private static final String ELEMENT_TO = "to";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
  
    /**
     * Element being loaded
     */
    protected ICopy copy;
   
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the copy
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the copy
     */
    public CopyOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        ////System.out.println("CopyOrganizer constructor begin");
        String copyName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(copyName);
        ////System.out.println("CopyOrganizer constructor begin 2 " + copyName);
        element.setParentProcess(parentProcess);
        ////System.out.println("CopyOrganizer constructor begin 2a");
        ((IBasicElement)element).setName( copyName );
        ////System.out.println("CopyOrganizer constructor begin 2b");
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        ////System.out.println("CopyOrganizer constructor begin 3");
        if(suppresed == null)
            ((ICopy)element).useActivitySupressJoinFailure();
       
        ((ICopy)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );

        copy = (ICopy) element;
        ////System.out.println("CopyOrganizer constructor");
    }

    /**
     * Returns the copy being loaded
     * @return the copy being loaded
     */
    public IActivity getActivity( )
    {
        return null;
    }
    
    /**
     * Loads all the copy information
     * @throws LoaderException If there is any problem loading the copy
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        ICopy copy = null;
        try
        {
            copy = ( ICopy )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement ICopy.", e );
        }
        
        String keep = getAttribute( ATTRIBUTE_KEEP_SRC_ELEMENT_NAME );
        if(keep != null)
            copy.setKeepSrcElementName(Boolean.parseBoolean( keep ));
        String ignore = getAttribute( ATTRIBUTE_IGNORE_MISSING_FROM_DATA );
        if(ignore != null)
            copy.setIgnoreMissingFromData(Boolean.parseBoolean( ignore ));
        
        ////System.out.println("CopyOrganizer attribs assign");
        
        Node from = getChild( ELEMENT_FROM );
        FromOrganizer fromOrganizer = new FromOrganizer( from, modelInstance,element.getParentProcess( ) );
        fromOrganizer.organizeInternalStructure( );
        copy.setFrom((IFrom)fromOrganizer.getElement( ));
        ////System.out.println("From set in CopyOrganizer");
        
        Node to = getChild( ELEMENT_TO );
        ToOrganizer toOrganizer = new ToOrganizer( to, modelInstance,element.getParentProcess( ) );
        toOrganizer.organizeInternalStructure( );
        copy.setTo((ITo)toOrganizer.getElement( ));
        ////System.out.println("To set in CopyOrganizer");

    }
    

    /**
     * Returns the copy being loaded
     * @return the copy being loaded
     */
    public ICopy getElement( )
    {
        return (ICopy) element;
    }
}
