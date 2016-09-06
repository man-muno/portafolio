package de.visiom.carpc.services.navigation.exceptions;

public class NavigationException extends Exception {

	public NavigationException(String message, Exception exception) {
		super(message,exception);
	}

}
