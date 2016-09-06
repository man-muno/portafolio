package de.visiom.carpc.services.navigation.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import de.visiom.carpc.services.navigation.Destination;
import de.visiom.carpc.services.navigation.exceptions.NoMorePointsException;
import de.visiom.carpc.services.navigation.exceptions.RerouteException;
import de.visiom.carpc.services.navigation.publishers.DisplayMessage;
import de.visiom.carpc.services.navigation.util.DistanceUtilities;
import de.visiom.carpc.services.navigation.util.ManeuverType;

public class Route {
	
	private static final double TTS_MAX_DISTANCE = 1.0;
	
	private static final double MINIMUM_POINT_DISTANCE = 0.003;

	private static final double MAXIMUM_POINT_DISTANCE = .1;

	private static final int MAXIMUM_DRIVEN_BY = 6;
	
	private static final Logger LOG = LoggerFactory.getLogger(Route.class);

	private Maneuver nextManeuver = null;

	private double travelDistance = 0;

	private double travelDuration = 0;

	private Destination destination;
	
	private Destination origin;

	private boolean hasHighway;

	private String routingType;

	private double oldDistanceToNextPoint;

	private Point nextPoint;

	private int countDrivenBy;

	private ArrayList<Point> legPoints = new ArrayList<Point>();
	
	private ArrayList<Maneuver> maneuverPoints = new ArrayList<Maneuver>();

	private int currentPointIndex;
	
	private ArrayList<Destination> otherDestinations = new ArrayList<Destination>();

	private int currentManuverIndex;
	
	private int oldManuverIndex;
	
	private Point getNextPoint(double currentLatitude, double currentLongitude) throws NoMorePointsException 
	{
		Point point = nextPoint;
		double distanceToPoint = 10000;
		int pointIndex = 0;
		boolean tooFar = false;
		int counter = 0;
		//Find the closest point to the current coordinates
		for(int i=currentPointIndex;i<legPoints.size() && !tooFar ;i++)
		{
			point = legPoints.get(i);
			double newDistance = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude, point.getLatitude(), point.getLongitude());
			if(newDistance < distanceToPoint && !point.equals(nextPoint))
			{
				distanceToPoint = newDistance;
				pointIndex = i;
				counter = 0;
			}
			if(counter > 10)
				tooFar = true;
			counter++;
				
		}
		
		currentPointIndex = pointIndex == 0 ? currentPointIndex : pointIndex;
		nextPoint = legPoints.get(currentPointIndex);
		
