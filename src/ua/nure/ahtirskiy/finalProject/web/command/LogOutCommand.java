package ua.nure.ahtirskiy.finalProject.web.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.exception.AppException;

/**
 * Method is invalidate session when user is log out
 * 
 * @author Y.Ahtirskiy
 **/

public class LogOutCommand extends Command {

	private static final long serialVersionUID = -69384752L;
	private static final Logger logger = Logger.getLogger(LogOutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"LogOut\" started");
		Locale loc = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			loc = (Locale) Config.get(session, Config.FMT_LOCALE);
			session.invalidate();
		}
		
		// save language settings in session
		session = request.getSession(true);
		Config.set(session, Config.FMT_LOCALE, loc);
		
		logger.debug("Command \"LogOut\" finished");
		return Path.INDEX_PAGE;
	}
}