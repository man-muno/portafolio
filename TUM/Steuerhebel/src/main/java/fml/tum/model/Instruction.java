package fml.tum.model;

public class Instruction {

	public static final String PICK_UP = "pickUp";
	public static final String DROP = "drop";
	public static final String DRIVE = "drive";
	
	public static final int ARROW_TYPE_NO_ARROW = 0;
	public static final int ARROW_TYPE_LINE = 1;
	public static final int ARROW_TYPE_OUTLINE = 2;
	public static final int ARROW_TYPE_SEGMENTED_OUTLINE = 3;
	
	public static final int DIRECTION_NO_DIRECTION = 0;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_STRAIGHT= 3;
	
	private String type = "";
	private int shelfNumber = 0;	
	private int column = 0;
	private char row;
	private int arrowType=0;
	private int direction=0;
	
	public int getArrowType() {
		return arrowType;
	}
	public String getType() {
		return type;
	}
	public int getShelfNumber() {
		return shelfNumber;
	}
	public int getColumn() {
		return column;
	}
	public char getRow() {
		return row;
	}
	public int getDirection() {
		return direction;
	}
	
}
