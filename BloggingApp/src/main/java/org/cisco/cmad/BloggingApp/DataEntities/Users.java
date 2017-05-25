package org.cisco.cmad.BloggingApp.DataEntities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Users {
	
	private String name;
	private long mobile;
	
	@Id
	@Column(name="MAIL_ID")
	private String mailid;
	private String location;
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="user")
	//@JoinColumn(name="VEHICLE_ID")
	private List<Vehicles> vehicle = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Vehicles> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicles> vehicle) {
		this.vehicle = vehicle;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getMailid() {
		return mailid;
	}
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	

}
