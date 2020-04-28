package ua.nure.ahtirskiy.finalProject.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.Path;
import ua.nure.ahtirskiy.finalProject.db.Role;

/**
 * Filtered access to command by role.
 * 
 *  @author Y.Ahtirskiy
 **/

public class CommandAccessFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(CommandAccessFilter.class);

	private Map<Role, List<String>> accessMap = new HashMap<Role, List<String>>();
	private List<String> outOfControl = new ArrayList<String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("Filter \"CommandAccess\" initialization starts");

		// roles
		accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
		accessMap.put(Role.DISPATCHER, asList(filterConfig.getInitParameter("dispatcher")));

		// out of control
		outOfControl = asList(filterConfig.getInitParameter("out-of-control"));

		logger.debug("Filter \"CommandAccess\" initialization finished");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("Filter starts");
		if(isAccess(request)) {
			logger.debug("Filter finished");
			chain.doFilter(request, response);
		} else {
			String error = "You do not have permission to access the requested resource";
			request.setAttribute("errorMessage", error);
			request.getRequestDispatcher(Path.ACCESS_FILTER_ERROR_PAGE).forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * Extract parameters from given string
	 **/

	private List<String> asList(String str) {
		List<String> list = new ArrayList<>();
		StringTokenizer temp = new StringTokenizer(str);
		while (temp.hasMoreTokens()) {
			list.add(temp.nextToken());
		}
		return list;
	}
	
	/**
	 * Determinate is access allowed to command
	 **/
	
	private boolean isAccess(ServletRequest request) {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// get command from request
		String command = request.getParameter("command");

		// determinate is access allowed
		if (command == null || command.isEmpty()) {
			return true;
		}
		
		HttpSession session = httpRequest.getSession(false);
		Role role = (Role) session.getAttribute("userRole");
		
		if (role==null) {
			return outOfControl.contains(command);
		}
		
		return accessMap.get(role).contains(command) || outOfControl.contains(command);
	}
}