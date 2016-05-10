package de.visiom.carpc.services.navigation;

import de.visiom.carpc.asb.servicemodel.valueobjects.SetValueObject;
import de.visiom.carpc.services.navigation.model.Point;

public class Destination {

	private Point point;

	private String address;

	private String name;

	private String id;
	
	private boolean isDestination;
	
	public Destination(Point point, String address, String name, String id) {
		this.point = point;
		this.address = address;
		this.name = name;
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public String getAddress() {
		return address;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result += prime * result + ((address == null) ? 0 : address.hashCode());
		result += prime * result + ((name == null) ? 0 : name.hashCode());
		result += prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Destination other = (Destination) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}

	public void setLongitude(double longitude) {
		point.setLongitude(longitude);
	}

	public void setLatitude(double latitude) {
		point.setLatitude(latitude);
	}

	public double getLatitude() {
		return point.getLatitude();
	}

	public double getLongitude() {
		return point.getLongitude();
	}
	
	public void setDestination(boolean isDestination) {
		this.isDestination = isDestination;
	}

	public String getId() {
		return id;
	}

	public boolean isDestination() {
		return isDestination;
	}

	@Override
	public String toString() {
		return "Destination [point=" + point + ", address=" + address
				+ ", name=" + name + ", id=" + id + ", isDestination="
				+ isDestination + "]";
	}
	
	

}
