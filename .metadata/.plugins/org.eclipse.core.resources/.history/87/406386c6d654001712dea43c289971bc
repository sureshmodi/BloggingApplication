package org.cisco.cmad.BloggingApp.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Table(name="BLOGPOST")

public class BlogPostEntity {
	
	@Id
	private String blogpostid;
	private String title;
	
	@Lob
	private String blogcontent;
				
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@XmlTransient
	private UserDetails user;
	
	private Date datecreated=new Date();
		
	@OneToMany(mappedBy="blogpost",fetch=FetchType.LAZY,cascade={CascadeType.REMOVE})
	@MapKeyColumn(name="COMMENTS_KEY",nullable=true)
	@Column(name="commentid")
	@XmlTransient
	private List<Comments> commentslist = new ArrayList<>();
	// private Map<String, Comments> comments = new HashMap<String, Comments>();
	
	@Transient
	private long comments_count=0;
		
	@Transient
	private List<Link> links = new ArrayList<>();
	
	public BlogPostEntity() {
		
		// this.datecreated=new Date();
		
	}
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
		return blogcontent;
	}

	public void setBlogContent(String blogContent) {
		this.blogcontent = blogContent;
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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLinks(URI uri, String ref) {

			Link link = new Link();
			link.setUri(uri);
			link.setReference(ref);
			links.add(link);
			
	}
	
	public long getComments_count() {
		return comments_count;
	}

	public void setComments_count(long comments_count) {
		this.comments_count = comments_count;
	}
	

}
