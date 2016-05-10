package de.visiom.carpc.services.navigation.model;

public class ManeuverPoint extends Point {

	private Maneuver maneuver;

	public ManeuverPoint(double lat, double lon, Maneuver maneuver) {
		super(lat, lon);
		this.maneuver = maneuver;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals((Point)obj);
	}

	public Maneuver getManeuver() {
		return maneuver;
	}

}
