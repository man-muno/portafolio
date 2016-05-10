package fml.tum.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Properties;
import java.util.Queue;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.delegates.Action;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fml.tum.model.statemachine.State;
import fml.tum.model.statemachine.TimerAction;
import fml.tum.model.statemachine.Trigger;
import fml.tum.msh.Steuerhebel;
import fml.tum.utils.PropertiesValues;

public class Warehouse extends Observable {

	/**
	 * Acceptable delta for correct mast height in mm 
	 */
	private int deltaMastHeight;

	/**
	 * Acceptable delta for correct sideshift. In mm
	 */
	private int deltaSideShift;

	/**
	 * Defines the value acceptable for the distance of the pallet
	 */
	private int desiredPalletRelativeDistance;

	/**
	 *The desired distance of the forkl lift in front of the shelf 
	 */
	private int desiredRangeFromShelf;
	
	
	/**
	 * List that contains the shelfs of the warehouse
	 */
	private ArrayList<Shelf> shelfs = new ArrayList<Shelf>();

	/**
	 * List of the instruction to be performed by the driver
	 */
	private ArrayList<Instruction> instructions = new ArrayList<Instruction>();
	
	/**
	 * Instruction to be performed by the driver
	 */
	private Instruction currentInstruction;

	/**
	 * State machine that helps identify in which point of execution of an instruction the driver is located
	 */
	private StateMachine<State, Trigger> states;

	/**
	 * The only forklift on the werehouse
	 */
	Forklift forklift;

	/**
	 * The current traffic sign identified on the warehouse
	 */
	private int command;

	/**
	 * When the fork lift identifies a shelf, it is said to be the current shelf
	 */
	private int currentShelfNumber;

	/**
	 * If a fork lift has detected a pallet
	 */
	private Pallet detectedPallet;

	/**
	 * Wait time for the states
	 */
	private int waitTime;

	private int currentImmersionPickupPallet=0;
	
	private int currentImmersionDropPallet=0;

	private boolean pickUp = false;

	private boolean dropedOff = true;
	
	//private boolean changed;


