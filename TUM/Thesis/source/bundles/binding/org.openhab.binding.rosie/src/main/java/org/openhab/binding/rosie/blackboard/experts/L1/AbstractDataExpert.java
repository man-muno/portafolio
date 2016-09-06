package org.openhab.binding.rosie.blackboard.experts.L1;

import org.openhab.binding.rosie.blackboard.AbstractKnowledgeSource;
import org.openhab.binding.rosie.blackboard.Blackboard;
import org.openhab.binding.rosie.blackboard.Location;
import org.openhab.binding.rosie.blackboard.SensorType;
import org.openhab.binding.rosie.blackboard.Tuple;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;

public abstract class AbstractDataExpert extends AbstractKnowledgeSource {
		
	/**
	 * Serial version ID
	 */
	private static final long serialVersionUID = 7000530973236582590L;

	/**
	 * Name of the type of sensor
	 */
	private SensorType sensorType;
	
	/**
	 * Name of item
	 */
	private String itemName;

	/**
	 * Location of where the sensor is located
	 */
	private Location location;
	
	/**
	 * Constructor for the class
	 * @param blackboard Not null
	 * @param itemType Name of the expert
	 */
	protected AbstractDataExpert(Blackboard blackboard, String itemType) {
		super(blackboard);
		this.itemName = itemType;
	}

	@Override
	public void evaluateKnowledge() {
		Tuple tuple = blackboard.getTupleValue(getExpertName());
		if (tuple != null && !tuple.isRead()){
			//blackboard.addTuple(sensorType, this.getItemName(), new Tuple(Level.L1, getRealValue((String)tuple.getPayload()), tuple.getTimeStamp(), false));
			blackboard.addTuple(sensorType, this.getExpertName(), Level.L1, tuple.getPayload(), tuple.getTimeStamp(), tuple.getLocation(), false);
			tuple.setRead();
		}
	}

	/**
	 * Returns the type of the sensor
	 * @return Not null
	 */
	public SensorType getSensorType(){
		return sensorType;
	}

	/**
	 * Changes the type of the sensor
	 * @param sensorType Not null
	 */
	public void setSensorType(SensorType sensorType) {
		this.sensorType = sensorType;
	}
	
	/**
	 * Returns the value of the payload according to OpenHAB's types
	 * @param payload not null
	 * @return May be null if none found
	 */
	public Object getRealValue(String payload){
		Object response = null;
		if(itemName.equals(ItemExpertFactory.SWITCH_TYPE)){
			if(OnOffType.ON.toString().equals(payload))
				response = OnOffType.ON;
			else
				response = OnOffType.OFF;
		} else if(itemName.equals(ItemExpertFactory.CONTACT_TYPE)){
			if(OpenClosedType.OPEN.toString().equals(payload))
				response = OpenClosedType.OPEN;
			else 
				response = OpenClosedType.CLOSED;
		} else if(itemName.equals(ItemExpertFactory.NUMBER_TYPE)){
			response = Double.parseDouble(payload);
		} 
		return response;
	}

	/**
	 * Changes the location of the sensor
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Returns the location of the sensor
	 * @return Not null
	 */
	public Location getLocation() {
		return location;
	}
}
