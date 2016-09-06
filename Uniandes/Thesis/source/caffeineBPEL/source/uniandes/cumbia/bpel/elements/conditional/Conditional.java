package uniandes.cumbia.bpel.elements.conditional;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.conditional.Else.IElse;
import uniandes.cumbia.bpel.elements.conditional.If.IIf;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class Conditional extends AbstractConditional
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Element to be executed if the if's condition is true
     */
    protected IIf iph;

    /**
     * List of all the else-ifs
     */
    protected List<IElseIf> elseIfs;

    /**
     * Element to be executed if the if's condition is false and no elseif executes
     */
    protected IElse elze;

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
    
    /**
     * Position of the current els if beeing evaluated
     */
    private int currentElseIf;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the new conditional
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type
     */
    public Conditional( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        currentElseIf = 0;
        elseIfs = new ArrayList<IElseIf>();
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Adds an else-if statement to the If element
     * @param elseIf The else-if statement to be added
     */
    public void addElseIf( IElseIf elseIf )
    {
        this.elseIfs.add( elseIf );
    }

    /**
     * Adds an else element to the If element
     * @param activity T
     */
    public void setElse( IElse elze )
    {
        this.elze = elze;
    }

    /**
     * Changes the element to be executed in case the evaluated condition is true
     * @param activity T
     */
    public void setIf( IIf iph )
    {
        this.iph = iph;
    }

    /**
     * @return the elseIfs
     */
    public List<IElseIf> getElseIfs( )
    {
        return elseIfs;
    }

    /**
     * @param elseIfs the elseIfs to set
     */
    public void setElseIfs( List<IElseIf> elseIfs )
    {
        this.elseIfs = elseIfs;
    }

    /**
     * @return the trueElement
     */
    public IIf getIf( )
    {
        return iph;
    }

    /**
     * @return the elseElement
     */
    public IElse getElse( )
    {
        return elze;
    }

    /**
     * Stops the process
     */
    public void stop( )
    {
        for( int i = 0; i < elseIfs.size( ); i++ )
        {
            IBasicElement element = ( IBasicElement )elseIfs.get( i );
            element.stop( );
        }
        if( elze != null )
            elze.stop( );
        if( iph != null )
            iph.stop( );
        parentProcess.notifyElementTerminated( this );
    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        int counter = 1;
        for( int i = 0; i < elseIfs.size( ); i++ )
            counter += elseIfs.get( i ).getElementCount( );
        return 1 + elseIfs.size( ) + ( elze != null ? elze.getElementCount( ) : 0 ) + ( iph != null ? iph.getElementCount( ) : 0 );
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
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
    }
    
    /**
     * Returns the position of the else-if to be evaluated or executed
     * @return
     */
    public int getPosElseIf( )
    {
        return currentElseIf;
    }
    
    /**
     * Executes the activity in the current else-if element
     */
    public void executeElseIfActivity( )
    {
        super.executeElseIf( );
    }
    
    /**
     * Evaluates the next else-if element
     */
    public void evaluateNextElseIf( )
    {
        currentElseIf++;
        super.checkNextElseIf();
    }
    
    /**
     * Evaluates the else element
     */
    public void evaluateElse( )
    {
        super.checkElse();
    }
    
    /**
     * Terminates execution of this element
     */
    public void finalizeElement( )
    {
        super.finalizing();
    }

    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        iph.initialize( );
        if( elze != null )
            elze.initialize( );
        for( IElseIf elseIf : elseIfs )
        {
            elseIf.initialize( );
        }
    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        currentElseIf=0;
        startConditional( );
    }
    
    /**
     * Executes the else activity
     */
    public void executeElseActivity( )
    {
        super.executeElse();
    }
    
    /**
     * Sets the if element to be executed
     */
    public void executeIfActivity( )
    {
        super.executeIf();
    }
    
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitConditional( );
    }
    
    /**
     * Evaluates the next else if 
     */
    public void evaluateElseIf( )
    {
        super.evaluateElseIF();
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IConditional.TYPE_CONDITIONAL;
    }
}
