package de.moebius.carassist.obd;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.eventsource.client.EventSource;

/**
 * Created by tlamp on 09.01.2015.
 */
public class WebOBDController implements  OBDController {

    private static OBDController INSTANCE = null;

    public static synchronized OBDController getInstance() {
        if (WebOBDController.INSTANCE == null) {
            INSTANCE = new WebOBDController();
        }
        return INSTANCE;
    }

    private List<OBDListener> listeners  =new LinkedList<OBDListener>();

    private Map<String, EventSource> eventSources = new HashMap<String,EventSource>();


    private static String BASE_URL = "http://vmkrcmar59.informatik.tu-muenchen.de:8080/services/obd/parameters/";

    private WebOBDController() {
        EventSource ev1 = new DefaultEventSourceLong("currentSpeed", BASE_URL, this);
        eventSources.put("currentSpeed", ev1);
        ev1.connect();
        EventSource ev2 = new DefaultEventSourceLong("coolantTemperature", BASE_URL, this);
        eventSources.put("coolantTemperature", ev2);
        ev2.connect();
        eventSources.put("coolantWarningLight", new DefaultEventSourceBoolean("coolantWarningLight", BASE_URL, this));

    }

    public synchronized void newValue(String signalName, Object value) {
        OBDData.put(signalName, value);
        notifyListener(new OBDEvent(this));
    }

    public void addListener(OBDListener inListener){
        listeners.add(inListener);
    }
    public void removeListener(OBDListener inListener){
        listeners.remove(inListener);
    }
    protected synchronized void notifyListener(OBDEvent inEvent){
        for (OBDListener listener : listeners) {
            listener.handleEvent(inEvent);
        }
    }
    protected Map<String, Object> OBDData = Collections.synchronizedMap(new HashMap<String, Object>());

    public Object getOBDValue(String inOBDAdapterName) {
        Object outObject = this.OBDData.get(inOBDAdapterName);
        if (outObject == null){
            throw new RuntimeException("No such OBD adapter available " + inOBDAdapterName);
        }
        return outObject;
    }

}
