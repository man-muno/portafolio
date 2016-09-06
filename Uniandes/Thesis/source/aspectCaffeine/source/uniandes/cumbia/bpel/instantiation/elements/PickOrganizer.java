package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.pick.IPick;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a pick element
 */
public class PickOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    private static final String ATTRIBUTE_CREATE_INSTANCE = "createInstance";
    
    private static final String ELEMENT_ON_MESSAGE = "onMessage";
    
    private static final String ELEMENT_ON_ALARM = "onAlarm";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

   
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the pick
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the pick
     */
    public PickOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess ) throws LoaderException
    {
        super(node, modelInstance, parentProcess);
        
        String pickName = getAttribute("name");
        element = (IBasicElement) modelInstance.getElement(pickName);
        element.setParentProcess(parentProcess);
        ((IActivity)element).setName( pickName );
        String suppresed = getAttribute( ATTRIBUTE_SUPPRESED_JOIN_FAILURE );
        
        if(suppresed == null)
            ((IActivity)element).useActivitySupressJoinFailure();
       
        ((IActivity)element).setSuppressJoinFailure( Boolean.parseBoolean( suppresed ) );
    }

    /**
     * Returns the pick being loaded
     * @return the pick being loaded
     */
    public IPick getActivity( )
    {
        return (IPick) element;
    }
    
    /**
     * Loads all the pick information
     * @throws LoaderException If there is any problem loading the pick
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        // Fist cast the object for easier use and validation
        IPick pick = null;
        try
        {
            pick = ( IPick )element;
        }
        catch( ClassCastException e )
        {
            throw new LoaderException( "Class does not implement IPick.", e );
        }
        
        String name = getAttribute( BaseActivityOrganizer.ATTRIBUTE_NAME );
        ((IActivity)element).setName(name);
        
        if(((IActivity)element)==null)
            throw new LoaderException("The pick element must have a name defined");
        
        List<Node> onMessages= getChilds(ELEMENT_ON_MESSAGE);
        for(int i=0;i<onMessages.size( );i++)
        {
            Node onAlarm = onMessages.get( i );
            OnMessageOrganizer onMessageLoader = new OnMessageOrganizer(onAlarm,modelInstance,parentProcess);
            onMessageLoader.organizeInternalStructure( );
            pick.addOnMessage(onMessageLoader.getActivity());
        }
        
        List<Node> onAlarms = getChilds(ELEMENT_ON_ALARM);
        for(int i=0;i<onAlarms.size( );i++)
        {
            Node onAlarm = onAlarms.get( i );
            OnAlarmOrganizer onAlarmOrganizer = new OnAlarmOrganizer(onAlarm,modelInstance,parentProcess);
            onAlarmOrganizer.organizeInternalStructure( );
            pick.addOnAlarm(onAlarmOrganizer.getActivity());
        }

    }



}
