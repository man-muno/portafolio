package de.visiom.carpc.services.navigation.model;

import de.visiom.carpc.services.navigation.util.DistanceUtilities;

public class Point {
	
	private static final double RADIOUS = 0.002;

	private static final double EQUALITY_DELTA = 0.0001;
	
	private double latitude;
	
	private double longitude;

	public Point(double lat, double lon) {
		this.latitude = lat;
		this.longitude = lon;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public boolean isNear(Point otherPoint) {
		double distance = DistanceUtilities.distanceInKM(latitude, longitude, otherPoint.latitude, otherPoint.longitude); 
		return distance < RADIOUS;
	}

	@Override
	public String toString() {
		return latitude + "," + longitude;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Point other = (Point) obj;
		if (Math.abs(Double.doubleToLongBits(latitude) - Double
				.doubleToLongBits(other.latitude)) > EQUALITY_DELTA)
			return false;
		if (Math.abs(Double.doubleToLongBits(longitude) - Double
				.doubleToLongBits(other.longitude)) > EQUALITY_DELTA )
			return false;
		return true;
	}
}
