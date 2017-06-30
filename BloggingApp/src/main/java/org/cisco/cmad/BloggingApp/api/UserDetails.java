package org.cisco.cmad.BloggingApp.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="USER_DETAILS")
public class UserDetails {
	
	@Column(name="EMAIL_ID")
	private String emailid;
	
	@Column(name="FULL_NAME")
	private String fullname;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="MOBILE_NUMBER")
	private long mobileno;
	
	@Column(name="REGISTRATION_DATE")
	private Date reg_date = new Date();
	
	@Id
	@Column(name="USER_ID")
	private String userid;
	
	@Column(name="PASSWORD")
	private String password;
		
	@Transient
	private List<Link> links = new ArrayList<>();
	
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY,cascade={CascadeType.REMOVE})
//	@CollectionTable(name="BlogPost")
	@MapKeyColumn(name="BLOGID_KEY",nullable=true)
	@Column(name="blogpostid")
	@XmlTransient
	private Map<String, BlogPostEntity> bloglist = new HashMap<String, BlogPostEntity>();
	
	
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

	public Map<String, BlogPostEntity> getBloglist() {
		return bloglist;
	}

	public void setBloglist(Map<String, BlogPostEntity> bloglist) {
		this.bloglist = bloglist;
	}

	public UserDetails() {
		
		//this.reg_date=new Date();
		
	}
	
	public UserDetails(String emailid, String fullname, String address, long mobileno,
					   String userid, String password ) {
		
		this.emailid=emailid;
		this.fullname=fullname;
		this.address=address;
		this.mobileno=mobileno;
		this.reg_date=new Date();
		this.userid=userid;
		this.password=password;
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
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLinks(URI uri, String ref, String blogtitle) {

			Link link = new Link();
			link.setUri(uri);
			link.setReference(ref);
			link.setBlogtitle(blogtitle);
			links.add(link);
			
	}
	
}
