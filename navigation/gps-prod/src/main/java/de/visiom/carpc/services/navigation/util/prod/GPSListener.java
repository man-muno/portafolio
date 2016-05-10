package de.visiom.carpc.services.navigation.util.prod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.taimos.gpsd4java.api.IObjectListener;
import de.taimos.gpsd4java.types.ATTObject;
import de.taimos.gpsd4java.types.DeviceObject;
import de.taimos.gpsd4java.types.DevicesObject;
import de.taimos.gpsd4java.types.SKYObject;
import de.taimos.gpsd4java.types.TPVObject;
import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.messagebus.events.ValueChangeEvent;
import de.visiom.carpc.asb.servicemodel.Service;
import de.visiom.carpc.asb.servicemodel.exceptions.NoSuchParameterException;
import de.visiom.carpc.asb.servicemodel.parameters.Parameter;
import de.visiom.carpc.asb.servicemodel.valueobjects.NumberValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.ValueObject;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;
import de.visiom.carpc.asb.serviceregistry.exceptions.NoSuchServiceException;
import de.visiom.carpc.services.navigation.ServiceConstants;
import de.visiom.carpc.services.navigation.publishers.TurnByTurnPublisher;

public class GPSListener implements IObjectListener{


	private static final Logger LOG = LoggerFactory.getLogger(GPSListener.class);
	private EventPublisher eventPublisher;
	private ServiceRegistry serviceRegistry;

	public GPSListener(EventPublisher eventPublisher, ServiceRegistry serviceRegistry) {
		this.eventPublisher = eventPublisher;
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void handleTPV(TPVObject tpv) 
	{
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME,ServiceConstants.NAVIGATION_LATITUDE, tpv.getLatitude());
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME,ServiceConstants.NAVIGATION_LONGITUDE, tpv.getLongitude());
	}
	
	public void publish(String serviceName, String parameterName, Double parameterValue) {

		try {
			Service service = serviceRegistry.getService(serviceName);
			Parameter parameter = service.getParameter(parameterName);

			ValueObject value = NumberValueObject.valueOf(parameterValue);
			ValueChangeEvent valueChangeEvent = ValueChangeEvent.createValueChangeEvent(parameter, value);
			eventPublisher.publishValueChange(valueChangeEvent);
		} catch (NoSuchServiceException e) {
			LOG.error("Can't find this Service: {}", serviceName);
		} catch (NoSuchParameterException e) {
			LOG.error("Can't find this Parameter: {}", parameterName);
		}

	}

	@Override
	public void handleATT(ATTObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDevice(DeviceObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleDevices(DevicesObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleSKY(SKYObject arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
