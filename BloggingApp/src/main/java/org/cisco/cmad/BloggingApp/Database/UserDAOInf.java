package org.cisco.cmad.BloggingApp.Database;

import org.cisco.cmad.BloggingApp.api.UserDetails;

public interface UserDAOInf {
	
		public void createUser(UserDetails userdetails) throws Exception;
		public UserDetails updateProfile(UserDetails userdetails);
		public UserDetails retreiveUser(String userid);
			

}
