package ua.nure.ahtirskiy.finalProject.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author Y.Ahtirskiy
 **/

public class AppException extends Exception{
	
	private static final long serialVersionUID = 291649384955227840L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
}
