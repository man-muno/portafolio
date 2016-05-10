package de.visiom.carpc.services.navigation.util;

public enum ManeuverType {
    NO_TURN (0, "None", "" ,""),
    ARRIVE_INTERMEDIATE_STOP(0,"ArriveIntermediateStop","",""),
    DEPART_INTERMEDIATE_STOP(0,"DepartIntermediateStop","",""),
    ARRIVE_FINISH(0,"ArriveFinish","Ankunft", "in"),
    
    DEPART_INTERMEDIATE_STOP_RETURNING(0,"DepartIntermediateStopReturning","", ""),
    NONE(0,"None","", ""),
    TAKE(0,"Take","", ""),
    ENTER_THEN_EXIT_ROUNDABOUT(0,"EnterThenExitRoundabout","Nehmen Sie im Kreisverkehr die Ausfahrt", "auf"),
    ROAD_NAME_CHANGE(0,"RoadNameChange","", ""),
    BEAR_THEN_KEEP(0,"BearThenKeep","", ""),
    BEAR_THEN_MERGE(0,"BearThenMerge","", ""),
    TAKE_TRANSIT(0,"TakeTransit","", ""),
    TRANSFER(0,"Transfer","", ""),
    TRANSIT_ARRIVE(0,"TransitArrive","", ""),
    TRANSIT_DEPART(0,"TransitDepart","", ""),
    TURN_BACK(0,"TurnBack","", ""),
    TURN_BACK_THEN_MERGE(0,"TurnThenMerge","", ""),
    UNKNOWN(0,"Unknown","", ""),
    UTURN(0,"UTurn","", ""),
    WAIT(0,"Wait","", ""),
    WALK(0,"Walk","", ""),

    STRIGHT (1, "Continue","Weiter","auf"),
    DEPART_START(1,"DepartStart","Starten", "nach"),
    KEEP_STRAIGHT(1, "KeepStraight","Weiter","auf"),
    KEEP_ON_RAMP_STRAIGTH(1, "KeepOnRampStraight","Weiter","auf"),
    KEEP_TO_STAY_STRAIGHT(1, "KeepToStayStraight","Weiter","auf"),
    RAMP_THEN_HIGHWAY_STRAIGHT(1, "RampThenHighwayStraight","Weiter","auf"),
    TAKE_RAMP_STRAIGHT(1, "TakeRampStraight","Weiter","auf"),
    
    LEFT_TURN(2, "TurnLeft", "Abbiegen Sie Links","auf"),
    BEAR_LEFT_THEN_LEFT(2, "BearLeftThenBearLeft", "Abbiegen Sie Links","auf"),
    BEAR_LEFT_THEN_RIGHT(2, "BearLeftThenBearRight", "Abbiegen Sie Links","auf"),
    BEAR_LEFT_THEN_TURN_LEFT(2, "BearLeftThenTurnLeft", "Abbiegen Sie Links","auf"),
    BEAR_LEFT_THEN_TURN_RIGHT(2, "BearLeftThenTurnRight", "Abbiegen Sie Links","auf"),
    KEEP_ON_RAMP_LEFT(2, "KeepOnRampLeft", "Abbiegen Sie Links","auf"),
    RAMP_THEN_HIGHWAY_LEFT(2, "RampThenHighwayLeft", "Abbiegen Sie Links","auf"),
    TAKE_RAMP_LEFT(2, "TakeRampLeft", "Abbiegen Sie Links","auf"),
    TURN_LEFT_THEN_BEAR_LEFT(2, "TurnLeftThenBearLeft", "Abbiegen Sie Links","auf"),
    TURN_LEFT_THEN_TURN_LEFT(2, "TurnLeftThenTurnLeft", "Abbiegen Sie Links","auf"),
    TURN_LEFT_THEN_BEAR_RIGHT(2, "TurnLeftThenBearRight", "Abbiegen Sie Links","auf"),
    TURN_LEFT_THEN_TURN_RIGHT(2, "TurnLeftThenTurnRight", "Abbiegen Sie Links","auf"),
    TURN_TO_STAY_LEFT(2, "TurnToStayLeft", "Abbiegen Sie Links","auf"),
    
