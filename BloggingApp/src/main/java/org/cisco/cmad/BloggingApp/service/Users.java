package org.cisco.cmad.BloggingApp.service;

import java.util.ArrayList;
import java.util.List;

import org.cisco.cmad.BloggingApp.api.BlogUsers;

public class Users {
	
	
	public List<BlogUsers> getusers() {
			BlogUsers user1 = new BlogUsers(1,"suresh","sureshmodhi@gmail.com");
			BlogUsers user2 = new BlogUsers(2,"prasanna","a.prasanna.lakshmi@gmail.com");
			
			List<BlogUsers> users = new ArrayList<>();
			users.add(user1);
			users.add(user2);
			
			return users;
	}

}
