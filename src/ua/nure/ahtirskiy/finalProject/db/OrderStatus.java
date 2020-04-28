package ua.nure.ahtirskiy.finalProject.db;

/**
 * Order status entity.
 * 
 * @author Y.Ahtirskiy
 **/

public enum OrderStatus {
	OPENED, COMPLETED, REJECTED;
	
	public String getName() {
		return name().toLowerCase();
	}
}