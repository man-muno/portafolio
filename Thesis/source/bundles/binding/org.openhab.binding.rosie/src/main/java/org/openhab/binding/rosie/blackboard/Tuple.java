package org.openhab.binding.rosie.blackboard;

import java.io.Serializable;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource.Level;

/**
 * Data to be written on the blackboard.
 * 
 * @author Manuel Munoz
 * 
 */
public class Tuple implements Serializable{

	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = -7316280571880764807L;

	/**
	 * From which expertise level it came
	 */
	private Level level;

	/**
	 * The data that is received from OpenHab
	 */
	private Object payload;

	/**
	 * Time at which this tuple got stored
	 */
	private String timeStamp;

	/**
	 * To decide whether this tuple notified the user already
	 */
	private boolean notified;

	/**
	 * Indoor/outdoor
	 */
	private Location location;

	/**
	 * Creates a tuple given the parameters
	 * @param level Not null
	 * @param payload Not null
	 * @param timeStamp Not null
	 * @param location Not null
	 * @param notified 
	 */
	public Tuple(Level level, Object payload, String timeStamp, Location location,  boolean notified) {
		this.level = level;
		this.payload = payload;
		this.timeStamp = timeStamp;
		this.notified = notified;
		this.location = location;
	}

	/**
	 * Payload getter. Changes notify to true
	 */
	public Object getPayload() {
		return payload;
	}

	/**
	 * get layer
	 */
	public Level getLevel() {
		return level;
	}

	/**
	 * timeStamp getter
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	public boolean isRead() {
		return notified;
	}

	/**
	 * Payload getter
	 */
	public void setPayloadn(Object payload) {
		this.payload = payload;
	}

	/**
	 * layer getter
	 */
	public void setLevel(Level level) {
		this.level = level;
	}

	/**
	 * timeStamp getter
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Changes the notified value to true
	 */
	public void setRead() {
		this.notified = true;
	}

	@Override
	public String toString() {
		return "Tuple [level=" + level + ", payload=" + payload
				+ ", timeStamp=" + timeStamp + ", notified=" + notified
				+ ", location=" + location + "]";
	}

	/**
	 * Changes the location of the item
	 * @param itemLocation Not null
	 */
	public void setLocation(Location itemLocation) {
		this.location = itemLocation;
	}

	/**
	 * Returns the location of the tuple
	 * @return Not null
	 */
	public Location getLocation() {
		return this.location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((payload == null) ? 0 : payload.hashCode());
		result = prime * result
				+ ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
		Tuple other = (Tuple) obj;
		if (level != other.level)
			return false;
		if (location != other.location)
			return false;
		if (payload == null) {
			if (other.payload != null)
				return false;
		} else if (!payload.equals(other.payload))
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}	
}
