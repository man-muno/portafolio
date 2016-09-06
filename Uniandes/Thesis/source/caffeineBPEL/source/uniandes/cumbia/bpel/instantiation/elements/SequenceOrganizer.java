package uniandes.cumbia.bpel.instantiation.elements;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.elements.sequence.ISequence;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a sequence element
 */
public class SequenceOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attribute
    // -----------------------------------------------------------------
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the sequence
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the sequence
     */
    public SequenceOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String sequenceName = getAttribute("name");
        element = (IActivity) modelInstance.getElement(sequenceName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( sequenceName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
        ////System.out.println("SequenceOrganizer constructor");
    }

 
    /**
     * Returns the sequence being loaded
     * @return the sequence being loaded
     */
    public ISequence getActivity( )
    {
        return (ISequence) element;
    }
    
    /**
     * Loads all the sequence information
     * @throws LoaderException If there is any problem loading the sequence
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        ////System.out.println("Sequence organizeInternalStructure");
        // Fist cast the object for easier use and validation
        ISequence sequence = null;
        try
        {
            sequence = ( ISequence )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement ISequence.", e );
        }

        sequence.setParentProcess( parentProcess );
        
        NodeList children= node.getChildNodes( );
        for(int i=0;i<children.getLength( );i++)
        {
            Node activity = children.item( i );
            ////System.out.println( "Element " + activity );
            ActivityOrganizer elementOrganizer = new ActivityOrganizer(activity,modelInstance,parentProcess);
            elementOrganizer.organizeInternalStructure( );
            if( elementOrganizer.getActivity( ) != null )
            {
                if( elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_PICK ) || elementOrganizer.getType( ).equals( ActivityOrganizer.ELEMENT_STARTING_RECEIVE ) )
                {
                    parentProcess.addStartingElement( elementOrganizer.getActivity( ) );
                }
                else
                    sequence.addActivity( elementOrganizer.getActivity( ) );
                ////System.out.println( "Element " + elementOrganizer.getActivity( ).getName( ) + " added" );
            }
        }
        
        ////System.out.println("Sequence organizeInternalStructure");
    }



}
