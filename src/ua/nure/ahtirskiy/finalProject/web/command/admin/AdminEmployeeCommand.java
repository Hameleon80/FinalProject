package ua.nure.ahtirskiy.finalProject.web.command.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Choose employee information from employee list (from session).
 *
 * @author Y.Ahtirskiy
 **/

public class AdminEmployeeCommand extends Command{

	private static final long serialVersionUID = -6203688029628680356L;
	private static final Logger logger = Logger.getLogger(AdminEmployeeCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminUpdateEmployee\" starts"); 
		HttpSession session = request.getSession(false);
		
		// get employee from session's attribute
		List<Employee> list = (List<Employee>)session.getAttribute("employee_list");
		int employeeId = Integer.parseInt(request.getParameter("employeeId"));
		Employee employee = new Employee();
		for(Employee e: list) {
			if (employeeId == e.getId()) {
				employee = e;
			}
		}
		
		// check that employee not empty
		if (employee.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// add employee in session's attribute 
		session.setAttribute("employee", employee);
		
		logger.debug("Command \"AdminUpdateEmployee\" finished");
		return Path.ADMIN_EMPLOYEE_UPDATE;
	}
}