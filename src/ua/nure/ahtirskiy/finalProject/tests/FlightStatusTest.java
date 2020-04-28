package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.ahtirskiy.finalProject.db.FlightStatus;

/**
 * Testing enumeration FlightStatus.
 * 
 * @author Y.Ahtirskiy
 **/

public class FlightStatusTest {
	FlightStatus[] flightStatus;
	int i;
	
	@Before
	public void init() {
		flightStatus = FlightStatus.values();
		i = 0;
	}
	
	@After
	public void after() {
		flightStatus = null;
		i = -1;
	}
	
	@Test
	public void testGetName() {
		for(FlightStatus fs: flightStatus) {
			assertEquals(fs.name().toLowerCase(), fs.getName());
		}
	}
	
	@Test
	public void testGetStatusId() {
		for(FlightStatus fs: flightStatus) {
			assertEquals(i, FlightStatus.getStatusId(fs.getName()));
			i++;
		}
	}
}