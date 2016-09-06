package uniandes.cumbia.bpel.elements.invoke.actions;

import java.net.MalformedURLException;
import java.net.URL;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.invoke.IInvoke;
import uniandes.cumbia.bpel.elements.process.IProcess;
import uniandes.cumbia.bpel.engine.IProcessEngine;
import uniandes.cumbia.bpel.instance.InstanceId;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.Transition;

public class SendMessage implements uniandes.cumbia.openobjects.statemachine.IAction
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
     * Sends the message for the invoke
     * 
     * @param event
     * @param transition The transition that this action belongs to
     * @param element The element that is the owner of the state machine
     */
    public void execute( EventNotification event, Transition transition, IOpenObject element )
    {
        IChannel channel = ( (IInteraction )element).getChannel( );
        IProcessEngine engine = ((IInvoke)element).getParentProcess( ).getEngine( );
        IInvoke invoke = ((IInvoke)element);
        IProcess parentProcess = ((IInvoke)element).getParentProcess( );
        InstanceId instanceID = parentProcess.getInstanceSpaceId( );
        int serviceID = instanceID.getServiceID( ); 
        
        String uri = channel.getPartnerLinkURI( );

        try
        {
            new URL( channel.getPartnerLinkURI( ) );
        }
        catch( MalformedURLException e )
        {
            uri = instanceID.getClientURI( );
        }

        if( !invoke.isAsynch( ))
        {
            invoke.waitResponse();
            Object outputMessage = engine.invokeSynchronusPartnerLink( channel.getName( ), uri, invoke.getPortType( ), invoke.getOperation( ), channel.getInboundMessage( ), instanceID, serviceID );
            invoke.messageReceived(outputMessage);
        }
        else
        {
            engine.invokeAsynchronusPartnerLink( channel.getName( ), uri, invoke.getPortType( ), invoke.getOperation( ), channel.getInboundMessage( ), invoke, instanceID, serviceID);
            invoke.finalized();
        }

    }

}
