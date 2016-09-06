package uniandes.cumbia.bpel.elements.pick.onmessage.actions;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.pick.onmessage.IOnMessage;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class RegisterOnMessage implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Registers the onMessage
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute(EventNotification event, Transition transition, IOpenObject element)
    {
        //System.out.println( "RegisterOnMessage action created" );
        // Begin time
        long beginTime = System.currentTimeMillis( );

        IChannel channel = ( ( IInteraction )element ).getChannel( );
        IOnMessage onMessage = ( IOnMessage )element;
        onMessage.getParentProcess( ).getEngine( ).registerElementExpectingMessage( channel.getName( ), channel.getPartnerLinkType( ), onMessage.getPortType( ), onMessage.getOperation( ), channel.getInboundMessage( ).getClass( ).getName( ), onMessage, onMessage.getParentProcess( ).getInstanceSpaceId( ), beginTime );
    }

}
