package uniandes.cumbia.bpel.elements.assign.to;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IData;

public interface ITo extends IBasicElement
{

    public static final String TYPE_TO = "To";
    public static final int PARTNER_LINK_VARIANT = 0;
    public static final int PROPERTY_VARIANT = 1;
    public static final int EXPRESSION_VARIANT = 2;
    public static final int LITERAL_VARIANT = 3;
    public static final int VARIABLE_VARIANT = 4;
    
    /**
     * @return the variable
     */
    public IData getData( );

    /**
     * @param variable the variable to set
     */
    public void setData( IData variable );

    /**
     * @return the part
     */
    public String getPart( );

    /**
     * @param part the part to set
     */
    public void setPart( String part );

    /**
     * @return the property
     */
    public String getProperty( );

    /**
     * @param property the property to set
     */
    public void setProperty( String property );

    /**
     * @return the query
     */
    public String getQuery( );

    /**
     * @param query the query to set
     */
    public void setQuery( String query );

    /**
     * @return the queryLanguage
     */
    public String getQueryLanguage( );

    /**
     * @param queryLanguage the queryLanguage to set
     */
    public void setQueryLanguage( String queryLanguage );

    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( );

    /**
     * @param expressionLanguage the expressionLanguage to set
     */
    public void setExpressionLanguage( String expressionLanguage );

    /**
     * @return the expresion
     */
    public String getExpresion( );

    /**
     * @param expresion the expresion to set
     */
    public void setExpression( String expresion );

    /**
     * Changes the type of the from
     * @param type the new type
     */
    public void setType( int type );
    
    /**
     * Returns the type
     */
    public int getType();
    
    /**
     * @return the channel
     */
    public IChannel getChannel( );

    /**
     * @param channel the channel to set
     */
    public void setChannel( IChannel channel );
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    void setSuppressJoinFailure( boolean suppressJoinFailure );
    
    /**
     * @return the suppressJoinFailure
     */
    boolean isSuppressJoinFailure( );
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( );
    
}