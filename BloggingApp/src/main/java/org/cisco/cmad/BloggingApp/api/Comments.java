package org.cisco.cmad.BloggingApp.api;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
public class Comments {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long commentid;
	private String comment;

	
	//@ManyToOne(fetch=FetchType.LAZY)
	//@XmlTransient
	//@JoinColumn(name="BLOG_ID",foreignKey=@ForeignKey(name="BLOGID_KEY"))
	//@JoinColumn(name="BLOGID")
	//private BlogPost blogpost;
	
	private Date commentdate=new Date();
		
//	public Comments() {
//		
//		// this.commentdate=new Date();
//		
//	}
//
//	public Comments(long commentid, String comment) {
//		this.commentid = commentid;
//		this.comment = comment;
//		this.commentdate = new Date();
//	}
	
	public long getCommentid() {
		return commentid;
	}
	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
//	public BlogPost getBlogpost() {
//		return blogpost;
//	}
//	public void setBlogpost(BlogPost blogpost) {
//		this.blogpost = blogpost;
//	}
	public Date getCommentdate() {
		return commentdate;
	}
	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}
	
	

}