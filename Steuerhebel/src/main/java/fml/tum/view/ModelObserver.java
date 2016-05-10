package fml.tum.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;

import fml.tum.model.Warehouse;
import fml.tum.model.statemachine.State;
import fml.tum.msh.Steuerhebel;
import fml.tum.utils.PropertiesValues;

public class ModelObserver implements Observer{

	/**
	 * Model to be shown by the views
	 */
	private Warehouse warehouse;
	
	/**
	 * The instance of the laser projector
	 */
	private LaserProjector laserProjector;
	
	
	private static final int ANIMATION_DELTA = 5;
	
	/**
	 * Starts the viewers instance 
	 * @param warehouse Must not be null
	 * @param properties 
	 */
	public ModelObserver(Warehouse warehouse, Properties properties) {
		this.warehouse = warehouse;
		laserProjector = new LaserProjector(properties);
	}
	
	@Override
	/**
	 * The warehouse parameter is not needed as it is one of the attributes
	 */
	public void update(Observable receivedWarehouse, Object arg) 
	{
		State state = warehouse.getCurrentState();
		System.out.println(state);
		if (state.equals(State.IDLE))
		{
			//Show Instruction
			laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
		}
		else if (state.equals(State.GOING_TO_LOCATION))
		{
			//Show Instruction
			laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
		}
		else if (state.equals(State.SHOWING_COMMAND))
		{
			//Show Instruction
			laserProjector.send(laserProjector.buildCommandMessage(warehouse.getCommand(), warehouse.getCurrentInstruction()));
		}
		else if (state.equals(State.GOING_TO_SHELF))
		{
			//Show Instruction
			laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
		}
		else if (state.equals(State.GOING_TO_COLUMN))
		{
			//Show Instruction
			laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
		}
		else if (state.equals(State.SETTING_MAST_ANGLE) || state.equals(State.WAITING_MAST_ANGLE))
		{
			//show mast angle
			laserProjector.send(laserProjector.buildMastAngleMessage((int)warehouse.getCurrentForkliftMastAngle(), true, warehouse.isCorrectMastAngle()));
		}
		else if (state.equals(State.SETTING_MAST_HEIGHT) || state.equals(State.WAITING_MAST_HEIGHT))
		{
			//Show mast height
			laserProjector.send(laserProjector.buildMastHeightMessage(warehouse.getCurrentForkliftMastHeight(), warehouse.getCurrentDesiredMastHeight(), true, warehouse.isCorrectMastHeight(),warehouse.forkliftHasPallet()));
		}
		else if (state.equals(State.SETTING_SIDE_SHIFT))
		{
			//Show Seitenverschiebung
			int displacement = warehouse.getDesiredSideShift();
			laserProjector.send(laserProjector.buildSideDisplacementMessage(displacement, true, warehouse.isCorrectSideShift()));
			
		}
		else if (state.equals(State.PICKED_UP_PALLET))
		{
			//Show Instruction
			//laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
			//Animation
			int immersion = warehouse.getCurrentImmersion();
			int module = warehouse.getShowPickUp() ? LaserProjector.EINTAUCHTIEFE_BELADEN_MODUL_ID : LaserProjector.EINTAUCHTIEFE_LEER_MODUL_ID;  
			int arrow = warehouse.getShowPickUp() ? -2000 : 2000;
			laserProjector.send(laserProjector.buildImmersionDepthMessage(module,immersion, arrow, warehouse.forkliftHasPallet(), true));
			
		}
		else if (state.equals(State.DROPPED_PALLET))
		{
			//Show Instruction
			//laserProjector.send(laserProjector.buildInstructionMessage(warehouse.getCurrentInstruction()));
			//Animation
			int immersion = warehouse.getCurrentImmersion();
			int module = warehouse.getShowDrop() ? LaserProjector.EINTAUCHTIEFE_BELADEN_MODUL_ID : LaserProjector.EINTAUCHTIEFE_LEER_MODUL_ID;  
			int arrow = warehouse.getShowDrop() ? 2000 : -2000;
			laserProjector.send(laserProjector.buildImmersionDepthMessage(module,immersion, arrow, warehouse.forkliftHasPallet(), true));
		}
		
	}
	
