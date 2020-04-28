package ua.nure.ahtirskiy.finalProject.entity;

import ua.nure.ahtirskiy.finalProject.db.Post;

/**
 * Employee entity.
 * 
 * @author Y.Ahtirskiy
 **/

public class Employee extends Entity {

	private static final long serialVersionUID = 1L;

	private String firstName = "";
	private String lastName = "";
	private int postId = -1;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int post_id) {
		this.postId = post_id;
	}

	@Override
	public String toString() {
		return firstName + ", " + lastName + "(" + Post.getPost(this) + ")";
	}

	public boolean isEmpty() {
		if (firstName.isEmpty() && lastName.isEmpty() && postId == -1) {
			return true;
		}
		return false;
	}
}