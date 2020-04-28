package ua.nure.ahtirskiy.finalProject.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.db.Post;
import ua.nure.ahtirskiy.finalProject.db.Role;
import ua.nure.ahtirskiy.finalProject.entity.Employee;
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * Printing employee list.
 * 
 * @author Y.Ahtirskiy
 **/

public class ShowEmployeeListTag extends TagSupport {

	private static final long serialVersionUID = 9016186946372199286L;

	Logger logger = Logger.getLogger(ShowEmployeeListTag.class);

	@Override
	public int doStartTag() throws JspException {
		logger.debug("Tag \"ShowEmployeeList\" starts");

		HttpSession session = pageContext.getSession();
		// get employee list and user role from session
		List<Employee> list = (List<Employee>) session.getAttribute("employee_list");
		Role role = (Role) session.getAttribute("userRole");

		// get jsp writer from page context
		JspWriter out = pageContext.getOut();

		// print table
		try {
			// caption
			out.write("<table id=\"content-table\">");
			out.write("<tr><th>First Name</th>");
			out.write("<th>Last name</th>");
			out.write("<th>Post</th></tr>");
		} catch (IOException ex) {
			logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
			ex.printStackTrace();
		}

		for (Employee employee : list) {
			try {
				out.write("<tr class=\"content\">");
				out.write("<td>" + employee.getFirstName() + "</td>");
				out.write("<td>" + employee.getLastName() + "</td>");
				out.write("<td>" + Post.getPost(employee).getName() + "</td>");
				if ("admin".equals(role.getName())) {
					out.write("<td><a href=\"controller?command=editEmployee&employeeId=" + employee.getId() + "\">Edit</a></td>");
					out.write("<td><a href=\"controller?command=deleteEmployee&employeeId=" + employee.getId() + "\">Delete</a></td>");
				}
				out.write("</tr>");
			} catch (IOException ex) {
				logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
				ex.printStackTrace();
			}
		}
		try {
			out.write("</table>");
		} catch (IOException ex) {
			logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
			ex.printStackTrace();
		}
		logger.debug("Tag \"ShowEmployeeList\" finished");
		return SKIP_BODY;
	}
}