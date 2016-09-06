package de.visiom.carpc.services.navigation.model;

public class Warning {

	private String severity;
	private String type;
	private String warningText;
	
	public Warning(String severity, String type, String warningText) {
		this.severity = severity;
		this.type = type;
		this.warningText = warningText;
	}
	

	public String getSeverity() {
		return severity;
	}

	public String getType() {
		return type;
	}

	public String getWarningText() {
		return warningText;
	}


	@Override
	public String toString() {
		return severity + " " + type + " " + warningText;
	}

}