	public static void main(String[] args) {
		


		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(Steuerhebel.CONFIG_FILE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Warehouse wh = new Warehouse(properties);
		wh.setInstructions(Steuerhebel.loadInstructions());
		System.out.println(wh.getCurrentState());
		wh.setForkliftMastAngle(0);
		System.out.println(wh.getCurrentState());
		wh.setCommand(1);
		System.out.println(wh.getCurrentState());
		wh.setCommand(0);
		System.out.println(wh.getCurrentState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.getCurrentState());
		wh.setForkliftCoordinates(101, 50, 3000);
		wh.setCurrentShelfNumber(1);
		System.out.println(wh.getCurrentState());
		wh.setForkliftCoordinates(101, 50, 1000);
		System.out.println(wh.getCurrentState());
		wh.setForkliftCoordinates(1400, 50, 1000);
		System.out.println(wh.getCurrentState());
		wh.setForkliftMastAngle(0);
		System.out.println(wh.getCurrentState());
		wh.setForkliftMastHeight(2925);
		System.out.println(wh.getCurrentState());
		wh.setCurrentPalletCoordinates(40, 0, 0, 0, 0);
		System.out.println(wh.getCurrentState());
		wh.setCurrentPalletCoordinates(1, 0, 0, 0, 0);
		System.out.println(wh.getCurrentState());
		
		
		int immersion = wh.getCurrentImmersion();
		int module = wh.getShowPickUp() ? LaserProjector.EINTAUCHTIEFE_BELADEN_MODUL_ID : LaserProjector.EINTAUCHTIEFE_LEER_MODUL_ID;  
		int arrow = wh.getShowPickUp() ? -2000 : 2000;
		
		/**for(int i = 0;i<2500;i++)
		{
			immersion = wh.getCurrentImmersion();
			module = wh.getShowPickUp() ? LaserProjector.EINTAUCHTIEFE_BELADEN_MODUL_ID : LaserProjector.EINTAUCHTIEFE_LEER_MODUL_ID;  
			arrow = wh.getShowPickUp() ? -2000 : 2000;
		}**/
		
		
		System.out.println(wh.getCurrentState());
		wh.setCurrentPalletCoordinates(0, 0, 0, 0, 0);
		System.out.println(wh.getCurrentState());
		wh.setForkliftMastHeight(1725);
		

		wh.setCommand(2);
		System.out.println(wh.getCurrentState());
		wh.setCommand(0);
		System.out.println(wh.getCurrentState());

		
		//wh.setForkliftMastHeight(2925);
		System.out.println(wh.getCurrentState());		
		wh.setForkliftMastAngle(0);
		System.out.println(wh.getCurrentState());
		
		for(int i = 0;i<2500;i++)
		{
			immersion = wh.getCurrentImmersion();
			module = wh.getShowDrop() ? LaserProjector.EINTAUCHTIEFE_BELADEN_MODUL_ID : LaserProjector.EINTAUCHTIEFE_LEER_MODUL_ID;  
			arrow = wh.getShowDrop() ? 2000 : -2000;  	
		}
		
		
		wh.setForkliftCoordinates(101, 50, 1000);
		System.out.println(wh.getCurrentState());
		wh.setForkliftCoordinates(1400, 50, 1000);
		System.out.println(wh.getCurrentState());		
		wh.setCurrentPalletCoordinates(1, 1, 1, 0, 0);
		System.out.println(wh.getCurrentState());
		
		
		
		wh.setForkliftMastAngle(0);
		System.out.println(wh.getCurrentState());
		wh.setCommand(1);
		System.out.println(wh.getCurrentState());
		wh.setCommand(0);
		System.out.println(wh.getCurrentState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.getCurrentState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.getCurrentState());
		wh.setCurrentShelfNumber(2);
		System.out.println(wh.getCurrentState());
		wh.setForkliftMastHeight(175);
	}
	
}
