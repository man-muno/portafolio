package uniandes.cumbia.bpel.elements.conditional.If;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IIf extends IActivity
{

    public static final String TYPE_IF = "If";

    /**
     * Changes the condition language of the if element
     * @param expreLanguage The new condition language for the element
     */
    public void setConditionLanguage( String expreLanguage );

    /**
     * Changes the expression (condition) of the element
     * @param booleanExp The new expression of the element
     */
    public void setCondition( String booleanExp );

    /**
     * Changes the element to be executed in case the evaluated condition is true
     * @param activity T
     */
    public void setActivity( IActivity activity );

    /**
     * @return the conditionLanguage
     */
    public String getConditionLanguage( );

    /**
     * @return the condition
     */
    public String getCondition( );

    /**
     * @return the trueElement
     */
    public IActivity getActivity( );
}
