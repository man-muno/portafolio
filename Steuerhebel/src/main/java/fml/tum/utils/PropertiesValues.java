package fml.tum.utils;

public class PropertiesValues {
	
	/**
	 * Host used for DSA communication
	 */
	public static final String DSA_HOST = "dsa.host";
	
	/**
	 * Port used for DSA communication
	 */
	public static final String DSA_PORT = "dsa.port";

	
	/**
	 * Acceptable delta for correct sideshift. In mm
	 */	
	public static final String SIDE_SHIFT_DELTA = "warehouse.side.shift.delta";

	/**
	 * Defines the value acceptable for the distance of the pallet
	 */
	public static final String DESIRED_PALLET_RELATIVE_DISTANCE = "warehouse.pallet.desired.relative.distance";
			
	/**
	 *The desired distance of the fork lift in front of the shelf 
	 */
	public static final String DESIRED_RANGE_FROM_SHELF = "warehouse.shelf.desired.distance.range";

	/**
	 * Acceptable delta for correct mast height in mm 
	 */
	public static final String MAST_HEIGHT_DELTA = "warehouse.mast.height.delta";
	
	/**
	 * Correct angle for the mast  
	 */
	public static final String CORRECT_MAST_ANGLE = "warehouse.mast.angle.correct";
	
	/**
	 * Delta for the correct mast angle
	 */
	public static final String CORRECT_MAST_ANGLE_DELTA = "warehouse.mast.angle.delta";
	
	/**
	 * Port of the MQTT broker
	 */
	public static final String MQTT_PORT = "mqtt.port";
	
	/**
	 * Host of the MQTT broker
	 */
	public static final String MQTT_HOST = "mqtt.host";

	/**
	 * Host of the laser projector
	 */
	public static final String PROJETOR_HOST = "projector.host";

	/**
	 * Port of the laser projector
	 */
	public static final String PROJETOR_PORT = "projector.port";

	/**
	 * Wait time for the states
	 */
	public static final String WAIT_TIME = "warehouse.wait.time";
	
	
	public static final String PROJECTOR_IMMERSION_DEPTH_X = "projector.immersion.depth.x";
	public static final String PROJECTOR_IMMERSION_DEPTH_Y ="projector.immersion.depth.y";

	public static final String PROJECTOR_SIDE_DISPLACEMENT_X = "projector.side.displacement.x";
	public static final String PROJECTOR_SIDE_DISPLACEMENT_Y="projector.side.displacement.y";

	public static final String PROJECTOR_MAST_ANGLE_X="projector.mast.angle.x";
	public static final String PROJECTOR_MAST_ANGLE_Y="projector.mast.angle.y";

	public static final String PROJECTOR_MAST_HEIGHT_X = "projector.mast.height.x";
	public static final String PROJECTOR_MAST_HEIGHT_Y = "projector.mast.height.y";
	
	public static final String PROJECTOR_INSTRUCTOR_X = "projector.instruction.x";
	public static final String PROJECTOR_INSTRUCTOR_Y = "projector.instruction.y";
	
	public static final String PROJECTOR_COMMAND_X = "projector.command.x";
	public static final String PROJECTOR_COMMAND_Y = "projector.command.y";
	
	public static String DESIRED_MAST_HEIGHT_EMPTY_A = "warehouse.desired.mastheight.empty.row.a";
	public static String DESIRED_MAST_HEIGHT_EMPTY_B = "warehouse.desired.mastheight.empty.row.b";
	public static String DESIRED_MAST_HEIGHT_EMPTY_C = "warehouse.desired.mastheight.empty.row.c";

	public static String DESIRED_MAST_HEIGHT_LOADED_A = "warehouse.desired.mastheight.loaded.row.a";
	public static String DESIRED_MAST_HEIGHT_LOADED_B = "warehouse.desired.mastheight.loaded.row.b";
	public static String DESIRED_MAST_HEIGHT_LOADED_C = "warehouse.desired.mastheight.loaded.row.c";
	
	public static String DESIRED_GAP_DELTA = "warehouse.column.desired.drop.delta";
	public static String FIST_COLUMN_DESIRED_DROP_POINT = "warehouse.column.1.desired.drop.point";
	public static String SECOND_COLUMN_DESIRED_DROP_POINT = "warehouse.column.2.desired.drop.point";
	public static String THIRD_COLUMN_DESIRED_DROP_POINT = "warehouse.column.3.desired.drop.point";
			
}
