package uniandes.cumbia.aspects.elements.instruction;


public interface IInnerInstruction extends IInstruction
{

    public static final String TYPE_INSTRUCTION = "Instruction";
    
    
    public void prepareInstruction();
    
    public void executeInstruction();
    
    public void setActionName( String actionName );

    public void setActionClass( String actionClass );
    
    public String getActionName( );

    public String getActionClass(  );
    
}
