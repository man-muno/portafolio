package uniandes.cumbia.bpel.elements.assign.copy;

import uniandes.cumbia.bpel.elements.IBasicElement;
import uniandes.cumbia.bpel.elements.assign.from.IFrom;
import uniandes.cumbia.bpel.elements.assign.to.ITo;

/**
 * Defines the services provided by the Copy element
 */
public interface ICopy extends IBasicElement
{

    public static final String TYPE_COPY = "Copy";
    
    /**
     * Changes the value of the keep source element name attribute
     * @param b The attribute's new value
     */
    public void setKeepSrcElementName( boolean b );

    /**
     * Changes the value of the ignore missing from data attribute
     * @param b The attribute's new value
     */
    public void setIgnoreMissingFromData( boolean b );

    /**
     * Changes the From element for a new one
     * @param from The new From element
     */
    public void setFrom( IFrom from );

    /**
     * Changes the To element for a new one
     * @param to The new To element
     */
    public void setTo( ITo to );
    
    /**
     * @return the keepSrcElementName
     */
    public boolean isKeepSrcElementName( );

    /**
     * @return the ignoreMissingFromData
     */
    public boolean isIgnoreMissingFromData( );

    /**
     * @return the from
     */
    public IFrom getFrom( );

    /**
     * @return the to
     */
    public ITo getTo( );
    
    /**
     * Changes the value of the suppress join failure property
     * @param suppressJoinFailure The new value for the property
     */
    void setSuppressJoinFailure( boolean suppressJoinFailure );
    
    /**
     * @return the suppressJoinFailure
     */
    boolean isSuppressJoinFailure( );
    
    /**
     * Sets that the element has the suppressJoinFailire not set and if asked must use the one from the process
     */
    public void useActivitySupressJoinFailure( );

}