package org.cisco.cmad.BloggingApp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class UserDetails {
	
	public UserCredentials getUsercredentials() {
		return usercredentials;
	}

	public void setUsercredentials(UserCredentials usercredentials) {
		this.usercredentials = usercredentials;
	}

	@Id
	@Column(name="EMAIL_ID")
	private String emailid;
	
	@Column(name="FULL_NAME")
	private String fullname;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="MOBILE_NUMBER")
	private long mobileno;
	
	@Column(name="REGISTRATION_DATE")
	private Date reg_date;
	
	@OneToOne(cascade=CascadeType.PERSIST,mappedBy="userdetails")
	private UserCredentials usercredentials;
	
	public UserDetails() {
		
	}
	
	public UserDetails(String emailid, String fullname, String address, long mobileno ) {
		
		this.emailid=emailid;
		this.fullname=fullname;
		this.address=address;
		this.mobileno=mobileno;
		this.reg_date=new Date();
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobileno() {
		return mobileno;
	}

	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
