package ua.nure.ahtirskiy.finalProject.web.command;

import java.io.IOException;
import java.util.List;

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
 * Searching flight for number from request.
 * 
 * @author Y.Ahtirskiy
 **/

public class SearchByNumberCommand extends Command {

	private static final long serialVersionUID = 8066764372117354856L;
	private static Logger logger = Logger.getLogger(SearchByNumberCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"SearchByNumber\" starts");
		
		// get flight number from request
		String number = request.getParameter("number");
		
		// validate field
		if (number == null || number.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		logger.trace("Number from request: " + number);
		
		// find flight in DB
		List<Flight> list = DBManager.getInstance().getFlightsByNumber(number);
		if (list == null || list.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT_BY_NUMBER + number);
			throw new AppException(Messages.ERR_CANNOT_FIND_FLIGHT_BY_NUMBER + number);
		}
		
		// add flight in session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("flights_list", list);
		
		logger.debug("Command \"SearchByNumber\" finished");
		return Path.LIST_FLIGHTS_PAGE;
	}
}