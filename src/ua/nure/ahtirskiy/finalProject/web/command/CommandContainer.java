package ua.nure.ahtirskiy.finalProject.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.SetCrewCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.StatusListCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminAddNewEmployeeCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminAddNewFlightCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminChangeOrderStatusCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminDeleteEmployee;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminDeleteFlight;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminEditFlightCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminEmployeeListCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminOrderListCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminEmployeeCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminUpdateFlightCommand;
import ua.nure.ahtirskiy.finalProject.web.command.admin.AdminUpdateEmployeeCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.DispatcherGetOrderListCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.DispatcherOrderCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.DispatcherSearchCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.EmployeeListCommand;
import ua.nure.ahtirskiy.finalProject.web.command.dispatcher.StatusSetCommand;

/**
 * Holder for all commands.
 * 
 * @author Y.Ahtirskiy
**/

public class CommandContainer {
	private static final Logger logger = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand());
		commands.put("noCommand", new NoCommand());
		commands.put("logout", new LogOutCommand());
		commands.put("updateSettings", new UpdateSettingsCommand());
		commands.put("listFlights", new ListFlightsCommand());
		commands.put("searchByNumber", new SearchByNumberCommand());
		commands.put("advancedSearch", new AdvancedSearchCommand());
		commands.put("sortByNumber", new SortByNumberCommand());
		commands.put("sortByName", new SortByNameCommand());
		
		// dispatcher command
		commands.put("dispatcherSearch", new DispatcherSearchCommand());
		commands.put("setCrew", new SetCrewCommand());
		commands.put("setStatus", new StatusSetCommand());
		commands.put("getEmployeeList", new EmployeeListCommand());
		commands.put("getStatusList", new StatusListCommand());
		commands.put("dispatcherOrder", new DispatcherOrderCommand());
		commands.put("dispatcherShowOrdersList", new DispatcherGetOrderListCommand()); 
		
		// admin commands
		commands.put("addNewFlight", new AdminAddNewFlightCommand());
		commands.put("editFlight", new AdminEditFlightCommand());
		commands.put("updateFlight", new AdminUpdateFlightCommand());
		commands.put("listEmployee", new AdminEmployeeListCommand());
		commands.put("editEmployee", new AdminEmployeeCommand());
		commands.put("updateEmployee", new AdminUpdateEmployeeCommand());
		commands.put("addNewEmployee", new AdminAddNewEmployeeCommand());
		commands.put("listOrders", new AdminOrderListCommand());
		commands.put("changeOrderStatus", new AdminChangeOrderStatusCommand());
		commands.put("deleteEmployee", new AdminDeleteEmployee());
		commands.put("deleteFlight", new AdminDeleteFlight());
		
		logger.debug("Command container create");
		logger.trace("Number of commands: " + commands.size());
	}
	
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			logger.trace("Can't find command: " + commandName);
			return commands.get("noCommand");
		}
		return commands.get(commandName);
	}
}
