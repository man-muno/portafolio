package uniandes.cumbia.bpel.elements.conditional.Else;

import uniandes.cumbia.bpel.elements.IActivity;

public interface IElse extends IActivity
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    public static final String TYPE_ELSE = "Else";

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Changes the element to be executed in case the evaluated condition is true
     * @param activity T
     */
    public void setActivity( IActivity activity );

    /**
     * @return the trueElement
     */
    public IActivity getActivity( );
}
