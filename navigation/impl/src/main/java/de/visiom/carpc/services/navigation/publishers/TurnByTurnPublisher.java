package de.visiom.carpc.services.navigation.publishers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.messagebus.async.ParallelWorker;
import de.visiom.carpc.asb.messagebus.events.ValueChangeEvent;
import de.visiom.carpc.asb.parametervalueregistry.exceptions.UninitalizedValueException;
import de.visiom.carpc.asb.servicemodel.Service;
import de.visiom.carpc.asb.servicemodel.exceptions.NoSuchParameterException;
import de.visiom.carpc.asb.servicemodel.parameters.NumericParameter;
import de.visiom.carpc.asb.servicemodel.parameters.Parameter;
import de.visiom.carpc.asb.servicemodel.parameters.SetParameter;
import de.visiom.carpc.asb.servicemodel.parameters.StringParameter;
import de.visiom.carpc.asb.servicemodel.parameters.SwitchParameter;
import de.visiom.carpc.asb.servicemodel.valueobjects.NumberValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.StateValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.StringValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.SwitchValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.ValueObject;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;
import de.visiom.carpc.asb.serviceregistry.exceptions.NoSuchServiceException;
import de.visiom.carpc.services.navigation.Navigation;
import de.visiom.carpc.services.navigation.ServiceConstants;
import de.visiom.carpc.services.navigation.exceptions.NavigationException;
import de.visiom.carpc.services.navigation.exceptions.RerouteException;

public class TurnByTurnPublisher extends ParallelWorker {

	private static final Logger LOG = LoggerFactory.getLogger(TurnByTurnPublisher.class);

	private EventPublisher eventPublisher;

	private ServiceRegistry serviceRegistry;

	private Navigation navigation;
	
	
	@Override
	public void initialize() throws NoSuchParameterException,NoSuchServiceException, UninitalizedValueException {
		//Get the navi and publish if there are any instructions
		navigation = Navigation.INSTANCE;
	}

	@Override
	public long getExecutionInterval() {
		return 500;
	}

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
	
	@Override
	public void execute() 
	{
		try {
			publishNaviMessage(navigation.getNavigationMessage());
		} catch (RerouteException e) {
			try {
				navigation.reroute();
			} catch (NavigationException e1) {
				LOG.error(e1.getMessage());
			}
		} catch (NavigationException e) {
			LOG.error(e.getMessage());
		}
	}


