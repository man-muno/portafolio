package de.visiom.carpc.services.navigation.model;

import java.util.ArrayList;
import java.util.List;

import de.visiom.carpc.services.navigation.util.DistanceUtilities;

public class Range {
	
	private String algorithm;
	
	private double batteryStatus;
	
	private Point initialPoint;
	
	private Point farthestPoint;
	
	private List<Point> rangePoints = new ArrayList<Point>();

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public void setBatteryStatus(double batteryStatus) {
		this.batteryStatus = batteryStatus;
	}

	public void setInitialPoint(Point initialPoint) {
		this.initialPoint = initialPoint;
		this.farthestPoint = initialPoint;
	}

	public void addPoint(double latRange, double lonRange) {
		Point newPoint = new Point(latRange, lonRange);
		if (DistanceUtilities.distanceInKM(initialPoint.getLatitude(), initialPoint.getLongitude(), latRange, lonRange) 
				> DistanceUtilities.distanceInKM(initialPoint.getLatitude(), initialPoint.getLongitude(), farthestPoint.getLatitude(), farthestPoint.getLongitude()))
			farthestPoint = newPoint;
		rangePoints.add(newPoint);
		
	}

	public Point getFarthestPoint() {	
		return farthestPoint;
	}
	
	

}
