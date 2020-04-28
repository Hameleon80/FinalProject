package ua.nure.ahtirskiy.finalProject.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.sort.Sort;

/**
 * Sorts flights by name.
 * 
 * @author Y.Ahtirskiy
 **/

public class SortByNameCommand extends Command {

	private static final long serialVersionUID = 1733680701357842083L;
	
	private final Logger logger = Logger.getLogger(SortByNameCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"SortByName\" starts");
		
		// get flights list from session
		HttpSession session = request.getSession(false);
		List<Flight> list = (List<Flight>) session.getAttribute("flights_list");
		String forward = Path.ERROR_PAGE;
		if (list == null) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_FLIGHTS);
			throw new AppException(Messages.ERR_CANNOT_OBTAIN_FLIGHTS); 
		}
		
		// sort
		Sort.sortByName(list);
		
		// add sorted flight list in session
		session.setAttribute("flights_list", list);
		forward = Path.LIST_FLIGHTS_PAGE;
		logger.debug("Command \"SortByName\" finished");
		return forward;
	}
}