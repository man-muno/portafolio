package uniandes.cumbia.bpel.elements.assign;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.assign.copy.ICopy;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a Assign bpel activity
 */
public class Assign extends AbstractAssign 
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Validates all the variables being modified by the activity
     */
    private boolean validate = false;

    /**
     * The parent process
     */
    private IProcess parentProcess;

    /**
     * Determines whether the joinFailure fault will be suppressed for all activities in the process. The effect of the attribute at the process level can be overridden by an
     * activity using a different value for the attribute. The default for this attribute is "no" at the process level.
     */
    protected boolean suppressJoinFailure = false;

    /**
     * Name of the element
     */
    protected String name;

    /**
     * The element does'nt have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;

    // -----------------------------------------------------------------
    // Elements
    // -----------------------------------------------------------------

    /**
     * List of all the "copy" elements in the assign
     */
    private List<ICopy> copies;

    private int copyIndex;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Assign( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        copyIndex = 0;
        copies = new ArrayList<ICopy>( );
    }

    /**
     * Changes the value of validate
     * @param b The new value of the validate
     */
    public void setValidate( boolean b )
    {
        this.validate = b;
    }

    /**
     * Adds an Copy element to the assign
     * @param g
     */
    public void addCopy( ICopy g )
    {
        copies.add( g );
    }

    /**
     * Stops the process
     */
    public void stop( )
    {
        for( int i = 0; i < copies.size( ); i++ )
        {
            ICopy copy = copies.get( i );
            ( ( IBasicElement )copy ).stop( );
        }
        parentProcess.notifyElementTerminated( this );
    }

    /**
     * @return the validate
     */
    public boolean isValidate( )
    {
        return validate;
    }

    /**
     * @return the copies
     */
    public List<ICopy> getCopies( )
    {
        return copies;
    }

    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }

    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }

    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
    }

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return useProcessSuppress ? parentProcess.isSuppressJoinFailure( ) : suppressJoinFailure;
    }

    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        useProcessSuppress = true;
    }

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        for( ICopy copy : copies )
        {
            copy.initialize( );
        }

    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        startAssign( );
    }

    /**
     * Changes the value of the current activity beeing executed
     */
    public void copyExecuted( )
    {
        copyIndex++;
        assignExecuteNext( );
    }

    /**
     * Returns the position of the next activity to execute
     * @return
     */
    public int getNextCopyToExecute( )
    {
        return copyIndex == copies.size( ) ? -1 : copyIndex;
    }

    /**
     * Method called when all copies are finished executing
     */
    public void finalizedAssign( )
    {
        assignFinalized( );
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitAssign( );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IAssign.TYPE_ASSIGN;
    }
}
