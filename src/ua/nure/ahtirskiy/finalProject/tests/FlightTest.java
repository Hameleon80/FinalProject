package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.ahtirskiy.finalProject.entity.Flight;

/**
 * Testing method isEmpty() from class Flight.
 * 
 * @author Y.Ahtirskiy
 **/

public class FlightTest {
	
	Flight flight;
	Flight flightNotEmpty;
	
	@Before
	public void init() {
		flight = new Flight();
		flightNotEmpty = new Flight();
		flightNotEmpty.setName("Test");
	}
	
	@After
	public void after() {
		flight = null;
	}
	
	@Test
	public void testFlightIsEmptyTrue() {
		assertEquals(true, flight.isEmpty());
	}
	
	@Test
	public void testFlightIsEmptyFalse() {
		assertEquals(false, flightNotEmpty.isEmpty());
	}
}
