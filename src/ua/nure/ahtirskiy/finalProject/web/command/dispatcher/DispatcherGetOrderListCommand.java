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

public class DispatcherGetOrderListCommand extends Command{

	private static final long serialVersionUID = 9189688591137179209L;
	Logger logger = Logger.getLogger(DispatcherGetOrderListCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		logger.debug("Command \"DispatcherGetOrderList\" starts");
		
		// get Order list from DB
		DBManager dbInstance = DBManager.getInstance();
		List<Order> list = dbInstance.getOrderList();
		
		// add Order list in session
		HttpSession session = request.getSession(false);
		session.setAttribute("order_list", list);
		
		logger.trace("Order list added");
		logger.debug("Command \"DispatcherGetOrderList\" starts");
		return Path.DISPATCHER_SHOW_ORDER_LIST;
	}
}