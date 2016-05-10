package org.openhab.binding.simulator.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openhab.binding.simulator.internal.SimulatorBinding;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartLabGenerator extends Generator {
		
	private static final Logger logger = LoggerFactory.getLogger(SmartLabGenerator.class);
	
	//Possible values for the contact sensor
	private static final String OPEN_SENSOR_VALUE = "OPEN";
	private static final String CLOSED_SENSOR_VALUE = "CLOSED";
	private static final String CONTACT_SENSOR_TYPE = "Contact";
	
	//Possible values for the switch sensors
	private static final String OFF_SENSOR_VALUE = "OFF";
	private static final String ON_SENSOR_VALUE = "ON";
	private static final String SWITCH_SENSOR_TYPE = "Switch";

	//Sensor names
	private static final String INDOOR_MOTION_SENSOR_NAME = "Big_motion";
	private static final String WINDOW_RIGHT_SENSOR_NAME = "EO_SL_WindowRight";
	private static final String WINDOW_LEFT_SENSOR_NAME = "EO_SL_WindowLeft";
	private static final String PRESENCE_SENSOR_NAME = "Smartlab_presence";
	private static final String PRESENCE_FACE_SENSOR_NAME = "Recognized_Face";
	private static final String FRONT_DOOR_SENSOR_NAME = "Slim_door";
	public static final String FILE_ITEM_SEPARATOR = ", ";
	
	private static final String SCENARIO = "scenario";

	private static final String CONTACT_SENSOR_TYPE_COMMAND = "update";
	private static final String SWITCH_SENSOR_TYPE_COMMAND = "send";
	private int seconds;
	private EventPublisher eventPublisher; 
	
	public SmartLabGenerator(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		seconds = 0;
	}

	public static void main(String[] args) {

	}

	public void tick(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		if(seconds < 60 ){ 
			scenario3(eventPublisher);	
		} else if(60 < seconds && seconds < 120 ){ 
			scenario4(eventPublisher);	
		} else if(120 == seconds){
			turnAllOff();
		} else if(120 < seconds && seconds < 180 ){
			scenario5(eventPublisher);	
		} else if(180 == seconds){
			turnAllOff();
		} else if(180 < seconds && seconds < 240 ){ 
			scenario6(eventPublisher);	
		} else if(240 == seconds){
			turnAllOff();
		} else if(240 < seconds && seconds < 300 ){
			scenario7(eventPublisher);	
		} else if(seconds == 300 ) {
			turnAllOff();
			logger.info(" ***************************** Simulation done ***************************** ");
		} else if(seconds > 310  && seconds < 340 ) {
			scenario0(eventPublisher);
		} else if(seconds == 340 ) {
			turnAllOff();
		} else if(seconds > 340  && seconds < 370 ) {
			scenario1(eventPublisher);
		} else if(seconds == 370){
			turnAllOff();
		} else if(seconds > 370  && seconds < 400 ) {
			scenario2(eventPublisher);
		} else if(seconds == 400) {
			turnAllOff();
		} else if(seconds > 400  && seconds < 430 ) {
			scenario3(eventPublisher);
		} else if(seconds == 430 ) {
			turnAllOff();
		} else if(seconds > 430  && seconds < 460 ) {
			scenario4(eventPublisher);
		} else if(seconds == 460){
			turnAllOff();
		} else if(seconds > 460  && seconds < 490 ) {
			scenario5(eventPublisher);
		} else if(seconds == 490){
			turnAllOff();
		} else if(seconds > 490  && seconds < 520 ) {
			scenario6(eventPublisher);
		} else if(seconds == 520){
			turnAllOff();
		} else if(seconds > 520  && seconds < 550 ) {
			scenario7(eventPublisher);
		}
		seconds++;
	}
	
	private void turnAllOff() {
		logger.debug("***************************************************** turn all off");
		//INDOOR_MOTION_SENSOR_NAME
		writeOpenhabCommands(INDOOR_MOTION_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//WINDOW_RIGHT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_RIGHT_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//WINDOW_LEFT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_LEFT_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//PRESENCE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_SENSOR_NAME, SWITCH_SENSOR_TYPE, OFF_SENSOR_VALUE);
		//PRESENCE_FACE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_FACE_SENSOR_NAME, SWITCH_SENSOR_TYPE, OFF_SENSOR_VALUE);
		//FRONT_DOOR_SENSOR_NAME
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
	}

	private void scenario7(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 7");
		simulateMotion();
		
		//FRONT_DOOR_SENSOR_NAME
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		writeScenario(7);
	}

	private void scenario6(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 6");
		//WINDOW_LEFT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_LEFT_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		simulatePresence();
		writeScenario(6);
	}

	private void simulatePresence() {
		//PRESENCE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_SENSOR_NAME, SWITCH_SENSOR_TYPE, ON_SENSOR_VALUE);
	}

	private void scenario5(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 5");
		//WINDOW_RIGHT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_RIGHT_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		writeScenario(5);
	}

	private void scenario4(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 4");
		//INDOOR_MOTION_SENSOR_NAME
		simulateMotion();
		simulateCamera();

		//FRONT_DOOR_SENSOR_NAME
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		writeScenario(4);
	}

	private void simulateCamera() {
		//PRESENCE_FACE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_FACE_SENSOR_NAME, SWITCH_SENSOR_TYPE, ON_SENSOR_VALUE);
	}

	private void simulateMotion() {
		//INDOOR_MOTION_SENSOR_NAME
		if(seconds % 3 == 0) {
			writeOpenhabCommands(INDOOR_MOTION_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		} else {
			writeOpenhabCommands(INDOOR_MOTION_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		}
	}

	private void scenario3(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 3");
		//INDOOR_MOTION_SENSOR_NAME
		writeOpenhabCommands(INDOOR_MOTION_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//WINDOW_RIGHT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_RIGHT_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//WINDOW_LEFT_SENSOR_NAME
		writeOpenhabCommands(WINDOW_LEFT_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		//PRESENCE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_SENSOR_NAME, SWITCH_SENSOR_TYPE, OFF_SENSOR_VALUE);
		//PRESENCE_FACE_SENSOR_NAME
		writeOpenhabCommands(PRESENCE_FACE_SENSOR_NAME, SWITCH_SENSOR_TYPE, OFF_SENSOR_VALUE);
		//FRONT_DOOR_SENSOR_NAME
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		writeOpenhabCommands(FRONT_DOOR_SENSOR_NAME, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
		writeScenario(3);
	}


	private void scenario2(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 2");
		simulateMotion();
		simulateCamera();
		simulatePresence();
		writeScenario(2);
	}
	

	private void scenario1(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 1");
		simulateMotion();
		writeScenario(1);
	}
	

	private void scenario0(EventPublisher eventPublisher) {
		logger.debug("***************************************************** Executig scenario 0");
		simulateCamera();
		simulatePresence();
		writeScenario(0);
	}

	private void writeScenario(int i) {
		eventPublisher.postUpdate(SCENARIO, new DecimalType(i));
	}

	private void writeOpenhabCommands(String name, String type, String value) {
		if(type.equals(CONTACT_SENSOR_TYPE)){
			logger.debug("postCommand " + name + " " +  value);
			eventPublisher.postCommand(name, (Command) getRealValue(type, value));
		} else if(type.equals(SWITCH_SENSOR_TYPE)){
			logger.debug("postUpdate: " + name + " " +  value);
			eventPublisher.postUpdate(name, (State) getRealValue(type, value));
		}	
	}
	
	public Object getRealValue(String itemType, String value){
		Object response = null;
		if(itemType.equals(SWITCH_SENSOR_TYPE)){
			if(OnOffType.ON.toString().equals(value))
				response = OnOffType.ON;
			else
				response = OnOffType.OFF;
		} else if(itemType.equals(CONTACT_SENSOR_TYPE)){
			if(OpenClosedType.OPEN.toString().equals(value))
				response = OpenClosedType.OPEN;
			else 	
				response = OpenClosedType.CLOSED;
		}
		
		return response;
	}

	@Override
	public void generateUntilNow(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isGenerated() {
		// TODO Auto-generated method stub
		
	}
}
