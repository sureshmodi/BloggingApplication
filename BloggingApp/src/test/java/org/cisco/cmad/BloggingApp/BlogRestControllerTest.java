package org.cisco.cmad.BloggingApp;

import static org.junit.Assert.*;

import javax.ws.rs.core.UriInfo;

import org.cisco.cmad.BloggingApp.Rest.BlogRestController;
import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.junit.Test;

public class BlogRestControllerTest {

	@Test
	public void testAddUser() {
		
		BlogRestController blog = new BlogRestController();
		UserDetails user = new UserDetails("test@gmail.com", "test", "test", 12345,"","test");
								
		try {
			blog.addUser(null);
		} catch (Exception e) {
			System.out.println("Suresh Test: Caught 1st Exception");
			assertTrue(e instanceof InvalidUserCredentialsException);
			
		}
		
		try {
			
			blog.addUser(user);
		} catch (Exception e) {
			System.out.println("Suresh Test: Caught 2nd Exception");
			assertTrue(e instanceof InvalidUserCredentialsException);
			
		}
		
		
	}

	@Test
	public void testCreateBlogpost() {
		
		
	}

	@Test
	public void testPostComment() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetBlogpost() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUserLogin() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateUser() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetallComments() {
		//fail("Not yet implemented");
	}

	@Test
	public void testDeleteBlogpost() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetallBlogposts() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUserDetails() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUserLogout() {
		//fail("Not yet implemented");
	}

}
