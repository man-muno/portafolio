package uniandes.cumbia.bpel.elements.conditional.Else;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class Else extends AbstractElse
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Element to be executed if the if's condition is true
     */
    protected IActivity activity;

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
    // Constructors
    // -----------------------------------------------------------------

    public Else( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Changes the element to be executed in case the evaluated condition is true
     * @param activity T
     */
    public void setActivity( IActivity activity )
    {
        this.activity = activity;
    }

    /**
     * @return the trueElement
     */
    public IActivity getActivity( )
    {
        return activity;
    }

    /**
     * Stops the process
     */
    public void stop( )
    {
        if( activity != null )
            activity.stop( );
        parentProcess.notifyElementTerminated( this );
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1 + ( activity != null ? activity.getElementCount( ) : 0 );
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
        useProcessSuppress = false;
    }

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        activity.initialize( );
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {

    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IElse.TYPE_ELSE;
    }

    /**
     * Re initializes the element
     */
    public void reInit()
    {
    //Does nothing
    }
}