		boolean maneuverFound = false;
		for(int i=currentPointIndex;i<legPoints.size() && !maneuverFound;i++)
		{
			Point tempManeuver = legPoints.get(i);
			if(tempManeuver instanceof ManeuverPoint)
			{
				nextManeuver = ((ManeuverPoint)tempManeuver).getManeuver();
				maneuverFound = true;
				currentManuverIndex = i;
			}
		}
		
		
		return nextPoint;
	}

	public double getTravelDistance() {
		return travelDistance;
	}

	public double getTravelDuration() {
		return travelDuration;
	}

	public void setTravelDuration(double travelDuration) {
		this.travelDuration = travelDuration;
	}

	public void setTravelDistance(double travelDistance) {
		this.travelDistance = travelDistance;
	}

	public void getTurnByTurnMessage(double currentLatitude, double currentLongitude, DisplayMessage message) throws RerouteException {
		try {
			// If no point, then there is a route but no routing points.
			if (nextPoint != null) {
				// Calculate the distance to the point
				double distanceToNextPoint = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude,nextPoint.getLatitude(), nextPoint.getLongitude());
				travelDistance += Math.abs(oldDistanceToNextPoint - distanceToNextPoint);
				if (distanceToNextPoint > oldDistanceToNextPoint) {
					// The points that should be getting closer are actually separating
					if (distanceToNextPoint > MAXIMUM_POINT_DISTANCE && countDrivenBy > MAXIMUM_DRIVEN_BY) {
						// The distance is greater than the constant. Reroute
						throw new RerouteException("Reroute: distance to next point: " + distanceToNextPoint + " count " + countDrivenBy);
					} else {
						// Assume drive by old point
						nextPoint = getNextPoint(currentLatitude, currentLongitude);
						// update distance
						oldDistanceToNextPoint = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude, nextPoint.getLatitude(),nextPoint.getLongitude());
						// Increase counter of driven by points. If the distance
						// is bigger for more than X times. Reroute
						countDrivenBy++;
					}
				} else {
					// The points are getting closer. Everything is going ok.
					countDrivenBy = 0;
					if (distanceToNextPoint < MINIMUM_POINT_DISTANCE) {
						// If the distance between the points is less than a meter, then assume the point has been reached and
						// change next point.
						nextPoint = getNextPoint(currentLatitude, currentLongitude);
						// update distance
						oldDistanceToNextPoint = DistanceUtilities.distanceInKM(currentLatitude,currentLongitude,nextPoint.getLatitude(),nextPoint.getLongitude());
						// if no point finish
					} else {
						// Update distance
						oldDistanceToNextPoint = distanceToNextPoint;
					}
				}
			} else {
				double distanceToNextPoint = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude,nextManeuver.getLatitude(),nextManeuver.getLongitude());
				if(!(new Point(currentLatitude, currentLongitude).equals(origin.getPoint())))
					countDrivenBy++;
				else
				{
					nextPoint = getNextPoint(currentLatitude,currentLongitude);
					oldDistanceToNextPoint = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude,	nextPoint.getLatitude(), nextPoint.getLongitude());
					countDrivenBy = 0;	
				}
				
				if(nextPoint != null) 
				{
					if (countDrivenBy > MAXIMUM_DRIVEN_BY)
					// The distance is greater than the constant. Reroute
					throw new RerouteException("Reroute: distance to next point: " + distanceToNextPoint + " count " + countDrivenBy);
				}
				else
				{
					//Null nextpoint shows it has not started
					if(countDrivenBy > MAXIMUM_DRIVEN_BY && !(new Point(currentLatitude, currentLongitude).equals(origin.getPoint())))
						// The distance is greater than the constant. Reroute
						throw new RerouteException("Reroute: distance to next point: " + distanceToNextPoint + " count " + countDrivenBy);
				}
			}
			checkOtherDestinations(currentLatitude, currentLongitude);
			double distanceToNextManeuver = DistanceUtilities.distanceInKM(currentLatitude, currentLongitude,nextManeuver.getLatitude(), nextManeuver.getLongitude());
			message.setTurnByTurnMessage(getManeuverMessage());
			message.setTTSMessage(getTTSMessage(distanceToNextManeuver));
			message.setStreet(nextManeuver.getStreetName());
			message.setManeuverType(nextManeuver.getType());
			message.setDistanceToDestination(travelDistance);
			message.setDistanceToNextManeuver(distanceToNextManeuver);
			//System.out.println(DistanceUtilities.distanceInKM(currentLatitude, currentLongitude,nextManeuver.getLatitude(), nextManeuver.getLongitude()) + " " + currentLatitude + "," + currentLongitude +  (nextPoint != null ? " " + nextPoint.getLatitude() + ","+ nextPoint.getLongitude() : " " ) + " " +nextManeuver.getLatitude() + ","+ nextManeuver.getLongitude());
			//System.out.println(currentLatitude + "," + currentLongitude);
		} catch (NoMorePointsException e) {
			// There are no more points, we assume the route is done
			message.setTurnByTurnMessage(null);
			message.setTTSMessage(null);
			message.setStreet(null);
			message.setManeuverType(null);
			message.setDistanceToDestination(0);
			message.setDistanceToNextManeuver(0);
		}
	}

	private String getManeuverMessage() {
		String message = ""; 
		//the message should only be when the maneuver is the next point and the first point after the maneuver
		if(nextPoint != null && nextManeuver != null && nextPoint.equals(nextManeuver.getPoint()))
		{
			String text = nextManeuver.getManeuverType().getText();
			String street = nextManeuver.getStreetName().trim();
			String prep = nextManeuver.getManeuverType().getPreposition();
			if(street != null && street.length()>0)
			{
				message = text + " " + prep + " " + street;
			}
			else
			{
				message = text;
			}
		}
		return message;
	}

	private String getTTSMessage(double distance)
	{
		String message = ""; 
		//the message should only be when the maneuver is the next point and the first point after the maneuver
		if(nextPoint != null && nextManeuver != null && nextPoint.equals(nextManeuver.getPoint()) && distance < TTS_MAX_DISTANCE)
		{
			String text = nextManeuver.getManeuverType().getText();
			String street = nextManeuver.getStreetName().trim();
			String prep = nextManeuver.getManeuverType().getPreposition();
			if(street != null && street.length()>0)
			{
				message = text + " " + prep + " " + street;
			}
			else
			{
				message = text;
			}
		}
		else if(currentManuverIndex != oldManuverIndex)
		{
			oldManuverIndex = currentManuverIndex;
			String text = nextManeuver.getManeuverType().getText();
			String street = nextManeuver.getStreetName().trim();
			String prep = nextManeuver.getManeuverType().getPreposition();
			if(street != null && street.length()>0)
			{
				message = text + " " + prep + " " + street;
			}
			else
			{
				message = text;
			}
		}
		return message;
	}

	private void checkOtherDestinations(double currentLatitude, double currentLongitude) 
	{
		for (Destination tempDestination : otherDestinations) 
		{
			if(tempDestination.getPoint().isNear(new Point(currentLatitude, currentLongitude)))
				otherDestinations.remove(tempDestination);
		}
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public void setOrigin(Destination origin) {
		this.origin = origin;
	}

	public void setHasHighway() {
		this.hasHighway = true;
	}

	public boolean getHasHighway() {
		return hasHighway;
	}


	public Point[] getNextlegStartFinishPoints() {
		Point[] points = null;
		boolean found = false;
		for (int i=0;i<maneuverPoints.size() && !found;i++) 
		{
			Maneuver maneuver = maneuverPoints.get(i);
			if(!maneuver.hasLeg() && i <= maneuverPoints.size() - 2)
			{
				points = new Point[2];
				double nextLat = maneuver.getLatitude();
				double nextLon = maneuver.getLongitude();
				points[0] = new ManeuverPoint(nextLat, nextLon, maneuver);
				Maneuver nextManeuver = maneuverPoints.get(i+1);
				double nextNextLat = nextManeuver.getLatitude();
				double nextNextLon = nextManeuver.getLongitude();
				points[1] = new ManeuverPoint(nextNextLat, nextNextLon,	nextManeuver);
				found = true;
			}
		}
		return points;
	}

	public void setType(String routingType) {
		this.routingType = routingType;
	}

	public String getType() {
		return routingType;
	}

	public void addLeg(Point[] legPoints) {
		this.legPoints.addAll(new ArrayList<Point>(Arrays.asList(legPoints)));
	}

	public Maneuver getNextManeuver() {
		return nextManeuver;
	}

	public Point getPoint() {
		return nextPoint;
	}

	public void addToLeg(Point point) {
		legPoints.add(point);
	}

	public ArrayList<Point> getLegPoints() {
		return legPoints;
	}

	public ArrayList<Integer> getManeuverPostition() {
		
		ArrayList<Integer> pos = new ArrayList<Integer>();
		for(int i=0;i<legPoints.size();i++)
		{
			Point point = legPoints.get(i);
			if(point instanceof ManeuverPoint)
				pos.add(i);
		}
		return pos;
	}

	public void addManeuverPoint(double lat, double lon, Maneuver maneuver) 
	{
		// The total travel distance comes from adding all the smaller distances
		if(maneuverPoints.size() > 0 )
		{
			Maneuver man = maneuverPoints.get(maneuverPoints.size()-1);	
			this.travelDistance += DistanceUtilities.distanceInKM(man.getLatitude(), man.getLongitude(), maneuver.getLatitude(), maneuver.getLongitude());	
		}
		
		// the duration of the drive is added from all the others
		this.travelDuration += maneuver.getTravelDuration();
		
		
		//The first maneuver
		if(nextManeuver == null)
			nextManeuver = maneuver;
		maneuverPoints.add(maneuver);
	}
	
	public ArrayList<Maneuver> getManeuvers()
	{
		return maneuverPoints;
	}
	
	public String getJSon()
	{
		Gson gson = new Gson();
		String json = gson.toJson(this);
		return json;
	}

	public void addNewDestination(Destination newDestination) {
		otherDestinations.add(newDestination);
	}

	public ArrayList<Destination> getOtherDestinations() {
		return otherDestinations;
	}
	
	public Destination getDestination()
	{
		return destination;
	}
	
	
}
