package ua.nure.ahtirskiy.finalProject.entity;

import java.text.ParseException;
import java.time.LocalDate;

/**
 * Flight entity.
 * 
 * @author Y.Ahtirskiy
 **/

public class Flight extends Entity{

	private static final long serialVersionUID = -2051641094236495813L;
	
	// fields
	int number;
	int statusId;
	LocalDate flightDate;
	String name;
	String cityFrom;
	String cityTo;
	
	//constructors
	
	public Flight() {
		number = 0;
		statusId = 0;
		name = "";
		cityFrom = "";
		cityTo = "";
	}
	
	public Flight(int id, int number, int statusId, LocalDate flightDate, String name, String cityFrom, String cityTo) {
		super(id);
		this.number = number;
		this.statusId = statusId;
		this.flightDate = flightDate;
		this.name = name;
		this.cityFrom = cityFrom;
		this.cityTo = cityTo;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public LocalDate getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}
	
	public void  setFlightDate(String date) throws ParseException {
		flightDate = LocalDate.parse(date);
	}
	
	public String getCityFrom() {
		return cityFrom;
	}
	public void setCityFrom(String cityFrom) {
		this.cityFrom = cityFrom;
	}
	public String getCityTo() {
		return cityTo;
	}
	public void setCityTo(String cityTo) {
		this.cityTo = cityTo;
	}
	
	// other methods
	public boolean isEmpty() {
		boolean result = false;
		if(id==0 && statusId==0 && name.isEmpty() && cityFrom.isEmpty() && cityTo.isEmpty()) {
			result = true;
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Number = " + number
				+ "Name = " + name
				+ "City from = " + cityFrom
				+ "City to = " + cityTo
				+ "Date = " + flightDate;
	}
}