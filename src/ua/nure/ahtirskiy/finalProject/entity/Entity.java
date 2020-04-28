package ua.nure.ahtirskiy.finalProject.entity;

import java.io.Serializable;

/**
 * Superclass for all who have id.
 * 
 * @author Y.Ahtirskiy
 **/

public class Entity implements Serializable {

	private static final long serialVersionUID = 8546978579464672423L;

	// field
	protected int id;

	// constructors
	public Entity() {

	}

	public Entity(int id) {
		super();
		this.id = id;
	}

	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