    private void publishNaviMessage(DisplayMessage turnByTurn) 
    {    	
    	
    	publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_MESSAGE_NAME , turnByTurn.getTurnByTurnMessage());
    	publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_STREET_NAME , turnByTurn.getStreetName());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_TTS_MESSAGE_NAME , turnByTurn.getTTSMessage());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DISTANCE_DEST_NAME , turnByTurn.getDistanceToDestination());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DISTANCE_MANEUVER_NAME , turnByTurn.getDistanceToNextManeuver());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DISTANCE_HOME_NAME , turnByTurn.getHomeRange());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DISTANCE_WORK_NAME , turnByTurn.getWorkRange());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DISTANCE_STATION_NAME , turnByTurn.getStationRange());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_RANGE_MAX_NAME , turnByTurn.getMaxRange());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_MANEUVER_TYPE_NAME , turnByTurn.getManeuverType());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_JSON_ROUTE , turnByTurn.getRoute() );
		//publishState(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DESTINATION , turnByTurn.getDestination());
		//publishState(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_ROUTING_TYPE , turnByTurn.getRoutingType());
		//publish(ServiceConstants.NAVIGATION_SERVICE_NAME, ServiceConstants.NAVIGATION_DESTINATION_ADDRESS , turnByTurn.getDestinationAddress());
		
	}

	
	public void publish(String serviceName, String parameterName, SetValueObject parameterValue) {

		try {
			Service service = serviceRegistry.getService(serviceName);
			Parameter parameter = (Parameter) service.getParameter(parameterName);
			ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, parameterValue);
			eventPublisher.publishValueChange(valueChangeEvent);
		} catch (NoSuchServiceException e) {
			LOG.error("Can't find this Service: {}", serviceName);
		} catch (NoSuchParameterException e) {
			LOG.error("Can't find this Parameter: {}", parameterName);
		} catch (Exception e) {
			//System.out.println(parameterName + " " + e.getMessage() + " setValueObject");
			e.printStackTrace();
		}

	}
	
	public void publish(String serviceName, String parameterName, Double parameterValue) {

		if(parameterValue != 0.0)
		{
			try {
				Service service = serviceRegistry.getService(serviceName);
				Parameter parameter = (NumericParameter) service.getParameter(parameterName);
				ValueObject value = NumberValueObject.valueOf(parameterValue);
				ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
				eventPublisher.publishValueChange(valueChangeEvent);
			} catch (NoSuchServiceException e) {
				LOG.error("Can't find this Service: {}", serviceName);
			} catch (NoSuchParameterException e) {
				LOG.error("Can't find this Parameter: {}", parameterName);
			} catch (Exception e) {
				//System.out.println(parameterName + " " + e.getMessage() + " Double");
				e.printStackTrace();
			}
		}
	}

	
	public void publish(String serviceName, String parameterName, String parameterValue) {
		if(parameterValue!= null && parameterValue.length() > 0)
		{
			try {
				Service service = serviceRegistry.getService(serviceName);
				Parameter parameter = (StringParameter) service.getParameter(parameterName);
				ValueObject value = StringValueObject.valueOf(parameterValue);
				ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
				eventPublisher.publishValueChange(valueChangeEvent);
			} catch (NoSuchServiceException e) {
				LOG.error("Can't find this Service: {}", serviceName);
			} catch (NoSuchParameterException e) {
				LOG.error("Can't find this Parameter: {}", parameterName);
			} catch (Exception e) {
				//System.out.println(parameterName + " " + e.getMessage() + " String");
				e.printStackTrace();
			}
		}
	}
	
	public void publishEmptyString(String serviceName, String parameterName) {
			try {
				Service service = serviceRegistry.getService(serviceName);
				Parameter parameter = (StringParameter) service.getParameter(parameterName);
				ValueObject value = StringValueObject.valueOf("");
				ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
				eventPublisher.publishValueChange(valueChangeEvent);
			} catch (NoSuchServiceException e) {
				LOG.error("Can't find this Service: {}", serviceName);
			} catch (NoSuchParameterException e) {
				LOG.error("Can't find this Parameter: {}", parameterName);
			} catch (Exception e) {
				//System.out.println(parameterName + " " + e.getMessage() + " String");
				e.printStackTrace();
			}
	}
	
	
	public void publishState(String serviceName, String parameterName, String parameterValue) {
		if(parameterValue!= null && parameterValue.length() > 0)
		{
			try {
				Service service = serviceRegistry.getService(serviceName);
				Parameter parameter = (Parameter) service.getParameter(parameterName);
				ValueObject value = StateValueObject.valueOf(parameterValue);
				ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
				eventPublisher.publishValueChange(valueChangeEvent);
			} catch (NoSuchServiceException e) {
				LOG.error("Can't find this Service: {}", serviceName);
			} catch (NoSuchParameterException e) {
				LOG.error("Can't find this Parameter: {}", parameterName);
			} catch (Exception e) {
				//System.out.println(parameterName + " " + e.getMessage() + " String");
				e.printStackTrace();
			}
		}
	}

	public void publish(String serviceName, String parameterName, boolean switchPosition) {
		try {
			Service service = serviceRegistry.getService(serviceName);
			Parameter parameter = (SwitchParameter) service.getParameter(parameterName);
			ValueObject value = SwitchValueObject.valueOf(switchPosition);
			ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
			eventPublisher.publishValueChange(valueChangeEvent);
		} catch (NoSuchServiceException e) {
			LOG.error("Can't find this Service: {}", serviceName);
		} catch (NoSuchParameterException e) {
			LOG.error("Can't find this Parameter: {}", parameterName);
		} catch (Exception e) {
			//System.out.println(parameterName + " " + e.getMessage() + " switch ");
			e.printStackTrace();
		}
	}
}