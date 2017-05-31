package org.cisco.cmad.BloggingApp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
//@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BlogPost {
	
	@Id
	private String blogpostid;
	private String title;
	private String blogContent;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@XmlTransient
	private UserDetails user;
	
	private Date datecreated=new Date();
	
	
	@OneToMany(fetch=FetchType.LAZY)
	
	private List<Comments> commentslist = new ArrayList<>();
	
//	public BlogPost() {
//		
//		// this.datecreated=new Date();
//		
//	}
//
//	public BlogPost(String blogpostid, String title, String blogContent) {
//		
//		this.blogpostid = blogpostid;
//		this.title = title;
//		this.blogContent = blogContent;
//		this.datecreated = new Date();
//	}

	public String getBlogpostid() {
		return blogpostid;
	}

	public void setBlogpostid(String blogpostid) {
		this.blogpostid = blogpostid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBlogContent() {
		return blogContent;
	}

	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Date getDatecreated() {
		return datecreated;
	}

	public void setDatecreated(Date datecreated) {
		this.datecreated = datecreated;
	}

	public List<Comments> getCommentslist() {
		return commentslist;
	}

	public void setCommentslist(List<Comments> commentslist) {
		this.commentslist = commentslist;
	}
	
	
	

}
