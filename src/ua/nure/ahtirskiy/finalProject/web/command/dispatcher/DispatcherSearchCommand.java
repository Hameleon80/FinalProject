package ua.nure.ahtirskiy.finalProject.web.command.dispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.db.FlightStatus;
import ua.nure.ahtirskiy.finalProject.entity.Crew;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Obtains information about flight, crew and status
 * 
 * @author Y.Ahtirskiy
 **/

public class DispatcherSearchCommand extends Command{

	private static final long serialVersionUID = 8066764372117354856L;
	private static Logger logger = Logger.getLogger(DispatcherSearchCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"DispatcherSearch\" starts");
		
		// extract parameters for search from request 
		String cityFrom = request.getParameter("cityFrom");
		String cityTo = request.getParameter("cityTo");
		String date = request.getParameter("date");
		
		if (cityFrom == null || cityTo == null || date == null || cityFrom.isEmpty() || cityTo.isEmpty() || date.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// get flight from DB
		DBManager dbinsance = DBManager.getInstance(); 
		Flight fly = dbinsance.getFlightsByCitysAndDate(cityFrom, cityTo, date);
		
		// get crew from DB
		Crew crew = dbinsance.getCrew(fly.getId());
		
		// get status from DB
		FlightStatus status = FlightStatus.values()[fly.getStatusId()];
		
		HttpSession session = request.getSession(false);
		// add attributes in session
		// if attribute "employee_list" not added -> add
		List<Employee> list = null;
		list = (List<Employee>) session.getAttribute("employee_list");
		if (list==null) {
			list = (List<Employee>) dbinsance.getEmployeeList();
			session.setAttribute("employee_list", list);
		}
		session.setAttribute("flight", fly);
		session.setAttribute("crew", crew);
		session.setAttribute("status", status);

		logger.debug("Command \"DispatcherSearch\" finished");
		return Path.DISPATCHER_SET_PAGE;
	}
}