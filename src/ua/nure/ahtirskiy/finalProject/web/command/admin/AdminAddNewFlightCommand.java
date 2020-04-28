package ua.nure.ahtirskiy.finalProject.web.command.admin;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
 * Add new Flight.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminAddNewFlightCommand extends Command {

	private static final long serialVersionUID = 6492637298323562306L;
	private static final Logger logger = Logger.getLogger(AdminAddNewFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AddNewFlight\" starts");

		// get flight from request
		int number = Integer.parseInt(request.getParameter("number"));
		int statusId = FlightStatus.getStatusId(request.getParameter("status"));
		String name = request.getParameter("name");
		String cityFrom = request.getParameter("cityFrom");
		String cityTo = request.getParameter("cityTo");
		LocalDate date = LocalDate.parse(request.getParameter("date"));

		// check added parameters not empty
		if (statusId == -1 || number == 0 || name == null || cityFrom == null || cityTo == null || date == null
				|| name.isEmpty() || cityFrom.isEmpty() || cityTo.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}

		// create new flight
		Flight flight = new Flight(0, number, statusId, date, name, cityFrom, cityTo);

		// add flight in DB
		DBManager dbInstance = DBManager.getInstance();
		dbInstance.addNewFlight(flight);

		// get new flights list
		List<Flight> list = dbInstance.getFlightList();

		// add new flights list to session
		HttpSession session = request.getSession(false);
		session.setAttribute("flights_list", list);

		logger.debug("Command \"AddNewFlight\" finished");
		return Path.LIST_FLIGHTS_PAGE;
	}
}