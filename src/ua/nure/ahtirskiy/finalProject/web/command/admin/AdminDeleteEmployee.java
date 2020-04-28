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
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Delete employee from DB.
 * 
 *  @author Y.Ahtirskiy
 */

public class AdminDeleteEmployee extends Command{

	private static final long serialVersionUID = -8125367073286813442L;
	Logger logger = Logger.getLogger(AdminDeleteEmployee.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminDeleteEmployee\" starts");
		
		// get id deleting employee
		String employeeId = request.getParameter("employeeId");
		System.out.println("ID ==> " + employeeId);
		
		// validate attribute
		if (employeeId == null || employeeId.isEmpty()) {
			logger.error(Messages.ERR_CANNOT_FIND_EMPLOYEE);
			throw new AppException(Messages.ERR_CANNOT_FIND_EMPLOYEE);
		}
		
		// delete employee from DB
		DBManager dbInstance = DBManager.getInstance();
		if (!dbInstance.deleteEmployee(Integer.parseInt(employeeId))) {
			logger.error(Messages.ERR_CANNOT_DELETE_EMPLOYEE);
			throw new AppException(Messages.ERR_CANNOT_DELETE_EMPLOYEE);
		}
		
		// get new employee list
		List<Employee> list = dbInstance.getEmployeeList();
		
		// add new list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("employee_list", list);
		
		logger.debug("Command \"AdminDeleteEmployee\" finished");
		
		return Path.ADMIN_EMPLOYEE_LIST;
	}
}