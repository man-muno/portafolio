package uniandes.cumbia.aspects.elements.instruction;

import uniandes.cumbia.aspects.elements.aspect.IAspect;
import uniandes.cumbia.aspects.engine.IAspectEngine;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

public class InstallAction extends AbstractInstruction implements IInnerInstruction
{


    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * The parent aspect
     */
    private IAspect parentAspect;
    private String actionName;
    private String actionClass;
    private IAspectEngine aspectEngine;

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------
    
    /**
     * 
     */
    public InstallAction( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
    }

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
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
     * Initializes the transition point
     * @param utils The instance utils associated with the instance
     */
    public void initialize( )
    {
        super.sendPrepareEvent( );
    }
    
    public void prepareInstruction()
    {
        super.sendExecuteEvent( );
    }
    
    public void executeInstruction()
    {
        super.sendFinalizedEvent( );
        super.sendReInitEvent( );
    }
    
    
    /**
     * Method that begins execution of the activity
     */
    public void execute( )
    {
        
    }

    /**
     * Stops the element
     */
    public void stop( )
    {

    }
    
    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IInstruction.TYPE_INSTRUCTION;
    }

    public void setActionName( String actionName )
    {
        this.actionName = actionName;
    }

    public void setActionClass( String actionClass )
    {
        this.actionClass = actionClass;
    }
    
    public String getActionName( )
    {
        return actionName;
    }

    public String getActionClass(  )
    {
        return actionClass;
    }

    public void setAspectEngine( IAspectEngine aspectEngine )
    {
        this.aspectEngine = aspectEngine;
    }
}
