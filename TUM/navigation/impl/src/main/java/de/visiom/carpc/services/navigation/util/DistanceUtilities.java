package de.visiom.carpc.services.navigation.util;

import de.visiom.carpc.services.navigation.model.Point;

/**
 * 
 * @author manuel
 *
 */
public class DistanceUtilities {

    //static final double _eQuatorialEarthRadius = 6378.1370D;
	static final double _eQuatorialEarthRadius = 6371.1370D;
    static final double _d2r = (Math.PI / 180D);

    /**public static int distanceInMeters(double lat1, double long1, double lat2, double long2) {
        return (int) (1000D * distanceInKM(lat1, long1, lat2, long2));
    }**/

    /**
     * HaversineAlgorithm. Taken from http://stackoverflow.com/a/14459730 
     * @param lat1
     * @param long1
     * @param lat2
     * @param long2
     * @return
     */
    public static double distanceInKM(double lat1, double long1, double lat2, double long2) {
        double dlong = (long2 - long1) * _d2r;
        double dlat = (lat2 - lat1) * _d2r;
        double a = Math.pow(Math.sin(dlat / 2D), 2D) + Math.cos(lat1 * _d2r) * Math.cos(lat2 * _d2r)
                * Math.pow(Math.sin(dlong / 2D), 2D);
        double c = 2D * Math.atan2(Math.sqrt(a), Math.sqrt(1D - a));
        double d = _eQuatorialEarthRadius * c	;

        return d;
    }
    
    
	public static double crossTrackDistance(Point p3, Point p1, Point p2) {
		double dist13 = DistanceUtilities.distanceInKM(p1.getLatitude(), p1.getLongitude(), p3.getLatitude(),p3.getLongitude());
		double bearing13 = bearing(p1.getLatitude(),	p1.getLongitude(), p3.getLatitude(), p3.getLongitude());
		double bearing12 = bearing(p1.getLatitude(),	p1.getLongitude(), p2.getLatitude(),p2.getLongitude());
		//dXt = Math.asin(Math.sin(d13/R)*Math.sin(θ13-θ12)) * R;
		double dist = Math.asin(Math.sin(dist13 / _eQuatorialEarthRadius) * Math.sin(bearing13 - bearing12)) * _eQuatorialEarthRadius;
		return dist;
	}

	
	
	public static double alongTrackDistance(Point p3, Point p1, Point p2) {
		//var dAt = Math.acos(Math.cos(d13/R)/Math.cos(dXt/R)) * R;
		double dist13 = DistanceUtilities.distanceInKM(p1.getLatitude(), p1.getLongitude(), p3.getLatitude(),p3.getLongitude());
		double dist = Math.acos(Math.cos(dist13/_eQuatorialEarthRadius)/Math.cos(crossTrackDistance(p3, p1, p2)/_eQuatorialEarthRadius)) * _eQuatorialEarthRadius;
		return dist;
	}
	
   static public double initial(double lat1, double long1, double lat2, double long2)
    {
        return (_bearing(lat1, long1, lat2, long2) + 360.0) % 360;
    }

    static public double _final(double lat1, double long1, double lat2, double long2)
    {
        return (_bearing(lat2, long2, lat1, long1) + 180.0) % 360;
    }

    public static double _bearing(double lat1, double long1, double lat2, double long2)
    {
        double degToRad = Math.PI / 180.0;
        double phi1 = lat1 * degToRad;
        double phi2 = lat2 * degToRad;
        double lam1 = long1 * degToRad;
        double lam2 = long2 * degToRad;

        return Math.atan2(Math.sin(lam2-lam1)*Math.cos(phi2),
            Math.cos(phi1)*Math.sin(phi2) - Math.sin(phi1)*Math.cos(phi2)*Math.cos(lam2-lam1)
        ) * 180/Math.PI;
    }
    
    public static double bearing(double lat1, double lon1, double lat2, double lon2){
    	  double longitude1 = lon1;
    	  double longitude2 = lon2;
    	  double latitude1 = Math.toRadians(lat1);
    	  double latitude2 = Math.toRadians(lat2);
    	  double longDiff= Math.toRadians(longitude2-longitude1);
    	  double y= Math.sin(longDiff)*Math.cos(latitude2);
    	  double x=Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);

    	  return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }   
}