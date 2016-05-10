package de.visiom.carpc.services.navigation.servers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.visiom.carpc.services.navigation.exceptions.BingException;
import de.visiom.carpc.services.navigation.model.Maneuver;
import de.visiom.carpc.services.navigation.model.Point;
import de.visiom.carpc.services.navigation.model.Route;
import de.visiom.carpc.services.navigation.util.DistanceUtilities;
import de.visiom.carpc.services.navigation.util.HttpRequest;
import de.visiom.carpc.services.navigation.util.bing.BingLegDeserializer;
import de.visiom.carpc.services.navigation.util.bing.BingRouteDeserializer;

public class Bing {

	private static final String BING_API_KEY = "AhQRc93KBBS8s4UX3uk-qy8z9Y4Zyz-8ebg0ODVKaPZZ0Yq5m6t_6NLdXU4cIlSf";

	private static final String BING_KEY_PARAMETER_NAME = "key";
	
	private static final String BING_URL = "http://dev.virtualearth.net/REST/V1/";
	
	private static final String ROUTE_URL = "Routes?";
	
	private static final String WAYPOINT_NAME = "wp.";
	
	private static final String VIA_WAYPOINT_NAME = "vwp.";
	
	private static final String OPTIMIZATION_CONSTANTS = "timeWithTraffic,timeAvoidClosure";
	
	private static final String OPTIMIZATION_NAME  = "optmz";
	
	private static final String LOCALIZATION = "culture";
	
	private static final String LOCALIZATION_GERMANY = "de-DE";
	
	private static final String ROUTE_PATH_OUTPUT = "routePathOutput";
	
	private static final String ROUTE_PATH_OUTPUT_POINTS =  "Points";
	

	public Route getRoute(Route route) throws BingException {
		String routeUrl = getBingRoutingMultipleURL(route);
		HttpRequest request = HttpRequest.get(routeUrl, true, LOCALIZATION,LOCALIZATION_GERMANY, BING_KEY_PARAMETER_NAME, BING_API_KEY);
		request.contentType(HttpRequest.CHARSET_UTF8);
		if (request.code() != 200)
			throw new BingException("Invalid response from Bing server " + request);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Route.class, new BingRouteDeserializer());
		Gson gson = new Gson();
		gson = gsonBuilder.create();

