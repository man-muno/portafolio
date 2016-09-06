package uniandes.cumbia.bpel.elements;

import java.util.Hashtable;

/**
 * Defines all services provided by a variable elementS
 */
public interface IData extends IBasicElement
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------
    
    public static final String TYPE_DATA = "Data";
    
    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    public static final int TYPE = 0;
    public static final int MESSAGE_TYPE = 1;
    public static final int ELEMENT = 2;
    
    /**
     * Changes the type of the variable
     * @param type The new type of the variable
     */
    public void setType( String type );

    /**
     * Changes the message type of the variable
     * @param messageType The new message type of the variable
     */
    public void setMessageType( String messageType );

    /**
     * Changes the element related to the variable
     * @param element The new variable's element
     */
    public void setElement( String element );
    
    /**
     * Returns the message type of the variable
     * @return Message type of the variable
     */
    public String getMessageType( );
    
    /**
     * Returns the type of the variable
     * @return The type of the variable
     */
    public String getType( );
    
    /**
     * Returns the element of the variable
     * @return The element of the variable
     */
    public String getElement( );

    /**
     * Changes the type of the variable
     * @param variableType the variable type
     */
    public void setVariableType( int variableType );
    /**
     * Returns the type of the variable
     */
    public int getVariableType( );

    /**
     * Adds a part of the variable's message type
     * @param partName The name of the part
     * @param className The class of the part
     */
    public void addPart( String partName, String className );

    /**
     * Returns the table relating parts names and class names
     * @return
     */
    public Hashtable<String, String> getPartsAndClasses( );

    /**
     * Adds an instance of the type of the part
     * @param partName
     * @param instance
     */
    public void addObject( String partName, Object instance );

    /**
     * Returns the object that represents a part
     * @param partName
     * @return
     */
    public Object getPart( String partName );
    
    /**
     * Returns the table of part names and objects
     * @return
     */
    public Hashtable<String, Object> getNameObjectTable();
    
}