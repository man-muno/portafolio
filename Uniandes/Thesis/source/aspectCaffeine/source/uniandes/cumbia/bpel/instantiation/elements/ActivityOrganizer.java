package uniandes.cumbia.bpel.instantiation.elements;

import java.util.List;

import org.w3c.dom.Node;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;
import uniandes.cumbia.openobjects.loaders.LoaderException;

/**
 * Class that loads the information of a exit element
 */
public class ActivityOrganizer extends BaseActivityOrganizer
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String ELEMENT_ASSIGN = "assign";

    public static final String ELEMENT_RECEIVE = "receive";

    public static final String ELEMENT_STARTING_RECEIVE = "startingReceive";

    public static final String ELEMENT_INVOKE = "invoke";

    public static final String ELEMENT_REPLY = "reply";

    public static final String ELEMENT_WAIT = "wait";

    public static final String ELEMENT_EMPTY = "empty";

    public static final String ELEMENT_EXIT = "exit";

    public static final String ELEMENT_WHILE = "while";

    public static final String ELEMENT_PICK = "pick";

    public static final String ELEMENT_SEQUENCE = "sequence";

    public static final String ELEMENT_FLOW = "flow";

    public static final String ELEMENT_IF = "if";

    public static final String ATTRIBUTE_CREATE_INSTANCE = "createInstance";

    public static final String ELEMENT_ON_MESSAGE = "onMessage";

    public static final String ELEMENT_STARTING_PICK = "startingPick";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent process of the element
     */
    private IProcess parentProcess;

    /**
     * Holds the type of element being processed
     */
    private String type;

    /**
     * Element being loaded
     */
    protected IBasicElement element;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor of the loader
     * @param node The node for loading the exit
     * @param context The context for loading the element
     * @throws LoaderException If there is any problem loading the exit
     */
    public ActivityOrganizer( Node node, ModelInstance modelInstance, IProcess parentProcess )
    {
        super( node, modelInstance, parentProcess );
        this.parentProcess = parentProcess;

    }

    /**
     * Returns the exit being loaded
     * @return the exit being loaded
     */
    public IActivity getActivity( )
    {
        return ( IActivity )element;
    }

    /**
     * Loads all the exit information
     * @throws LoaderException If there is any problem loading the exit
     */
    public void organizeInternalStructure( ) throws LoaderException
    {
        String nodeName = node.getNodeName( );
        if( nodeName.equals( ELEMENT_ASSIGN ) )
        {
            AssignOrganizer assignOrganizer = new AssignOrganizer( node, modelInstance, parentProcess );
            assignOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )assignOrganizer.getActivity( );
            type = ELEMENT_ASSIGN;
        }
        else if( nodeName.equals( ELEMENT_EMPTY ) )
        {
            EmptyOrganizer emptyOrganizer = new EmptyOrganizer( node, modelInstance, parentProcess );
            emptyOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )emptyOrganizer.getActivity( );
            type = ELEMENT_EMPTY;
        }
        else if( nodeName.equals( ELEMENT_EXIT ) )
        {
            ExitOrganizer exitOrganizer = new ExitOrganizer( node, modelInstance, parentProcess );
            exitOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )exitOrganizer.getActivity( );
            type = ELEMENT_EXIT;
        }
        else if( nodeName.equals( ELEMENT_FLOW ) )
        {
            FlowOrganizer flowOrganizer = new FlowOrganizer( node, modelInstance, parentProcess );
            flowOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )flowOrganizer.getActivity( );
            type = ELEMENT_FLOW;
        }
        else if( nodeName.equals( ELEMENT_IF ) )
        {
            ConditionalOrganizer ifOrganizer = new ConditionalOrganizer( node, modelInstance, parentProcess );
            ifOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )ifOrganizer.getActivity( );
            type = ELEMENT_IF;
        }
        else if( nodeName.equals( ELEMENT_INVOKE ) )
        {
            InvokeOrganizer invokeOrganizer = new InvokeOrganizer( node, modelInstance, parentProcess );
            invokeOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )invokeOrganizer.getActivity( );
            type = ELEMENT_INVOKE;
        }
        else if( nodeName.equals( ELEMENT_PICK ) )
        {
            boolean foundCreateInstance = false;
            String createInstance = getAttribute( node, ATTRIBUTE_CREATE_INSTANCE );
            if( createInstance != null && createInstance.equalsIgnoreCase( "yes" ) )
                foundCreateInstance = true;
            if( !foundCreateInstance )
            {
                PickOrganizer pickOrganizer = new PickOrganizer( node, modelInstance, parentProcess );
                pickOrganizer.organizeInternalStructure( );
                element = ( IBasicElement )pickOrganizer.getActivity( );
                type = ELEMENT_PICK;
            }
            else
            {
                StartingPickOrganizer startingOnMessage = new StartingPickOrganizer( node, modelInstance, parentProcess );
                startingOnMessage.organizeInternalStructure( );
                element = ( IBasicElement )startingOnMessage.getActivity( );
                type = ELEMENT_STARTING_PICK;
            }

        }
        else if( nodeName.equals( ELEMENT_RECEIVE ) )
        {
            String createInstance = getAttribute( node, ATTRIBUTE_CREATE_INSTANCE );
            if( createInstance == null || createInstance.equalsIgnoreCase( "no" ) )
            {
                ReceiveOrganizer receiveOrganizer = new ReceiveOrganizer( node, modelInstance, parentProcess );
                receiveOrganizer.organizeInternalStructure( );
                element = ( IBasicElement )receiveOrganizer.getActivity( );
                type = ELEMENT_RECEIVE;
            }
            else
            {
                StartingReceiveOrganizer startingReceiveOrganizer = new StartingReceiveOrganizer( node, modelInstance, parentProcess );
                startingReceiveOrganizer.organizeInternalStructure( );
                element = ( IBasicElement )startingReceiveOrganizer.getActivity( );
                type = ELEMENT_STARTING_RECEIVE;
            }
        }
        else if( nodeName.equals( ELEMENT_REPLY ) )
        {
            ReplyOrganizer replyOrganizer = new ReplyOrganizer( node, modelInstance, parentProcess );
            replyOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )replyOrganizer.getActivity( );
            type = ELEMENT_REPLY;
        }
        else if( nodeName.equals( ELEMENT_SEQUENCE ) )
        {
            SequenceOrganizer sequenceOrganizer = new SequenceOrganizer( node, modelInstance, parentProcess );
            sequenceOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )sequenceOrganizer.getActivity( );
            type = ELEMENT_SEQUENCE;
        }
        else if( nodeName.equals( ELEMENT_WAIT ) )
        {
            WaitOrganizer waitOrganizer = new WaitOrganizer( node, modelInstance, parentProcess );
            waitOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )waitOrganizer.getActivity( );
            type = ELEMENT_WAIT;
        }
        else if( nodeName.equals( ELEMENT_WHILE ) )
        {
            WhileOrganizer whileOrganizer = new WhileOrganizer( node, modelInstance, parentProcess );
            whileOrganizer.organizeInternalStructure( );
            element = ( IBasicElement )whileOrganizer.getActivity( );
            type = ELEMENT_WHILE;
        }
    }

    public String getType( )
    {
        return type;
    }
}
