package ua.nure.ahtirskiy.finalProject.web.command;

import ua.nure.ahtirskiy.finalProject.Path;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Method add error message if command don't exist.
 *  
 *  @author Y.Ahtirskiy
 **/

public class NoCommand extends Command{
	
	private static final long serialVersionUID = 128723942984792374L;
	private static final Logger logger = Logger.getLogger(NoCommand.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("Command \"NoCommand\" start");
		request.setAttribute("errorMessage", "No such command");
		logger.error("Set the request attribute: errorMessage");
		logger.debug("Command \"NoCommand\" finished");
		
		return Path.ERROR_PAGE;
	}
}
