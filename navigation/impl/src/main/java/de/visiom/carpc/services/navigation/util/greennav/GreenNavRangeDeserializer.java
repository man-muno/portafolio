package de.visiom.carpc.services.navigation.util.greennav;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.visiom.carpc.services.navigation.model.Point;
import de.visiom.carpc.services.navigation.model.Range;
import de.visiom.carpc.services.navigation.model.Route;

public class GreenNavRangeDeserializer implements JsonDeserializer<Range> {

	@Override
	public Range deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
	    JsonObject jsonObject = json.getAsJsonObject();

	    Range range = new Range();
	    range.setAlgorithm((jsonObject.get("algorithm").getAsString()));
	    range.setBatteryStatus(jsonObject.get("batteryStatus").getAsDouble());
	    
	    JsonObject initialPointJson = jsonObject.get("initialPoint").getAsJsonObject();
	    double lat = initialPointJson.get("latitude").getAsDouble();
	    double lon = initialPointJson.get("longitude").getAsDouble();
	    range.setInitialPoint(new Point(lat,lon));
	    
	    JsonArray rangePointJson = jsonObject.get("rangePoints").getAsJsonArray();
	    for(int i = 0; i < rangePointJson.size();i++)
	    {
	    	JsonObject jpoint = rangePointJson.get(i).getAsJsonObject();
		    double latRange = jpoint.get("latitude").getAsDouble();
		    double lonRange = jpoint.get("longitude").getAsDouble();
		    range.addPoint(latRange,lonRange);
	    }
	    return range;
	}

}
