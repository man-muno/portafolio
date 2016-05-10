package org.openhab.binding.rosie.blackboard;

/**
 * Types of sensors possible
 */
public enum SensorType {

	PRESENCE("Presence"),
	ENVIRONMENTAL("Enviromental"), 
	ELECTRICAL("Electrical"), 
	MOTION("Motion"), 
	POINT_OF_ENTRY("PointOfEntry");
	
	/**
	 * Name of the sensor type
	 */
	private final String name;
	
	/**
	 * Default constructor
	 * @param name Not null
	 */
	private SensorType(String name){
		this.name = name;
	}
	
	/**
	 * Parses a String to the SensorType
	 * @param sensorType Name of the sensor
	 * @return Null if not found
	 */
	public static SensorType parseString(String sensorType) {
		SensorType response  = null;
		if(sensorType.toLowerCase().equals("presence")) 
			response  = PRESENCE;	
		else if(sensorType.toLowerCase().equals("environmental"))
			response  = ENVIRONMENTAL;	
		else if(sensorType.toLowerCase().equals("electrical"))
			response  = ELECTRICAL;	
		else if(sensorType.toLowerCase().equals("motion"))
			response  = MOTION;	
		else if(sensorType.toLowerCase().equals("pointofentry"))
			response  = POINT_OF_ENTRY;
		return response;
	}
}
