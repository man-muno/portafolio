package de.visiom.carpc.services.navigation.handlers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.visiom.carpc.asb.messagebus.CommandPublisher;
import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.messagebus.commands.GenericResponse;
import de.visiom.carpc.asb.messagebus.commands.Response;
import de.visiom.carpc.asb.messagebus.commands.ValueChangeRequest;
import de.visiom.carpc.asb.messagebus.events.ValueChangeEvent;
import de.visiom.carpc.asb.messagebus.handlers.ValueChangeRequestHandler;
import de.visiom.carpc.asb.servicemodel.Service;
import de.visiom.carpc.asb.servicemodel.exceptions.IncompatibleValueException;
import de.visiom.carpc.asb.servicemodel.exceptions.NoSuchParameterException;
import de.visiom.carpc.asb.servicemodel.parameters.Parameter;
import de.visiom.carpc.asb.servicemodel.parameters.SetParameter;
import de.visiom.carpc.asb.servicemodel.valueobjects.ValueObject;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;
import de.visiom.carpc.asb.serviceregistry.exceptions.NoSuchServiceException;
import de.visiom.carpc.services.navigation.ServiceConstants;

public class NavigationRequestHandler extends ValueChangeRequestHandler {

	
	private static final Logger LOG = LoggerFactory.getLogger(NavigationRequestHandler.class);
	
	private static final String RECEIVE_LOG_MESSAGE = "Received a value change request for {}. Dispatching the request to the ValueStore...";
	
	private CommandPublisher commandPublisher;

	private EventPublisher eventPublisher;

	private ServiceRegistry serviceRegistry;

	public void setEventPublisher(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void setCommandPublisher(CommandPublisher commandPublisher) {
		this.commandPublisher = commandPublisher;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}
	

	public void start() throws NoSuchParameterException, NoSuchServiceException, IncompatibleValueException 
	{
		
	}

	@Override
	public void onValueChangeRequest(ValueChangeRequest request, Long requestID) {
		LOG.info(RECEIVE_LOG_MESSAGE, request.getParameter());
		//System.out.println("received " + request.getParameter().getName() + " value " + request.getValue());
		Response response = GenericResponse.createGenericResponse(GenericResponse.STATUS_OK);
		ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(request.getParameter(), request.getValue());
		eventPublisher.publishValueChange(valueChangeEvent);
		commandPublisher.publishResponse(response, requestID, request.getParameter().getService());
	}
	
}
