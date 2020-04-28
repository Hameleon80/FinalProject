package ua.nure.ahtirskiy.finalProject.db;

/**
 * Flight status entity.
 * 
 * @author Y.Ahtirskiy
 **/

public enum FlightStatus {
	CREATED, READY, IN_FLIGHT, LANDED, COMPLETE;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static int getStatusId(String status) {
		for(FlightStatus f:FlightStatus.values()) {
			if (status.equals(f.getName()))
				return f.ordinal();
		}
		return -1;
	}
}