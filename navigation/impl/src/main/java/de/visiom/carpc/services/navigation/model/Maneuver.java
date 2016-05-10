package de.visiom.carpc.services.navigation.model;

import java.util.ArrayList;
import java.util.List;

import de.visiom.carpc.services.navigation.util.DistanceUtilities;
import de.visiom.carpc.services.navigation.util.ManeuverType;

public class Maneuver {
	
	
	private Point startPoint;
	
	private ManeuverType maneuverType;

	private double travelDistance;
	
	private double travelDuration;
	
	private int compassDegree;
	
	private String compassDirection;
	
	private String streetName;

	private List<Warning> warnings = new ArrayList<Warning>();
	
	/**
	 * Complete message to show to the HUD
	 */
	private String textMessage;
	
	private double distanceToNextManeuver = 0;

	private boolean hasLeg;
	
	public Maneuver(double lat, 
			double lon, 
			String textMessage, 
			String street, 
			ManeuverType maneuverType,
			int compassDegree, 
			String compassDirection, 
			double travelDuration) {
		this.startPoint = new Point(lat,lon);
		this.textMessage = textMessage;
		this.maneuverType = maneuverType;
		this.streetName = street;
		this.compassDegree = compassDegree;
		this.compassDirection = compassDirection;
		this.distanceToNextManeuver = travelDistance;
		this.travelDuration = travelDuration;
	}

	public double getTravelDistance() {
		return travelDistance;
	}

	public String getTravelDistanceMessage() 
	{
		String message = ""; 
		if(travelDistance == 0)
		{
			message = "";
		}
		else if(travelDistance < 1)
			message = "In "+ (int)(travelDistance * 1000) + " meters";
		else
			message = "In "+ (int)travelDistance + " kilometers";
		
		return message;
	}
	
	

	public double getTravelDuration() {
		return travelDuration;
	}


	public int getCompassDegree() {
		return compassDegree;
	}


	public String getCompassDirection() {
		return compassDirection;
	}


	public double getLatitude() {
		return startPoint.getLatitude();
	}


	public double getLongitude() {
		return startPoint.getLongitude();
	}


	public String getTextMessage() {
		String message = "";
		if(textMessage.length() > 0)
		{
			message = textMessage; 
		}
		else
		{
			message = maneuverType.getText().length() > 0  ?  maneuverType.getText() : "";
			message = message + (maneuverType.getPreposition().length() > 0  ?  " " + maneuverType.getPreposition() : "");
			message = message + (getStreetName().length() > 0  ?  " " + getStreetName() : "");
		}
		return  message;
	}
	
	public String getType() {
		return  maneuverType != null ? maneuverType.getGreenNavCode() + "": ManeuverType.NONE.getGreenNavCode()+"" ;
	}
	
	public ManeuverType getManeuverType(){
		return maneuverType;
	}
	
	public String getStreetName() {
		return streetName;
	}


	public String getTravelTime() {
		int minutes = (int) (travelDuration/(60));
		int seconds = (int) ((travelDuration) % 60);
		String str = String.format("%d:%02d", minutes, seconds);
		return str;
	}

	public double getDistanceToNextManeuver() {
		return this.distanceToNextManeuver;
	}


	public void setDistanceToNextManeuver(double distance) {
		this.distanceToNextManeuver = distance;
	}


	@Override
	public String toString() {
		return startPoint.toString();
	}
	
	public Point getPoint()
	{
		return startPoint;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((maneuverType == null) ? 0 : maneuverType.hashCode());
		result = prime * result
				+ ((startPoint == null) ? 0 : startPoint.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maneuver other = (Maneuver) obj;
		if (maneuverType != other.maneuverType)
			return false;
		if (startPoint == null) {
			if (other.startPoint != null)
				return false;
		} else if (!startPoint.equals(other.startPoint))
			return false;
		return true;
	}


	public void addWarning(String severity, String type, String warningText) {
		
		warnings.add(new Warning(severity, type, warningText));
	}


	public boolean hasLeg() {
		return hasLeg;
	}


	public void legSet() {
		hasLeg = true;
	}
	
}
