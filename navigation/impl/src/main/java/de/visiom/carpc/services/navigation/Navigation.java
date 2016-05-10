package de.visiom.carpc.services.navigation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;
import de.visiom.carpc.services.navigation.exceptions.BingException;
import de.visiom.carpc.services.navigation.exceptions.GreenNavException;
import de.visiom.carpc.services.navigation.exceptions.InvalidResponseException;
import de.visiom.carpc.services.navigation.exceptions.NavigationException;
import de.visiom.carpc.services.navigation.exceptions.RerouteException;
import de.visiom.carpc.services.navigation.model.IVehicle;
import de.visiom.carpc.services.navigation.model.Maneuver;
import de.visiom.carpc.services.navigation.model.ManeuverPoint;
import de.visiom.carpc.services.navigation.model.Point;
import de.visiom.carpc.services.navigation.model.Range;
import de.visiom.carpc.services.navigation.model.Route;
import de.visiom.carpc.services.navigation.model.Vehicle;
import de.visiom.carpc.services.navigation.publishers.DisplayMessage;
import de.visiom.carpc.services.navigation.servers.Bing;
import de.visiom.carpc.services.navigation.servers.GreenNav;
import de.visiom.carpc.services.navigation.util.DistanceUtilities;
import de.visiom.carpc.services.navigation.util.ManeuverType;
import de.visiom.carpc.services.navigation.util.bing.BingRouteDeserializer;

public enum Navigation {
	
	INSTANCE;

	private static final int BATTERY_DIFFERENCE = 10;

	/**
	 * Type of vehicle
	 */
	private IVehicle me = null;
	
	/**
	 * Final destination
	 */
	private Destination destination;
	
	/**
	 * The selected routing type for the route
	 */
	private String routingType = ROUTING_TYPE_ENERGY_EFFICIENT;

	/**
	 * Logger
	 */
    private final Logger LOG = LoggerFactory.getLogger(Navigation.class);
	
    /**
     * GreenNav server
     */
	private final GreenNav greenNav = new GreenNav();
	
	/**
	 * Bing server
	 */
	private final Bing bing = new Bing();

	/**
	 * Type of routing. Serves as a parameter for some methods
	 */
	public static final String ROUTING_TYPE_ENERGY_EFFICIENT = "Energy-Efficient";
	
	/**
	 * Type of routing. Serves as a parameter for some methods
	 */
	public static final String ROUTING_TYPE_TRAFFIC = "Traffic";
	
	/**
	 * Type of routing. Serves as a parameter for some methods
	 */
	public static final String ROUTING_TYPE_FASTEST = "Fastest";
	
	/**
	 * Current latitude of the car. The default value is while the car becomes the real coordinates
	 */
	private double currentLatitude = 0.0;

	/**
	 * Current longitude of the car. The default value is while the car becomes the real coordinates
	 */
	private double currentLongitude = 0.0;
	
	/**
	 * The current battery level
	 */
	private int currentBatteryLevel=100;
	
	/**
	 * The battery level when the last Range was asked from the server. Default is 100.
	 */
	private int oldBatteryLevel=100;

	/**
	 * The current range that has been calculated
	 */
	private Range currentRange;
	
	/**
	 * The current route that has been calculated
	 */
	private Route currentRoute;

	/**
	 * True when there was a change on the route and the json string needs to be updated
	 */
	private boolean newJSon;

	/**
	 * The distance from the current point to the point set as home
	 */
	private double distanceToHome;
	
	/**
	 * The distance from the current point to the ponte set as work
	 */
	private double distanceToWork;
	
	private double distanceToStation;
	
	private Destination station = new Destination(new Point(48.177488,11.585103), "Parzivalplatz 4, 80803 München", "Lädestation", "Lädestation");
	
	/**
	 * True if the navigation services is available
	 */
	private boolean active = false;
	
	/**
	 * Max range calculated with a 100% batery level, and the Garching campus as a starting point
	 */
	private static final double DEFAULT_VEHICLE_MAX_RANGE = 98.26241393767744;
	
	/**
	 * List of favorit destinations
	 */
	private HashMap<String, Destination> favorites = new HashMap<String, Destination>();
	
	private static final String DESTINATION = "destination";
	
	public static final String FAVORITE_HOME = "home";
	public static final String FAVORITE_WORK= "work";
	public static final String FAVORITE_1 = "favorite 1";
	public static final String FAVORITE_2 = "favorite 2";
	public static final String FAVORITE_3 = "favorite 3";
	public static final String FAVORITE_4 = "favorite 4";
	public static final String FAVORITE_5 = "favorite 5";
	
