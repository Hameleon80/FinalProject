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
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Get Employee list for dispatcher.
 * 
 * @author Y.Ahtirskiy
 **/

public class EmployeeListCommand extends Command {

	private static final long serialVersionUID = 690083905301097436L;

	private final Logger logger = Logger.getLogger(EmployeeListCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"GetEmployeeList\" starts");
		List<Employee> list = null;

		// get employee list from DB
		list = DBManager.getInstance().getEmployeeList();

		// check employee list is not empty
		if (list == null || list.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_LIST);
			throw new AppException(Messages.ERR_CANNOT_OBTAIN_EMPLOYEE_LIST);
		}

		// add employees list to session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("employee_list", list);
		logger.debug("Command \"GetEmployeeList\" finished");
		return Path.DISPATCHER_SET_CREW_PAGE;
	}
}