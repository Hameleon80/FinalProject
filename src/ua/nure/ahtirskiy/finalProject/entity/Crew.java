package ua.nure.ahtirskiy.finalProject.entity;

/**
 * Crew entity.
 * 
 * @author Y.Ahtirskiy
 **/

public class Crew extends Entity{

	private static final long serialVersionUID = 665359955475709358L;
	
	private int firstPilot_id;
	private int secondPilot_id;
	private int navigator_id;
	private int radioman_id;
	private int stewardess1_id;
	private int stewardess2_id;
	
	public int getFirstPilot_id() {
		return firstPilot_id;
	}
	public void setFirstPilot_id(int firstPilot_id) {
		this.firstPilot_id = firstPilot_id;
	}
	public int getSecondPilot_id() {
		return secondPilot_id;
	}
	public void setSecondPilot_id(int secondPilot_id) {
		this.secondPilot_id = secondPilot_id;
	}
	public int getNavigator_id() {
		return navigator_id;
	}
	public void setNavigator_id(int navigator_id) {
		this.navigator_id = navigator_id;
	}
	public int getRadioman_id() {
		return radioman_id;
	}
	public void setRadioman_id(int radioman_id) {
		this.radioman_id = radioman_id;
	}
	public int getStewardess1_id() {
		return stewardess1_id;
	}
	public void setStewardess1_id(int stewardess1_id) {
		this.stewardess1_id = stewardess1_id;
	}
	public int getStewardess2_id() {
		return stewardess2_id;
	}
	public void setStewardess2_id(int stewardess2_id) {
		this.stewardess2_id = stewardess2_id;
	}
	
	@Override
	public String toString() {
		return "First pilot: " + firstPilot_id +
				"Second pilot: " + secondPilot_id +
				"Navigator: " + navigator_id +
				"Radioman: " + radioman_id +
				"Stewardess #1: " + stewardess1_id +
				"Stewardess #2: " + stewardess2_id;
	}
}