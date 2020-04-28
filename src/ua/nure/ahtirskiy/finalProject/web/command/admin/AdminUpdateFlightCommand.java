package ua.nure.ahtirskiy.finalProject.web.command.admin;

import java.io.IOException;
import java.text.ParseException;
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
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Updates information about flight.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminUpdateFlightCommand extends Command{

	private static final long serialVersionUID = -7852727259415774922L;
	
	private static final Logger logger = Logger.getLogger(AdminUpdateFlightCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminUpdateFlight\" starts");
		DBManager dbInstance = DBManager.getInstance();
		
		// get flight from request
		Flight flight = new Flight();
		flight.setId(Integer.parseInt(request.getParameter("id")));
		flight.setNumber(Integer.parseInt(request.getParameter("number")));
		flight.setName(request.getParameter("name"));
		flight.setCityFrom(request.getParameter("cityFrom"));
		flight.setCityTo(request.getParameter("cityTo"));
		try {
			flight.setFlightDate(request.getParameter("date"));
		} catch (ParseException e) {
			throw new AppException(Messages.ERR_CANNOT_INSERT_DATE);
		}
		
		// validate requested information
		if (flight == null || flight.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// update flight in DB
		dbInstance.updateFlight(flight);

		// get new flights list
		List<Flight> list = dbInstance.getFlightList();
		
		// add flights list to session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("flights_list", list);
		logger.debug("Command \"AdminUpdateFlight\" finished");
		return Path.LIST_FLIGHTS_PAGE;
	}
}