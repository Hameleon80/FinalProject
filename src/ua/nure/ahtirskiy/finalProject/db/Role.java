package ua.nure.ahtirskiy.finalProject.db;

import ua.nure.ahtirskiy.finalProject.entity.User;

/**
 * Role entity.
 * 
 * @author Y.Ahtirskiy
 **/

public enum Role {
	ADMIN, DISPATCHER;
	
	public static Role getRole (User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
}