	private int distCount = 0;
	

	/**
	 * Name of the default vehicle
	 */
	private static final String DEFAULT_VEHICLE_NAME = "Sam";
	
	/**
	 * Constructor for the navigation class. Sets the default vehicle
	 */
	private Navigation() 
	{
		if(me == null)
		{
			try {
				me = getVehicleType(DEFAULT_VEHICLE_NAME);
			} catch (NavigationException e) {
				LOG.error("No default vehicle found. Using hardcoded one. " + e.getMessage());
			} finally {
				me = setDefaultVehicle();
			}	
		}
		oldBatteryLevel=100;
	}

	/**
	 * When there is no connection when the navigation is first created, it
	 * reads a file with the default auto. When the file cannot be read, it
	 * returns an auto with the name "Sam"
	 * 
	 * @return Default auto.
	 */
	private IVehicle setDefaultVehicle() {
		Vehicle defaultVehicle = new Vehicle();
		defaultVehicle.setName(DEFAULT_VEHICLE_NAME);
		return defaultVehicle;
	}


	/**
	 * Returns the full description of the vehicle in the GreenNav server.
	 * 
	 * @param name
	 *            Name of the vehicle. Must be one of the names on the server.
	 * @return Vehicle with all the properties
	 * @throws NavigationException 
	 * @throws InvalidResponseException
	 *             Exception thrown when the server responds with a code
	 *             different from 200
	 */
	public IVehicle getVehicleType(String name) throws NavigationException  {
		return greenNav.getVehicle(name);
	}
	
	
	/**
	 * Returns the route asking the GreenNav server. This route is the most energy efficient route 
	 * @throws NavigationException
	 */
	private Route getGreenRoute()
	{
		//System.out.println("getGreenRoute");
		Route route = null;
        double latEnd = destination.getPoint().getLatitude(); 
        double lonEnd = destination.getPoint().getLongitude();
		route = greenNav.getRoute(currentLatitude, currentLongitude, latEnd, lonEnd, currentBatteryLevel, me);
		destination = new Destination(new Point(latEnd,lonEnd), destination.getAddress(), "", "");
		route.setDestination(destination);
		route.setOrigin(new Destination(new Point(currentLatitude,currentLongitude), "", "", ""));
		route.setType(ROUTING_TYPE_ENERGY_EFFICIENT);
		currentRoute = route;
		active = true;
		return route;
	}

	private Route getFastestRoute() throws NavigationException
	{
		//System.out.println("getFastesRoute");
		Route route = null;
		try {
	        double latEnd = destination.getPoint().getLatitude(); 
	        double lonEnd = destination.getPoint().getLongitude();
			route = bing.getFastestRoute(currentLatitude,currentLongitude,latEnd,lonEnd);
			destination = new Destination(new Point(latEnd,lonEnd), destination.getAddress(), "", "");
			route.setDestination(destination);
			route.setOrigin(new Destination(new Point(currentLatitude,currentLongitude),"", "", ""));
			route.setType(ROUTING_TYPE_FASTEST);
			currentRoute = route;
			active = true;
		} catch (BingException e) {
			LOG.error(e.getMessage());
			throw new NavigationException(e.getMessage(),e);
		}
		
		return route;
	}
	
	
	private Route getTrafficRoute() throws NavigationException 
	{
		//System.out.println("getTrafficRoute");
		Route route = null;
        double latEnd = destination.getPoint().getLatitude(); 
        double lonEnd = destination.getPoint().getLongitude();
		Route greenRoute = greenNav.getRoute(currentLatitude, currentLongitude, latEnd, lonEnd, currentBatteryLevel, me);
		route = addTraffic(greenRoute);
		destination = new Destination(new Point(latEnd,lonEnd), destination.getAddress(), "","");
		route.setDestination(destination);
		route.setOrigin(new Destination(new Point(currentLatitude,currentLongitude), "", "",""));
		route.setType(ROUTING_TYPE_TRAFFIC);
		currentRoute = route;
		active = true;
		return route;
	}
	
	/**
	 *  Returns the best greenest route given current traffic conditions.
	 * @param greenRoute
	 * @return
	 */
	private Route addTraffic(Route greenRoute) throws NavigationException   
	{
		//System.out.println("addtraffic");
		Route trafficRoute = null;
		try {
			trafficRoute = bing.getRoute(greenRoute);
		} catch (BingException e) {
			throw new NavigationException(e.getMessage(),e);
		}
		
		return trafficRoute;
	}
	
