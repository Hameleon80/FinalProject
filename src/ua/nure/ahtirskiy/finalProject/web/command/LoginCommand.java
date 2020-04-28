package ua.nure.ahtirskiy.finalProject.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.db.Role;
import ua.nure.ahtirskiy.finalProject.entity.User;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * Check entered login and password.
 * 
 * @author Y.Ahtirskiy
 **/

public class LoginCommand extends Command {

	private static final long serialVersionUID = 8799977755664332L;
	private static final Logger logger = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"Login\" start");
		// Start session
		HttpSession session = request.getSession();

		// get login and password from request
		DBManager dbInstance = DBManager.getInstance();
		String login = request.getParameter("login");
		logger.trace("Login = " + login);
		String password = request.getParameter("password");

		// check login and password is not empty
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}

		// Find user by login in DB
		User user = dbInstance.getUserByLogin(login);
		logger.trace("User: " + user);
		if (user == null || !password.equals(user.getPassword())) {
			logger.trace(Messages.ERR_CANNOT_FIND_USER_BY_LOGIN);
			throw new AppException(Messages.ERR_CANNOT_FIND_USER_BY_LOGIN);
		}

		// Get users role
		Role role = Role.getRole(user);
		logger.trace("Users role: " + role);

		// Redirect user to page by his role
		String forward = Path.ERROR_PAGE;
		if (role == Role.ADMIN || role == Role.DISPATCHER) {
			forward = Path.INDEX_PAGE;
		}
		session.setAttribute("user", user);
		session.setAttribute("userRole", role);
		logger.trace("Set session attribute: user = " + user + " user role = " + role);
		logger.debug("Command \"Login\" finished");

		return forward;
	}
}
