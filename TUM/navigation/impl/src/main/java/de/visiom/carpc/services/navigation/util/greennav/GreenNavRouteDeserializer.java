package de.visiom.carpc.services.navigation.util.greennav;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;






import de.visiom.carpc.services.navigation.model.Maneuver;
import de.visiom.carpc.services.navigation.model.ManeuverPoint;
import de.visiom.carpc.services.navigation.model.Point;
import de.visiom.carpc.services.navigation.model.Route;
import de.visiom.carpc.services.navigation.util.ManeuverType;

public class GreenNavRouteDeserializer implements JsonDeserializer<Route> {

	@Override
	public Route deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
	    JsonObject jsonObject = json.getAsJsonObject();

	    // Delegate the deserialization to the PointDeserializer class
	    //Point[] points = context.deserialize(jsonObject.get("route"), Point[].class);

	    Route route = new Route();

	    route.setTravelDuration(jsonObject.get("time").getAsDouble());
	    route.setTravelDistance(jsonObject.get("distance").getAsDouble()/1000);
	    
		JsonArray points = jsonObject.get("route").getAsJsonArray();
		int maneuverSize = 0;
		
		for (int i = 0; i < points.size(); i++) 
		{
			JsonObject jsonPoint = points.get(i).getAsJsonObject();
			
			double latitude = jsonPoint.get("latitude").getAsDouble();
			double longitude = jsonPoint.get("longitude").getAsDouble();
			
			//Fist point is the start maneuver
			if(i==0)
			{
				Maneuver man =  new Maneuver(latitude, longitude, "","", ManeuverType.DEPART_START, 0, "", 0);
				route.addManeuverPoint(latitude, longitude, man);
			}
			else if(i==points.size()-1)
			{
				//Last point is the arrive maneuver
				Maneuver man =  new Maneuver(latitude, longitude, "","", ManeuverType.ARRIVE_FINISH, 0, "", 0);
				route.addManeuverPoint(latitude, longitude, man);	
			}
			else
			{
				//Other points are either normal maneuvers or part of the leg
				String street = "";
				int turn = jsonPoint.get("turn").getAsInt();
				if(turn != 0)
				{
					//If there is a street, then it is considered maneuver
					street = jsonPoint.get("street").getAsString();
					char[] chars = street.toCharArray();
					for (int j = 0; j < chars.length; j++) {
						char temp = chars[j];
						if((int) temp == 65533)
							chars[j] = 'ß';
					}
					street = new String(chars);
					ManeuverType maneuverType = null;
					for (ManeuverType m : ManeuverType.values())
					{
						if(m.getGreenNavCode() == turn)
						{
							maneuverType = m;
							break;
						}
					}
					//Greenav server does not provide time or compass direcctions
					Maneuver man =  new Maneuver(latitude, longitude, "", street, maneuverType, 0, "", 0);
					route.addManeuverPoint(latitude, longitude, man);
					route.addToLeg(new ManeuverPoint(latitude, longitude, man));
					maneuverSize++;
				}
				else
				{
					route.addToLeg(new Point(latitude, longitude));
				}
			}
		}
	    return route;
	}

}
