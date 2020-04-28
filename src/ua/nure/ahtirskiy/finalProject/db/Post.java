package ua.nure.ahtirskiy.finalProject.db;

import ua.nure.ahtirskiy.finalProject.entity.Employee;

/**
 * Employee post entity.
 * 
 * @author Y.Ahtirskiy
 **/

public enum Post {
	PILOT, NAVIGATOR, RADIOMAN, STEWARDESS;
	
	public String getName() {
		return name().toLowerCase();
	}
	
	public static Post getPost(Employee employee) {
		int postId = employee.getPostId();
		return Post.values()[postId];
	}
	
	public static int getPostId (String postName) {
		int res=-1;
		switch (postName) {
		case "PILOT" : res=0;
		break;
		case "NAVIGATOR": res=1;
		break;
		case "RADIOMAN": res=2;
		break;
		case "STEWARDESS": res=3;
		break;
		}
		return res;
	}
}