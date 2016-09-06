package uniandes.cumbia.aspect.test.animation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import uniandes.cumbia.aspect.test.animation.loader.AnimationLoader;
import uniandes.cumbia.bpel.elements.runtime.BpelRuntime;
import uniandes.cumbia.testFW.interfaces.IAnimator;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class Animator implements IAnimator
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    private final static String ELEMENT_ANIMATION = "animation";

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The animation
     */
    private IAnimation animation;


    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    @Override
    public void executeAnimation( )
    {
        for( final IAnimationCommand command : animation.getAnimationCommands( ) )
        {
            //System.out.println("Before Execute Command");
            command.setAnimation( animation );
            //command.execute( );
            Thread runCommandThread = new Thread(command);
            runCommandThread.start();
            try
            {
                Thread.sleep(15000);
            }
            catch( InterruptedException e )
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //System.out.println("After Execute Command");
        }
        try
        {
            //wait( );
            // Wait one second for all things are ready, and then the animation
            // would be completely executed.
            Thread.sleep( 1000 );
        }
        catch( InterruptedException e )
        {
            e.printStackTrace( );
        }
    }

    @Override
    public Object loadAnimation( String pathAnimation, Object runtime )
    {
        try
        {
            // Creates a DOM Parser
            DOMParser domParser = new DOMParser( );
            // Parses the DOM Tree
            InputStream xmlStream = new FileInputStream( new File( pathAnimation ) );
            domParser.parse( new InputSource( xmlStream ) );
            // Gets the document.
            Document doc = domParser.getDocument( );
            // Creates the load context.
            BpelRuntime bpelRuntime = ( BpelRuntime )runtime;
            NodeList list = doc.getElementsByTagName( ELEMENT_ANIMATION );
            if( list.getLength( ) > 0 )
            {
                bpelRuntime.start( );
                // Parses the animation tag
                AnimationLoader loader = new AnimationLoader( list.item( 0 ) );
                loader.loadElement( );
                animation = loader.getElement( );
                ( ( Animation )animation ).setAnimator( this );
            }
        }
        catch( Exception e )
        {
            // Do nothing, just notify the error by console.
            e.printStackTrace( );
        }
        return null;
    }

    public void notifyAnimationExecuted( )
    {
        //notify( );
    }
}
