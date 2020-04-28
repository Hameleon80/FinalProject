package ua.nure.ahtirskiy.finalProject.web.command.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.entity.Crew;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Set conformity between flight and crew.
 * 
 * @author Y.Ahtirskiy
 **/

public class SetCrewCommand extends Command{

	private static final long serialVersionUID = 4233760747763798220L;
	
	private static final Logger logger = Logger.getLogger(SetCrewCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"SetCrew\" starts");
		HttpSession session = request.getSession(false);
		
		// extract flight id
		Flight flight = (Flight) session.getAttribute("flight");
		// validate session's attribute
		if (flight == null || flight.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_FIND_FLIGHT);
			throw new AppException(Messages.ERR_CANNOT_FIND_FLIGHT);
		}
		
		int flightId = flight.getId();
		
		// get crew from request
		Crew crew = new Crew();
		crew.setFirstPilot_id(Integer.parseInt(request.getParameter("firstPilot")));
		crew.setSecondPilot_id(Integer.parseInt(request.getParameter("secondPilot")));
		crew.setNavigator_id(Integer.parseInt(request.getParameter("navigator")));
		crew.setRadioman_id(Integer.parseInt(request.getParameter("radioman")));
		crew.setStewardess1_id(Integer.parseInt(request.getParameter("stewardess1")));
		crew.setStewardess2_id(Integer.parseInt(request.getParameter("stewardess2")));
		
		// set conformity between flight and crew
		DBManager dbInstance = DBManager.getInstance();
		crew = dbInstance.setCrew(crew, flightId);
		session.setAttribute("crew", crew);
		logger.debug("Command \"SetCrew\" finished");
		return Path.DISPATCHER_SET_PAGE;
	}
}