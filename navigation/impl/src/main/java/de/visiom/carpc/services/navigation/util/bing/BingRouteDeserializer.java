package de.visiom.carpc.services.navigation.util.bing;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.visiom.carpc.services.navigation.model.Maneuver;
import de.visiom.carpc.services.navigation.model.ManeuverPoint;
import de.visiom.carpc.services.navigation.model.Route;
import de.visiom.carpc.services.navigation.util.ManeuverType;

public class BingRouteDeserializer implements JsonDeserializer<Route> {

	
	
	@Override
	public Route deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		Route route = new Route();
		
		JsonObject jsonObject = json.getAsJsonObject();	
		
		//Get the resource sets as an array
		JsonArray resourceSetsArray = jsonObject.getAsJsonArray("resourceSets");
		for(int z=0;z<resourceSetsArray.size();z++)
		{
			//resources is defined as an array on the json response. Use for for safety, but should only do 1 iteration
			JsonArray resourcesArray = resourceSetsArray.get(z).getAsJsonObject().get("resources").getAsJsonArray();			
			for(int i=0;i<resourcesArray.size();i++)
			{
				//routeLegs is defined as an array on the json response. Use for for safety, but should only do 1 iteration
				JsonArray routeLegsArray = resourcesArray.get(i).getAsJsonObject().get("routeLegs").getAsJsonArray();
				for(int j=0;j<routeLegsArray.size();j++)
				{
					//The itenerayItems are the items that we are interested in.
					JsonArray itineraryItemsArray = routeLegsArray.get(j).getAsJsonObject().get("itineraryItems").getAsJsonArray();
					for(int k=0;k<itineraryItemsArray.size();k++)
					{
						JsonObject coordinate = (JsonObject) itineraryItemsArray.get(k);
						//The compass direction can be interesting, but nothig is done with it atm
						String compassDirection = coordinate.get("compassDirection").getAsString();
						//The  distance were taken because it was vey very different result once it was calculated to calculate turn by turn  
						//double travelDistance = coordinate.get("travelDistance").getAsDouble();
						double travelDuration = coordinate.get("travelDuration").getAsDouble();
						
						JsonArray details = (JsonArray) coordinate.get("details");
						int compassDegree = ((JsonObject)details.get(0)).get("compassDegrees") != null ?  ((JsonObject)details.get(0)).get("compassDegrees").getAsInt() : 0;
						String roadType = ((JsonObject)details.get(0)).get("roadType").getAsString();
						
						if(roadType.indexOf("Highway") != -1)
							route.setHasHighway();
						
						String maneuverType = ((JsonObject)details.get(0)).get("maneuverType").getAsString();
						String streetName = ((JsonObject)details.get(0)).get("names") != null ? ((JsonObject)details.get(0)).get("names").getAsJsonArray().get(0).getAsString() : "";
						
						JsonObject instruction = (JsonObject) coordinate.get("instruction");
						String text = instruction.get("text").getAsString();
						

						JsonObject maneuverPoint = (JsonObject)coordinate.get("maneuverPoint");
						JsonArray coordinates = maneuverPoint.get("coordinates").getAsJsonArray();
						double lat = coordinates.get(0).getAsDouble();
						double lon = coordinates.get(1).getAsDouble();
						
						ManeuverType maneuverTypeObj = 	getManeuverType(maneuverType);
						Maneuver man =  new Maneuver(lat, lon, text, streetName, maneuverTypeObj, compassDegree, compassDirection, travelDuration);
						JsonElement warnings = routeLegsArray.get(j).getAsJsonObject().get("warnings");
						getWarnings(man, itineraryItemsArray, warnings);						
						route.addManeuverPoint(lat, lon, man);
					}
				}	
			}
		}
		
		return route;
	}

	private ManeuverType getManeuverType(String maneuverType) {
		ManeuverType  maneuverTypeObj = null;
		for (ManeuverType m : ManeuverType.values())
			if(m.getBingName().equals(maneuverType))
			{
				maneuverTypeObj = m;
				break;
			}
		return maneuverTypeObj;
	}

	private void getWarnings(Maneuver man, JsonArray itineraryItemsArray, JsonElement warnings) {
		if(warnings != null)
		{
			JsonArray warningsArray = warnings.getAsJsonArray();
			for(int l=0;l<warningsArray.size();l++)
			{
				JsonObject warning = (JsonObject) itineraryItemsArray.get(l);
				String severity = warning.get("severity").getAsString();
				String warningText = warning.get("text").getAsString();
				String type = warning.get("warningType").getAsString();
				man.addWarning(severity,type,warningText);
			}	
		}
	}
}
