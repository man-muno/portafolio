package uniandes.cumbia.bpel.test.animation.loader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uniandes.cumbia.bpel.test.animation.Animation;

public class AnimationLoader extends AbstractLoader
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private static final String EXECUTE_SYNCHRONUS_CALL = "execute-synchronous-call";

    private static final String EXECUTE_ASYNCHRONUS_CALL = "execute-asynchronous-call";

    private static final String PROCESS_ASYNCHRONOUS_RESPONSE = "process-asynchronous-response";
    
    private static final String INVOKE_SYNCHRONOUS_CALL = "invoke-synchronous-call";
    
    private static final String INVOKE_ASYNCHRONOUS_CALL = "invoke-asynchronous-call";
    
    private static final String WAIT = "wait";
    
    private static final String CREATE_TEST_WSDL = "createTestWsdlService";
    
    private static final String ADD_ASPECT = "addAspect";

    // -----------------------------------------------------------------
    // Atttributes
    // -----------------------------------------------------------------

    /**
     * Element loaded.
     */
    private Animation animation;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    /**
     * Constructor of the loader
     * @param node DOM Node of the animation
     * @param context Context for loading the element
     */
    public AnimationLoader( Node node )
    {
        super( node );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    /**
     * Loads the animation.
     * @throws LoaderException If there is any problem loading the element.
     */
    public void loadElement( ) throws LoaderException
    {
        animation = new Animation();
        
        NodeList nodes = node.getChildNodes( );
        
        for(int i=0;i<nodes.getLength( );i++)
        {
            Node childNode = nodes.item( i );
            if(childNode.getNodeName( ).equals( EXECUTE_ASYNCHRONUS_CALL ))
            {
                ExecuteAsynchronusCallLoader loader = new ExecuteAsynchronusCallLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
            else if(childNode.getNodeName( ).equals( EXECUTE_SYNCHRONUS_CALL ))
            {
                ExecuteSynchronusCallLoader loader = new ExecuteSynchronusCallLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
            else if(childNode.getNodeName( ).equals( PROCESS_ASYNCHRONOUS_RESPONSE ))
            {
                ProcessAsynchronusResponseLoader loader = new ProcessAsynchronusResponseLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
            else if(childNode.getNodeName( ).equals( INVOKE_SYNCHRONOUS_CALL ))
            {
                InvokeSynchronusCallLoader loader = new InvokeSynchronusCallLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );                
            }
            else if(childNode.getNodeName( ).equals( INVOKE_ASYNCHRONOUS_CALL ))
            {
                InvokeAsynchronusCallLoader loader = new InvokeAsynchronusCallLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
            else if(childNode.getNodeName( ).equals( WAIT ))
            {
                WaitLoader loader = new WaitLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
            else if(childNode.getNodeName( ).equals( CREATE_TEST_WSDL ))
            {
                CreateTestWSDLLoader loader = new CreateTestWSDLLoader(childNode);
                loader.loadElement( );
                animation.addAnimationCommand( loader.getElement() );
            }
        }
        
    }

 

    /**
     * Returns the loaded element.
     * @return The loaded element.
     */
    public Animation getElement( )
    {
        return animation;
    }
}