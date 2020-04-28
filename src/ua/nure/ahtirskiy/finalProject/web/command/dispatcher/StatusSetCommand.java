package ua.nure.ahtirskiy.finalProject.web.command.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.db.FlightStatus;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Update flight status.
 * 
 * @author Y.Ahtirskiy
 **/

public class StatusSetCommand extends Command {

	private static final long serialVersionUID = -5473248456914108363L;
	private static final Logger logger = Logger.getLogger(StatusSetCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"StatusSet\" starts");
		DBManager dbInstance = DBManager.getInstance();
		String statusStr = request.getParameter("flightStatus");

		// extract Flight ID from session
		HttpSession session = request.getSession(false);
		Flight flight = (Flight) session.getAttribute("flight");
		
		// validate information
		if (flight == null || flight.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT);
			throw new AppException(Messages.ERR_CANNOT_FIND_FLIGHT);
		}
		int flightId = flight.getId();

		// update flight status in DB
		int statusId = dbInstance.updateFlightStatus(statusStr, flightId);

		FlightStatus status = FlightStatus.values()[statusId];

		// update status in session's attribute
		session.setAttribute("status", status);

		logger.debug("Command \"StatusSet\" finished");
		return Path.DISPATCHER_SET_PAGE;
	}
}