package ua.nure.ahtirskiy.finalProject.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Filter set encoding for page.
 * 
 * @author Y.Ahtirskiy
 **/

public class EncodingFilter implements Filter{
	
	private static Logger logger = Logger.getLogger(EncodingFilter.class);
	private String encoding;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("Encoding filter starts");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		logger.trace("Request uri: " + httpRequest.getRequestURI());
		String requestEncoding = request.getCharacterEncoding();
		if(requestEncoding==null) {
			logger.trace("Request encoding = null, set encoding: " + encoding);
			request.setCharacterEncoding(encoding);
		}
		
		logger.debug("Encoding filter finished");
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.debug("Encoding filter initialization starts");
		//get encoding from web.xml
		encoding = filterConfig.getInitParameter("encoding");
		logger.trace("Encoding from web.xml ==> " + encoding);
		logger.debug("Encoding filter initialization finished");	
	}

	@Override
	public void destroy() {
		// do nothing
	}
}