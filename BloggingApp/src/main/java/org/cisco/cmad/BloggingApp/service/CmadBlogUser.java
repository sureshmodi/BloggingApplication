package org.cisco.cmad.BloggingApp.service;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cisco.cmad.BloggingApp.Database.BlogPostDAOInf;
import org.cisco.cmad.BloggingApp.Database.CommentsDAOInf;
import org.cisco.cmad.BloggingApp.Database.JPABlogAppDAO;
import org.cisco.cmad.BloggingApp.Database.UserDAOInf;
import org.cisco.cmad.BloggingApp.Rest.BlogRestController;
import org.cisco.cmad.BloggingApp.Rest.Errormsg;
import org.cisco.cmad.BloggingApp.api.BlogPostNotFoundException;
import org.cisco.cmad.BloggingApp.api.BlogUser;
import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.api.UserNotFoundException;

public class CmadBlogUser implements BlogUser{

	private UserDAOInf userdao = new JPABlogAppDAO();
		
	@Override
	public void createUser(UserDetails user) {
		
		try {
			userdao.createUser(user);
		} catch (Exception ex) {
			System.out.println("Suresh,Caught Exception:"+ex.toString());
			Errormsg msg = new Errormsg("USer "+user.getUserid()+" already exists");
			
		}	
		
	    user.setPassword("xxx");
		
	}

	@Override
	public UserDetails userLogin(UserDetails user) {
		
		UserDetails userdb = null;
		System.out.println("Received Userid:"+user.getUserid());
		
		userdb = userdao.retreiveUser(user.getUserid());
		
		if (userdb != null) {
			
			if ((user.getPassword().equals(userdb.getPassword()))) {
				userdb.setPassword("xxxxxx");
				return userdb;
				
			} else {
				System.out.println("Suresh: Invalid userid/password");
				throw new InvalidUserCredentialsException("Invalid userid/password");
				
			}		
		} else {
			System.out.println("Suresh: Uknown user");
			throw new UserNotFoundException("Unknown User");
		}
			
		
	}

	@Override
	public UserDetails updateUser(UserDetails user) {
		
		UserDetails userdb = null;
		userdb = userdao.updateProfile(user);
		return userdb;
				
	}
	
	
}
