package ua.nure.ahtirskiy.finalProject.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ua.nure.ahtirskiy.finalProject.db.Post;

/**
 * Testing enumeration Post.
 * 
 * @author Y.Ahtirskiy
 **/

public class PostTest {
	
	Post[] postArray;
	int i;
	
	@Before
	public void init() {
		postArray = Post.values();
		i = 0;
	}
	
	@After
	public void after() {
		postArray = null;
		i = -1;
	}
	
	@Test
	public void testGetName() {
		for (Post post: postArray) {
			assertEquals(post.name().toLowerCase(), post.getName());
		}
	}
	
	@Test
	public void testetPostId() {
		for(Post post: postArray) {
			assertEquals(i, Post.getPostId(post.name()));
			i++;
		}
	}
}