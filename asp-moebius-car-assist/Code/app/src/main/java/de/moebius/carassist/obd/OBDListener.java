package de.moebius.carassist.obd;

import java.util.EventListener;

/**
 * Created by tlamp on 09.01.2015.
 */
public interface OBDListener extends EventListener{
    public void handleEvent(OBDEvent inEvent);

}
