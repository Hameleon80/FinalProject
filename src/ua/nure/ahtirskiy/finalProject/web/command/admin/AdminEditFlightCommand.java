package ua.nure.ahtirskiy.finalProject.web.command.admin;

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
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Update information about flight.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminEditFlightCommand extends Command {

	private static final long serialVersionUID = -7852727259415774922L;
	private static final Logger logger = Logger.getLogger(AdminEditFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminEditFlight\" starts");

		// get flight ID from request
		String id = request.getParameter("flightId");

		// validate field
		if (id == null || id.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}

		// get flight from DB
		DBManager dbInstance = DBManager.getInstance();
		Flight flight = dbInstance.getFlightsById(Integer.parseInt(id));
		logger.trace("Get flights list");

		// add flight in sessions attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("flight", flight);

		logger.debug("Command \"AdminEditFlights\" finished");
		return Path.ADMIN_EDIT_FLIGHT_PAGE;
	}
}