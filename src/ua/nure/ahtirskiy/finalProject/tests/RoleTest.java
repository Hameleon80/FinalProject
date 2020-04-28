package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import ua.nure.ahtirskiy.finalProject.db.Role;
import ua.nure.ahtirskiy.finalProject.entity.User;

/**
 * Testing enumeration Role.
 * 
 * @author Y.Ahtirskiy 
 **/

public class RoleTest {
	
	Role[] roleArray;
	Role role;
	User user;
	
	@Before
	public void init() {
		roleArray = Role.values();
		role = Role.ADMIN;
		user = new User();
		user.setRoleId(0);
	}
	
	@After
	public void after() {
		roleArray = null;
		user = null;
	}
	
	@Test
	public void testGetName() {
		for(Role role: roleArray) {
			assertEquals(role.name().toLowerCase(), role.getName());
		}
	}
	
	@Test
	public void testGetRole() {
		assertEquals(role, Role.getRole(user));
	}
}
