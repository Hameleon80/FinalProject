package ua.nure.ahtirskiy.finalProject.web.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;


public class ContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		
		log("Initialization of Servlet context is started");
		ServletContext servlCont = sce.getServletContext();
		
		initLog4j(servlCont);
		initialContainer();
		Properties locales = initialLocale(servlCont);
		// save locale setting to servlet context
		servlCont.setAttribute("locales", locales);
		
		log("Initialization of Servlet context is finished");
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		log("Servlet context destruction finished");
	}
	
	private void log(String message) {
		System.out.println("[ContextListener ]" + message);
	}
	
	/**
	 * Initialization Log4j
	 * 
	 * @param sc Servlet context
	 **/
	
	private void initLog4j (ServletContext sc) {
		log("Initialization of LOG4J is started");
		
		try {
			PropertyConfigurator.configure(sc.getRealPath("WEB-INF/log4j.properties"));
			log("Log4j initializeted");
		} catch (Exception ex) {
			log("Can't initializated Log4j");
			ex.printStackTrace();
		}
		log("Initialization of LOG4J is finished");
	}
	
	/**
	 * Initialization Command Container.
	 **/
	
	private void initialContainer() {
		log("Initialization of Command container starts");
		// initialize commands container
		// just load class to JVM
		try {
			Class.forName("ua.nure.ahtirskiy.finalProject.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException("Can't initialized Cammand container");
		}
		log("Initialization of Command container finished");
	}
	
	/**
	 * Initialization Locale.
	 * 
	 **/
	
	 private Properties initialLocale(ServletContext servletContext) {
		 log("Initialization of Locale starts");
		 
		 Properties locales = new Properties();
		 String path = servletContext.getRealPath("WEB-INF/locales.properties");
		 try {
			 locales.load(new FileInputStream(path));
		 } catch (IOException ex) {
			 log("Cannot set locale properties.");
		 }
		 
		 log("Initialization of Locale finished");
		 return locales;
	 }
}