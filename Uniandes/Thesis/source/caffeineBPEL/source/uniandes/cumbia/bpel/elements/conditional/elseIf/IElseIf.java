package uniandes.cumbia.bpel.elements.conditional.elseIf;

import uniandes.cumbia.bpel.elements.IActivity;


public interface IElseIf extends IActivity
{
    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_ELSE = "ElseIf";
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * @return the conditionLanguage
     */
    public String getConditionLanguage( );

    /**
     * Changes the condition language of the if element
     * @param expreLanguage The new condition language for the element
     */
    public void setConditionLanguage( String conditionLanguage );

    
    /**
     * @return the condition
     */
    public String getCondition( );

    /**
     * Changes the expression (condition) of the element
     * @param booleanExp The new expression of the element
     */
    public void setCondition( String condition );

    /**
     * @return the activity
     */
    public IActivity getActivity( );

    /**
     * Changes the activity related to the else-if
     * @param element
     */
    public void setActivity( IActivity activity );
    

}
