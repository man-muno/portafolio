package uniandes.cumbia.bpel.elements.conditional;

import java.util.List;

import uniandes.cumbia.bpel.elements.IStructured;
import uniandes.cumbia.bpel.elements.conditional.Else.IElse;
import uniandes.cumbia.bpel.elements.conditional.If.IIf;
import uniandes.cumbia.bpel.elements.conditional.elseIf.IElseIf;

public interface IConditional extends IStructured
{

    public static final String TYPE_CONDITIONAL = "Conditional";

    /**
     * Adds an else-if statement to the If element
     * @param elseIf The else-if statement to be added
     */
    public void addElseIf( IElseIf elseIf );

    /**
     * Adds an else element to the If element
     * @param activity T
     */
    public void setElse( IElse ellse );

    /**
     * Changes the element to be executed in case the evaluated condition is true
     * @param activity T
     */
    public void setIf( IIf iff );

    /**
     * @return the elseIfs
     */
    public List<IElseIf> getElseIfs( );

    /**
     * @param elseIfs the elseIfs to set
     */
    public void setElseIfs( List<IElseIf> elseIfs );

    /**
     * @return the trueElement
     */
    public IIf getIf( );

    /**
     * @return the elseElement
     */
    public IElse getElse( );

    /**
     * Sets the if element to be executed
     */
    public void executeIfActivity( );

    /**
     * Evaluates the next else if 
     */
    public void evaluateElseIf( );

    /**
     * Returns the position of the else-if to be evaluated or executed
     * @return
     */
    public int getPosElseIf( );

    /**
     * Executes the activity in the current else-if element
     */
    public void executeElseIfActivity( );

    /**
     * Evaluates the next else-if element
     */
    public void evaluateNextElseIf( );

    /**
     * Evaluates the else element
     */
    public void evaluateElse( );

    /**
     * Executes the else activity
     */
    public void executeElseActivity( );

    /**
     * Terminates execution of this element
     */
    public void finalizeElement( );
    
    /**
     * Re initializes the element
     */
    public void reInit();
}
