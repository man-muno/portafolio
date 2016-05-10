package fml.tum.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fml.tum.model.Instruction;
import fml.tum.model.Warehouse;
import fml.tum.msh.Steuerhebel;
import fml.tum.utils.DatagramSender;
import fml.tum.utils.PropertiesValues;

public class LaserProjector
{

	
	/******************************************************************************
	 * Constants for the projector size
	 *******************************************************************************/
	
	private static final int PROJECTOR_SCALE = 100;
	
	/******************************************************************************
	 * Identifiers for the header
	 *******************************************************************************/
	private static final byte VERSION = (byte)0b00000100;
	private static final byte MESSAGE_TYPE_LIST_MODULES = (byte)0b00001001;
	private static final byte MESSAGE_TYPE_CLEAR = (byte)0b00001000;

	/******************************************************************************
	 * Size of the packets
	 *******************************************************************************/
	private static final int HEADER_BYTE_SIZE = 29;
	private static final int MASTNEIGUNG_BYTE_SIZE = 29;
	private static final int HUBHOEHE_BYTE_SIZE = 29;
	private static final int SEITENVERSCHIEBUNG_BYTE_SIZE = 29;
	private static final int EINTAUCHTIEFE_BYTE_SIZE = 29;
	private static final int INSTRUCTION_BYTE_SIZE = 29;
	
	/******************************************************************************
	 * Identifiers for the modules
	 *******************************************************************************/
	private static final int MASTNEIGUNG_MODUL_ID = 10;
	private static final int HUBHOEHE_MODUL_EMPTY_ID = 20;
	private static final int HUBHOEHE_MODUL_LOADED_ID = 21;
	private static final int SEITENVERSCHIEBUNG_MODUL_ID = 30;
	public static final int EINTAUCHTIEFE_LEER_MODUL_ID = 40;
	public static final int EINTAUCHTIEFE_BELADEN_MODUL_ID = 41;
	private static final int NAVIGATION_MODUL_ID = 50;
	private static final int OBJECT_MODUL_ID = 60;
	
	/******************************************************************************
	 * Constants for the types of images that can be sent to the projector
	 *******************************************************************************/
	private static final int ARROW_TYPE_NO_ARROW = 0;
	private static final int ARROW_TYPE_LINE = 1;
	private static final int ARROW_TYPE_OUTLINE = 2;
	private static final int ARROW_TYPE_SEGMENTED_OUTLINE = 3;

	private static final int SYMBOL_NO_SYMBOL = 0;
	private static final int SYMBOL_OK = 1;
	private static final int SYMBOL_WARNING= 2;
	
	private static final int DIRECTION_NO_DIRECTION = 0;
	private static final int DIRECTION_RIGHT = 1;
	private static final int DIRECTION_LEFT = 2;
	private static final int DIRECTION_STRAIGHT= 3;
	
	
	/**
	 * Host where the projector runs
	 */
	private String host;

	/**
	 * Port where the projector runs
	 */
	private int port;
	
	/**
	 * Execution pool
	 */
	private ExecutorService pool;

	private int sideDisplacementX;

	private int sideDisplacementY;

	private int mastAngleX;

	private int mastAngleY;

	private int mastHeightX;

	private int mastHeightY;

	private int immersionDepthX;

	private int immersionDepthY;

	private int instructionX;

	private int instructionY;

	private int commandX;

	private int commandY;

