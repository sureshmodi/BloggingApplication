package org.cisco.cmad.BloggingApp.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class BlogPost {
	
	@Id
	private String blogpostid;
	private String title;
	private String blogContent;
	private String username;
	
	

}