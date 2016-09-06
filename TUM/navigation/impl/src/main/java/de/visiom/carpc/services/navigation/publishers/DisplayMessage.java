package de.visiom.carpc.services.navigation.publishers;

import java.util.ArrayList;

import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;
import de.visiom.carpc.services.navigation.Destination;

public class DisplayMessage {

	private double nextManeuverDistance;
	
	private String turnByTurnMessage="";
	
	private double maxRange;
	
	private double homeRange;
	
	private double destinationRange;
	
	private double distanceToWork;

	private String maneuverType="";
	
	private String streetName="";

	private ArrayList<Destination> otherDestinations;

	private String jsonRoute;

	private String ttsMessage;

	private double distanceToStation;

	private String destinationName;

	private String routingType;

	private String destinationAddress;

	private boolean isNavigationAvailable;

	public double getDistanceToNextManeuver() {
		return nextManeuverDistance;
	}

	public void setDistanceToNextManeuver(double nextManeuverDistance) {
		this.nextManeuverDistance = nextManeuverDistance;
	}

	public String getTurnByTurnMessage() {
		return turnByTurnMessage;
	}

	public void setTurnByTurnMessage(String turnByTurnMessage) {
		this.turnByTurnMessage = turnByTurnMessage;
	}

	public double getMaxRange() {
		return maxRange;
	}

	public void setMaxRange(double maxRange) {
		this.maxRange = maxRange;
	}

	public double getHomeRange() {
		return homeRange;
	}

	public void setHomeRange(double homeRange) {
		this.homeRange = homeRange;
	}

	public double getDistanceToDestination() {
		return destinationRange;
	}

	public void setDistanceToDestination(double destinationRange) {
		this.destinationRange = destinationRange;
	}

	public String getManeuverType() {
		return maneuverType;
	}
	
	public String setManeuverType(String maneuverType) {
		return this.maneuverType = maneuverType;
	}

	public void setWorkRange(double distanceToWork) {
		this.distanceToWork = distanceToWork;
	}

	public double getWorkRange() {
		return distanceToWork;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreet(String streetName) {
		this.streetName = streetName;
	}

	@Override
	public String toString() {
		return "DisplayMessage [nextManeuverDistance=" + nextManeuverDistance
				+ ", turnByTurnMessage=" + turnByTurnMessage + ", maxRange="
				+ maxRange + ", homeRange=" + homeRange + ", destinationRange="
				+ destinationRange + ", distanceToWork=" + distanceToWork
				+ ", maneuverType=" + maneuverType + ", streetName="
				+ streetName + "]";
	}

	public void setOtherPoints(ArrayList<Destination> otherDestinations) {
		this.otherDestinations = otherDestinations;
	}
	
	public ArrayList<Destination> getOtherDestinations()
	{
		return otherDestinations;
	}

	public void setRoute(String jsonRoute) {
		this.jsonRoute = jsonRoute;
	}
	
	public String getRoute() {
		return jsonRoute;
	}

	public void setTTSMessage(String ttsMessage) {
		this.ttsMessage = ttsMessage;
	}
	
	public String getTTSMessage() {
		return ttsMessage;
	}

	public void setStationRange(double distanceToStation) 
	{
		this.distanceToStation = distanceToStation;
	}

	public double getStationRange() {
		return distanceToStation;
	}

	public String getDestination() {			
		return destinationName;
	}
	
	public void setDestination(String destinationName)
	{			
		this.destinationName = destinationName;
	}

	public void setRoutingType(String routingType) {
		this.routingType = routingType;
		
	}

	public void setDestinationAddress(String address) {
		this.destinationAddress = address;
	}

	public void setIsNavigationActive(boolean available) {
		this.isNavigationAvailable = available;
	}

	public String getRoutingType() {
		return routingType;
	}

	public String getDestinationAddress() { 
		return destinationAddress;
	}

	public boolean getIsNavigationActive() {
		return isNavigationAvailable;
	}
}
