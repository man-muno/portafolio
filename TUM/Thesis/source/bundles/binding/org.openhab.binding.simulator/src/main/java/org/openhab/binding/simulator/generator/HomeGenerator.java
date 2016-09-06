package org.openhab.binding.simulator.generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openhab.binding.simulator.internal.SimulatorBinding;
import org.openhab.core.events.EventPublisher;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.OpenClosedType;
import org.openhab.core.types.Command;
import org.openhab.core.types.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeGenerator extends Generator {
		
	private static final Logger logger = LoggerFactory.getLogger(HomeGenerator.class);
	
	//Possible values for the contact sensor
	private static final String OPEN_SENSOR_VALUE = "OPEN";
	private static final String CLOSED_SENSOR_VALUE = "CLOSED";
	private static final String CONTACT_SENSOR_TYPE = "Contact";
	
	//Possible values for the switch sensors
	private static final String OFF_SENSOR_VALUE = "OFF";
	private static final String ON_SENSOR_VALUE = "ON";
	private static final String SWITCH_SENSOR_TYPE = "Switch";

	//Sensor names
	private static final String OUTDOOR_MOTION_SENSOR_NAME = "Outdoor_Motion";
	private static final String INDOOR_MOTION_SENSOR_NAME = "Indoor_Motion";
	private static final String WINDOW_SENSOR_NAME = "Window";
	private static final String PRESENCE_SENSOR_NAME = "Presence";
	private static final String FRONT_DOOR_SENSOR_NAME = "Front_Door";
	private static final String BACKDOOR_SENSOR_NAME = "Back_Door";
	private static final String BACKCAMERA_SENSOR_NAME = "Recognized_Face";
	public static final String FILE_ITEM_SEPARATOR = ", ";
	
	
	public boolean isWokenUp;
	public boolean gotUp;
	public boolean atHome;
	private boolean sleeping;
	private boolean goToWork;
	public int seconds;
	private boolean backdoorOpen=false;	

	private FileWriter writer;
	private FileWriter commandWriter;
	public static final int SECONDS_IN_A_DAY = 86400;
	private static final String CONTACT_SENSOR_TYPE_COMMAND = "update";
	private static final String SWITCH_SENSOR_TYPE_COMMAND = "send";
	private DayOfTheWeek[] days;
	private int currentDay;
	private EventPublisher eventPublisher; 
	private boolean generated=false;
	
	public HomeGenerator( ) {
		// TODO Auto-generated constructor stub
	}
	
	public HomeGenerator(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public static void main(String[] args) {
		HomeGenerator generator = new HomeGenerator();
		//generator.generate(1);
		generator.generateUntilNow(1);
	}

	public void generate(int weeks) {
		try {
			writer = new FileWriter("simulated_data.csv", false);
			commandWriter  = new FileWriter("simulated_command_data.txt", false);
			for(int j=0;j<weeks;j++){
				simulateWeek();	
			}
			writer.close();
			commandWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void simulateWeek() throws IOException {		
		//it beggins sleeping on a monday evening
		DayOfTheWeek[] values = DayOfTheWeek.values();
		
		for (int i = 0; i < values.length; i++) {
			//New day, new values
			DayOfTheWeek day = values[i];
			sleeping = true;
			gotUp = false;
			isWokenUp = false;
			goToWork = false;
			atHome = false;
			for (seconds = 1;seconds<=SECONDS_IN_A_DAY;seconds++){
				if (day.equals(DayOfTheWeek.SATURDAY))
					simulateSaturday(day);
				else if(day.equals(DayOfTheWeek.SUNDAY))
					simulateSunday(day);
				else
					simulateWeekday(day);
			}
		}
	}
	
	private void simulateSunday(DayOfTheWeek day) {
		//Base probability 
		int probabilitySampleSpace = 100;
		int getHomeTime = 3 * 3600;
		int wakeHomeTime= 11 * 3600;
		int getUpTime = 11 * 3600 + 1800;
		int goToBed = 23 * 3600 + 1800;
		//Depending  on the time of the day, several things can happen
		if(getHomeTime < seconds && seconds < wakeHomeTime &&!atHome){
			atHome = new Random().nextInt(probabilitySampleSpace)==0;
			if(atHome){
				sleeping = false;
				isWokenUp = false;
				gotUp = false;
				goToWork = false;
				arriveHome(day);
			}
		} else if(wakeHomeTime <= seconds && seconds < getUpTime && !isWokenUp) {
			//set when to wake up
			isWokenUp = new Random().nextInt(probabilitySampleSpace)==0;
			if (isWokenUp){
				sleeping = false;
				gotUp = false;
				goToWork = false;
				atHome = false;
			}
		} else if(getUpTime <= seconds && seconds < goToBed && !gotUp) {
			//Set time when to get to work
			gotUp = new Random().nextInt(probabilitySampleSpace)==0;
			if(gotUp){
				sleeping = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
				startLuften(day);
			}
		}
		
		//Hanging out on a sunday
		hangoutAtHome(day, probabilitySampleSpace);
		randomMovementOutside(day);
	}

	private void simulateSaturday(DayOfTheWeek day) {
		//Base probability 
		int probabilitySampleSpace = 100;
		int wakeUpTime = 9 * 3600;
		int getUpTime = 9 * 3600 + 1800;
		int getOutTime = 17 * 3600 + 1800;
		//Depending  on the time of the day, several things can happen
		if(seconds < wakeUpTime) {
			if(sleeping){
				gotUp = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
			}
		} else if(wakeUpTime <= seconds && seconds < getUpTime && !isWokenUp) {
			//set when to wake up
			isWokenUp = new Random().nextInt(probabilitySampleSpace)==0;
			if (isWokenUp){
				sleeping = false;
				gotUp = false;
				goToWork = false;
				atHome = false;
			}
		} else if(getUpTime <= seconds && seconds < getOutTime && !gotUp) {
			//Set time when to get to work
			gotUp = new Random().nextInt(probabilitySampleSpace)==0;
			if(gotUp){
				sleeping = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
				startLuften(day);
			}
		} else if(getOutTime <=seconds && !goToWork) {
			//Set when to get back home
			goToWork = new Random().nextInt(probabilitySampleSpace)==0;
			if(goToWork){
				sleeping = false;
				isWokenUp = false;
				gotUp = false;
				atHome = false;
				leaveHome(day);
			}
		}
		hangoutAtHome(day, probabilitySampleSpace);
		randomMovementOutside(day);
	}

	private void simulateWeekday(DayOfTheWeek day)  {
		//Base probability 
		int probabilitySampleSpace = 100;
		//Times when events happen
		int wakeUpTimeWeekday = 7 * 3600;
		int getUpTimeWeekday = 7 * 3600 + 1800;
		int getOutTimeWeekday = 8 * 3600 + 1800;
		int getHomeTimeWeekday = 18 * 3600;
		int goToBedTimeWeekday = 23 * 3600 + 1800;
		//Depending  on the time of the day, several things can happen
		if(seconds < wakeUpTimeWeekday && sleeping) {
				gotUp = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
		} else if(wakeUpTimeWeekday <= seconds && seconds < getUpTimeWeekday && !isWokenUp) {
			//set when to wake up
			isWokenUp = new Random().nextInt(probabilitySampleSpace)==0;
			if (isWokenUp){
				sleeping = false;
				gotUp = false;
				goToWork = false;
				atHome = false;
			}
		} else if(getUpTimeWeekday <= seconds && seconds < getOutTimeWeekday && !gotUp) {
			//Set time when to get to work
			gotUp = new Random().nextInt(probabilitySampleSpace)==0;
			if(gotUp){
				sleeping = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
				startLuften(day);
			}
		} else if(getOutTimeWeekday <=seconds && seconds < getHomeTimeWeekday  && !goToWork) {
			//Set when to get back home
			goToWork = new Random().nextInt(probabilitySampleSpace)==0;
			if(goToWork){
				sleeping = false;
				isWokenUp = false;
				gotUp = false;
				atHome = false;
				leaveHome(day);	
			}
		} else if( getHomeTimeWeekday <= seconds && seconds < goToBedTimeWeekday && !atHome){
			//Set when to get home
			atHome = new Random().nextInt(probabilitySampleSpace)==0;
			if(atHome){
				sleeping = false;
				isWokenUp = false;
				gotUp = false;
				goToWork = false;
				arriveHome(day);
			}
		} else if(  (goToBedTimeWeekday <= seconds && !sleeping)){
			//Set when to go to bed
			sleeping = new Random().nextInt(probabilitySampleSpace)==0;
			if(sleeping){
				gotUp = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
				closeWindow(day);
			}
		}
		hangoutAtHome(day, probabilitySampleSpace); 
		randomMovementOutside(day);
	}

	private void randomMovementOutside(DayOfTheWeek day) {
		int probability = 10000; 
		if(new Random().nextInt(probability)==0)
			moveArround(day, OUTDOOR_MOTION_SENSOR_NAME);
	}
		
	private void leaveHome(DayOfTheWeek day) {
		closeBackdoor(day);
		backdoorOpen = false;
		seconds += new Random().nextInt(120);
		openCloseFrontDoor(day);
		seconds += new Random().nextInt(120);				
		turnPresenceOff(day);
	}

	private void startLuften(DayOfTheWeek day) {
		openWindow(day);
		seconds += new Random().nextInt(120);
		openBackdoor(day);
		backdoorOpen = true;
	}

	private void arriveHome(DayOfTheWeek day) {
		turnPresenceOn(day);
		seconds += new Random().nextInt(120);
		openCloseFrontDoor(day);
	}

	private void openCloseFrontDoor(DayOfTheWeek day) {
		writeSensor(day,  FRONT_DOOR_SENSOR_NAME,CONTACT_SENSOR_TYPE,ON_SENSOR_VALUE );
		seconds += new Random().nextInt(120);
		writeSensor(day,  FRONT_DOOR_SENSOR_NAME,CONTACT_SENSOR_TYPE,OFF_SENSOR_VALUE );
	}

	private void hangoutAtHome(DayOfTheWeek day, int probabilitySampleSpace) {
		if(!day.equals(DayOfTheWeek.SUNDAY)){
			if (sleeping && new Random().nextInt(probabilitySampleSpace*20) == 0){
				//The user is sleeping. The sensor should pick up little movementwriteMotionSensor
				moveArround(day, INDOOR_MOTION_SENSOR_NAME);
			} else if (isWokenUp && new Random().nextInt(probabilitySampleSpace)==0){
				//The user is not longer sleeping, however is still in bed, and the sensor picks up more movement
				 moveArround(day, INDOOR_MOTION_SENSOR_NAME);				 
			} else if ( (atHome || gotUp) && new Random().nextInt(probabilitySampleSpace/20)==0) {
				//the is just hanging around on the house
				moveArround(day, INDOOR_MOTION_SENSOR_NAME);
				//Decide if is going outside
				goToTheBackyard(day, probabilitySampleSpace);
			}			
		} else {
			if (isWokenUp && new Random().nextInt(probabilitySampleSpace)==0){
				moveArround(day,INDOOR_MOTION_SENSOR_NAME);				 
			} else if (gotUp && new Random().nextInt(probabilitySampleSpace/20)==0) {
				moveArround(day,INDOOR_MOTION_SENSOR_NAME);
				//Decide if is going outside				
				goToTheBackyard(day, probabilitySampleSpace);
			}
		}
 	}

	private void goToTheBackyard(DayOfTheWeek day, int probabilitySampleSpace) {
		if (new Random().nextInt(4000)==0 && backdoorOpen){
			//Check how long would be outside
			int duration = (new Random().nextInt(15) + 5) * 60;
			for (int i = 0; i<duration; i++)
				if(new Random().nextInt(probabilitySampleSpace/20)==0){
					moveArround(day, OUTDOOR_MOTION_SENSOR_NAME);
					seconds+=new Random().nextInt(5)+1;
					//TODO: set the camera 
					moveArround(day, BACKCAMERA_SENSOR_NAME);
				}
		}
	}
	
	private void moveArround(DayOfTheWeek day, String sensorName) {
		writeSensor(day, sensorName, CONTACT_SENSOR_TYPE, OPEN_SENSOR_VALUE);
		seconds+=new Random().nextInt(5)+1;
		writeSensor(day, sensorName, CONTACT_SENSOR_TYPE, CLOSED_SENSOR_VALUE);
	}
	
	private void closeWindow(DayOfTheWeek day) {
		writeSensor(day,  WINDOW_SENSOR_NAME,CONTACT_SENSOR_TYPE,CLOSED_SENSOR_VALUE );
	}

	private void turnPresenceOff(DayOfTheWeek day) {
		writeSensor(day,  PRESENCE_SENSOR_NAME,SWITCH_SENSOR_TYPE,OFF_SENSOR_VALUE );
	}

	private void closeBackdoor(DayOfTheWeek day) {
		writeSensor(day,  BACKDOOR_SENSOR_NAME,CONTACT_SENSOR_TYPE,CLOSED_SENSOR_VALUE );
	}
	
	private void turnPresenceOn(DayOfTheWeek day) {
		writeSensor(day,  PRESENCE_SENSOR_NAME,SWITCH_SENSOR_TYPE,ON_SENSOR_VALUE );
	}
	
	private void openBackdoor(DayOfTheWeek day) {
		writeSensor(day,  BACKDOOR_SENSOR_NAME,CONTACT_SENSOR_TYPE,OPEN_SENSOR_VALUE );
	}

	private void openWindow(DayOfTheWeek day) {
		writeSensor(day,  WINDOW_SENSOR_NAME,CONTACT_SENSOR_TYPE,OPEN_SENSOR_VALUE );
	}
	
	private void writeSensor(DayOfTheWeek day, String name, String type, String value) {
		//writeCSV(day, name, type, value);
		writeOpenhabCommands(day, name, type, value);
	}

	private void writeOpenhabCommands(DayOfTheWeek day, String name, String type, String value) {
		if(generated){
			if(type.equals(CONTACT_SENSOR_TYPE)){
				logger.debug("postCommand " + name + " " +  value);
				eventPublisher.postCommand(name, (Command) getRealValue(type, value));
			} else if(type.equals(SWITCH_SENSOR_TYPE)){
				logger.debug("postUpdate: " + name + " " +  value);
				eventPublisher.postUpdate(name, (State) getRealValue(type, value));
			}	
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

	public int[] splitToComponentTimes(int seconds)
	{
	    int hours = (int) seconds / 3600;
	    int remainder = (int) seconds - hours * 3600;
	    int mins = remainder / 60;
	    remainder = remainder - mins * 60;
	    int secs = remainder;
	    hours = hours == 24 ? 0 : hours;
	    int[] ints = {hours , mins , secs};
	    return ints;
	}

	public void generateUntilNow(int weeks) {
		for(int j=0;j<weeks;j++){
			simulateWeekUntilNow();
		}
	}
	
	private void simulateWeekUntilNow() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		long currentTimeInSeconds = c.get(Calendar.HOUR_OF_DAY) * 60 * 60;
		currentTimeInSeconds += c.get(Calendar.MINUTE) * 60;
		currentTimeInSeconds += c.get(Calendar.SECOND);
		//it beggins sleeping on a monday evening
		days = DayOfTheWeek.getDays(day_of_week);
		
		//for (currentDay = 0; currentDay < days.length; currentDay++) {
			//New day, new values
			DayOfTheWeek day = days[currentDay];
			sleeping = true;
			gotUp = false;
			isWokenUp = false;
			goToWork = false;
			atHome = false;
			for (seconds = 1;seconds<=currentTimeInSeconds;seconds++){
				if (day.equals(DayOfTheWeek.SATURDAY))
					simulateSaturday(day);
				else if(day.equals(DayOfTheWeek.SUNDAY))
					simulateSunday(day);
				else
					simulateWeekday(day);
			}
		//}
			
	}

	public void tick(EventPublisher eventPublisher) {
		this.eventPublisher = eventPublisher;
		//First simulate the command
		DayOfTheWeek day = days[currentDay];
		if (day.equals(DayOfTheWeek.SATURDAY)){
			simulateSaturday(day);
		} else if(day.equals(DayOfTheWeek.SUNDAY)){
			simulateSunday(day);
		} else {
			simulateWeekday(day);
		}
	
		//move to the next second for setup
		seconds++;
		if(seconds>SECONDS_IN_A_DAY){
			seconds=0;
			currentDay++;
			if(currentDay<=days.length){
				sleeping = true;
				gotUp = false;
				isWokenUp = false;
				goToWork = false;
				atHome = false;
			}
		}		
	}

	public void isGenerated() {
		generated = true;
	}
	

}
