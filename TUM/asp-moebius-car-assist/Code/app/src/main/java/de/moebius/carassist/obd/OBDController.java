package de.moebius.carassist.obd;

/**
 * Created by david on 28.01.15.
 */
public interface OBDController {

    public void addListener(OBDListener inListener);

    public void removeListener(OBDListener inListener);

    public Object getOBDValue(String inOBDAdapterName);

}
