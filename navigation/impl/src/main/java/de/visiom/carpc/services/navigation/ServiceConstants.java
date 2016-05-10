package de.visiom.carpc.services.navigation;

import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;

public class ServiceConstants {
    
    /**
     * Name of the gps service
     */
    public static final String GPS_SERVICE_NAME = "GPS";
    
    /**
     * Name of the car service
     */
	public static final String CAR_SERVICE_NAME = "cah";
	
	/**
	 * Name of the navigation service
	 */
	public static final String NAVIGATION_SERVICE_NAME = "navigation";	
    
    public static final String LATITUDE_PARAM_NAME = "latitude";
    
    public static final String LONGITUDE_PARAM_NAME = "longitude";
    
    public static final String LATITUDE_START_NAME = "latitudeStart";
    
    public static final String LONGITUDE_START_NAME = "longitudeStart";

    public static final String LATITUDE_END_NAME = "latitudeEnd";
    
    public static final String LONGITUDE_END_NAME = "longitudeEnd";

    public static final Object BATTERY_STATUS = "ladezustand";
    
    
    /**
     * Message to be shown on the dashboard. Contains the address and the command
     */
	public static final String NAVIGATION_MESSAGE_NAME = "navi_turnByTurn-Message";
	
    /**
     * Street to be shown on the dashboard.
     */
	public static final String NAVIGATION_STREET_NAME = "navi_turnByTurn-Street";
	
    /**
     * Message to be shown on the dashboard. Contains the address and the command
     */
	public static final String NAVIGATION_TTS_MESSAGE_NAME = "navigationTextToSpeech";
	
	/**
	 * Total Distance to destination
	 */
	public static final String NAVIGATION_DISTANCE_DEST_NAME = "navi_turnByTurn-Distance-to-Dest";
	
	/**
	 * Distance to the next maneuver
	 */
	public static final String NAVIGATION_DISTANCE_MANEUVER_NAME = "navi_turnByTurn-Distant-to-Maneuver";
	
	/**
	 * Distance to point set as home
	 */
	public static final String NAVIGATION_DISTANCE_HOME_NAME = "navi_Distance-to-Home";

	/**
	 * Distance to point set as work
	 */
	public static final String NAVIGATION_DISTANCE_WORK_NAME = "navi_Distance-to-Work";
	
	/**
	 * Maximum range with battery
	 */
	public static final String NAVIGATION_RANGE_MAX_NAME = "navi_Range-Max";
	
	/**
	 * Type of the next maneuver
	 */
	public static final String NAVIGATION_MANEUVER_TYPE_NAME = "navi_turnByTurn-ManeuverType";
	
	public static final String DESTINATION_ADDRESS_PARAM_NAME = "addressEnd";
	public static final String NAVIGATION_ROUTE_TYPE = "navigation_route_type";
	public static final String NAVIGATION_TYPE_FASTEST = "fastestRoute";
	public static final String NAVIGATION_TYPE_ENERGY = "energyEfficientRoute";
	public static final String NAVIGATION_TYPE_TRAFFIC = "withTrafficRoute";

	public static final String HOME = "home";
	public static final String HOME_LATITUDE = "homeLatitude";
	public static final String HOME_LONGITUDE = "homeLongitude";
	public static final String HOME_ADDRESS = "homeAddress";
	public static final String HOME_NAME = "homeName";

	public static final String WORK = "work";
	public static final String WORK_LATITUDE = "workLatitude";
	public static final String WORK_LONGITUDE = "workLongitude";
	public static final String WORK_ADDRESS = "workAddress";
	public static final String WORK_NAME = "workName";

	public static final String FAVORITE_1 = "favorite1";
	public static final String FAVORITE_1_LATITUDE = "favorite1Latitude";
	public static final String FAVORITE_1_LONGITUDE = "favorite1Longitude";
	public static final String FAVORITE_1_ADDRESS = "favorite1Address";
	public static final String FAVORITE_1_NAME = "favorite1Name";

	public static final String FAVORITE_2 = "favorite2";
	public static final String FAVORITE_2_LATITUDE = "favorite2Latitude";
	public static final String FAVORITE_2_LONGITUDE = "favorite2Longitude";
	public static final String FAVORITE_2_ADDRESS = "favorite2Address";
	public static final String FAVORITE_2_NAME = "favorite2Name";


	public static final String FAVORITE_3 = "favorite3";
	public static final String FAVORITE_3_LATITUDE = "favorite3Latitude";
	public static final String FAVORITE_3_LONGITUDE = "favorite3Longitude";
	public static final String FAVORITE_3_ADDRESS = "favorite3Address";
	public static final String FAVORITE_3_NAME = "favorite3Name";


	public static final String FAVORITE_4 = "favorite4";
	public static final String FAVORITE_4_LATITUDE = "favorite4Latitude";
	public static final String FAVORITE_4_LONGITUDE = "favorite4Longitude";
	public static final String FAVORITE_4_ADDRESS = "favorite4Address";
	public static final String FAVORITE_4_NAME = "favorite4Name";


	public static final String FAVORITE_5 = "favorite5";
	public static final String FAVORITE_5_LATITUDE = "favorite5Latitude";
	public static final String FAVORITE_5_LONGITUDE = "favorite5Longitude";
	public static final String FAVORITE_5_ADDRESS = "favorite5Address";
	public static final String FAVORITE_5_NAME = "favorite5Name";


	
	public static final String CANCEL_ROUTING = "cancelRouting";
	public static final String NEW_WAYPOINT = "newWaypoint";
	
	public static final String NEW_FAVORITE = "newfavorite";
	public static final String REMOVE_FAVORITE = "removefavorite";
	public static final String LIST_FAVORITES = "listFavorites";

	public static final String NAVIGATION_FAVORITES = "favorites";

	public static final String NEW_WAYPOINT_LATITUDE = "waypointLatitude";
	public static final String NEW_WAYPOINT_LONGITUDE = "waypointLongitude";
	public static final String NEW_WAYPOINT_TYPE = "waypointType";

	public static final String NAVIGATION_LATITUDE = "latitude";
	public static final String NAVIGATION_LONGITUDE = "longitude";

	public static final String NAVIGATION_JSON_ROUTE = "navi_turnByTurn-jsonRoute";
	public static final String ROUTING_TYPE = "routingType";
	public static final String IS_NAVIGATION_ACTIVE = "isNavigationActive";

	public static final String NAVIGATION_DISTANCE_STATION_NAME = "navi_Distance-to-Station";

	public static final String NAVIGATION_DESTINATION = "destination";
	public static final String NAVIGATION_ROUTING_TYPE = "routingType";
	public static final String NAVIGATION_DESTINATION_ADDRESS = "destinationAddress";
	public static final String NAVIGATION_IS_NAVIGATION_ACTIVE = "isNavigationActive";
	public static final String NAVIGATION_CANCEL_ROUTING = "cancelRouting";
	

	public static final String ROUTING_TYPE_ENERGY_EFFICIENT = "Energy-Efficient";
	public static final String ROUTING_TYPE_TRAFFIC = "Traffic";
	public static final String ROUTING_TYPE_FASTEST  = "Fastest";
	
}
