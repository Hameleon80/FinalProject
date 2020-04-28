package ua.nure.ahtirskiy.finalProject.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.db.Role;
import ua.nure.ahtirskiy.finalProject.entity.Flight;
import ua.nure.ahtirskiy.finalProject.exception.Messages;

/**
 * Shows flights list.
 * 
 * @author Y.Ahtirskiy
 **/

public class ShowFlightListTag extends TagSupport{

	private static final long serialVersionUID = -2091909419502158907L;
	Logger logger = Logger.getLogger(ShowFlightListTag.class);
	
	@Override
		public int doStartTag() throws JspException {
			logger.debug("Tag \"ShowFlightList\" starts");
			
			// get session and JspWriter from Context
			HttpSession session = pageContext.getSession();
			JspWriter out = pageContext.getOut();
			
			// get flights list from session
			List<Flight> list = (List<Flight>) session.getAttribute("flights_list");
			
			// get user role from session
			Role role = (Role) session.getAttribute("userRole");
			
			try {
				out.write("<table id=\"content-table\">");
				out.write("<tr><th>Number</th>");
				out.write("<th>Flight name</th>");
				out.write("<th>From</th>");
				out.write("<th>To</th>");
				out.write("<th>Date</th></tr>");
				
			} catch (IOException e) {
				logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
				e.printStackTrace();
			}
			
			for(Flight flight: list) {
				try {
					out.write("<tr class=\"content\">");
					out.write("<td>" + flight.getNumber() + "</td>");
					out.write("<td>" + flight.getName() + "</td>");
					out.write("<td>" + flight.getCityFrom() + "</td>");
					out.write("<td>" + flight.getCityTo() + "</td>");
					out.write("<td>" + flight.getFlightDate() + "</td>");
					if (role != null && "admin".equals(role.getName())) {
						out.write("<td><a href=\"controller?command=editFlight&flightId=" + flight.getId() + "\">Edit</a></td>");
						out.write("<td><a href=\"controller?command=deleteFlight&flightId=" + flight.getId() + "\">Delete</a></td>");
					}
					out.write("</tr>");
				} catch (IOException e) {
					logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
					e.printStackTrace();
				}
			}
			
			try {
				out.write("</table>");
			} catch (IOException e) {
				logger.error(Messages.ERR_CANNOT_DISPLAY_TABLE);
				e.printStackTrace();
			}
			
			logger.debug("Tag \"ShowFlightList\" finished");
			return SKIP_BODY;
		}

}
