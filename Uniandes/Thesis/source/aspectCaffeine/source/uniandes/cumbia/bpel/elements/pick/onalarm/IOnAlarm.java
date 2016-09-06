package uniandes.cumbia.bpel.elements.pick.onalarm;

import uniandes.cumbia.bpel.elements.IActivity;
import uniandes.cumbia.bpel.elements.pick.IPick;


public interface IOnAlarm extends IActivity
{

    public static final String TYPE_ON_ALARM = "OnAlarm";
    
    /**
     * Changes the type of the onAlarm
     * @param typeUntil The new type of the onAlarm
     */
    public void setType( String type );

    /**
     * Changes the activity to be executed when the onAlarm ends 
     * @param element The new element
     */
    public void setActivity( IActivity activity );

    /**
     * Changes the expression language of the element
     * @param expresionLanguage The new expression language of the element
     */
    public void setExpressionLanguage( String expresionLanguage );
    
    /**
     * @return the expression
     */
    public String getExpression( );

    /**
     * @param expression the expression to set
     */
    public void setExpression( String expression );

    /**
     * @return the type
     */
    public String getType( );
    /**
     * @return the expressionLanguage
     */
    public String getExpressionLanguage( );

    /**
     * @return the activity
     */
    public IActivity getActivity( );

    /**
     * Sets the pick to the on message
     * @param pick
     */
    public void setPick( IPick pick );
    /**
     * Returns the pick owner of the element
     */
    public IPick getPick();

    /**
     * Tells the on alarm that the counter stoped
     */
    public void stopedCounting( );
}
