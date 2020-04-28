package ua.nure.ahtirskiy.finalProject.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.entity.Crew;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.entity.Order;
import ua.nure.ahtirskiy.finalProject.entity.User;
import ua.nure.ahtirskiy.finalProject.exception.DBException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * DB manager.Has methods for working with DB (MySQL). 
 * Implements pattern Singleton.
 * 
 * @author Y.Ahtirskiy
 **/

public final class DBManager {

	private DataSource dataSource;
	private static DBManager instance;
	
	private static final Logger logger = Logger.getLogger(DBManager.class);
	
	synchronized public static DBManager getInstance() throws DBException {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	private DBManager() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/AirlinesPool");
			logger.trace("Data source : " + dataSource);
		} catch (NamingException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	/////////////////////////////
	// SQL queries to DataBase //
	/////////////////////////////
	
	// SELECT queries
	private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	private static final String FIND_ALL_FLIGHTS = "SELECT * FROM flight ORDER BY name";
	private static final String FIND_FLIGHT_BY_NUMBER = "SELECT * FROM flight WHERE number=?";
	private static final String FIND_FLIGHT_BY_ID = "SELECT * FROM flight WHERE id = ?";
	private static final String FIND_FLIGHT_BY_CITYS_AND_DATE = "SELECT * FROM flight WHERE city_from=? AND city_to=? AND flight_date=?";
	private static final String GET_FLIGHT_STATUS_ID = "SELECT id FROM flight_status WHERE name=?";
	private static final String FIND_CREW_BY_FLIGHT_ID = "SELECT crew_id FROM flight_crew WHERE flight_id=?";
	private static final String FIND_CREW_BY_ID = "SELECT * FROM crew WHERE id=?";
	private static final String FIND_CREW_ID_FROM_FLIGHT_CREW = "SELECT crew_id FROM flight_crew WHERE flight_id=?";
	private static final String FIND_EMPLOYEE = "SELECT * FROM employees ORDER BY post_id";
	private static final String FIND_ORDER_LIST = "SELECT * FROM orders ORDER BY id";

	// INSERT queries
	private static final String INSERT_CREW = "INSERT INTO crew VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_FLIGHT_CREW = "INSERT INTO flight_crew VALUES (?, ?)";
	private static final String INSERT_DISPATCHER_ORDER = "INSERT INTO orders VALUES (DEFAULT, ?, 0)";
	private static final String INSERT_NEW_EMPLOYEE = "INSERT INTO employees VALUES (DEFAULT, ?, ?, ?)";
	private static final String INSERT_NEW_FLIGHT = "INSERT INTO flight VALUES (DEFAULT, ?, ?, ?, ?, ? ,?)";

	// UPDATE queries
	private static final String UPDATE_FLIGHT_CREW = "UPDATE flight_crew SET crew_id = ? WHERE flight_id = ?";
	private static final String UPDATE_FLIGHT_STATUS = "UPDATE flight SET status_id = ? WHERE id = ?";
	private static final String UPDATE_FLIGHT = "UPDATE flight SET number=?, name=?, city_from=?, city_to=?, flight_date=? WHERE id = ?";
	private static final String UPDATE_EMPLOYEE = "UPDATE employees SET first_name=?, last_name=?, post_id=? WHERE id = ?";
	private static final String UPDATE_ORDER_STATUS = "UPDATE orders SET status_id=? WHERE id=?";
	
	// DELETE queries
	private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE id = ?";
	private static final String DELETE_FLIGHT = "DELETE FROM flight WHERE id = ?";
	
	/////////////////////////////////////////
	// Methods for get information from DB //
	/////////////////////////////////////////
	
	/**
	 * Returns all flights with given number
	 * 
	 * @param num Flight number
	 * @return List of flights
	 * @throws DBException
	 **/
	
	public List<Flight> getFlightsByNumber(String num) throws DBException {
		logger.debug("Method \"getFlightsByNumber\" starts");
		List<Flight> list = new ArrayList<Flight>();
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_FLIGHT_BY_NUMBER);
			pstmt.setInt(1, Integer.parseInt(num));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(Utils.extractFlight(rs));
			}
		} catch (SQLException ex) {
			logger.error("No flight with number: " + num);
			ex.printStackTrace();
			throw new DBException("Can't find flight with this number", ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"getFlightsByNumber\" finished");
		return list;
	}
	
	/**
	 * Returns flight with given advanced parameters.
	 * 
	 * @param cityFrom	Name of city where flight started
	 * @param cityTo	Name of destination city
	 * @param date		Date when flight start
	 * @return Flight	Flight with given parameters
	 * @throws DBException
	 **/
	
	public Flight getFlightsByCitysAndDate(String cityFrom, String cityTo, String date) throws DBException {
		logger.debug("Method \"getFlightsByCitysAndDater\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Flight fly = null;
		try {
			if (cityFrom == null || cityTo == null || date == null) {
				throw new SQLException();
			}
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_FLIGHT_BY_CITYS_AND_DATE);
			int k = 1;
			pstmt.setString(k++, cityFrom);
			pstmt.setString(k++, cityTo);
			pstmt.setString(k++, date);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				fly = Utils.extractFlight(rs);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"getFlightsByCitysAndDater\" finished");
		return fly;
	}
	
	/**
	 * Returns user with given login.
	 * 
	 * @param login	Login of searching user.
	 * @return User entity.
	 * @throws DBException.
	 **/
	
	public User getUserByLogin(String login) throws DBException {
		logger.debug("Method \"getUserByLogin\" starts");
		User user = null;
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = Utils.extractUser(rs);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"getUserByLogin\" finished");
		return user;
	}

	/**
	 * Return all flights.
	 * 
	 * @return List of flights.
	 * @throws DBException.
	 **/
	
	public List<Flight> getFlightList() throws DBException {
		logger.debug("Method \"Find flight list\" start");
		List<Flight> list = new ArrayList<>();
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(FIND_ALL_FLIGHTS);
			while (rs.next()) {
				list.add(Utils.extractFlight(rs));
			}
		} catch (SQLException ex) {
			logger.trace(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
		} finally {
			Utils.close(connect, stmt, rs);
		}
		logger.debug("Method \"Find flight list\" finished");
		return list;
	}

	/**
	 * Method returns all orders from dispatcher.
	 * 
	 * @return List of orders.
	 * @throws DBException.
	 **/

	public List<Order> getOrderList() throws DBException {
		logger.debug("Method \"GetOrder\" starts");
		List<Order> list = new ArrayList<>();
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(FIND_ORDER_LIST);
			while (rs.next()) {
				Order order = Utils.extractOrder(rs);
				list.add(order);
			}
		} catch (SQLException ex) {
			logger.trace(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ORDERS, ex);
		} finally {
			Utils.close(connect, stmt, rs);
		}
		logger.debug("Method \"GetOrder\" finished");
		return list;
	}
	
	/**
	 * Returns crew with given flight ID.
	 * 
	 *  @param flightId	ID of flight whose crew need to find.
	 *  @return	Crew entity. 
	 *  @throw DBException.
	 **/
	
	public Crew getCrew(int flightId) throws DBException {
		logger.debug("Method \"getCrew\" starts");
		int crewId = getCrewId(flightId);
		Crew crew = null;
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_CREW_BY_ID);
			pstmt.setInt(1, crewId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				crew = Utils.extractCrew(rs);
			}
		} catch (SQLException ex) {
			throw new DBException("Can't find crew", ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"getCrew\" finished");
		return crew;
	}

	/**
	 * Returns crew id, that conform to flight id.
	 * 
	 * @param	flightId ID of flight
	 * @return	crew ID
	 * @throws	DBException
	 **/
	
	public int getCrewId(int flightId) throws DBException {
		logger.debug("Method \"getCrewId\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int crewId = 0;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_CREW_BY_FLIGHT_ID);
			pstmt.setInt(1, flightId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				crewId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CREW, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"getCrewId\" finished");
		return crewId;
	}
	
	/**
	 * Returns all employees.
	 * 
	 * @return	List of employee entities.
	 * @throws	DBException
	 **/
	
	public List<Employee> getEmployeeList() throws DBException {
		logger.debug("Method \"getEmployeeList\" starts");
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			connect = Utils.getConnection(dataSource);
			stmt = connect.createStatement();
			rs = stmt.executeQuery(FIND_EMPLOYEE);
			while (rs.next()) {
				list.add(Utils.extractEmloyee(rs));
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_LIST, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_LIST, ex);
		} finally {
			Utils.close(connect, stmt, rs);
		}
		logger.debug("Method \"getEmployeeList\" finished");
		return list;
	}

	/**
	 * Returns status ID with given status name.
	 * 
	 * @param status	Name of status.
	 * @return			Flight status ID
	 * @throws			DBException 
	 **/
	
	private int getStatusId(String status) throws DBException {
		logger.debug("Method \"GetStatusId\" starts");
		int statusId = 0;
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(GET_FLIGHT_STATUS_ID);
			pstmt.setString(1, status);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				statusId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_FLIGHT_STATUS_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_FLIGHT_STATUS_ID, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"GetStatusId\" finished");
		return statusId;
	}

	/**
	 * Returns the flight with the given ID.
	 * 
	 * @param id	Flight ID.
	 * @return		All information about flight.
	 * @throws		DBException.
	 **/

	public Flight getFlightsById(int id) throws DBException {
		logger.debug("Method \"GetFlightById\" starts");
		Flight flight = null;
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_FLIGHT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flight = Utils.extractFlight(rs);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_FIND_FLIGHT, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"GetFlightById\" finished");
		return flight;
	}
	
	/**
	 * Returns crew ID for given flight ID.
	 * 
	 * @param flightId	Flight ID.
	 * @return			Crew ID.
	 * @throws			DBException.
	 **/
	
	private int getCrewIdFromFlightCrew(int flightId) throws DBException {
		logger.debug("Method \"getCrewIdFromFlightCrew\" starts");

		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int crewId = 0;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(FIND_CREW_ID_FROM_FLIGHT_CREW);
			pstmt.setInt(1, flightId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				crewId = rs.getInt(1);
			}
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_CREW_ID, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CREW_ID, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}

		logger.debug("Method \"getCrewIdFromFlightCrew\" finished");
		return crewId;
	}

	 ////////////////////////////////////////////
	 // Methods for INSERT information into DB //
	 ////////////////////////////////////////////

	/**
	 * Method insert crew in DB (table crew) and set connection with flight (table
	 * flight_crew). If flight have a crew - create new crew (table crew) and
	 * update connection with flight (table flight_crew)
	 * 
	 * @param crew		New crew
	 * @param flightId	Flight ID
	 * @return			New crew (with ID)
	 * @throws			DBException
	 **/
	public Crew setCrew(Crew crew, int flightId) throws DBException {
		logger.debug("Method setCrew starts");

		// get crew id from table flight_crew
		crew = setNewCrew(crew);
		int crewIdFromFlightCrew = getCrewIdFromFlightCrew(flightId);
		if (crewIdFromFlightCrew == 0) {
			setFlightCrew(flightId, crew.getId());
			return crew;
		}
		updateCrew(flightId, crew.getId());
		logger.debug("Method setCrew finished");
		return crew;
	}
	
	/**
	 * Set new crew.
	 * 
	 * @param crew	New crew.
	 * @return		New Crew (with ID).
	 * @throws		DBException.
	 **/
	
	private Crew setNewCrew(Crew crew) throws DBException {
		logger.debug("Method \"setNewCrew\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(INSERT_CREW, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			pstmt.setInt(k++, crew.getFirstPilot_id());
			pstmt.setInt(k++, crew.getSecondPilot_id());
			pstmt.setInt(k++, crew.getNavigator_id());
			pstmt.setInt(k++, crew.getRadioman_id());
			pstmt.setInt(k++, crew.getStewardess1_id());
			pstmt.setInt(k++, crew.getStewardess2_id());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					crew.setId(rs.getInt(1));
				}
			}
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_INSERT_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_CREW, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"setNewCrew\" finished");
		return crew;
	}

	/**
	 * Method update record of flight in table flight_crew.
	 * 
	 * @param flightId	Flight ID.
	 * @param crewId	Crew ID.
	 * @throws DBException.
	 **/

	private void updateCrew(int flightId, int crewId) throws DBException {
		logger.debug("Method \"updateCrew\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(UPDATE_FLIGHT_CREW);
			int k = 1;
			pstmt.setInt(k++, crewId);
			pstmt.setInt(k++, flightId);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_UPDATE_CREW, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_CREW, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"updateCrew\" finished");
	}

	/**
	 * Method update record of Flight Status in flight table.
	 * 
	 * @param status	Flight status.
	 * @param flightId	Flight ID.
	 * @return			Flight status ID.
	 * @throws			DBException.
	 **/

	public int updateFlightStatus(String status, int flightId) throws DBException {
		logger.debug("Method \"UpdateFlightStatus\" starts");
		int statusId = getStatusId(status);
		Connection connect = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			connect = Utils.getConnection(dataSource);
			pstmt = connect.prepareStatement(UPDATE_FLIGHT_STATUS);
			int k = 1;
			pstmt.setInt(k++, statusId);
			pstmt.setInt(k++, flightId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_UPDATE_FLIGHT_STATUS, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT_STATUS, ex);
		} finally {
			Utils.close(connect, pstmt, rs);
		}
		logger.debug("Method \"UpdateFlightStatus\" finished");
		return statusId;
	}

	/**
	 * Method set conformity between Flight and Crew in table flight_crew.
	 * 
	 * @param flightId	Flight ID.
	 * @param crewId	Crew ID.
	 * @throws			DBException.
	 **/
	private void setFlightCrew(int flightId, int crewId) throws DBException {
		logger.debug("Method \"setFlightCrew\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(INSERT_FLIGHT_CREW);
			int k = 1;
			pstmt.setInt(k++, flightId);
			pstmt.setInt(k++, crewId);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_INSERT_FLIGHT_CREW_CONFORMITY, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_FLIGHT_CREW_CONFORMITY, ex);
		} finally {
			Utils.close(connect, pstmt);
		}

		logger.debug("Method \"setFlightCrew\" finished");
	}

	/**
	 * Method insert order from dispatcher to administrator in DB.
	 * 
	 * @param description	Description of problem.
	 * @return				True, if successful. False, if not. 
	 * @throws DBException
	 **/

	public void createDispatcherOrder(String decription) throws DBException {
		logger.debug("Method \"CreateDispatcherOrder\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(INSERT_DISPATCHER_ORDER);
			pstmt.setString(1, decription);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_INSERT_ORDER, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ORDER, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"CreateDispatcherOrder\" finished");
	}

	/**
	 * Update information about flight.
	 * 
	 * @param flight	Flight
	 * @throws DBException
	 **/

	public void updateFlight(Flight flight) throws DBException {
		logger.debug("Method \"UpdateFlight\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(UPDATE_FLIGHT);
			int k = 1;
			pstmt.setInt(k++, flight.getNumber());
			pstmt.setString(k++, flight.getName());
			pstmt.setString(k++, flight.getCityFrom());
			pstmt.setString(k++, flight.getCityTo());
			pstmt.setDate(k++, java.sql.Date.valueOf(flight.getFlightDate()),
					Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev")));
			pstmt.setInt(k++, flight.getId());
			pstmt.executeUpdate();
			logger.trace("Query is execute");
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FLIGHT, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"UpdateFlight\" finished");
	}
	
	/**
	 * Update information about employee.
	 * 
	 * @param employee	Employee.
	 * @throws DBException.
	 **/
	
	public void updateEmployee(Employee employee) throws DBException {
		logger.debug("Method \"UpdateEmployee\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(UPDATE_EMPLOYEE);
			int k = 1;
			pstmt.setString(k++, employee.getFirstName());
			pstmt.setString(k++, employee.getLastName());
			pstmt.setInt(k++, employee.getPostId());
			pstmt.setInt(k++, employee.getId());
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_UPDATE_EMPLOYEE, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_EMPLOYEE, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"UpdateEmployee\" finished");
	}

	/**
	 * Update order status.
	 * 
	 * @param statusId	Order status ID.
	 * @param orderId	Order ID.
	 * @return			List of orders.
	 * @throws			DBException.
	 **/
	
	public List<Order> updateOrderStatus(int statusId, int orderId) throws DBException {
		logger.debug("Method \"UpdateOrderStatus\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(UPDATE_ORDER_STATUS);
			int k = 1;
			pstmt.setInt(k++, statusId);
			pstmt.setInt(k++, orderId);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ORDER_STATUS, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		List<Order> list = getOrderList();
		logger.debug("Method \"UpdateOrderStatus\" finished");
		return list;
	}

	 ///////////////////////////////
	 // Add new information in DB //
	 ///////////////////////////////

	/**
	 * Add new Employee.
	 * 
	 * @param Employee.
	 * @throws DBException 
	 **/
	
	public void addNewEmployee (Employee employee) throws DBException {
		logger.debug("Method \"AddNewEmployee\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(INSERT_NEW_EMPLOYEE);
			int k=1;
			pstmt.setString(k++, employee.getFirstName());
			pstmt.setString(k++, employee.getLastName());
			pstmt.setInt(k++, employee.getPostId());
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_INSERT_EMPLOYEE, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_EMPLOYEE, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"AddNewEmployee\" finished");
	}
	
	/**
	 * Add new Flight.
	 * 
	 * @param flight	Added flight.
	 * @throws			DBException 
	 **/
	
	public void addNewFlight (Flight flight) throws DBException {
		logger.debug("Method \"AddNewFlight\" starts");
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(INSERT_NEW_FLIGHT);
			int k=1;
			pstmt.setInt(k++, flight.getNumber());
			pstmt.setString(k++, flight.getName());
			pstmt.setString(k++, flight.getCityFrom());
			pstmt.setString(k++, flight.getCityTo());
			pstmt.setDate(k++, java.sql.Date.valueOf(flight.getFlightDate()),
					Calendar.getInstance(TimeZone.getTimeZone("Europe/Kiev")));
			pstmt.setInt(k++, flight.getStatusId());
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			try {
				connect.rollback();
			} catch (SQLException ex1) {
				logger.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
				throw new DBException(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex1);
			}
			logger.error(Messages.ERR_CANNOT_INSERT_FLIGHT, ex);
			throw new DBException(Messages.ERR_CANNOT_INSERT_FLIGHT, ex);
		} finally {
			Utils.close(connect, pstmt);
		}
		logger.debug("Method \"AddNewFlight\" finished");
	}
	
	 ////////////////////////////////
	 // Delete information from DB //
	 ////////////////////////////////
	
	/**
	 * Delete employee.
	 * 
	 * @param id	Employee id
	 * @throws		DBException
	 */
	
	public boolean deleteEmployee(int id) throws DBException {
		logger.debug("Method \"DeleteEmployee\" starts");
		boolean res = false;
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(DELETE_EMPLOYEE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_DELETE_EMPLOYEE);
			return res;
		} finally {
			Utils.close(connect, pstmt);
		}
		res = true;
		logger.trace("Deleting is successful");
		logger.debug("Method \"DeleteEmployee\" finished");
		return res;
	}
	
	/**
	 * Delete flight.
	 * 
	 * @param id	Flight id
	 * @throws		DBException
	 */
	
	public boolean deleteFlight(int id) throws DBException {
		logger.debug("Method \"deleteFlight\" starts");
		boolean res = false;
		Connection connect = null;
		PreparedStatement pstmt = null;
		try {
			connect = Utils.getConnection(dataSource);
			connect.setAutoCommit(false);
			pstmt = connect.prepareStatement(DELETE_FLIGHT);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			connect.commit();
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_DELETE_FLIGHT);
			return res;
		} finally {
			Utils.close(connect, pstmt);
		}
		res = true;
		logger.trace("Deleting is successful");
		logger.debug("Method \"deleteFlight\" finished");
		return res;
	}
}