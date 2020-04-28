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
 * Gets flights list from DB.
 * 
 * @author Y.Ahtirskiy
 **/

public class ListFlightsCommand extends Command {

	private static final long serialVersionUID = -6654629314245301431L;
	private static final Logger logger = Logger.getLogger(ListFlightsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"ListFlightCommand\" starts");

		// get Flights List
		List<Flight> list = DBManager.getInstance().getFlightList();
		if (list==null || list.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_FLIGHTS);
			throw new AppException(Messages.ERR_CANNOT_OBTAIN_FLIGHTS);
		}

		// add flights list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("flights_list", list);

		logger.debug("Command \"ListFlightCommand\" finished");
		return Path.LIST_FLIGHTS_PAGE;
	}
}