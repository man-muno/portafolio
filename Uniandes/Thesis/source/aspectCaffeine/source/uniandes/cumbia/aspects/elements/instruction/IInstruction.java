package uniandes.cumbia.aspects.elements.instruction;

import uniandes.cumbia.aspects.elements.IBasicElement;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.elements.IOpenObject;

public interface IInstruction extends IBasicElement, IOpenObject
{

    public static final String TYPE_INSTRUCTION = "Instruction";
    
    
    public void prepareInstruction();
    
    public void executeInstruction();

    public void setAspectEngine( IAspectEngine aspectEngine );

   
    
}
