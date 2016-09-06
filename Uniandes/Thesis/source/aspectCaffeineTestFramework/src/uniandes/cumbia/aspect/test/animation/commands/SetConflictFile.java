package uniandes.cumbia.aspect.test.animation.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import uniandes.cumbia.aspect.test.animation.IAnimation;
import uniandes.cumbia.aspect.test.animation.IAnimationCommand;
import uniandes.cumbia.aspects.instantiation.ConflictingInstructionsLoader;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class SetConflictFile implements IAnimationCommand, Runnable
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private IAnimation animation;

    private String conflictPath;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    public SetConflictFile( String conflictPath )
    {
        this.conflictPath = conflictPath;
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Executes the command
     */
    public void execute( )
    {

        try
        {
            // Creates a DOM Parser
            DOMParser domParser = new DOMParser( );
            // Parses the DOM Tree
            InputStream xmlStream = new FileInputStream( conflictPath );
            domParser.parse( new InputSource( xmlStream ) );
            // Saca el documento
            Document doc = domParser.getDocument( );
            ConflictingInstructionsLoader loader = new ConflictingInstructionsLoader( doc );
            loader.load( );
        }
        catch( FileNotFoundException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( SAXException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch( IOException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Prepare the command for execution
     */
    public void prepare( )
    {

    }

    /**
     * Sets the animation for the command
     * @param animation
     */
    public void setAnimation( IAnimation animation )
    {
        this.animation = animation;
    }

    @Override
    public void run( )
    {
        execute( );
    }
}
