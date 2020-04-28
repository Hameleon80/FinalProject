package ua.nure.ahtirskiy.finalProject.web.command.dispatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.FlightStatus;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Get flights statuses list;
 * 
 * @author Y.Ahtirskiy
 **/

public class StatusListCommand extends Command {

	private static final long serialVersionUID = 1734905708467260093L;
	private static final Logger logger = Logger.getLogger(StatusListCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"getStatusList\" starts");

		// create list of Flight Statuses
		List<String> list = new ArrayList<>();
		list.add(FlightStatus.CREATED.getName());
		list.add(FlightStatus.READY.getName());
		list.add(FlightStatus.IN_FLIGHT.getName());
		list.add(FlightStatus.LANDED.getName());
		list.add(FlightStatus.COMPLETE.getName());

		// set list of Flight Statuses in session
		HttpSession session = request.getSession(false);
		session.setAttribute("status_list", list);
		logger.trace("attribute \"status\" set in session");
		logger.debug("Command \"getStatusList\" finished");
		return Path.DISPATCHER_SET_STATUS_PAGE;
	}
}