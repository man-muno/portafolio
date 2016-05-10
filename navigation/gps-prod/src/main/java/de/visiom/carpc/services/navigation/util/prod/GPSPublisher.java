package de.visiom.carpc.services.navigation.util.prod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.messagebus.async.ParallelWorker;
import de.visiom.carpc.asb.messagebus.events.ValueChangeEvent;
import de.visiom.carpc.asb.servicemodel.Service;
import de.visiom.carpc.asb.servicemodel.exceptions.NoSuchParameterException;
import de.visiom.carpc.asb.servicemodel.parameters.Parameter;
import de.visiom.carpc.asb.servicemodel.valueobjects.NumberValueObject;
import de.visiom.carpc.asb.servicemodel.valueobjects.ValueObject;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;
import de.visiom.carpc.asb.serviceregistry.exceptions.NoSuchServiceException;
import de.visiom.carpc.services.navigation.ServiceConstants;
import de.visiom.carpc.services.navigation.util.GPSSource;
import de.visiom.carpc.services.navigation.util.GPSSourceListener;

public class GPSPublisher extends ParallelWorker{


	private static final Logger LOG = LoggerFactory.getLogger(GPSPublisher.class);

	private EventPublisher eventPublisher;

	private ServiceRegistry serviceRegistry;

	private double longitude;

	private double latitude;

	@Override
    public void initialize() {
        
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
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME,ServiceConstants.NAVIGATION_LATITUDE, latitude);
		publish(ServiceConstants.NAVIGATION_SERVICE_NAME,ServiceConstants.NAVIGATION_LONGITUDE, longitude);
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

	public void setCoordinates(double latitude, double longitude) 
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
