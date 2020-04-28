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
 * Add in DB new Employee.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminAddNewEmployeeCommand extends Command {

	private static final long serialVersionUID = -7483915196477291302L;

	private static final Logger logger = Logger.getLogger(AdminAddNewEmployeeCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AddNewEmployee\" starts");

		// get new employee from request
		Employee employee = new Employee();
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		if (firstName == null || lastName == null || firstName.isEmpty() || lastName.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		int postId = Post.getPostId(request.getParameter("post"));
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setPostId(postId);

		// add new employee in DB
		DBManager dbInstance = DBManager.getInstance();
		dbInstance.addNewEmployee(employee);

		// get from DB new employee list
		List<Employee> list = dbInstance.getEmployeeList();

		// add list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("employee_list", list);

		logger.debug("Command \"AddNewEmployee\" finished");
		return Path.ADMIN_EMPLOYEE_LIST;
	}
}