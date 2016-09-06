package uniandes.cumbia.aspects.elements.advice;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class Advice extends AbstractAdvice
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent aspect
     */
    private IAspect parentAspect;

    /**
     * Position of the current instruction being evaluated
     */
    private int currentInstructionIndex;

    /**
     * List of all the instructions in the transition point
     */
    private List<IInstruction> instructions;
    
    /**
     * Instruction to execute next
     */
    private IInstruction instructionToExecute;

    /**
     * The type of advice
     */
    private String type;

    private IAspectEngine aspectEngine;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    /**
     * Creates the new conditional
     * 
     * @param modelInstance This is the model instance where the element exists
     * @param elementName Name that distinguishes the element in the model
     * @param typeName Name of the type
     */
    public Advice( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        currentInstructionIndex = 0;
        instructions = new ArrayList<IInstruction>( );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Stops the element
     */
    public void stop( )
    {

    }

    /**
     * Returns the count of the elements. This helps to figure out the amount of elements inside elements like a scope or a flow
     * @return the amount of elements inside the element
     */
    public int getElementCount( )
    {
        return 0;
    }

    /**
     * Returns the parent aspect of this element.
     * @return Parent aspect of this element
     */
    public IAspect getParentAspect( )
    {
        return parentAspect;
    }

    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentAspect( IAspect parentAspect )
    {
        this.parentAspect = parentAspect;
    }

    /**
     * @return the name of the element
     */
    public String getName( )
    {
        return elementName;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.elementName = name;
    }

    /**
     * Terminates execution of this element
     */
    public void finalizeElement( )
    {
        super.sendFinalizedEvent( );
    }

    /**
     * Initializes the transition point
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {

    }

    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        super.sendExecuteInstructionEvent( );
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IAdvice.TYPE_ADVICE;
    }
    
    /**
     * Calculates the next instruction to be executed
     */
    public void calculateNextInstruction( )
    {
        if(currentInstructionIndex < instructions.size( ))
        {
            instructionToExecute = instructions.get( currentInstructionIndex );
            currentInstructionIndex++;
        }
        else
        {
            currentInstructionIndex = 0;
            instructionToExecute = null;
        }
    }
    
    /**
     * Executes the next instruction
     */
    public void executeNextInstruction()
    {
        if(instructionToExecute != null)
        {
            instructionToExecute.execute();
        }
        else
        {
            super.sendFinalizedEvent();
            super.sendReInit( );
        }
    }
    
    /**
     * Returns a list of all the instructions
     * @return
     */
    public List getInstructions( )
    {
        return instructions;
    }
    
    /**
     * Adds a new instruction
     * @param instruction
     */
    public void addInstruction( IInstruction instruction )
    {
        instructions.add(instruction);
    }
    

    /**
     * Sets the type of the transition point
     * @param type
     */
    public void setType( String type )
    {
        this.type = type;
    }
    
    /**
     * Returns the type of advice
     */
    public String getType()    
    {
        return type;
    }
    
    public void setAspectEngine( IAspectEngine aspectEngine )
    {
        this.aspectEngine = aspectEngine;
        for(IInstruction instruction : instructions)
        {
            instruction.setAspectEngine(aspectEngine);
        }
    }
}
