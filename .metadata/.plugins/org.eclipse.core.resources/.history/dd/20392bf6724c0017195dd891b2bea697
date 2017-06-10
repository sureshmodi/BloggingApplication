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
import org.cisco.cmad.BloggingApp.api.UserAlreadyExistsException;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.api.UserNotFoundException;
import org.cisco.cmad.BloggingApp.api.UserRegistrationFailedException;
import org.glassfish.jersey.internal.util.ExceptionUtils;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class CmadBlogUser implements BlogUser{

	private UserDAOInf userdao = new JPABlogAppDAO();
		
	@Override
	public void createUser(UserDetails user) {
		
		try {
			userdao.createUser(user);
			user.setPassword("xxx");
		} catch (Exception ex) {
			
			System.out.println("Suresh,Caught Exception:"+ex.toString());
			
			Throwable th = ex.getCause();
										
			while(th.getCause().getCause() != null)
					th = th.getCause();			
							
			System.out.println("Suresh: root cause: "+th.getCause());
			System.out.println("Suresh: message: "+th.getCause().getMessage());
			
			if(th.getCause() instanceof MySQLIntegrityConstraintViolationException || 
			   th.getCause() instanceof com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException) {
					throw new UserAlreadyExistsException("User "+user.getUserid()+" already exists");
			} else {
					throw new UserRegistrationFailedException("User registration Failed");
			}		
			
		}	
		
	    
		
	}

	@Override
	public UserDetails userLogin(UserDetails user) {
		
		UserDetails userdb = null;
		System.out.println("Received Userid:"+user.getUserid());
		
		userdb = userdao.retreiveUser(user.getUserid());
		
		if (userdb != null) {
			
			if ((user.getPassword().equals(userdb.getPassword()))) {
				userdb.setPassword("xxxxxxx");
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
