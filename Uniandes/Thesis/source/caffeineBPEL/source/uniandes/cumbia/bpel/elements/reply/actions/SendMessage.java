/*******************************************************************************
 * $Id: SendMessage.java,v 1.6 2009/02/10 01:45:04 man-muno Exp $
 * 
 * Proyecto Cumbia
 * (http://agamenon.uniandes.edu.co/~csw)
 * 
 * Grupo de Investigaci�n en Construcci�n de Software
 * Departamento de Ingenier�a de Sistemas y Computaci�n
 * Universidad de los Andes
 * Bogot� - Colombia
 * 
 * Copyright (c) 2008
 * Todos los derechos reservados. 
 * 
 *******************************************************************************/
package uniandes.cumbia.bpel.elements.reply.actions;

import java.io.Serializable;

import uniandes.cumbia.bpel.elements.IChannel;
import uniandes.cumbia.bpel.elements.IInteraction;
import uniandes.cumbia.bpel.elements.reply.IReply;
import uniandes.cumbia.openobjects.elements.IOpenObject;
import uniandes.cumbia.openobjects.execution.events.EventNotification;
import uniandes.cumbia.openobjects.statemachine.IAction;
import uniandes.cumbia.openobjects.statemachine.Transition;

/**
 * Action for the Process State Machine
 */
public class SendMessage implements IAction, Serializable
{
	// -----------------------------------------------------------------
	// Methods
	// -----------------------------------------------------------------
	/**
	 * Stops and resets all the activities and subprocesses
	 * 
	 * @param event 
	 * @param transition The transition that this action belongs to
	 * @param element The element that is the owner of the state machine
	 */
	public void execute(EventNotification event, Transition transition, IOpenObject element)
	{
	    //System.out.println("SendMessage action");
	    IChannel channel = ((IInteraction) element).getChannel( );
		IReply reply = (IReply) element;
		reply.getParentProcess( ).getEngine( ).sendReplyResponse(channel.getName( ),reply.getPortType( ),reply.getOperation( ), channel.getOutboundMessage( ).getPart( "payload" ), reply.getName(), reply.getParentProcess( ).getInstanceSpaceId( ));
	}

}
