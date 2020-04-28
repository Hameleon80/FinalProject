package ua.nure.ahtirskiy.finalProject.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.web.command.Command;
import ua.nure.ahtirskiy.finalProject.web.command.CommandContainer;

/**
 * Main servlet controller.
 * 
 * @author Y.Ahtirskiy
 **/

@WebServlet("/controller")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 546978312125545646L;
	private static final Logger logger = Logger.getLogger(Controller.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.debug("Controller starts");
		// get command from request
		String commandName = request.getParameter("command");
		logger.trace("Command name: " + commandName);

		// get command by name
		Command command = CommandContainer.get(commandName);
		String forward = Path.ERROR_PAGE;
		try {
			forward = command.execute(request, response);
		} catch (AppException ex) {
			HttpSession session = request.getSession(false);
			logger.error(ex.getMessage());
			session.setAttribute("errorMessage", ex.getMessage());
		}
		logger.trace("Forward adress: " + forward);
		logger.debug("Controller finished");
		response.sendRedirect(forward);
	}
}