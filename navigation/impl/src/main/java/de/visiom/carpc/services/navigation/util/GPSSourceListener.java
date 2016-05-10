package de.visiom.carpc.services.navigation.util;

public interface GPSSourceListener {

    public void onGPSCoordinatesChange(double latitude, double longitude);
    public void setGpsSource(GPSSource gpsSource);

}