	public LaserProjector(Properties properties) {		
		
		pool = Executors.newFixedThreadPool(50);
		this.host = properties.getProperty(PropertiesValues.PROJETOR_HOST);
		this.port =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJETOR_PORT));
		this.sideDisplacementX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_SIDE_DISPLACEMENT_X));
		this.sideDisplacementY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_SIDE_DISPLACEMENT_Y));
		this.mastAngleX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_MAST_ANGLE_X));
		this.mastAngleY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_MAST_ANGLE_Y));
		this.mastHeightX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_MAST_HEIGHT_X));
		this.mastHeightY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_MAST_HEIGHT_Y));
		this.immersionDepthX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_IMMERSION_DEPTH_X));
		this.immersionDepthY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_IMMERSION_DEPTH_Y));
		this.instructionX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_INSTRUCTOR_X));
		this.instructionY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_INSTRUCTOR_Y));
		this.commandX =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_COMMAND_X));
		this.commandY =  Integer.parseInt(properties.getProperty(PropertiesValues.PROJECTOR_COMMAND_Y));
		
	}


	/**
	 * Creates a new thread and sends the buffer to the projector
	 * @param buffer
	 */
	public void send(ByteBuffer buffer)
	{
		//new Thread(new DatagramSender(host,port,buffer)).start();
		pool.execute(new DatagramSender(host,port,buffer));
	}

	
	public ByteBuffer buildImmersionDepthMessage(int moduleId,int currentPosition,int desiredPosition,  boolean hasPallet, boolean toShow)
	{
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+EINTAUCHTIEFE_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildImmersionDepthModuleData( moduleId,buffer, currentPosition,desiredPosition, hasPallet, toShow);
		buffer.flip();
		return buffer;
	}
	
	public ByteBuffer buildSideDisplacementMessage(int idealPosition, boolean toShow, boolean isCorrect)
	{
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+SEITENVERSCHIEBUNG_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildSideDisplacementModuleData(buffer, idealPosition, toShow, isCorrect);
		buffer.flip();
		return buffer;
	}

	public ByteBuffer buildMastAngleMessage(int mastDegrees, boolean toShow, boolean correct)
	{
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+MASTNEIGUNG_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildMastAngleModuleData(buffer, mastDegrees, toShow, correct);
		buffer.flip();
		return buffer;
	}

	public ByteBuffer buildMastHeightMessage(int currentMastHeight,int desiredMastHeight, boolean toShow, boolean correct, boolean isLoaded)
	{
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+HUBHOEHE_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildMastHeightModuleData(buffer, currentMastHeight,desiredMastHeight , toShow, correct, isLoaded);
		buffer.flip();
		return buffer;
	}	
	
	
	private void buildImmersionDepthModuleData(int moduleId, ByteBuffer buffer,	int currentPosition,int desiredPosition, boolean hasPallet, boolean toShow) {
		byte dummyByte = (byte)0;
		//We send only one module
		buffer.put((byte)1);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);	
		buffer.putInt(moduleId);
		buffer.putInt(immersionDepthX);
		buffer.putInt(immersionDepthY);
		buffer.putInt(PROJECTOR_SCALE);
		System.out.println("Current " + currentPosition + " desired " + desiredPosition );
		buffer.putInt(currentPosition);
		buffer.putInt(desiredPosition);
		int trueByte = toShow ? 1 : 0;
		buffer.putInt(trueByte);
		//The protocol has 2 bytes at the end that are not in used
		
	}
	
	private void buildSideDisplacementModuleData(ByteBuffer buffer,	int idealPosition, boolean toShow, boolean isCorrect ) {
		byte dummyByte = (byte)0;
		//We send only one module
		buffer.put((byte)1);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);	
		buffer.putInt(SEITENVERSCHIEBUNG_MODUL_ID);
		buffer.putInt(sideDisplacementX);
		buffer.putInt(sideDisplacementY);
		buffer.putInt(PROJECTOR_SCALE);
		buffer.putInt(idealPosition);
		buffer.put(dummyByte);
		int trueByte = toShow ? 1 : 0;
		buffer.putInt(trueByte);
		trueByte =  0;
		buffer.putInt(trueByte);
		trueByte = isCorrect ? 1 : 0;
		buffer.putInt(trueByte);
	}
	
	private void buildMastAngleModuleData(ByteBuffer buffer, int angle, boolean toShow, boolean correct) 
	{
		byte dummyByte = (byte)0;
		//We send only one module
		buffer.put((byte)1);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);	
		buffer.putInt(MASTNEIGUNG_MODUL_ID);
		buffer.putInt(mastAngleX);
		buffer.putInt(mastAngleY);
		buffer.putInt(PROJECTOR_SCALE);
		buffer.putInt(angle);
		buffer.put(dummyByte);
		int trueByte = toShow ? 1 : 0;
		buffer.putInt(trueByte);
		buffer.putInt(0);
		trueByte = correct ? 1 : 0;
		buffer.putInt(trueByte);
	}
	
	private void buildMastHeightModuleData(ByteBuffer buffer, int currentMastHeight,int desiredMastHeight, boolean toShow, boolean correct, boolean isLoaded) 
	{
		byte dummyByte = (byte)0;
		//We send only one module
		buffer.put((byte)1);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);	
		buffer.putInt(isLoaded ? HUBHOEHE_MODUL_LOADED_ID : HUBHOEHE_MODUL_EMPTY_ID);
		buffer.putInt(mastHeightX);
		buffer.putInt(mastHeightY);
		buffer.putInt(PROJECTOR_SCALE);
		buffer.putInt(currentMastHeight);
		buffer.putInt(desiredMastHeight);
		int trueByte = toShow ? 1 : 0;
		buffer.putInt(trueByte);
		buffer.putInt(trueByte);
		trueByte = correct ? 1 : 0;
		buffer.putInt(trueByte);
	}

	private void buildHeader(ByteBuffer buffer) 
	{
		buffer.put(VERSION);
		buffer.put(MESSAGE_TYPE_LIST_MODULES);
		byte dummyByte = (byte)0;
		//Put 2 dummy bytes
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		//Put timestamp
		putTimeStamp(buffer);
		//Put 4 dummy bytes
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);		
	}

	private void putTimeStamp(ByteBuffer buffer) 
	{
		buffer.putLong(System.currentTimeMillis());
	}
	

	
	public ByteBuffer buildInstructionMessage(Instruction currentInstruiction) {
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+INSTRUCTION_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildInstructionModuleData(buffer, currentInstruiction);
		buffer.flip();
		return buffer;
	}


	private void buildInstructionModuleData(ByteBuffer buffer, Instruction currentInstruction) {
		byte dummyByte = (byte)0;
		//We send only one module
		buffer.put((byte)1);
		buffer.put(dummyByte);
		buffer.put(dummyByte);
		buffer.put(dummyByte);	
		buffer.putInt(NAVIGATION_MODUL_ID);
		buffer.putInt(instructionX);
		buffer.putInt(instructionY);
		buffer.putInt(PROJECTOR_SCALE);
		int arrowType=concatenateInts(getArrowType(currentInstruction), SYMBOL_NO_SYMBOL);
		buffer.putInt(arrowType);
		int direction = getDirection(currentInstruction);
		buffer.putInt(direction);
		buffer.putInt(0);
		int objective = concatenateInts(currentInstruction.getShelfNumber(),getRowNumber(currentInstruction.getRow()),currentInstruction.getColumn());
		buffer.putInt(objective);
	
		//The protocol has 1 byte at the end that is not in used
		
	}



	private int getRowNumber(char row) {
		return (int )row - 64;
	}


	private int getDirection(Instruction currentInstruction) {
		int direction = 0;
		switch (currentInstruction.getDirection()) {
		case Instruction.DIRECTION_NO_DIRECTION:
			direction = DIRECTION_NO_DIRECTION;
			break;
		case Instruction.DIRECTION_RIGHT:
			direction = DIRECTION_RIGHT;
			break;
		case Instruction.DIRECTION_LEFT:
			direction = DIRECTION_LEFT;
			break;
		case Instruction.DIRECTION_STRAIGHT:
			direction = DIRECTION_STRAIGHT;
			break;
		}
		return direction;
	}


	private int getArrowType(Instruction currentInstruiction) {
		int arrowType = 0;
		switch (currentInstruiction.getArrowType()) {
		case Instruction.ARROW_TYPE_NO_ARROW:
			arrowType = ARROW_TYPE_NO_ARROW;
			break;
		case Instruction.ARROW_TYPE_LINE:
			arrowType = ARROW_TYPE_LINE;
			break;
		case Instruction.ARROW_TYPE_OUTLINE:
			arrowType = ARROW_TYPE_OUTLINE;
			break;
		case Instruction.ARROW_TYPE_SEGMENTED_OUTLINE:
			arrowType = ARROW_TYPE_SEGMENTED_OUTLINE;
			break;
		}
		return arrowType;
	}

	public ByteBuffer buildCommandMessage(int command, Instruction currentInstruction) {
		ByteBuffer buffer = ByteBuffer.allocate(HEADER_BYTE_SIZE+INSTRUCTION_BYTE_SIZE);
		buffer.order(ByteOrder.BIG_ENDIAN);
		buffer.clear();
		buildHeader(buffer);
		buildCommandModuleData(buffer, command, currentInstruction);
		buffer.flip();
		return buffer;
	}


	private void buildCommandModuleData(ByteBuffer buffer, int command, Instruction currentInstruction) {

		byte dummyByte = (byte)0;
		//first check if the command is for the navigation module or the object module
		if(command / 10000 == 99)
		{
			//Starts with 99 it is an object command
			int sendCommand = command % 10000; 
			buffer.put((byte)1);
			buffer.put(dummyByte);
			buffer.put(dummyByte);
			buffer.put(dummyByte);	
			buffer.putInt(OBJECT_MODUL_ID);
			buffer.putInt(commandX);
			buffer.putInt(commandY);
			buffer.putInt(PROJECTOR_SCALE);
			buffer.putInt(sendCommand);
		}
		else 
		{
			buffer.put((byte)1);
			buffer.put(dummyByte);
			buffer.put(dummyByte);
			buffer.put(dummyByte);	
			buffer.putInt(NAVIGATION_MODUL_ID);
			buffer.putInt(commandX);
			buffer.putInt(commandY);
			buffer.putInt(PROJECTOR_SCALE);
			int arrowType=(int)command/10;
			buffer.putInt(arrowType);
			int direction = command % 10;
			buffer.putInt(direction);
			buffer.putInt(0);
			int objective = concatenateInts(currentInstruction.getShelfNumber(),getRowNumber(currentInstruction.getRow()),currentInstruction.getColumn());
			buffer.putInt(objective);	
		}	
		//The protocol has 1 byte at the end that is not in used
	}
	
	private int concatenateInts(int...numbers)
	{
		int concat = 0;
		for(int i=0;i<numbers.length;i++)
			concat += numbers[numbers.length - i-1] * ((int)Math.pow(10, i));

		return concat;
	}
}
