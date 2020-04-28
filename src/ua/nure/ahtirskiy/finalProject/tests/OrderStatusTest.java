package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.ahtirskiy.finalProject.db.OrderStatus;

/**
 * Testing enumeration OrderStatus
 * 
 * @author Y.Ahtirskiy
 **/

public class OrderStatusTest {
	
	OrderStatus[] orderStatus;
	
	@Before
	public void init() {
		orderStatus = OrderStatus.values();
	}
	
	@After
	public void after() {
		orderStatus = null;
	}
	
	@Test
	public void testGetName() {
		for(OrderStatus order: orderStatus) {
			assertEquals(order.name().toLowerCase(), order.getName());
		}
	}
}