package uniandes.cumbia.bpel.elements.assign.from;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class From extends AbstractFrom
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * The query of the from
     */
    private String query;
    
    /**
     * Part to copy from
     */
    private String part;
    
    /**
     * The language that defines the query
     */
    private String queryLanguage;
    
    /**
     * The query itself
     */
    private String queryContent;
    
    /**
     * The role played
     */
    private String endPointReference;
    
    /**
     * The variable to copy from
     */
    private IData variable;
    
    /**
     * Property that goes with the variable
     */
    private String property;
    
    /**
     * An expression language where the expression is defined
     */
    private String expressionLanguage;
    
    /**
     * The expression for the from
     */
    private String expression;
    
    /**
     * A literal value to copy from
     */
    private String literalValue;
    
    /**
     * The channel where to take the information from
     */
    private IChannel channel;
    
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
     * The type of the from
     */
    private int type;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    public From(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
    }
    
    /**
     * Stops the element
     */
    public void stop( )
    {
        
    }

    /**
     * @return the query
     */
    public String getQuery( )
    {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery( String query )
    {
        this.query = query;
    }

    /**
     * @return the part
     */
    public String getPart( )
    {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart( String part )
    {
        this.part = part;
    }

    /**
     * @return the queryLanguage
     */
    public String getQueryLanguage( )
    {
        return queryLanguage;
    }

    /**
     * @param queryLanguage the queryLanguage to set
     */
    public void setQueryLanguage( String queryLanguage )
    {
        this.queryLanguage = queryLanguage;
    }

    /**
     * @return the endPointReference
     */
    public String getEndPointReference( )
    {
        return endPointReference;
    }

    /**
     * @param endPointReference the endPointReference to set
     */
    public void setEndPointReference( String endPointReference )
    {
        this.endPointReference = endPointReference;
    }

    /**
     * @return the variable
     */
    public IData getVariable( )
    {
        return variable;
    }

    /**
     * @param variable the variable to set
     */
    public void setVariable( IData variable )
    {
        this.variable = variable;
    }

    /**
     * @return the property
     */
    public String getProperty( )
    {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty( String property )
    {
        this.property = property;
    }

    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( )
    {
        return expressionLanguage;
    }

    /**
     * @param expressionLanguage the expressionLanguage to set
     */
    public void setExpressionLanguage( String expressionLanguage )
    {
        this.expressionLanguage = expressionLanguage;
    }

    /**
     * @return the expression
     */
    public String getExpression( )
    {
        return expression;
    }

    /**
     * @param expresion the expresion to set
     */
    public void setExpression( String expression )
    {
        this.expression = expression;
    }

    /**
     * @return the literalValue
     */
    public String getLiteralValue( )
    {
        return literalValue;
    }

    /**
     * @param literalValue the literalValue to set
     */
    public void setLiteralValue( String literalValue )
    {
        this.literalValue = literalValue;
    }

    /**
     * @return the queryContent
     */
    public String getQueryContent( )
    {
        return queryContent;
    }

    /**
     * @param queryContent the queryContent to set
     */
    public void setQueryContent( String queryContent )
    {
        this.queryContent = queryContent;
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
     * Changes the type of the from
     * @param type the new type
     */
    public void setType( int type )
    {
        this.type = type;
    }
    
    /**
     * Returns the type
     */
    public int getType()
    {
        return type;
    }

    /**
     * @return the channel
     */
    public IChannel getChannel( )
    {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel( IChannel channel )
    {
        this.channel = channel;
    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IFrom.TYPE_FROM;
    }

}
