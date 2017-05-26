package org.cisco.cmad.BloggingApp.DataEntities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Table(name="BOOKS")

public class Books {
	
	@Id
	@Column(name="ISBN_ID")
	private int isbn_id;
	
	@Column(name="TITLE")
	private String title;
	
	/*@Column(name="AUTHOR")
	private Authors author;*/
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="YEAR_of_PUBLISH")
	@Temporal (TemporalType.DATE)
	
	private Date yearofpublish;
	
	@Column(name="EDITION")
	private String edition_no;
	
	public Date getYearofpublish() {
		return yearofpublish;
	}
	public void setYearofpublish(Date yearofpublish) {
		this.yearofpublish = yearofpublish;
	}
	public String getEdition_no() {
		return edition_no;
	}
	public void setEdition_no(String edition_no) {
		this.edition_no = edition_no;
	}
	public int getIsbn_id() {
		return isbn_id;
	}
	public void setIsbn_id(int isbn_id) {
		this.isbn_id = isbn_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
		
	/*public Authors getAuthor() {
		return author;
	}
	public void setAuthor(Authors author) {
		this.author = author;
	}*/
	
}

