package uniandes.cumbia.bpel.elements.startingPoints.actions;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.startingPoints.IStartingPick;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class RegisterOnMessage implements IAction
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
        
        //System.out.println("RegisterOnMessage action created");
        //Begin time
        long beginTime = System.currentTimeMillis();
        
        IChannel channel = ((IInteraction) element).getChannel( );
        IStartingPick pick = (IStartingPick) element;
        pick.getParentProcess( ).getEngine( ).registerElementExpectingMessage( channel.getName( ), channel.getPartnerLinkType( ), pick.getPortType( ), pick.getOperation( ), channel.getInboundMessage( ).getClass( ).getName( ), pick, pick.getParentProcess( ).getInstanceSpaceId( ), beginTime );
    }
}
