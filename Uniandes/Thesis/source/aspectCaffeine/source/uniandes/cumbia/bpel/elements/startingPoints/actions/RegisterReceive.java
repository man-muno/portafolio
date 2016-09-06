package uniandes.cumbia.bpel.elements.startingPoints.actions;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingReceive;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class RegisterReceive implements IAction
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
     * Register an element to receive a message
     * 
     * @param event 
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        
        //System.out.println("RegisterReceive action created");
        //Begin time
        long beginTime = System.currentTimeMillis();
        
        IChannel channel = ((IInteraction) element).getChannel(  );
        IStartingReceive receive = (IStartingReceive) element;
        receive.getParentProcess( ).getEngine( ).registerElementExpectingMessage( channel.getName( ), channel.getPartnerLinkType( ), receive.getPortType( ), receive.getOperation( ), channel.getInboundMessage( ).getClass( ).getName( ), receive, receive.getParentProcess( ).getInstanceSpaceId( ), beginTime );
    }
}
