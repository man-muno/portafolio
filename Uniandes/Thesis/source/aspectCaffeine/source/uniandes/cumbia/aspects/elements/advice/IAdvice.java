package uniandes.cumbia.aspects.elements.advice;

import java.util.List;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.elements.IOpenObject;

public interface IAdvice extends IBasicElement, IOpenObject
{

    public static final String TYPE_ADVICE = "Advice";
    
    public static final String BEFORE_TYPE = "before";
    
    public static final String AFTER_TYPE = "after";
    
    public static final String AROUND_TYPE = "around";
    
    /**
     * Calculates the next instruction to be executed
     */
    public void calculateNextInstruction( );
    
    /**
     * Executes the next instruction
     */
    public void executeNextInstruction();

    /**
     * Returns a list of all the instructions
     * @return
     */
    public List<IInstruction> getInstructions( );

    /**
     * Adds a new instruction
     * @param instruction
     */
    public void addInstruction( IInstruction instruction );
    

    /**
     * Sets the type of the transition point
     * @param type
     */
    public void setType( String type );
    
    /**
     * Returns the type of advice
     */
    public String getType();

    public void setAspectEngine( IAspectEngine aspectEngine );
}
