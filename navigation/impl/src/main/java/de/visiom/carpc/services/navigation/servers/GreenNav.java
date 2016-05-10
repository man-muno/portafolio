package de.visiom.carpc.services.navigation.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.visiom.carpc.services.navigation.exceptions.InvalidResponseException;
import de.visiom.carpc.services.navigation.exceptions.NoVertexInRangeException;
import de.visiom.carpc.services.navigation.model.IVehicle;
import de.visiom.carpc.services.navigation.model.Range;
import de.visiom.carpc.services.navigation.model.Route;
import de.visiom.carpc.services.navigation.model.Vehicle;
import de.visiom.carpc.services.navigation.util.HttpRequest;
import de.visiom.carpc.services.navigation.util.greennav.GreenNavRangeDeserializer;
import de.visiom.carpc.services.navigation.util.greennav.GreenNavRouteDeserializer;

public class GreenNav {
	
	// private static final String URL = "http://www.isp.uni-luebeck.de/";
	
	/**
	 * Logger
	 */
    private final static Logger LOG = LoggerFactory.getLogger(GreenNav.class);

	private static final String URL = "https://greennav.isp.uni-luebeck.de/";

	private static final String GREENNAV_URL = "greennav/";

	private static final String VEHICLES_URL_GREENNAV = "vehicles/";

	private static final String VERTICES_URL_GREENNAV = "vertices/";

	private static final String NEAREST_URL_GREENNAV = "nearest";

	private static final String LAT_URL_GREENNAV = "lat";

	private static final String LONG_URL_GREENNAV = "lon";

	private static final String ROUTES_URL_GREENNAV = "routes/";

	private static final String RANGES_URL_GREENNAV = "ranges/";

	private static final String BATTERY_URL_GREENNAV = "battery";

	private static final String OPT_URL_GREENNAV = "/opt/";

	private static final String ENERGYE_URL_GREENNAV = "energy";

	private static final String TURNS_GREENNAV_PARAMETER_NAME = "turns";

	
	/**
	 * Obtains the nearest vertex according to the location given. A vertex is a
	 * node on the network. A vertex represents a location on the map.
	 * 
	 * @param latitude
	 *            Represents the latitude in degrees -90 <= latitude <= 90
	 * @param longitud
	 *            Represents the longitud in degrees -180 <= logitud <= 180
	 * @return Represents the ID of the GreenNav vertex
	 * @exception NoVertexInRangeException
	 *                Signals that no vertex was fount in the range. The message
	 *                from the server is a 404 Http error.
	 */
	private long getNearestVertice(double latitude, double longitud)  {
		String nearestUrl = URL + GREENNAV_URL + VERTICES_URL_GREENNAV
				+ NEAREST_URL_GREENNAV;
		HttpRequest request = HttpRequest.get(nearestUrl, true,
				LAT_URL_GREENNAV, latitude, LONG_URL_GREENNAV, longitud);
		long vertex = 0;
		if (request.code() != 200)
			LOG.error("Invalid response from GreenNav server getNearestVertice " + request);
		else 
			vertex = Long.parseLong(request.body());
		return vertex;
	}
	
	/**
	 * Returns the full description of the vehicle in the GreenNav server.
	 * 
	 * @param name
	 *            Name of the vehicle. Must be one of the names on the server.
	 * @return Vehicle with all the properties
	 * @throws InvalidResponseException
	 *             Exception thrown when the server responds with a code
	 *             different from 200
	 */
	public IVehicle getVehicle(String name) {
		String getVehicleUrl = URL + GREENNAV_URL + VEHICLES_URL_GREENNAV + name;
		IVehicle vehicle = null;
		//String getVehicleUrl = "http://www.isp.uni-luebeck.de/greennavnew/vehicles/" + name;
		HttpRequest request = HttpRequest.get(getVehicleUrl);
		if (request.code() != 200)
			LOG.error("Invalid response from GreenNav server " + request);
		else
		{
			Gson gson = new Gson();
			try
			{
				vehicle = gson.fromJson(request.body(), Vehicle.class);
			}
			catch ( com.google.gson.JsonSyntaxException e)
			{
				LOG.error("Invalid response from GreenNav server ",e);
			}	
		}		
		return vehicle;
	}
	
	/**
	 * Calculates the route given start, end and battery level
	 * 
	 * @param fromId
	 *            Id of the nearest vertex to the start of the route
	 * @param toId
	 *            If Id of the nearest vertex to the end of the route
	 * @param batteryLevel
	 *            Car's battery level 0 <= batteryLevel <= 100
	 * @param car Vehicle type
	 * @return Object with the route and the points. Not null
	 * @throws GreenNavException 
	 * @throws Throwable
	 */
	private Route getRoute(long fromId, long toId, int batteryLevel, IVehicle car) {
		// TODO: Add algorithm parameter to the request?
		// /vehicles/Sam/routes/136303999200-47771267000/opt/energy?battery=100
		String routeUrl = URL + GREENNAV_URL + VEHICLES_URL_GREENNAV + car.getName() + "/" + ROUTES_URL_GREENNAV + fromId + "-"	+ toId + OPT_URL_GREENNAV + ENERGYE_URL_GREENNAV;
		HttpRequest request = HttpRequest.get(routeUrl, true, BATTERY_URL_GREENNAV, batteryLevel, TURNS_GREENNAV_PARAMETER_NAME, true).acceptCharset(HttpRequest.CHARSET_UTF8);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Route.class, new GreenNavRouteDeserializer());
		Gson gson = new Gson();
		gson = gsonBuilder.create();
		Route route = null;
		if (request.code() != 200)
			LOG.error("Invalid response from GreenNav server request: " + request + " response: " + request.body());
		else
			route  = gson.fromJson(request.body(), Route.class); 
		return route;
	}
	
	/**
	 * Calculates the route given start, end and battery level
	 * 
	 * @param latStart
	 *            latitude of the starting point.
	 * @param lonStart
	 *            longitude of the starting point
	 * @param latEnd
	 *            latitude of the end point
	 * @param lonEnd
	 *            longitude of the end point
	 * @param batteryLevel
	 *            Car's battery level 0 <= batteryLevel <= 100
	 * @param checkTraficIncidents
	 *            true if it is needed to check for traffic incidents on the
	 *            route
	 * @return returns the route from starting point to end point
	 * @throws GreenNavException 
	 */
	public Route getRoute(double latStart, double lonStart, double latEnd, double lonEnd, int batteryLevel, IVehicle car) {
		Route route = getRoute(getNearestVertice(latStart, lonStart), getNearestVertice(latEnd, lonEnd), batteryLevel, car);
		return route;
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
	 * @throws GreenNavException 
	 */
	public Range getRange(double lat, double lon, IVehicle car, double batteryLevel) {
		// /vehicles/Sam/ranges/136303999200?battery=2
		
		String rangeUrl = URL + GREENNAV_URL + VEHICLES_URL_GREENNAV
				+ car.getName() + "/" + RANGES_URL_GREENNAV + getNearestVertice(lat, lon) + "/";
		HttpRequest request = HttpRequest.get(rangeUrl, true,
				BATTERY_URL_GREENNAV, batteryLevel);

		Range response = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Range.class, new GreenNavRangeDeserializer());
		Gson gson = new Gson();
		gson = gsonBuilder.create();
		if (request.code() != 200)
			LOG.warn("Error from GreenNav server with response code " + request.code() + " will return null so default can be calculated");
		else
			response = gson.fromJson(request.body(), Range.class); 		
		return response;
	}	
}