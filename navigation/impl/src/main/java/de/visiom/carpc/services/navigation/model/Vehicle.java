package de.visiom.carpc.services.navigation.model;

/**
 * Describes the vehicles define on the GreenNav server.
 * @author Manuel Munoz
 *
 */
public class Vehicle implements IVehicle{
	
	/**
	 * The name of the vehicle type. Never null because it works as the vehicle type id. i.e: "Sam"
	 */
	private String name;
	
	/**
	 * : Rotational inertia (dimensionless), currently not used. i.e: 1.1
	 */
	private double lambda;
	
	/**
	 * The weight of an empty vehicle of that type in kg. i.e: 490.0 
	 */
	private double emptyWeight; 
		
	/**
	 * : The drag coefﬁcient (dimensionless). i.e: 0.25
	 */
	private double cw;
	
	/**
	 * The maximum speed of the vehicle in m/s. i.e: 26.38888888888889 
	 */
	private double vMax;
	
	/**
	 * Efﬁciency for discharge (dimensionless). i.e: 0.8
	 */
	private double etaMDischarge;
	
	
	/**
	 * Efﬁciency for recuperation (dimensionless). i.e: 0.8 
	 */
	private double etaMRecuperation;
	
	/**
	 * An approximate value of vehicles front surface in m. i.e:1.14 
	 */
	private double surfaceA;
	


	/**
	 * The capacity of the vehicle’s battery in J. i.e: 2.52E7
	 */
	public double capacity;
	
	/**
	 * Currently not used.
	 */
	public double auxiliaryPower;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public double getEmptyWeight() {
		return emptyWeight;
	}

	public void setEmptyWeight(double emptyWeight) {
		this.emptyWeight = emptyWeight;
	}

	public double getCw() {
		return cw;
	}

	public void setCw(double cw) {
		this.cw = cw;
	}

	public double getvMax() {
		return vMax;
	}

	public void setvMax(double vMax) {
		this.vMax = vMax;
	}

	public double getEtaMDischarge() {
		return etaMDischarge;
	}

	public void setEtaMDischarge(double etaMDischarge) {
		this.etaMDischarge = etaMDischarge;
	}

	public double getEtaMRecuperation() {
		return etaMRecuperation;
	}

	public void setEtaMRecuperation(double etaMRecuperation) {
		this.etaMRecuperation = etaMRecuperation;
	}
	
	public double getSurfaceA() {
		return surfaceA;
	}

	public void setSurfaceA(double surfaceA) {
		this.surfaceA = surfaceA;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public double getAuxiliaryPower() {
		return auxiliaryPower;
	}

	public void setAuxiliaryPower(double auxiliaryPower) {
		this.auxiliaryPower = auxiliaryPower;
	}
	
}
