package ua.nure.ahtirskiy.finalProject.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.ahtirskiy.finalProject.entity.Flight;

/**
 * Sort entity.
 * 
 * @author Y.Ahtirskiy
 **/

public final class Sort  {

	private Sort() {}
	
	// comparators
	public static final Comparator<Flight> SORT_FLIGHT_BY_NUMBER = new Comparator<Flight>() {
		public int compare(Flight fly1, Flight fly2) {
			return Integer.valueOf(fly1.getNumber()).compareTo(fly2.getNumber());
		}
	};
	
	public static final Comparator<Flight> SORT_FLIGHT_BY_NAME = new Comparator<Flight>() {
		public int compare(Flight fly1, Flight fly2) {
			return fly1.getName().compareTo(fly2.getName());
		}
	};
	// sorted methods
	
	public static void sortByNumber(List<Flight> list) {
		Collections.sort(list, SORT_FLIGHT_BY_NUMBER);		
	}
	
	public static void sortByName(List<Flight> list) {
		Collections.sort(list, SORT_FLIGHT_BY_NAME);
	}
}
