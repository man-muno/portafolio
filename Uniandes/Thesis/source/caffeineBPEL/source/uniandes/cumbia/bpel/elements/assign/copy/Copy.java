package uniandes.cumbia.bpel.elements.assign.copy;

import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.assign.to.ITo;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.openobjects.execution.instance.ModelInstance;


/**
 * Represents a bpel Copy element 
 */
public class Copy extends AbstractCopy implements ICopy
{

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------
    
    /**
     * Used to specify whether the element name of the destination (as selected by the to-spec) 
     * will be replaced by the element name of the source (as selected by the from-spec) during the copy operation
     */
    private boolean keepSrcElementName;
    
    /**
     * Used to specify whether a bpel:selectionFailure standard fault is suppressed
     */
    private boolean ignoreMissingFromData;
    
    /**
     * The from associated with the copy
     */
    private IFrom from;
    
    /**
     * The to associated with the copy
     */
    private ITo to;
    
    
    /**
     * The parent process
     */
    private IProcess parentProcess;
    
    /**
     * Determines whether the joinFailure fault will be suppressed for all activities in the process. The effect of the attribute at the process level can be overridden by an
     * activity using a different value for the attribute. The default for this attribute is "no" at the process level.
     */
    protected boolean suppressJoinFailure = false;
    
    /**
     * Name of the element 
     */
    protected String name;
    
    
    /**
     * The element does'nt have a the suppressJoinFailure set and most use the defined in the process;
     */
    private boolean useProcessSuppress = true;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    
    public Copy(ModelInstance modelInstance, String elementName, String typeName)
    {
        super(modelInstance, elementName, typeName);
    }
    
    /**
     * Changes the value of the ignore missing from data attribute
     * @param b The attribute's new value
     */
    public void setIgnoreMissingFromData( boolean b )
    {
        ignoreMissingFromData = b;
    }

    /**
     * Changes the value of the keep source element name attribute
     * @param b The attribute's new value
     */
    public void setKeepSrcElementName( boolean b )
    {
        keepSrcElementName = b;
    }

    /**
     * Changes the From element for a new one
     * @param from The new From element
     */
    public void setFrom( IFrom from )
    {
        this.from = from;
    }

    /**
     * Changes the To element for a new one
     * @param to The new To element
     */
    public void setTo( ITo to )
    {
        this.to = to;
    }

    
    /**
     * Stops the element
     */
    public void stop( )
    {
        from.stop();
        to.stop();
    }

    /**
     * @return the keepSrcElementName
     */
    public boolean isKeepSrcElementName( )
    {
        return keepSrcElementName;
    }

    /**
     * @return the ignoreMissingFromData
     */
    public boolean isIgnoreMissingFromData( )
    {
        return ignoreMissingFromData;
    }

    /**
     * @return the from
     */
    public IFrom getFrom( )
    {
        return from;
    }

    /**
     * @return the to
     */
    public ITo getTo( )
    {
        return to;
    }

    /**
     * Returns the parent process of this element. If this element is the process-root, null is returned
     * @return Parent process of this element
     */
    public IProcess getParentProcess( )
    {
        return parentProcess;
    }
    
    /**
     * Sets the parent process for this element
     * @param parentProcess Parent process
     */
    public void setParentProcess( IProcess parentProcess )
    {
        this.parentProcess = parentProcess;
    }
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    public void setSuppressJoinFailure( boolean suppressJoinFailure )
    {
        this.suppressJoinFailure = suppressJoinFailure;
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
     * @return the suppressJoinFailure
     */
    public boolean isSuppressJoinFailure( )
    {
        return useProcessSuppress ? parentProcess.isSuppressJoinFailure( ) : suppressJoinFailure;
    }
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( )
    {
        useProcessSuppress = true;
    }

    /**
     * Initialize the element
     */
    public void initialize( )
    {
        from.initialize( );
        to.initialize( );
    }

    /**
     * Returns the name type of actual element
     * @return String different from null
     */
    public String getElementType( )
    {
        return ICopy.TYPE_COPY;
    }
}
