package de.visiom.carpc.services.navigation.util.bing;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.visiom.carpc.services.navigation.model.Point;

public class BingLegDeserializer implements JsonDeserializer<Point[]>{

	
	private Point end;
	private Point start;

	public BingLegDeserializer(Point a, Point b) {
		this.start = a;
		this.end = b;
	}

	@Override
	public Point[] deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();	
		Point[] points = null;
		
		JsonArray resourceSetsArray = jsonObject.getAsJsonArray("resourceSets");
		for(int z=0;z<resourceSetsArray.size();z++)
		{
			JsonArray resourcesArray = resourceSetsArray.get(z).getAsJsonObject().get("resources").getAsJsonArray();
			for(int i=0;i<resourcesArray.size();i++)
			{
				JsonArray coordinates =  resourcesArray.get(i).getAsJsonObject().get("routePath").getAsJsonObject().getAsJsonObject("line").getAsJsonArray("coordinates");
				points = new Point[coordinates.size()-1];
				for (int j = 0; j < coordinates.size()-1; j++) 
				{
					double lat = coordinates.get(j).getAsJsonArray().get(0).getAsDouble() ;
					double lon = coordinates.get(j).getAsJsonArray().get(1).getAsDouble();
					if(j==0)
						points[j] = start;
					else
						points[j] = new Point(lat, lon);
					//System.out.println(lat+","+lon);
				}
			}
		}
		//Point[] response = new Point[points.length-2];
		//System.arraycopy(points, 1, response, 0 ,points.length-2);
		return points;
		
	}
	
	
	public Point[] deserialize2(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
		JsonObject jsonObject = json.getAsJsonObject();	
		
		Point[] points = null;
		
		JsonArray resourceSetsArray = jsonObject.getAsJsonArray("resourceSets");
		for(int z=0;z<resourceSetsArray.size();z++)
		{
			JsonArray resourcesArray = resourceSetsArray.get(z).getAsJsonObject().get("resources").getAsJsonArray();
			for(int i=0;i<resourcesArray.size();i++)
			{
				JsonArray coordinates =  resourcesArray.get(i).getAsJsonObject().get("routePath").getAsJsonObject().getAsJsonObject("line").getAsJsonArray("coordinates");
				points = new Point[coordinates.size()];
				for (int j = 0; j < coordinates.size(); j++) 
				{
					double lat = coordinates.get(j).getAsJsonArray().get(0).getAsDouble() ;
					double lon = coordinates.get(j).getAsJsonArray().get(1).getAsDouble();
					points[j] = new Point(lat, lon);
				}
			}
		}
		//Point[] response = new Point[points.length-2];
		//System.arraycopy(points, 1, response, 0 ,points.length-2);
		return points;
		
	}
	  
}
