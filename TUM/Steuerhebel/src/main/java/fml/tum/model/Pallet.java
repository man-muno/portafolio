package fml.tum.model;

public class Pallet {

	private int transX;
	private int transY;
	private int transZ;
	private float rotX;
	private float rotY;

	public Pallet(int transX, int transY, int transZ, float rotX, float rotY) {
		this.transX=transX;
		this.transY=transY;
		this.transZ=transZ;
		this.rotX=rotX;
		this.rotY=rotY;
	}

	public int getCurrentX() {
		return transX;
	}

	public void set(int transX, int transY, int transZ, float rotX,	float rotY) {
		this.transX=transX;
		this.transY=transY;
		this.transZ=transZ;
		this.rotX=rotX;
		this.rotY=rotY;
	}

}
