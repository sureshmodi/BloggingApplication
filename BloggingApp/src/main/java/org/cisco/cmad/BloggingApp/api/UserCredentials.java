package org.cisco.cmad.BloggingApp.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

// @Entity
public class UserCredentials {
	
	@Id
	private String userid;
	private String password;
		
	public UserCredentials() {
		
	}
	
	public UserCredentials(String userid, String password) {
		
		this.userid = userid;
		this.password = password;
	}

	@OneToOne
	@JoinColumn(name="EMAIL_ID")
	private UserDetails userdetails;
	
	public UserDetails getUserdetails() {
		return userdetails;
	}

	public void setUserdetails(UserDetails userdetails) {
		this.userdetails = userdetails;
	}

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
