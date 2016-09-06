package de.moebius.carassist.obd;

/**
 * Created by tlamp on 09.01.2015.
 */
public class OBDEvent {

    private WebOBDController source;

    /**
     * Constructs a new instance of this class.
     *
     * @param inSource the object which fired the event.
     */
    public OBDEvent(WebOBDController inSource) {
        this.source = inSource;
    }

    public WebOBDController getSource() {
        return this.source;
    }

}
