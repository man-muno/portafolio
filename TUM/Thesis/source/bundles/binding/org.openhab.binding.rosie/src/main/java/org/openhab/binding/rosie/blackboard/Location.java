package org.openhab.binding.rosie.blackboard;

/**
 * Location of the sensors. It is configured on the items file
 */
public enum Location {

	INDOOR("Indoor"),
	OUTDOOR("Outdoor"),
	UNDEFINED("Undefined");
	
	/**
	 * Name for the location
	 */
	private final String name;
	
	/**
	 * Default constructor
	 * @param name Not null
	 */
	private Location(String name){
		this.name = name;
	}
	
	/**
	 * Given the String, it returns the location.
	 * @param String with the location. If it not found UNDEFINED is returned
	 * @return location Values of the enum
	 */
	public static Location parseLocation(String location) {
		Location response  = null;
		if(location.toLowerCase().equals(INDOOR.name.toLowerCase())) 
			response  = INDOOR;	
		else if(location.toLowerCase().equals(OUTDOOR.name.toLowerCase()))
			response  = OUTDOOR;
		else
			response = UNDEFINED;
		return response;
	}
}
