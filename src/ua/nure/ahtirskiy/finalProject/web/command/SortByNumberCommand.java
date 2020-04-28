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
import ua.nure.ahtirskiy.finalProject.sort.Sort;

/**
 * Sorts flights by number.
 * 
 * @author Y.Ahtirskiy
 **/

public class SortByNumberCommand extends Command{
	private final Logger logger = Logger.getLogger(SortByNumberCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"SortByNumber\" starts");
		HttpSession session = request.getSession(false);
		
		// get flights list from session
		List<Flight> list = (List<Flight>) session.getAttribute("flights_list");
		String forward = Path.ERROR_PAGE;
		if (list==null) {
			logger.trace("Nothing to sort");
			return forward;
		}
		
		//sort
		Sort.sortByNumber(list);
		
		// add sorted flight list in session
		session.setAttribute("flights_list", list);
		forward = Path.LIST_FLIGHTS_PAGE;
		logger.debug("Command \"SortByNumber\" finished");
		return forward;
	}
}