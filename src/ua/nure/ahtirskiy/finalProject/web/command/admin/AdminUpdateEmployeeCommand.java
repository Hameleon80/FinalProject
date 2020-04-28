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
import ua.nure.ahtirskiy.finalProject.db.Post;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Updates information about employee in DB.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminUpdateEmployeeCommand extends Command{

	private static final long serialVersionUID = 1210370081851128322L;
	
	private static final Logger logger = Logger.getLogger(AdminUpdateEmployeeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminUpdateEmployee\" starts");
		Employee employee = new Employee();

		// extract employee from request
		employee.setId(Integer.parseInt(request.getParameter("id")));
		employee.setFirstName(request.getParameter("firstName"));
		employee.setLastName(request.getParameter("lastName"));
		employee.setPostId(Post.getPostId(request.getParameter("post")));
		
		// validate requested information
		if (employee == null || employee.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// update employee in DB
		DBManager dbInstance = DBManager.getInstance();
		dbInstance.updateEmployee(employee);
		
		// set new attribute EmployeeList in session
		List<Employee> list = dbInstance.getEmployeeList();
		HttpSession session = request.getSession(false);
		session.setAttribute("employee_list", list);
		
		logger.debug("Command \"AdminUpdateEmployee\" finished");
		return Path.ADMIN_EMPLOYEE_LIST;
	}
}