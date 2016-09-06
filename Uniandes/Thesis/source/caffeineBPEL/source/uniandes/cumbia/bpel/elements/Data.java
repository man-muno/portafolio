package uniandes.cumbia.bpel.elements;

import java.util.Hashtable;
import java.util.Iterator;

import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;

/**
 * Represents a bpel variable
 */
public class Data extends AbstractData implements IData
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    /**
     * Name of the variable
     */
    private String name;

    /**
     * Attribute messageType refers to a WSDL message type definition
     */
    private String messageType;

    /**
     * Attribute type refers to an XML Schema type (simple or complex)
     */
    private String type;

    /**
     * Attribute element refers to an XML Schema element.
     */
    private String element;

    /**
     * The parent process
     */
    private IProcess parentProcess;

    /**
     * The type of the variable
     */
    private int variableType;

    /**
     * The table that contains the relation between the name of the part and the class
     */
    private Hashtable<String, String> nameClassTable;

    /**
     * The table that relates the part name and an instance of the object
     */
    private Hashtable<String, Object> nameObjectTable;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    public Data( ModelInstance modelInstance, String elementName, String typeName )
    {
        super( modelInstance, elementName, typeName );
        nameClassTable = new Hashtable<String, String>( );
        nameObjectTable = new Hashtable<String, Object>( );
    }

    /**
     * Changes the element related to the variable
     * @param element The new variable's element
     */
    public void setElement( String element )
    {
        this.element = element;
    }

    /**
     * Changes the message type of the variable
     * @param messageType The new message type of the variable
     */
    public void setMessageType( String messageType )
    {
        this.messageType = messageType;
    }

    /**
     * Changes the name of the variable
     * @param name New name of the variable
     */
    public void setName( String name )
    {
        this.name = name;
    }

    /**
     * Changes the type of the variable
     * @param type The new type of the variable
     */
    public void setType( String type )
    {
        this.type = type;
    }

    /**
     * Returns the name of the variable
     * @return The name of the variable
     */
    public String getName( )
    {
        return name;
    }

    /**
     * Returns the message type of the variable
     * @return Message type of the variable
     */
    public String getMessageType( )
    {
        return messageType;
    }

    /**
     * Returns the type of the variable
     * @return The type of the variable
     */
    public String getType( )
    {
        return type;
    }

    /**
     * Returns the element of the variable
     * @return The element of the variable
     */
    public String getElement( )
    {
        return element;
    }

    /**
     * Stops the element
     */
    public void stop( )
    {
        // Does nothing
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
     * @return the parentProcess
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }

    /**
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return parentProcess.isSuppressJoinFailure( );
    }

    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        //
    }

    /**
     * Establishes the parent process associated with this partner link
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }

    /**
     * Adds a part of the variable's message type
     * @param partName The name of the part
     * @param className The class of the part
     */
    public void addPart( String partName, String className )
    {
        nameClassTable.put( partName, className );
    }

    /**
     * Returns the table relating parts names and class names
     * @return
     */
    public Hashtable<String, String> getPartsAndClasses( )
    {
        return nameClassTable;
    }

    /**
     * Adds an instance of the type of the part
     * @param partName
     * @param instance
     */
    public void addObject( String partName, Object instance )
    {
        //System.out.println( "Data " + name + " setting message " + instance );
        nameObjectTable.put( partName, instance );
    }

    /**
     * Returns the object that represents a part
     * @param partName
     * @return
     */
    public Object getPart( String partName )
    {
        return nameObjectTable.get( partName );
    }
    
    /**
     * Returns the table of part names and objects
     * @return
     */
    public Hashtable<String, Object> getNameObjectTable()
    {
        return nameObjectTable;
    }

    /**
     * Changes the parents process set suppress join failure
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        // Does nothing
    }

    /**
     * Changes the type of the variable
     * @param variableType the variable type
     */
    public void setVariableType( int variableType )
    {
        this.variableType = variableType;
    }
    /**
     * Returns the type of the variable
     */
    public int getVariableType( )
    {
        return variableType;
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return IData.TYPE_DATA;
    }

    @Override
    public boolean equals(Object obj)
    {
        //System.out.println("overriden equals in Data");
        if(!(obj instanceof IData))
                return false;
        IData data = (IData) obj;
        
        boolean response = false;
        
        if(data.getName( ).equals( name ) 
                && data.getMessageType( ).equals( messageType ) 
                && data.getType( ).equals( type ) 
                && data.getElement( ).equals( element )
                && data.getVariableType( ) == variableType)
            response = response || true;
        
        
        Iterator itClassTable = nameClassTable.keySet( ).iterator( );
        while(itClassTable.hasNext( ))
        {
            String key = (String)itClassTable.next( );
            if(data.getPartsAndClasses( ).get( key ).equals( nameClassTable.get( key ) ))
                response = response || true;
        }
        
        Iterator itNameObjectTable = nameObjectTable.keySet( ).iterator( );
        while(itNameObjectTable.hasNext( ))
        {
            String key = (String)itNameObjectTable.next( );
            if(data.getPart( key ).equals( nameObjectTable.get( key ) ))
                response = response || true;
        }
        
        return response;
    }
}
