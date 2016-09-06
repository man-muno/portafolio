package uniandes.cumbia.aspects.elements.aspectedElement;

import java.util.ArrayList;
import java.util.List;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.elements.instruction.IInstruction;
import uniandes.cumbia.aspects.elements.transitionPoint.ITransitionPoint;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class AspectedElement extends AbstractAspectedElement
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent aspect
     */
    private IAspect parentAspect;

    /**
     * Name of the element
     */
    protected String name;

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
    public AspectedElement( ModelInstance modelInstance, String elementName, String typeName )
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
        return name;
    }

    /**
     * Changes the name of the element
     * @param name the name to set
     */
    public void setName( String name )
    {
        this.name = name;
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

    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IAspectedElement.TYPE_ASPECTED_ELEMENT;
    }

}