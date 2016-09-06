package uniandes.cumbia.bpel.elements.While.actions;

import java.io.StringReader;

import uniandes.cumbia.bpel.elements.While.IWhile;
import uniandes.cumbia.bpel.engine.utils.xpath.BPELContext;
import uniandes.cumbia.bpel.engine.utils.xpath.ParseException;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathExpression;
import uniandes.cumbia.bpel.engine.utils.xpath.XPathParser;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class ReInitActivity implements uniandes.cumbia.openobjects.statemachine.IAction
{

    // -----------------------------------------------------------------
    // Constants
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Constructors
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------
    
    /**
     * Evaluates the condition of the while
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        IWhile whille = (IWhile)element;
        whille.getActivity( ).reInit();
    }

}
 