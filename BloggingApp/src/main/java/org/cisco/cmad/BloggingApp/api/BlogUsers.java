package org.cisco.cmad.BloggingApp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BlogUsers {
	
	private long id;
	private String name;
	private Date registered;
	private String emailid;
	private List<Link> links=new ArrayList<>();
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public BlogUsers() {
		
	}
	
	public BlogUsers(long id, String name, String emailid) {
		
		this.id = id;
		this.name = name;
		this.registered = new Date();
		this.emailid = emailid;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegistered() {
		return registered;
	}
	public void setRegistered(Date registered) {
		this.registered = registered;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	
	public void addLinks(String uri, String rel) {
			  Link link = new Link();
			  link.setUri(uri);
			  link.setRel(rel);
			  links.add(link);
				   
	}
	
}
