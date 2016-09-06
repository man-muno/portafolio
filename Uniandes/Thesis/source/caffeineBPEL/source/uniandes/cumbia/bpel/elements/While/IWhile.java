package uniandes.cumbia.bpel.elements.While;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.IStructured;

public interface IWhile extends IStructured
{

    public static final String TYPE_WHILE = "While";
    
    /**
     * @return the conditionLanguage
     */
    public String getConditionLanguage( );

    /**
     * @param conditionLanguage the conditionLanguage to set
     */
    public void setConditionLanguage( String conditionLanguage );

    /**
     * @return the condition
     */
    public String getCondition( );

    /**
     * @param condition the condition to set
     */
    public void setCondition( String condition );

    /**
     * @return the activity
     */
    public IActivity getActivity( );

    /**
     * @param activity the activity to set
     */
    public void setActivity( IActivity activity );

    /**
     * Executes the activity once
     */
    public void executeActivity( );

    /**
     * Terminates the execution of the while
     */
    public void finalizing();
    

    /**
     * Re initializes the element
     */
    public void reInit();
    
}
