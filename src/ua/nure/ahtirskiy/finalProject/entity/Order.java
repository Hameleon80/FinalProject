package ua.nure.ahtirskiy.finalProject.entity;

/**
 * Order entity.
 * 
 * @author Y.Ahtirskiy
 **/

public class Order extends Entity{

	private static final long serialVersionUID = -5420818249814599837L;
	
	String description;
	int statusId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "Problem description: " + System.lineSeparator() 
				+ description;
	}
}