    RIGHT_TURN    (3, "TurnRight", "Abbiegen Sie Rechts","auf"),
    BEAR_RIGHT_THEN_LEFT(3, "BearRightThenBearLeft", "Abbiegen Sie Rechts","auf"),
    BEAR_RIGHT_THEN_RIGHT(3, "BearRightThenBearRight", "Abbiegen Sie Rechts","auf"),
    BEAR_RIGHT_THEN_TURN_LEFT(3, "BearRightThenTurnLeft", "Abbiegen Sie Rechts","auf"),
    BEAR_RIGHT_THEN_TURN_RIGHT(3, "BearRightThenTurnRight", "Abbiegen Sie Rechts","auf"),
    KEEP_ON_RAMP_RIGHT(3, "KeepOnRampRight", "Abbiegen Sie Rechts","auf"),
    RAMP_THEN_HIGHWAY_RIGHT(3, "RampThenHighwayRight", "Abbiegen Sie Rechts","auf"),
    TAKE_RAMP_RIGHT(3, "TakeRampRight", "Abbiegen Sie Rechts","auf"),
    TURN_RIGHT_THEN_BEAR_LEFT(3, "TurnRightThenBearLeft", "Abbiegen Sie Rechts","auf"),
    TURN_RIGHT_THEN_BEAR_RIGHT(3, "TurnRightThenBearRight", "Abbiegen Sie Rechts","auf"),
    TURN_RIGHT_THEN_TURN_LEFT(3, "TurnRightThenTurnLeft", "Abbiegen Sie Rechts","auf"),
    TURN_RIGHT_THEN_TURN_RIGHT(3, "TurnRightThenTurnRight", "Abbiegen Sie Rechts","auf"),
    TURN_TO_STAY_RIGHT(3, "TurnToStayRight", "Abbiegen Sie Rechts","auf"),
    
    SLIGHT_LEFT_TURN (4,   "BearLeft", "Halblinks abbiegen", "auf"),
    KEEP_LEFT (4,   "KeepLeft", "Halblinks abbiegen", "auf"),
    KEEP_TO_STAY_LEFT(4,   "KeepToStayLeft", "Halblinks abbiegen", "auf"),
    
    SLIGHT_RIGHT_TURN  (5, "BearRight", "Halbrechts abbiegen", "auf"),
    KEEP_RIGHT (5, "KeepRight", "Bear Rigth", "auf"),
    KEEP_TO_STAY_RIGHT(5, "KeepToStayRight", "Bear Rigth", "auf"),
    
    SHARP_LEFT_TURN  (6, "", "Abbiegen Sie scharf Links","auf"),
    SHARP_RIGTH_TURN (7, "", "Abbiegen Sie scharf Rechts","auf"),
    
    ENTER_ROUND_ABOUT(20, "EnterRoundabout", "Nehmen Sie den Kreisverkehr", "auf"),
    EXIT_ROUND_ABOUT (21, "ExitRoundabout", "Nehmen Sie im Kreisverkehr die Ausfahrt", "auf");   
    
 
    
    private final int greenNavCode;   
	private final String bingName;
    private final String text;
    private final String preposition;
    
    
    ManeuverType(int greenNavCode, String bingName, String text, String preposition) {
        this.greenNavCode = greenNavCode;
        this.bingName = bingName;
        this.text = text;
        this.preposition = preposition;
    }

    public int getGreenNavCode() {
		return greenNavCode;
	}

	public String getBingName() {
		return bingName;
	}
	
	public String getText() {
		return text;
	}

	public String getPreposition() {
		return preposition;
	}

	@Override
	public String toString() {
		return getGreenNavCode() + "";
	}
	
	
}
