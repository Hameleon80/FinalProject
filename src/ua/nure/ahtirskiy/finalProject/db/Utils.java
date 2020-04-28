package ua.nure.ahtirskiy.finalProject.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
 * Helper methods for work with DB.
 * 
 * @author Y.Ahtirskiy
 **/

public class Utils {
	
	static Logger logger = Logger.getLogger(Utils.class);
	
	//get connection to DB 
	public static Connection getConnection(DataSource dataSource) throws DBException {
		Connection connect = null;
		try {
			connect = dataSource.getConnection();
		} catch (SQLException ex) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return connect;
	}
	
	 /////////////////////////////////////////
	 // Methods to extract entities from DB //
	 /////////////////////////////////////////
	
	/**
	 * Extracts a user entity from the result set.
	 * 
	 * @param rs	Result set from which a user entity will be extracted.
	 * @return		User entity
	 * @throws		SQLException
	 * @throws DBException 
	 **/
	
	public static User extractUser(ResultSet rs) throws SQLException, DBException {
		
		if(rs == null) {
			logger.error(Messages.ERR_CANNOT_EXTRACT_USER);
			throw new DBException(Messages.ERR_CANNOT_EXTRACT_USER);
		}
		
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setFirstName(rs.getString("first_name"));
		user.setLastName(rs.getString("last_name"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}

	/**
	 * Extracts a flight entity from the result set.
	 * 
	 * @param rs	Result set from which a flight entity will be extracted.
	 * @return		Flight entity
	 * @throws		SQLException
	 * @throws DBException 
	 **/
	
	static Flight extractFlight(ResultSet rs) throws SQLException, DBException {
		
		if(rs == null) {
			logger.error(Messages.ERR_CANNOT_EXTRACT_USER);
			throw new DBException(Messages.ERR_CANNOT_EXTRACT_USER);
		}
		
		Flight f = new Flight();
		f.setId(rs.getInt(1));
		f.setNumber(rs.getInt(2));
		f.setName(rs.getString(3));
		f.setCityFrom(rs.getString(4));
		f.setCityTo(rs.getString(5));
		f.setFlightDate(rs.getObject(6, LocalDate.class));
		f.setStatusId(rs.getInt(7));
		return f;
	}
	
	/**
	 * Extracts a crew entity from the result set.
	 * 
	 * @param rs	Result set from which a crew entity will be extracted.
	 * @return		Crew entity
	 * @throws		SQLException
	 * @throws DBException 
	 **/
	
	static Crew extractCrew(ResultSet rs) throws SQLException, DBException {
		
		if(rs == null) {
			logger.error(Messages.ERR_CANNOT_EXTRACT_USER);
			throw new DBException(Messages.ERR_CANNOT_EXTRACT_USER);
		}
		
		Crew crew = new Crew();
		crew.setId(rs.getInt(1));
		crew.setFirstPilot_id(Integer.parseInt(rs.getString(2)));
		crew.setSecondPilot_id(Integer.parseInt(rs.getString(3)));
		crew.setNavigator_id(Integer.parseInt(rs.getString(4)));
		crew.setRadioman_id(Integer.parseInt(rs.getString(5)));
		crew.setStewardess1_id(Integer.parseInt(rs.getString(6)));
		crew.setStewardess2_id(Integer.parseInt(rs.getString(7)));
		return crew;
	}
	
	/**
	 * Extracts a employee entity from the result set.
	 * 
	 * @param rs	Result set from which a employee entity will be extracted.
	 * @return		Employee entity
	 * @throws		SQLException
	 * @throws DBException 
	 **/
	
	static Employee extractEmloyee(ResultSet rs) throws SQLException, DBException {
		
		if(rs == null) {
			logger.error(Messages.ERR_CANNOT_EXTRACT_USER);
			throw new DBException(Messages.ERR_CANNOT_EXTRACT_USER);
		}
		
		Employee employee = new Employee();
		employee.setId(rs.getInt(1));
		employee.setFirstName(rs.getString(2));
		employee.setLastName(rs.getString(3));
		employee.setPostId(rs.getInt(4));
		return employee;
	}
	
	/**
	 * Extracts a order entity from the result set.
	 * 
	 * @param rs	Result set from which a order entity will be extracted.
	 * @return		Order entity
	 * @throws		SQLException
	 * @throws DBException 
	 **/
	
	static Order extractOrder(ResultSet rs) throws SQLException, DBException {
		
		if(rs == null) {
			logger.error(Messages.ERR_CANNOT_EXTRACT_USER);
			throw new DBException(Messages.ERR_CANNOT_EXTRACT_USER);
		}
		
		Order order = new Order();
		order.setId(rs.getInt(1));
		order.setDescription(rs.getString(2));
		order.setStatusId(rs.getInt(3));
		return order;
	}

	 /////////////////////////////////////
	 // Methods for close DB connection //
	 /////////////////////////////////////

	/**
	 * Close a connection
	 **/

	static void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				logger.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	/**
	 * Close a statement
	 **/
	static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				logger.error("Can't close Statement", ex);
			}
		}
	}

	/**
	 * Close a result set
	 **/
	static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				logger.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	/**
	 * Close all resources
	 **/

	static void close(Connection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
	}
	
	/**
	 * Close Connection and Statement  
	 */
	
	static void close(Connection connect, Statement stmt) {
		close(connect);
		close(stmt);
	}
}