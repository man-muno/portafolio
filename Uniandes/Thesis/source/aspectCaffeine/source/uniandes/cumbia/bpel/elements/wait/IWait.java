package uniandes.cumbia.bpel.elements.wait;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IWait extends IActivity
{
    
    public static final String TYPE_WAIT = "Wait";
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Returns the type of the wait
     * @return The type of the wait
     */
    public String getType( );

    /**
     * Changes the type of the wait
     * @param type The new Type of wait
     */
    public void setType( String type );

    /**
     * Returns the expression language where the expression is defined
     * @return The actual expression language
     */
    public String getExpressionLanguage( );

    /**
     * Changes the expression language
     * @param expressionLanguage The new expression language
     */    
    public void setExpressionLanguage( String expressionLanguage );

    /**
     * Returns the waiting expression
     * @return The waiting expression
     */
    public String getExpression( );

    /**
     * Changes the waiting expression
     * @param expression The new waiting expression
     */
    public void setExpression( String expression );

    /**
     * Terminates the execution of the element
     */
    public void terminate( );
    
    /**
     * Re initializes the element
     */
    public void reInit();
   
}
