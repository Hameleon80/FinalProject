package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.ahtirskiy.finalProject.entity.Employee;

/**
 * Testing method isEmpty() from class Employee.
 * 
 * @author Y.Ahtirskiy
 **/

public class EmployeeTest {
	
	Employee employee;
	Employee employee2;
	
	@Before
	public void init() {
		employee = new Employee();
		employee2 = new Employee();
		employee2.setFirstName("John");
	}
	
	@After
	public void afterTest() {
		employee = null;
	}
	
	@Test
	public void testEmployeeIsEmpty() {
		assertEquals(true, employee.isEmpty());
	}
	
	@Test
	public void testEmployeeIsEmptyFalse() {
		assertEquals(false, employee2.isEmpty());
	}
}