	/**
	 * Constructor. It creates two shelfs, and the state machine
	 * @param properties 
	 */
	public Warehouse(Properties properties) {
		shelfs.add(new Shelf(properties));
		shelfs.add(new Shelf(properties));
		forklift = new Forklift(Float.parseFloat((properties.getProperty(PropertiesValues.CORRECT_MAST_ANGLE))),Float.parseFloat(properties.getProperty(PropertiesValues.CORRECT_MAST_ANGLE_DELTA)));
		createStateMachine();
		deltaMastHeight = Integer.parseInt(properties.getProperty(PropertiesValues.MAST_HEIGHT_DELTA));
		deltaSideShift = Integer.parseInt(properties.getProperty(PropertiesValues.SIDE_SHIFT_DELTA));
		desiredPalletRelativeDistance  = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_PALLET_RELATIVE_DISTANCE));
		desiredRangeFromShelf = Integer.parseInt(properties.getProperty(PropertiesValues.DESIRED_RANGE_FROM_SHELF));
		waitTime = Integer.parseInt(properties.getProperty(PropertiesValues.WAIT_TIME));
		currentImmersionPickupPallet=-400;
		currentImmersionDropPallet=-400;
	}

	/**
	 * Creates the state machine
	 */
	private void createStateMachine() {
		states = new StateMachine<State, Trigger>(State.IDLE);

		// Transitions from idle
		states.configure(State.IDLE).permit(Trigger.SHOW_COMMAND,State.SHOWING_COMMAND);
		states.configure(State.IDLE).permit(Trigger.GO_TO_LOCATION,State.GOING_TO_LOCATION);

		// Transitions from Command
		states.configure(State.SHOWING_COMMAND).permit(Trigger.GO_TO_IDLE,State.IDLE);
		states.configure(State.SHOWING_COMMAND).permit(Trigger.GO_TO_LOCATION,State.GOING_TO_LOCATION);

		// Transitions from GoingToLocation
		states.configure(State.GOING_TO_LOCATION).permit(Trigger.GO_TO_IDLE,State.IDLE);
		states.configure(State.GOING_TO_LOCATION).permit(Trigger.GO_TO_SHELF,State.GOING_TO_SHELF);
		states.configure(State.GOING_TO_LOCATION).permit(Trigger.SHOW_COMMAND,State.SHOWING_COMMAND);
		

		// Transitions from GointToShelf
		states.configure(State.GOING_TO_SHELF).permit(Trigger.GO_TO_COLUMN,State.GOING_TO_COLUMN);
		states.configure(State.GOING_TO_SHELF).permit(Trigger.GO_TO_LOCATION,State.GOING_TO_LOCATION);

		// Transitions from GoingToColumn
		states.configure(State.GOING_TO_COLUMN).permit(Trigger.SET_MAST_ANGLE,State.SETTING_MAST_ANGLE);
		states.configure(State.GOING_TO_COLUMN).permit(Trigger.GO_TO_SHELF,State.GOING_TO_SHELF);
		
		// Transitions from SETTING_MAST_ANGLE
		states.configure(State.SETTING_MAST_ANGLE).permit(Trigger.WAIT_MAST_ANGLE,State.WAITING_MAST_ANGLE);
		states.configure(State.SETTING_MAST_ANGLE).permit(Trigger.GO_TO_COLUMN,State.GOING_TO_COLUMN);
		
		//Transitions for WAITING_MAST_ANGLE
		states.configure(State.WAITING_MAST_ANGLE).permit(Trigger.SET_MAST_HEIGHT,State.SETTING_MAST_HEIGHT);
		states.configure(State.WAITING_MAST_ANGLE).permit(Trigger.SET_MAST_ANGLE,State.SETTING_MAST_ANGLE);
		

		// Transitions from SETTING_MAST_HEIGHT
		states.configure(State.SETTING_MAST_HEIGHT).permit(Trigger.WAIT_MAST_HEIGHT,State.WAITING_MAST_HEIGHT);
		states.configure(State.SETTING_MAST_HEIGHT).permit(Trigger.SET_MAST_ANGLE,State.SETTING_MAST_ANGLE);
		
		//Transitions for WAITING_MAST_HEIGHT
		states.configure(State.WAITING_MAST_HEIGHT).permit(Trigger.SET_SIDE_SHIFT,State.SETTING_SIDE_SHIFT);
		states.configure(State.WAITING_MAST_HEIGHT).permit(Trigger.SET_MAST_HEIGHT,State.SETTING_MAST_HEIGHT);
		
		// Transitions from SETTING_SIDE_SHIFT
		states.configure(State.SETTING_SIDE_SHIFT).permit(Trigger.WAIT_SIDESHIFT,State.WAITING_SIDESHIFT);
		states.configure(State.SETTING_SIDE_SHIFT).permit(Trigger.SET_MAST_HEIGHT,State.SETTING_MAST_HEIGHT);
		
		// Transitions from WAITING_SIDESHIFT		
		states.configure(State.WAITING_SIDESHIFT).permit(Trigger.PICK_UP_PALLET,State.PICKED_UP_PALLET);		
		states.configure(State.WAITING_SIDESHIFT).permit(Trigger.DROP_PALLET,State.DROPPED_PALLET);
		states.configure(State.WAITING_SIDESHIFT).permit(Trigger.SET_SIDE_SHIFT,State.SETTING_SIDE_SHIFT);

		// Transitions from PickUpPalette
		states.configure(State.PICKED_UP_PALLET).permit(Trigger.SET_SIDE_SHIFT,State.SETTING_SIDE_SHIFT);
		states.configure(State.PICKED_UP_PALLET).permit(Trigger.GO_TO_LOCATION,State.GOING_TO_LOCATION);

		// Transitions from DropedPalette
		states.configure(State.DROPPED_PALLET).permit(Trigger.SET_SIDE_SHIFT,State.SETTING_SIDE_SHIFT);
		states.configure(State.DROPPED_PALLET).permit(Trigger.GO_TO_LOCATION,State.GOING_TO_LOCATION);
		
		//Configure actions
		states.configure(State.PICKED_UP_PALLET).onExit(callGoToLocationFromPickUpPallet);
		states.configure(State.DROPPED_PALLET).onExit(callGoToLocationFromDropPallet);
		
		states.configure(State.WAITING_MAST_ANGLE).onEntry(startTimerMastAngle);
		states.configure(State.WAITING_MAST_HEIGHT).onEntry(startTimerMastHeight);
		states.configure(State.WAITING_SIDESHIFT).onEntry(startTimerSideshift);
	}

	/**
	 * Changes the fork lift coordinates with the current measured coordinates, depending on what is received from other systems
	 * @param currentX The x value.
	 * @param currentY The y value.
	 * @param currentZ The z value.
	 */
	public void setForkliftCoordinates(int currentX, int currentY, int currentZ) {
		forklift.setCurrentXYZ(currentX, currentY, currentZ);
		moveStateMashine();
	}

	/**
	 * Changes the mast angle, with the new one. The ideal value is 0
	 * @param degrees Takes positive and negative
	 */
	public void setForkliftMastAngle(float degrees) {
		forklift.setCurrentMastDegrees(degrees);
		moveStateMashine();
	}

	/**
	 * Sets the height of the mast  
	 * @param height Can be negative
	 */
	public void setForkliftMastHeight(int height) {
		forklift.setCurrentMastHeight(height);
		moveStateMashine();
	}

	/**
	 * The command received from the listener 
	 * @param command Can be 0
	 */
	public void setCommand(int command) {
		this.command = command;
		moveStateMashine();
	}

	/**
	 * Updates the pallet coordinates to those received from the other systems. When all the translating values are 0, we assume there is no pallet found
	 * @param transX Translation value for X
	 * @param transY Translation value for Y
	 * @param transZ Translation value for Z
	 * @param rotX Rotation value for X
	 * @param rotY Rotation value for Y
	 */
	public void setCurrentPalletCoordinates(int transX, int transY, int transZ,float rotX, float rotY) 
	{	
		//We care about pallet info if the fork lift has to set the correct side shift
		if(states.isInState(State.SETTING_SIDE_SHIFT))
		{
			if(transX != 0 || transY != 0 || transZ != 0)
				detectedPallet = new Pallet(transX, transY, transZ, rotX, rotY);
			if(transX == 0 && transY == 0 && transZ == 0)
				detectedPallet = null;
		}
		
		if(states.isInState(State.PICKED_UP_PALLET) )
		{
			if(transX != 0 || transY != 0 || transZ != 0)
				detectedPallet = new Pallet(transX, transY, transZ, rotX, rotY);
			if(transX == 0 && transY == 0 && transZ == 0)
			{	
				forklift.setPallet(true);
				detectedPallet = null;
			}
		}

		
		if(states.isInState(State.DROPPED_PALLET))
			if(transX != 0 || transY != 0 || transZ != 0)
				forklift.setPallet(false);
		
		moveStateMashine();
	}

	/**
	 * Changes the instructions for the new ones. Updates current instruction as well.
	 * @param newInstructions
	 */
	public void setInstructions(Instruction[] newInstructions) {
		if(newInstructions != null)
		{
			instructions = new ArrayList<Instruction>(Arrays.asList(newInstructions));
			currentInstruction = instructions.size() > 0 ? instructions.remove(0) : null;
		}
		else
			instructions = new ArrayList<Instruction>();

	}

	/**
	 * 
	 * @param currentShelfNumber
	 */
	public void setCurrentShelfNumber(int currentShelfNumber) {
		if(currentShelfNumber != 0 && this.currentShelfNumber != currentShelfNumber)
			this.currentShelfNumber = currentShelfNumber;
		moveStateMashine();
	}

	/**
	 * Moves the state machine depending on the current state and the values given by the fork lift, the current instruction and pallet 
	 */
	private void moveStateMashine()
	{
		//Initial state
		if((states.isInState(State.IDLE) &&  this.command == 0))
			//Show the command of there is one	
			if(this.command != 0)
				states.fire(Trigger.SHOW_COMMAND);
			else
				//If not got to location
				states.fire(Trigger.GO_TO_LOCATION);

		//If going to location and found a command
		if(states.isInState(State.GOING_TO_LOCATION) && states.canFire(Trigger.SHOW_COMMAND))
			if(this.command != 0)
				states.fire(Trigger.SHOW_COMMAND);
		
		//If there showing a command an no longer a command change to go to location
		if(states.isInState(State.SHOWING_COMMAND) && states.canFire(Trigger.GO_TO_LOCATION))
			if(this.command == 0)
				states.fire(Trigger.GO_TO_LOCATION);
		
		//That means that the fork lift is driving 
		if(states.isInState(State.GOING_TO_LOCATION) && states.canFire(Trigger.GO_TO_SHELF))
			//It has an instruction where the shelf corresponds to the current one
			if(currentInstruction != null && currentShelfNumber == currentInstruction.getShelfNumber())
				states.fire(Trigger.GO_TO_SHELF);
	
		
		//That means that the forklift is in front of the correct shelf
		if(states.isInState(State.GOING_TO_SHELF) && states.canFire(Trigger.GO_TO_COLUMN))
			//The distance from the shelf has to be under the specified value
			if(hasCorrectDistanceFromShelf())
				states.fire(Trigger.GO_TO_COLUMN);
		
		//That means forklift is in front of the correct column and can set the mast angle
		if(states.isInState(State.GOING_TO_COLUMN) && states.canFire(Trigger.SET_MAST_ANGLE))
			//Only look for the row if it is on the correct column
			if(isCorrectColumn() && hasCorrectDistanceFromShelf())
				states.fire(Trigger.SET_MAST_ANGLE);

		//The fork lift is positioned on the correct column with the correct mast angle and now checks for the height
		if(states.isInState(State.SETTING_MAST_ANGLE) && states.canFire(Trigger.WAIT_MAST_ANGLE))
			if(forklift.isCorrectMastAngle() && hasCorrectDistanceFromShelf() && isCorrectColumn())
				states.fire(Trigger.WAIT_MAST_ANGLE);
		
		//The fork lift is with the correct angle, and now it must position the correct height
		if(states.isInState(State.SETTING_MAST_HEIGHT) && (states.canFire(Trigger.WAIT_MAST_HEIGHT)))
			if(isCorrectMastHeight() && hasCorrectDistanceFromShelf() && isCorrectColumn() /*&& forklift.isCorrectMastAngle()*/)
				states.fire(Trigger.WAIT_MAST_HEIGHT);
		
		//The fork is correctly placed before the location
		if(states.isInState(State.SETTING_SIDE_SHIFT) && (states.canFire(Trigger.WAIT_SIDESHIFT)))
			if(isCorrectSideShift() && hasCorrectDistanceFromShelf() && isCorrectColumn() /*&& forklift.isCorrectMastAngle() && isCorrectMastHeight()*/)
				states.fire(Trigger.WAIT_SIDESHIFT);
				/**if(currentInstruction.getType().equals(Instruction.PICK_UP))
					states.fire(Trigger.PICK_UP_PALLET);
				else if(currentInstruction.getType().equals(Instruction.DROP))
					states.fire(Trigger.DROP_PALLET);**/
		
		//The fork lift is with the correct angle, and height and picks up the pallet
		if(states.isInState(State.PICKED_UP_PALLET) && states.canFire(Trigger.GO_TO_LOCATION))
			if(forklift.hasPallet() && forklift.getCurrentZ() > 1200)
				states.fire(Trigger.GO_TO_LOCATION);
		
		//The fork lift is with the correct angle, and height and picks up the pallet
		if(states.isInState(State.DROPPED_PALLET) && states.canFire(Trigger.GO_TO_LOCATION))
			if(!forklift.hasPallet())
				states.fire(Trigger.GO_TO_LOCATION);
	
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns true if the fork lift is in front of the shelf closer than DESIRED_DISTANCE_FROM_SHELF
	 * @return false, otherwise
	 */
	private boolean hasCorrectDistanceFromShelf() {
		return forklift.getCurrentZ() < desiredRangeFromShelf;
	}

	/**
	 * Returns true if the fork lift is in front of the right column, calculated from the current X coordinate and the column on the instruction
	 * @return false otherwise
	 */
	private boolean isCorrectColumn() {
		return shelfs.get(currentShelfNumber-1).isCorrectColumn(currentInstruction.getColumn(), forklift.getCurrentX());
	}

	/**
	 * Returns if the desired side shift is with in the DELTA_SIDE_SHIFT value 
	 * @return true if it is with in tolerance
	 */
	public boolean isCorrectSideShift() {
		return Math.abs(getDesiredSideShift()) < deltaSideShift;
	}

	/**
	 * Returns if the desired mast height is with in the MAST_HEIGHT_DELTA value 
	 * @return true if it is with in tolerance
	 */
	public boolean isCorrectMastHeight() {
		int sh = shelfs.get(currentShelfNumber-1).getDesiredMastHeight(currentInstruction.getRow(),forklift.hasPallet());
		int fh = forklift.getCurrentMastHeight();
		System.out.println("Current mast height: " + fh + " Expected: " + sh);
		return Math.abs(sh-fh) <= deltaMastHeight;
	}

	/*
	 * TODO: Remove - Just for testing
	 */
	public static void main(String[] args) {

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(Steuerhebel.CONFIG_FILE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Warehouse wh = new Warehouse(properties);
		wh.setInstructions(Steuerhebel.loadInstructions());
		System.out.println(wh.states.getState());
		wh.setForkliftMastAngle(0);
		System.out.println(wh.states.getState());
		wh.setCommand(1);
		System.out.println(wh.states.getState());
		wh.setCommand(0);
		System.out.println(wh.states.getState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.states.getState());
		wh.setForkliftCoordinates(101, 50, 3000);
		wh.setCurrentShelfNumber(1);
		System.out.println(wh.states.getState());
		wh.setForkliftCoordinates(101, 50, 1000);
		System.out.println(wh.states.getState());
		wh.setForkliftCoordinates(1400, 50, 1000);
		System.out.println(wh.states.getState());
		wh.setForkliftMastAngle(0);
		System.out.println(wh.states.getState());
		wh.setForkliftMastHeight(2895);
		System.out.println(wh.states.getState());
		wh.setCurrentPalletCoordinates(40, 0, 0, 0, 0);
		System.out.println(wh.states.getState());
		wh.setCurrentPalletCoordinates(1, 0, 0, 0, 0);
		System.out.println(wh.states.getState());
		wh.setCurrentPalletCoordinates(0, 0, 0, 0, 0);
		System.out.println(wh.states.getState());
		wh.setForkliftMastHeight(1595);
		
		
		wh.setCommand(2);
		System.out.println(wh.states.getState());
		wh.setCommand(0);
		System.out.println(wh.states.getState());
		wh.setForkliftMastHeight(305);
		System.out.println(wh.states.getState());
		wh.setForkliftMastHeight(205);
		System.out.println(wh.states.getState());
		wh.setForkliftMastHeight(175);
		System.out.println(wh.states.getState());		
		wh.setForkliftMastAngle(0);
		System.out.println(wh.states.getState());
		wh.setForkliftCoordinates(101, 50, 1000);
		System.out.println(wh.states.getState());
		wh.setForkliftCoordinates(1400, 50, 1000);
		System.out.println(wh.states.getState());		
		wh.setCurrentPalletCoordinates(1, 1, 1, 0, 0);
		System.out.println(wh.states.getState());
		
		
		wh.setForkliftMastAngle(0);
		System.out.println(wh.states.getState());
		wh.setCommand(1);
		System.out.println(wh.states.getState());
		wh.setCommand(0);
		System.out.println(wh.states.getState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.states.getState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.states.getState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.states.getState());
	}
	
	/**
	 * A little house keeping for when the state machine will be executed next
	 */
	private void goToLocationFromPickUpPalletAction() 
	{
		detectedPallet = null;
		currentInstruction = instructions.size() > 0 ? instructions.remove(0) : null;
	}
	
	/**
	 * Changes the current instruction to the next, if there is one
	 */
	private void goToLocationFromDropPalletAction() 
	{
		currentInstruction = instructions.size() > 0 ? instructions.remove(0) : null;
	}
	
	/**
	 * Returns the current state of the state machine
	 * @return Always a valid state
	 */
	public State getCurrentState() {
		return states.getState();
	}

	/**
	 * Returns the current instruction. 
	 * @return Can be null
	 */
	public Instruction getCurrentInstruction() {
		return currentInstruction;
	}

	/**
	 * Returns the current angle of the fork lift's mast
	 * @return Can be negative
	 */
	public float getCurrentForkliftMastAngle() {
		return forklift.getCurrentMastAngle();
	}

	/**
	 * Returns the current height of the fork lift's mast
	 * @return
	 */
	public int getCurrentForkliftMastHeight() {
		return forklift.getCurrentMastHeight();
	}

	/**
	 * Returns the desired mast height depending on where the mast should be, given the current instruction.
	 * Needs a valid current instruction
	 * @return 0 if no instruction
	 */
	public int getCurrentDesiredMastHeight() {
		return shelfs.get(currentShelfNumber-1) != null ?shelfs.get(currentShelfNumber-1).getDesiredMastHeight(currentInstruction.getRow(),forklift.hasPallet()) : 0;
	}

	/**
	 * Returns the desired side shift. Depends on if the fork lift has detected a pallet or just the shelf. 
	 * @return 0 if no pallet or shelf detected
	 */
	public int getDesiredSideShift() 
	{
		int shift = 0;
		//Side shift depends on if is calculated when there is pallet
		if(detectedPallet!=null)
		{
			//Calculate with pallet
			int sign = Integer.compare(detectedPallet.getCurrentX(),desiredPalletRelativeDistance);
			int value = Math.abs(desiredPalletRelativeDistance - detectedPallet.getCurrentX());
			shift = sign * value;
			System.out.println("Sideshift from pallet: " + shift + " From current "  + detectedPallet.getCurrentX() + " expected " + desiredPalletRelativeDistance);
		}
		else
		{
			int desiredDropPoint = shelfs.get(currentShelfNumber-1).getDesiredDropPoint(currentInstruction.getColumn());
			int sign = Integer.compare(forklift.getCurrentX(),desiredDropPoint);
			int value = Math.abs(forklift.getCurrentX()-desiredDropPoint);
			shift = sign * value;
			System.out.println("Sideshift from fork lift: " + shift + " From current "  + forklift.getCurrentX() + " expected " + desiredDropPoint);
		}
		return shift;
	}

	/**
	 * Returns if the fork lift has a pallet
	 * @return false, when no pallet detected
	 */
	public boolean forkliftHasPallet() {
		return forklift.hasPallet();
	}

	/**
	 * Returns the detected immersion of the fork lift
	 * @return Can be negative
	 */
	public int getCurrentImmersion()
	{
		int returnValue = 0;
		if(states.isInState(State.PICKED_UP_PALLET))
		{
			currentImmersionPickupPallet = currentImmersionPickupPallet  + (pickUp ? -10 : 10);
			if(!pickUp)
				if(currentImmersionPickupPallet == 1000)
					pickUp = true;
			
			if(pickUp && currentImmersionPickupPallet == -400)
				pickUp = false;
			
			returnValue = currentImmersionPickupPallet;
		}
		
		if(states.isInState(State.DROPPED_PALLET))
		{
			currentImmersionDropPallet = currentImmersionDropPallet  + (dropedOff ? 10 : -10);
			if(dropedOff)
				if(currentImmersionDropPallet == 1000)
					dropedOff = false;
			
			if(!dropedOff && currentImmersionDropPallet == -400)
				dropedOff = true;
			returnValue = currentImmersionDropPallet;
		}
		
		/**if(states.isInState(State.PICKED_UP_PALLET))
			currentImmersion = currentImmersion == 1000 ? -400 : currentImmersion + 10;  
		if(states.isInState(State.DROPPED_PALLET))
			currentImmersion = currentImmersion == -1000 ? 400 : currentImmersion-10;**/
		return returnValue;
	}

	/**
	 * Returns the current detected command
	 * @return 0 if no command detected
	 */
	public int getCommand() {
		return command;
	}	
	

	private void startTimerMastAngle(final Trigger forwardTrigger, final Trigger backwardTrigger) {	
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	if(forklift.isCorrectMastAngle())
		        			states.fire(forwardTrigger);
		        		else
		        			states.fire(backwardTrigger);
		            	System.out.println("Trigger state " + states.getState());
		            }
		        }, 
		        waitTime 
		);
	}
	
	private void startTimerMastHeight(final Trigger forwardTrigger, final Trigger backwardTrigger) {	
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	if(isCorrectMastHeight())
		        			states.fire(forwardTrigger);
		        		else
		        			states.fire(backwardTrigger);
		            	System.out.println("Trigger state " + states.getState());
		            	pickUp = false;
		            }
		        }, 
		        waitTime 
		);
	}
	
	private void startTimerSideshift(final Trigger forwardTrigger, final Trigger backwardTrigger) {	
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	if(isCorrectSideShift())
		        			states.fire(forwardTrigger);
		        		else
		        			states.fire(backwardTrigger);
		            	System.out.println("Trigger state " + states.getState());
		            	pickUp = false;
		            }
		        }, 
		        waitTime 
		);
	}
	
	Action callGoToLocationFromPickUpPallet = new Action() 
	{
        @Override
        public void doIt() 
        {
                goToLocationFromPickUpPalletAction();
        }

	};

	Action callGoToLocationFromDropPallet = new Action() 
	{
        @Override
        public void doIt() {
                goToLocationFromDropPalletAction();
        }

	};
	
	Action startTimerMastAngle = new Action()
	{
        @Override
        public void doIt() 
        {	
        	startTimerMastAngle(Trigger.SET_MAST_HEIGHT,Trigger.SET_MAST_ANGLE);
        }
	};	
	
	Action startTimerMastHeight = new Action()
	{
        @Override
        public void doIt() 
        {	
        	startTimerMastHeight(Trigger.SET_SIDE_SHIFT,Trigger.SET_MAST_HEIGHT);
        }
	};
	
	Action startTimerSideshift = new Action()
	{
        @Override
        public void doIt() 
        {	
        	startTimerSideshift(currentInstruction.getType().equals(Instruction.PICK_UP) ? Trigger.PICK_UP_PALLET : Trigger.DROP_PALLET ,Trigger.SET_SIDE_SHIFT);
        }
	};

	public boolean isCorrectMastAngle() {
		return forklift.isCorrectMastAngle();
	}

	public boolean getShowPickUp() {
		return pickUp;
	}

	public boolean getShowDrop() {
		return dropedOff;
	}	

	
}
