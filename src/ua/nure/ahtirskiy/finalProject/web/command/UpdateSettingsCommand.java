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
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * Update language settings.
 * 
 * @author Y.Ahtirskiy
 **/

public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 8277814349851082048L;

	private static final Logger logger = Logger.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"UpdateSettins\" starts");

		// get name selected locale
		String localeName = request.getParameter("locale");
		
		// validate field
		if(localeName == null || localeName.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		logger.trace("Selected locale ==> " + localeName);

		// set selected locale in session
		HttpSession session = request.getSession(false);
		Locale loc = new Locale(localeName);
		Config.set(session, Config.FMT_LOCALE, loc);

		logger.debug("Command \"UpdateSettins\" starts");
		return Path.INDEX_PAGE;
	}
}