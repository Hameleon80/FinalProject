package ua.nure.ahtirskiy.finalProject.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * Searching in DB flight for city from, city to and date flight.
 * Parameters for searching gets from request.
 * 
 *  @author Y.Ahtirskiy
 **/

public class AdvancedSearchCommand extends Command{

	private static final long serialVersionUID = -3060361990532893154L;
	private static final Logger logger = Logger.getLogger(AdvancedSearchCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdvancedSearchCommand\" starts");
		DBManager dbInstance = DBManager.getInstance();
		
		// get requested parameters
		String cityFrom = request.getParameter("cityFrom");
		String cityTo = request.getParameter("cityTo");
		String date = request.getParameter("date");
		
		// validation fields
		if (cityFrom == null || cityTo == null || date == null || cityFrom.isEmpty() || cityTo.isEmpty() || date.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// find flight in DB
		Flight flight =  dbInstance.getFlightsByCitysAndDate(cityFrom, cityTo, date);
		if (flight == null || flight.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT);
			throw new AppException (Messages.ERR_CANNOT_FIND_FLIGHT);
		}
		logger.trace("Finding flight: " + flight);
		
		// add flight in session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("flight", flight);
		logger.debug("Command \"AdvancedSearchCommand\" finished");
		return Path.FLIGHT_PAGE;
	}
}