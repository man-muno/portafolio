package uniandes.cumbia.bpel.elements.wait;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a Wait bpel activity
 */
public class Wait extends AbstractWait
{
    
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_FOR = "for";
    
    public static final String TYPE_UNTIL = "until";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The delaying method of the wait
     */
    private String type;
    
    /**
     * Expression language
     */
    private String expressionLanguage;
    
    /**
     * The clause duration expression
     */
    private String expression;

    
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
     * The element doesn't have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    
    public Wait(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
    }
    
    /**
     * Returns the type of the wait
     * @return The type of the wait
     */
    public String getType( )
    {
        return type;
    }

    /**
     * Changes the type of the wait
     * @param type The new Type of wait
     */
    public void setType( String type )
    {
        this.type = type;
    }

    /**
     * Returns the expression language where the expression is defined
     * @return The actual expression language
     */
    public String getExpressionLanguage( )
    {
        return expressionLanguage;
    }

    /**
     * Changes the expresion language
     * @param expressionLanguage The new expresion language
     */    
    public void setExpressionLanguage( String expressionLanguage )
    {
        this.expressionLanguage = expressionLanguage;
    }

    /**
     * Returns the waiting expression
     * @return The waiting expression
     */
    public String getExpression( )
    {
        return expression;
    }

    /**
     * Changes the waiting expression
     * @param expression The new waiting expression
     */
    public void setExpression( String expression )
    {
        this.expression = expression;
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
     * Stops the exit
     */
    public void stop( )
    {
        parentProcess.notifyElementTerminated( this );
    }
    
    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 1;
    }    

    
    /**
     * Initializes the element
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
       
    }
    
    /**
     * Terminates the execution of the element
     */
    public void terminate( )
    {
        super.finalizedWait();
    }

    
    /**
     * Method that begins execution of the activity
     */
    public void execute()
    {
        startWait( );
    }
    /**
     * Re initializes the element
     */
    public void reInit()
    {
        super.reInitWait( );
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType()
    {
        return IWait.TYPE_WAIT;
    }
}
