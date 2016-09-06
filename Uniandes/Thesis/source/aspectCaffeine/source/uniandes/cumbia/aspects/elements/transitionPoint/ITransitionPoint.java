package uniandes.cumbia.aspects.elements.transitionPoint;

import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.advice.IAdvice;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.elements.IOpenObject;

public interface ITransitionPoint extends IBasicElement, IOpenObject
{

    
    public static final String TYPE_GRAPH_TRANSITION_POINT = "GraphTransitionPoint";
    
    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Calculates the next advice to be executed
     */
    public void calculateNextAdvice( );

    /**
     * Executes the advice selected for execution
     */
    public void executeAdvice( );

    /**
     * Returns a list of all the advices
     * @return
     */
    public List<IAdvice> getAdvices( );

    /**
     * Adds a new advice
     * @param advice
     */
    public void addAdvice( IAdvice advice );
    
    /**
     * 
     * @param pointCut
     */
    public void setPointCut( String pointCut );

    /**
     * Sets the process instance where the aspect should be executed
     * @param instance
     */
    public void setProcessInstance( int instance );
    
    /**
     * Returns the point cut expression
     * @return
     */
    public String getPointCut();
    
    /**
     * Returns the process instance
     * @return
     */
    public int getProcessInstance();

    public void setAspectEngine( IAspectEngine engine );
}
