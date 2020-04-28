package ua.nure.ahtirskiy.finalProject.web.command.admin;

import java.io.IOException;
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
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Obtain from DB employees list.
 *
 * @author Y.Ahtirskiy
 **/

public class AdminEmployeeListCommand extends Command{

	private static final long serialVersionUID = -6203688029628680356L;
	
	private static final Logger logger = Logger.getLogger(AdminEmployeeListCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminUpdateEmployee\" starts");
		DBManager dbInstance = DBManager.getInstance();
		
		// get employee list from DB
		List<Employee> list = dbInstance.getEmployeeList();
		
		// add employee list to session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("employee_list", list);
		logger.debug("Command \"AdminUpdateEmployee\" finished");
		return Path.ADMIN_EMPLOYEE_LIST;
	}
}