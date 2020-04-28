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
import ua.nure.ahtirskiy.finalProject.entity.Order;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Obtain list of dispatcher's orders.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminOrderListCommand extends Command{

	private static final long serialVersionUID = -1129358144322093000L;
	
	private static final Logger logger = Logger.getLogger(AdminOrderListCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminOrderList\" starts");
		DBManager dbInstance = DBManager.getInstance();
		
		// get orders list (from dispatcher)
		List<Order> list = dbInstance.getOrderList();
		
		// add orders list to session's attribute
		HttpSession session = request.getSession(false);
		session.setAttribute("order_list", list);
		logger.debug("Command \"AdminOrderList\" finished");
		return Path.ADMIN_ORDER_LIST;
	}
}