package ua.nure.ahtirskiy.finalProject.web.command.dispatcher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.DBManager;
import ua.nure.ahtirskiy.finalProject.entity.Order;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Add new order in DB.
 * 
 * @author Y.Ahtirskiy
 **/

public class DispatcherOrderCommand extends Command {

	private static final long serialVersionUID = 4136431932949469780L;
	private static final Logger logger = Logger.getLogger(DispatcherOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"DispatcherOrder\" starts");

		// get text of order from request
		String problemDescription = request.getParameter("text");

		if (problemDescription.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// add order in DB
		DBManager dbInstance = DBManager.getInstance();
		dbInstance.createDispatcherOrder(problemDescription);
		
		// get new Order list
		List<Order> list = dbInstance.getOrderList();
		
		// add Order list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("order_list", list);
		
		logger.debug("Command \"DispatcherOrder\" finished");
		return Path.DISPATCHER_SHOW_ORDER_LIST;
	}
}