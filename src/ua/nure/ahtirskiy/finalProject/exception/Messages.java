package ua.nure.ahtirskiy.finalProject.exception;

/**
 * Holder for messages of exceptions.
 * 
 * @author Y.Ahtirskiy
 **/

public class Messages {
	
	private Messages() {
		// empty constructor
	}
	
	//DB messages

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";
	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";
	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";
	public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction";
	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";
	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";
	
	public static final String ERR_CANNOT_FIND_FLIGHT = "Cannon find flight to your request";
	public static final String ERR_CANNOT_OBTAIN_FLIGHTS = "Cannot obtain flights";
	public static final String ERR_CANNOT_OBTAIN_FLIGHT_STATUS_ID = "Cannot obtain flight status ID";
	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";
	public static final String ERR_CANNOT_OBTAIN_ORDERS = "Cannot obtain orders";
	public static final String ERR_CANNOT_OBTAIN_CREW = "Cannot obtain crew";
	public static final String ERR_CANNOT_OBTAIN_CREW_ID = "Cannot obtain crew ID";
	public static final String ERR_CANNOT_OBTAIN_EMPLOYEE_LIST = "Cannot obtain employee list";
	
	public static final String ERR_CANNOT_INSERT_CREW = "Cannot create new crew";
	public static final String ERR_CANNOT_INSERT_EMPLOYEE = "Cannot create new employee";
	public static final String ERR_CANNOT_INSERT_ORDER = "Cannot create new order";
	public static final String ERR_CANNOT_INSERT_FLIGHT = "Cannot create new flight";
	public static final String ERR_CANNOT_INSERT_FLIGHT_CREW_CONFORMITY = "Cannot set conformity between flight and crew";
	public static final String ERR_CANNOT_UPDATE_CREW = "Cannot update crew information";
	public static final String ERR_CANNOT_UPDATE_EMPLOYEE = "Cannot update employee information";
	public static final String ERR_CANNOT_UPDATE_FLIGHT = "Cannot update flight information";
	public static final String ERR_CANNOT_UPDATE_FLIGHT_STATUS = "Cannot update flight status";
	public static final String ERR_CANNOT_UPDATE_ORDER_STATUS = "Cannot update order status";
	public static final String ERR_CANNOT_EXTRACT_USER = "Cannot extract user";
	
	public static final String ERR_CANNOT_DELETE_EMPLOYEE = "Cannot delete employee";
	public static final String ERR_CANNOT_DELETE_FLIGHT = "Cannot delete flight";
	
	// Application messages
	
	public static final String ERR_CANNOT_FIND_FLIGHT_BY_NUMBER = "Cannot find flight with number: ";
	public static final String ERR_CANNOT_FIND_USER_BY_LOGIN = "Cannot find user with such login/password";
	public static final String ERR_EMPTY_FIELD = "One of fields is empty";
	public static final String ERR_CANNOT_INSERT_DATE = "Cannot set date";
	public static final String ERR_CANNOT_FIND_EMPLOYEE = "Cannot find employee";
	
	public static final String ERR_CANNOT_DISPLAY_TABLE = "Cannot display a table";
}
