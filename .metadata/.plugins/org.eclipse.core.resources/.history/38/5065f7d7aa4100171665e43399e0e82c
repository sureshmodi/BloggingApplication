package org.cisco.cmad.BloggingApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.cisco.cmad.BloggingApp.Database.Database;
import org.cisco.cmad.BloggingApp.Exception.DataNotFoundException;
import org.cisco.cmad.BloggingApp.api.BlogUsers;

public class ProfileMgmt {
	
		private Map<Long,BlogUsers> blogusers = Database.getProfiles();
		
		public ProfileMgmt() {
					blogusers.put(1L, new BlogUsers(1,"suresh","sureshmodhi@gmail.com"));
					blogusers.put(2L, new BlogUsers(2,"Lakshmi Prasanna","a.prasanna.lakshmi@gmail.com"));
					blogusers.put(3L, new BlogUsers(3,"Vihaan Modi","vihaan.modi@gmail.com"));
		}
		
		public List<BlogUsers> getallUsers() {
		
			return new ArrayList<BlogUsers>(blogusers.values());
				
		}
		
		public BlogUsers getUser(Long id) {
			
				if (blogusers.containsKey(id) == true) {
							      	BlogUsers user = blogusers.get(id);
							      	return user;
				} else {
									throw new DataNotFoundException("User with ID "+id+"  not found");
				}
			
		}

		public BlogUsers  addUser(BlogUsers user) {
			user.setId(blogusers.size()+1);
			blogusers.put(user.getId(), user);
			return user;
		}
		
		public BlogUsers updateUser(long id,BlogUsers user) {
			  blogusers.put(id, user);
				return user ;
		}
		
		public void  deleteUser(Long Id) {
			
				 blogusers.remove(Id);
		}
		

}
