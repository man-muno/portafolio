package de.visiom.carpc.services.navigation.util.prod;

import java.io.IOException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.taimos.gpsd4java.backend.GPSdEndpoint;
import de.taimos.gpsd4java.backend.ResultParser;
import de.visiom.carpc.asb.messagebus.EventPublisher;
import de.visiom.carpc.asb.serviceregistry.ServiceRegistry;

public class GPS implements IGPS{

    private static final Logger LOG = LoggerFactory.getLogger(GPS.class);

    private GPSdEndpoint gpsEndpoint;

	private ServiceRegistry serviceRegistry;

	private EventPublisher eventPublisher;

    public void init() throws InterruptedException {
    	
            String host = "localhost";
            int port = 2947;
            boolean active = true;
            try {
                gpsEndpoint = new GPSdEndpoint(host, port, new ResultParser());
                gpsEndpoint.addListener(new GPSListener(eventPublisher,serviceRegistry));
                gpsEndpoint.start();
                gpsEndpoint.watch(true, true);
            } catch (Exception e) {
                LOG.error("IO error", e);
              
            }
    }
    
    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
    
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

}
