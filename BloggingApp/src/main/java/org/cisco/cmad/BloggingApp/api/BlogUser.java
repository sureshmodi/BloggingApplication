package org.cisco.cmad.BloggingApp.api;

public interface BlogUser {
	
	public void createUser(UserDetails userdetails);
	public UserDetails updateUser(UserDetails user, String jwttoken);
	public UserDetails userLogin(UserDetails user);
	

}