	/**
	 * Calculates all reachable points given a start and a battery level
	 * 
	 * @param fromId
	 *            Id of the nearest vertex to the start of the route
	 * @param batteryLevel
	 *            Car's battery level 0 <= batteryLevel <= 100
	 * @return Object with the possible reachable range
	 * @throws InvalidResponseException
	 */
	private Range getRange() throws NavigationException{
		//System.out.println("getRange");
		if(oldBatteryLevel - currentBatteryLevel >= BATTERY_DIFFERENCE || currentRange == null)
		{
			currentRange = greenNav.getRange(currentLatitude, currentLongitude, me, currentBatteryLevel);
			//Assign the battery level when the range was calculated last time
			oldBatteryLevel = currentBatteryLevel;
		}
		return currentRange;
	}
	
	/**
	 * Calculates the distance for the farthest point for the reachable range
	 * 
	 * @param fromId
	 *            Id of the nearest vertex to the start of the route
	 * @param batteryLevel
	 *            Car's battery level 0 <= batteryLevel <= 100
	 * @return Object with the possible reachable range
	 * @throws InvalidResponseException
	 */
	private double getDistanceToFarthestPoint() throws NavigationException
	{
		//System.out.println("getDistanceToFarthersPoint");
		Range range = getRange();
		double distanceToFarthesPoint = 0;
		//if no range could be calculated nor there ever was one calculated
		//If there was a range already calculated, then it will use that one to calculate the distance
		if (range == null && currentRange == null)
		{
			distanceToFarthesPoint = getDefaultRangeValue();
		}
		else 
		{
			distanceToFarthesPoint = DistanceUtilities.distanceInKM(currentLatitude,currentLongitude,range.getFarthestPoint().getLatitude(),range.getFarthestPoint().getLongitude());
		}
		return distanceToFarthesPoint;
	}


	/**
	 * Returns the default distance with the current battery level.
	 * @return The constant DEFAULT_VEHICLE_MAX_RANGE is used as the 100% battery level. The current value is calculated according the current battery level.
	 */
	private double getDefaultRangeValue() {
		return DEFAULT_VEHICLE_MAX_RANGE * (currentBatteryLevel / 100.0);
	}
	
