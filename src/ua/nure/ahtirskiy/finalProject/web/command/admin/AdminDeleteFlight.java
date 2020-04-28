package ua.nure.ahtirskiy.finalProject.web.command.admin;

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
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Delete flight from DB.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminDeleteFlight extends Command{

	private static final long serialVersionUID = 6100935723616716861L;
	Logger logger = Logger.getLogger(AdminDeleteFlight.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminDeleteFlight\" starts");
		
		// get flight id from request
		String flightId = request.getParameter("flightId");
		
		// validate parameter
		if (flightId == null || flightId.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_DELETE_FLIGHT);
			throw new AppException(Messages.ERR_CANNOT_DELETE_FLIGHT);
		}
		
		// delete flight from DB
		DBManager dbInstance = DBManager.getInstance();
		if(!dbInstance.deleteFlight(Integer.parseInt(flightId))) {
			logger.error(Messages.ERR_CANNOT_DELETE_FLIGHT);
			throw new AppException(Messages.ERR_CANNOT_DELETE_FLIGHT);
		}
		
		// get new flight list
		List<Flight> list = dbInstance.getFlightList();
		
		// validate list
		if(list == null || list.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_FLIGHTS);
			throw new AppException(Messages.ERR_CANNOT_OBTAIN_FLIGHTS);
		}
		
		// add new flight list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("flights_list", list);
		
		logger.debug("Command \"AdminDeleteFlight\" finished");
		return Path.LIST_FLIGHTS_PAGE;
	}
}