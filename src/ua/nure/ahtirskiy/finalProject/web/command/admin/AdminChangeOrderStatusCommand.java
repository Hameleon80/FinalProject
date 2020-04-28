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
import ua.nure.ahtirskiy.finalProject.db.OrderStatus;
import ua.nure.ahtirskiy.finalProject.entity.Order;
import ua.nure.ahtirskiy.finalProject.exception.AppException;
import ua.nure.ahtirskiy.finalProject.exception.Messages;
import ua.nure.ahtirskiy.finalProject.web.command.Command;

/**
 * Updates dispatcher's order status.
 * 
 * @author Y.Ahtirskiy
 **/

public class AdminChangeOrderStatusCommand extends Command {

	private static final long serialVersionUID = -2885837907302744373L;
	private static final Logger logger = Logger.getLogger(AdminChangeOrderStatusCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"AdminChangeOrderStatus\" starts");

		// get order ID and status ID from request
		String statusId = request.getParameter("orderStatus");
		String orderId = request.getParameter("orderId");
		
		// validate fields
		if (statusId == null || statusId.isEmpty() || orderId == null || orderId.isEmpty()) {
			logger.error(Messages.ERR_EMPTY_FIELD);
			throw new AppException(Messages.ERR_EMPTY_FIELD);
		}
		
		// update order's status and get new orders list
		DBManager dbInstance = DBManager.getInstance();
		List<Order> list = dbInstance.updateOrderStatus(Integer.parseInt(statusId), Integer.parseInt(orderId));

		// add updating orders list
		HttpSession session = request.getSession(false);
		session.setAttribute("order_list", list);
		logger.debug("Command \"AdminChangeOrderStatus\" finished");
		return Path.ADMIN_ORDER_LIST;
	}
}