	/**
	 * Changes the current latitude value
	 * @param currentLatitude Valid latitude value
	 */
	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}
	
	/**
	 * Changes the current longitude value
	 * @param currentLatitude Valid longitude value
	 */
	public void setCurrentLongitude(double currentLongitude) {
		this.currentLongitude = currentLongitude;
	}

	/**
	 * Returns the message to be given to the bus. The message changes dependending on the state of the route, current destinations, etc. 
	 * @return Valid display message
	 * @throws RerouteException If the route should be rerouted
	 * @throws NavigationException Problems during the calculation of the ranges.
	 */
	public DisplayMessage getNavigationMessage() throws RerouteException, NavigationException 
	{
		//The default values that the message must have for the dash
		DisplayMessage message = new DisplayMessage();
		if(currentLatitude != 0.0 && currentLongitude != 0.0)
		{
			double distanceToFarthesPoint = getDistanceToFarthestPoint();
			//System.out.println("distanceToFarthesPoint " + distanceToFarthesPoint);
			message.setMaxRange(distanceToFarthesPoint);
			double distanceToHome = getDistanceToHome();
			//System.out.println("distanceToFarthesPoint " + distanceToHome);
			message.setHomeRange(distanceToHome);
			double distanceToWork = getDistanceToWork();
			//System.out.println("distanceToWork " + distanceToWork);
			message.setWorkRange(distanceToWork);
			double distanceToStation = getDistanceToStation();
			//System.out.println("distanceToStation " + distanceToStation);
			message.setStationRange(distanceToStation);

			
			//System.out.println("getNavigationMessage active " + active );
			
			if(active && currentRoute != null)
			{
				//If there is a route, then ask for the route message
				currentRoute.getTurnByTurnMessage(currentLatitude,currentLongitude, message);
				message.setOtherPoints(currentRoute.getOtherDestinations());
				if(newJSon)
				{
					message.setRoute(currentRoute.getJSon());
					//System.out.println("jsonroute " + currentRoute.getJSon());
					newJSon = false;
				}
				
				message.setDestination(destination.getName());
				message.setDestinationAddress(destination.getAddress());
				message.setRoutingType(routingType);
			}
				
			/*if(message.getTurnByTurnMessage() == null)
				cancelTurnByTurn();*/
			message.setIsNavigationActive(active && currentRoute != null);
		}
		
	
		return message;
	}

	
	/**
	 * Clears the current route
	 */
	public void cancelTurnByTurn()
	{
		active = false;
		currentRoute = null;
	}
	
	/**
	 * Returns the distance to the work destination, if one exists. It calculates a new distance if the battery level has decreased 1 point since last access.
	 * @return Positive integer
	 * @throws NavigationException If there is a problem accessing the server to calculate
	 */
	private double getDistanceToWork() throws NavigationException {
		//System.out.println("getDistanceToWork");
		Destination work = favorites.get(FAVORITE_WORK);
		if((Math.abs(oldBatteryLevel - currentBatteryLevel) >= BATTERY_DIFFERENCE || distCount < 3) && work != null)
		{
			distCount++;
			oldBatteryLevel = currentBatteryLevel;
			Route route = null;
			route = greenNav.getRoute(currentLatitude, currentLongitude, work.getPoint().getLatitude(), work.getPoint().getLongitude(), 100, me);
			distanceToWork = route.getTravelDistance();
		}
		return distanceToWork;
	}
	
	/**
	 * Returns the distance to the work destination, if one exists. It calculates a new distance if the battery level has decreased 1 point since last access.
	 * @return Positive integer
	 * @throws NavigationException If there is a problem accessing the server to calculate
	 */
	private double getDistanceToStation() throws NavigationException {
		//System.out.println("getDistanceToStation");
		if((Math.abs(oldBatteryLevel - currentBatteryLevel) >= BATTERY_DIFFERENCE || distCount < 3) && station != null)
		{
			distCount++;
			oldBatteryLevel = currentBatteryLevel;
			Route route = null;
			route = greenNav.getRoute(currentLatitude, currentLongitude, station.getPoint().getLatitude(), station.getPoint().getLongitude(), 100, me);
			distanceToStation= route.getTravelDistance();
		}
		return distanceToStation;
	}

	/**
	 * Returns the distance to the home destination, if one exists. It calculates a new distance if the battery level has decreased 1 point since last access.
	 * @return Positive integer
	 * @throws NavigationException If there is a problem accessing the server to calculate
	 */
	private double getDistanceToHome() throws NavigationException {
		//System.out.println("getDistanceToHome");
		Destination home = favorites.get(FAVORITE_HOME);
		if((Math.abs(oldBatteryLevel - currentBatteryLevel) >= BATTERY_DIFFERENCE || distCount < 3) && home != null)
		{
			distCount++;
			oldBatteryLevel = currentBatteryLevel;
			Route route = null;
			route = greenNav.getRoute(currentLatitude, currentLongitude, home.getPoint().getLatitude(), home.getPoint().getLongitude(), 100, me);
			distanceToHome = route.getTravelDistance();
			
		}
		return distanceToHome;
	}

	/**
	 * Updates the battery status of the car 
	 * @param batteryStatus Positive integer
	 */
	public void setCurrentBatteryStatus(int batteryStatus) 
	{
		this.currentBatteryLevel = batteryStatus;
	}

	/**
	 * Changes the destination for home 
	 * @param newHome Valid destination
	 */
	public void addFavorite(String name, Destination newDestination) 
	{
		//System.out.println("addFavorite");
		favorites.put(name, newDestination);
	}

	public Destination getFavoriteByAddress(String address) {
		//System.out.println("getDestinationByAddres");
		Iterator<Destination> iterator = favorites.values().iterator();
		Destination response = null;
		while(iterator.hasNext())
		{
			Destination temp = iterator.next();
			if(temp.getAddress().equals(address))
				response = temp;
		}
		return response;
		
	}

	/**
	 * Calculates the route given the parameters. If the destination given is the same as the current destination, then it will not calculate a new route
	 * @param destLatitude Latitude point for the destination
	 * @param destLongitude Longitude point for the destination
	 * @param destAddress Destination address. It does not use it for calculating purpose
	 * @param routeType Type of route as defined on the constants that start with ROUTING_TYPE
	 * @throws NavigationException Thrown if there is a problem with the services 
	 */
	public void calculateRoute() throws NavigationException {
        //System.out.println("Calculating route...");
		if(routingType.equals(ROUTING_TYPE_ENERGY_EFFICIENT))
			getGreenRoute();
		else if(routingType.equals(ROUTING_TYPE_FASTEST))
			getFastestRoute();
		else if(routingType.equals(ROUTING_TYPE_TRAFFIC))
			getTrafficRoute();
		
		completeLegPoints();
		newJSon = true;
	}

	public Route addWaypointToRoute(double wayPointLatitude, double wayPointLongitude, String wayPointType) throws NavigationException 
	{
		//System.out.println("addWaypointToRoute");
		Route trafficRoute = null;
		if(active && currentRoute!= null)
		{
			try {
				trafficRoute = bing.addWaypointToRoute(currentRoute, wayPointLatitude, wayPointLongitude);
				trafficRoute.addNewDestination(new Destination(new Point(wayPointLatitude,wayPointLongitude),wayPointType,wayPointType,""));
				newJSon = true;
			} catch (BingException e) {
				throw new NavigationException(e.getMessage(),e);
			}
			
		}		
		return trafficRoute;
	}

	public boolean completeLegPoints() throws NavigationException 
	{
		boolean added = false;
		try {
			if(active && currentRoute != null && (currentRoute.getType().equals(ROUTING_TYPE_FASTEST) || currentRoute.getType().equals(ROUTING_TYPE_TRAFFIC)))
			{
				//System.out.println("completeLegPoints");
				Point[] startPoints = currentRoute.getNextlegStartFinishPoints();
				if(startPoints != null)
				{
					Point[] legPoints =  bing.getLegPoints(startPoints[0], startPoints[1]);
					currentRoute.addLeg(legPoints);
					((ManeuverPoint)startPoints[0]).getManeuver().legSet();
					Maneuver maneuver = ((ManeuverPoint)startPoints[1]).getManeuver();
					if(maneuver.getManeuverType() != null && maneuver.getManeuverType().equals(ManeuverType.ARRIVE_FINISH))
						currentRoute.addToLeg(startPoints[1]);
					added = true;
				}
			}
		} catch (BingException e) {
			throw new NavigationException(e.getMessage(), e);
		}
		return added;
	}	
	
	/**
	 * Cancels the current route and calculates a new one, starting from the current local point, to the destinaton set before.
	 * @throws NavigationException
	 */
	public void reroute() throws NavigationException {
		//System.out.println("Rerouting");
		cancelTurnByTurn();
		calculateRoute();
	}
	
	public boolean setActiveNavigation() throws NavigationException 
	{
		//System.out.println("setActiveNavigation active: " + active + " currentRoute: " + currentRoute + " destination: " + destination + " routingType: " + routingType);
		if(!active && currentRoute == null && destination != null && routingType != null){
			calculateRoute();
			return true;
		}	
		return false;
	
	}

	public void setRoutingType(String routingType) 
	{
		//System.out.println("routingType");
		this.routingType = routingType;
	}
	 
	public Destination getFavoriteByName(String name)
	{
		 
		//System.out.println(favorites.containsKey(name) + " byName " +  name);
		Iterator<Destination> iterator = favorites.values().iterator();
		Destination response = null;
		while(iterator.hasNext())
		{
			Destination temp = iterator.next();
			if(temp.getAddress().equals(name))
				response = temp;
		}
		return response;
	}
	
	public Destination getFavoriteByKey(String key)
	{
		//System.out.println(favorites.get(key) + " byKey " +  key);
		return favorites.get(key);
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
		//System.out.println("Set destination " + this.destination);
	}

	public boolean isNavigationActive() {
		return active;
	}

	public String getJSonFavorites() {
		Gson gson = new Gson();
		ArrayList<Destination> destinations = new ArrayList<Destination>();
		destinations.add(favorites.get(FAVORITE_HOME));
		destinations.add(favorites.get(FAVORITE_WORK));
		destinations.add(favorites.get(FAVORITE_1));
		destinations.add(favorites.get(FAVORITE_2));
		destinations.add(favorites.get(FAVORITE_3));
		destinations.add(favorites.get(FAVORITE_4));
		destinations.add(favorites.get(FAVORITE_5));
		
		String json = gson.toJson(destinations);
		return json;
	}
			
	public void setFavoriteName(String favorite, String name) {
		favorites.get(favorite).setName(name);
	}

	public void setFavoriteLongitud(String favorite, double longitude) {
		favorites.get(favorite).setLongitude(longitude);
	}

	public void setFavoriteLatitude(String favorite, double latitude) {
		favorites.get(favorite).setLatitude(latitude);
		
	}

	public void setFavoriteAddress(String favorite, String address) {
		favorites.get(favorite).setAddress(address);
	}

	public void replaceFavoritesFromJSON(String favsJson) 
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = new Gson();
		gson = gsonBuilder.create();
		Destination[] favs = gson.fromJson(favsJson, Destination[].class);
		favorites.clear();
		for (int i = 0; i < favs.length; i++) {
			Destination fav = favs[i];
			favorites.put(fav.getId(), fav);
			if(fav.isDestination())
				destination = (Destination) fav;			
		}
      	
	}

	public Destination getDestination() {
		return destination;
	}
}
