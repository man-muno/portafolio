package de.visiom.carpc.services.navigation.exceptions;

import com.google.gson.JsonSyntaxException;

public class GreenNavException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2399196049420186791L;
	
	public GreenNavException(String message) {
		super(message);
	}

	public GreenNavException(String message, Exception exception) {
		super(message,exception);
	}
	


}
