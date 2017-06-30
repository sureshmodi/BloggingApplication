package org.cisco.cmad.BloggingApp;

import static org.junit.Assert.*;

import org.cisco.cmad.BloggingApp.Rest.BlogRestController;
import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.service.CmadBlogUser;
import org.junit.Assert;
import org.junit.Test;

import javassist.expr.Instanceof;

import static org.junit.Assert.*;

import net.bytebuddy.dynamic.TypeResolutionStrategy.Passive;

public class BlogUserTest {

	@Test
	public void testCreateUser() {
		
				
	}

	@Test
	public void testUserLogin() {
		
		UserDetails userdetails = null;
		CmadBlogUser user = new CmadBlogUser();
		
		
		
		try {
			
			user.createUser(userdetails);
			
		} catch (Exception e) {
			System.out.println("Suresh Test: Caught 1st Exception");
			e.printStackTrace();
			e.getMessage();
			assertTrue(e instanceof NullPointerException);
			
		}
	}

	@Test
	public void testUpdateUser() {
		
		UserDetails userdetails = null;
		CmadBlogUser user = new CmadBlogUser();
		
		
		
		try {
			
			user.updateUser(userdetails);
			
		} catch (Exception e) {
			System.out.println("Suresh Test: Caught 1st Exception");
			e.printStackTrace();
			e.getMessage();
			assertTrue(e instanceof NullPointerException);
			
		}
	}

	@Test
	public void testGetUserDetails() {
		UserDetails userdetails = null;
		CmadBlogUser user = new CmadBlogUser();
		
			
		try {
			
			user.getUserDetails(null);
			
		} catch (Exception e) {
			System.out.println("Suresh Test: Caught 1st Exception");
			e.printStackTrace();
			e.getMessage();
			assertTrue(e instanceof InvalidUserCredentialsException);
			
		}
	}

}