		return gson.fromJson(request.body(), Route.class);
	}

	public Route getFastestRoute(double latStart, double lonStart,double latEnd, double lonEnd) throws BingException {
		String routeUrl = BING_URL + ROUTE_URL;
		routeUrl += WAYPOINT_NAME + 0 + "=" +latStart+","+lonStart+ "&" +WAYPOINT_NAME + 1 + "=" +latEnd+","+lonEnd;
		HttpRequest request = HttpRequest.get(routeUrl, true, LOCALIZATION,LOCALIZATION_GERMANY,OPTIMIZATION_NAME, OPTIMIZATION_CONSTANTS, BING_KEY_PARAMETER_NAME, BING_API_KEY);
		request.contentType(HttpRequest.CHARSET_UTF8);
		if (request.code() != 200)
			throw new BingException("Invalid response from Bing server " + request);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Route.class, new BingRouteDeserializer());
		Gson gson = new Gson();
		gson = gsonBuilder.create();

		return gson.fromJson(request.body(), Route.class);
	}
	
	public Point[] getLegPoints(Point a, Point b) throws BingException
	{
		String routeUrl = BING_URL + ROUTE_URL;
		routeUrl += WAYPOINT_NAME + 0 + "=" +a.getLatitude()+","+a.getLongitude()+ "&" +WAYPOINT_NAME + 1 + "=" +b.getLatitude()+","+b.getLongitude();
		HttpRequest request = HttpRequest.get(routeUrl, true, LOCALIZATION,LOCALIZATION_GERMANY,OPTIMIZATION_NAME,"distance",ROUTE_PATH_OUTPUT,ROUTE_PATH_OUTPUT_POINTS, BING_KEY_PARAMETER_NAME, BING_API_KEY);
		if (request.code() != 200)
			throw new BingException("Invalid response from Bing server " + request);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Point[].class, new BingLegDeserializer(a,b));
		Gson gson = new Gson();
		gson = gsonBuilder.create();
		return gson.fromJson(request.body(), Point[].class);
	}


	private String getBingRoutingMultipleURL(Route  route) {
		String  routeUrl = BING_URL + ROUTE_URL;

		//total amount of maneuvers on the route. Only 50 in total can be queried to bing
		ArrayList<Maneuver> maneuvers = route.getManeuvers(); 
		double ratio =  maneuvers.size() / 50.0;
		double stepSize = Math.ceil(ratio);
		Maneuver maneuver = null;
		int counter = 0;
		for (int i = 0; i < maneuvers.size(); i += stepSize)
		{
			String wayPointType  = "";
			maneuver = maneuvers.get(i);
			//Only 10 viawaypoints can be between the waypoints
			
			if(i % 10 == 0 || (i+stepSize >= maneuvers.size()))
				wayPointType = WAYPOINT_NAME;
			else
				wayPointType = VIA_WAYPOINT_NAME;
			
			routeUrl += wayPointType + counter + "=" +maneuver.getLatitude()+","+maneuver.getLongitude() + (counter < maneuvers.size()-1 ? "&" : "");
			counter++;
		}
		return routeUrl;
	}

	public Route addWaypointToRoute(Route currentRoute, double wayPointLatitude,double wayPointLongitude) throws BingException 
	{
		String routeUrl = getBingRoutingMultipleURL(currentRoute, wayPointLatitude,wayPointLongitude);
		HttpRequest request = HttpRequest.get(routeUrl, true, LOCALIZATION,LOCALIZATION_GERMANY, BING_KEY_PARAMETER_NAME, BING_API_KEY);
		request.contentType(HttpRequest.CHARSET_UTF8);
		if (request.code() != 200)
			throw new BingException("Invalid response from Bing server " + request);
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Route.class, new BingRouteDeserializer());
		Gson gson = new Gson();
		gson = gsonBuilder.create();

		return gson.fromJson(request.body(), Route.class);
	}

	private String getBingRoutingMultipleURL(Route route, double wayPointLatitude, double wayPointLongitude) {
		String  routeUrl = BING_URL + ROUTE_URL;

		//total amount of maneuvers on the route. Only 50 in total can be queried to bing
		ArrayList<Maneuver> maneuvers = route.getManeuvers();
		maneuvers = addNewWaypoint(maneuvers, wayPointLatitude, wayPointLongitude);
		double ratio =  maneuvers.size() / 50.0;
		double stepSize = Math.ceil(ratio);
		Maneuver maneuver = null;
		int counter = 0 ;
		for (int i = 0; i < maneuvers.size(); i += stepSize)
		{
			String wayPointType  = "";
			maneuver = maneuvers.get(i);
			
			//Only 10 viawaypoints can be between the waypoints
		if(i % 10 == 0 || (i+stepSize >= maneuvers.size()))
				wayPointType = WAYPOINT_NAME;
			else
				wayPointType = VIA_WAYPOINT_NAME;
			
			routeUrl += wayPointType + counter + "=" +maneuver.getLatitude()+","+maneuver.getLongitude() + (counter < maneuvers.size()-1 ? "&" : "");
			counter++;
		}
		return routeUrl;
	}

	private ArrayList<Maneuver> addNewWaypoint(ArrayList<Maneuver> maneuvers, double wayPointLatitude, double wayPointLongitude) {
		int index = 0;
		double distance = 10000;
		for(int i=0;i<maneuvers.size();i++)
		{
			Maneuver point = maneuvers.get(i);
			if(DistanceUtilities.distanceInKM(point.getLatitude(), point.getLongitude(), wayPointLatitude, wayPointLongitude) < distance)
			{
				index = i;
				distance = DistanceUtilities.distanceInKM(point.getLatitude(), point.getLongitude(), wayPointLatitude, wayPointLongitude);
			}
		}
		
		maneuvers.add(index, new Maneuver(wayPointLatitude, wayPointLongitude,"","", null,0,"",0));
		return maneuvers;
	}
}
