package fml.tum.model;

public class Forklift {

	/**
	 * Delta for the correct mast angle
	 */
	private float correctMastAngleDelta;
	
	/**
	 * Correct angle for the mast  
	 */
	private float correctMastAngle;

	/**
	 * current mast angle
	 */
	private float currentMastAngle;
	
	/**
	 * Current mas height
	 */
	private int currentMastHeight;

	/**
	 * Current x measurement
	 */
	private int currentX=0;
	
	/**
	 * Current x measurement
	 */
	private int currentY=0;
	
	/**
	 * Current x measurement
	 */
	private int currentZ=0;

	/**
	 * True if it has been identified that it has a pallet
	 */
	private boolean pallet = false;
	
	public Forklift(float correctMastAngle, float correctMastAngleDelta) {
		this.correctMastAngle = correctMastAngle;
		this.correctMastAngleDelta = correctMastAngleDelta;
	}

	/**
	 * Changes the mast angle
	 * @param mastDegrees can be negatice
	 */
	public void setCurrentMastDegrees(float mastDegrees) {
		this.currentMastAngle = mastDegrees;
	}

	/**
	 * Height of the mast
	 * @param currentMastHeight Bigger than 0 
	 */
	public void setCurrentMastHeight(int currentMastHeight) {
		this.currentMastHeight = currentMastHeight;
	}

	/**
	 * Updates the values for the position of the forklift
	 * @param currentX Bigger than 0
	 * @param currentY Bigger than 0
	 * @param currentZ Bigger than 0
	 */
	public void setCurrentXYZ(int currentX, int currentY, int currentZ) {
		this.currentX = currentX;
		this.currentY = currentY;
		this.currentZ = currentZ;
	}

	/**
	 * Current x measurement 
	 * @return  Bigger than 0
	 */
	public int getCurrentX() {
		return currentX;
	}

	/**
	 * Current y measurement 
	 * @return  Bigger than 0
	 */
	public int getCurrentY() {
		return currentY;
	}

	/**
	 * Current z measurement 
	 * @return  Bigger than 0
	 */
	public int getCurrentZ() {
		return currentZ;
	}

	/**
	 * If the fork lift carries a pallet
	 * @return True if it has a pallet
	 */
	public boolean hasPallet() {
		return pallet;
	}
	
	/**
	 * Changes the value if the pfork lift picks up or drops a pallet
	 * @param pallet True if it now has a pallet, false otherwise
	 */
	public void setPallet(boolean pallet)
	{
		this.pallet = pallet;
	}

	/**
	 * Return the current mast height 
	 * @return Positive integer
	 */
	public int getCurrentMastHeight() {
		return currentMastHeight;
	}

	/**
	 * Current angle for the mast
	 * @return Can  be negative
	 */
	public float getCurrentMastAngle() {
		return currentMastAngle;
	}

	/**
	 * Returns true if the mast angle is within the tolerance level given by CORRECT_MAST_ANGLE_DELTA
	 */
	public boolean isCorrectMastAngle() {
		System.out.println("Current Mast Angle: " + currentMastAngle + " expected: " + correctMastAngle);
		return Math.abs(currentMastAngle - correctMastAngle) <= correctMastAngleDelta;
	}
}
