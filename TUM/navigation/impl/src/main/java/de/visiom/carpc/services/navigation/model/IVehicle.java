package de.visiom.carpc.services.navigation.model;

/**
 * Describes the vehicles define on the GreenNav server.
 * @author Manuel Munoz
 *
 */
public interface IVehicle {
	
	public String getName() ;

	public void setName(String name) ;

	public double getLambda() ;

	public void setLambda(double lambda) ;

	public double getEmptyWeight() ;

	public void setEmptyWeight(double emptyWeight) ;

	public double getCw() ;

	public void setCw(double cw) ;

	public double getvMax() ;

	public void setvMax(double vMax) ;

	public double getEtaMDischarge() ;

	public void setEtaMDischarge(double etaMDischarge) ;

	public double getEtaMRecuperation() ;

	public void setEtaMRecuperation(double etaMRecuperation);
	
	public double getSurfaceA() ;

	public void setSurfaceA(double surfaceA) ;

	public double getCapacity() ;

	public void setCapacity(double capacity) ;

	public double getAuxiliaryPower() ;

	public void setAuxiliaryPower(double auxiliaryPower);
}
