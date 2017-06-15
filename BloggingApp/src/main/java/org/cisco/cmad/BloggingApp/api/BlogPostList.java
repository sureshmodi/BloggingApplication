package org.cisco.cmad.BloggingApp.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BlogPostList {
	
	//private List<BlogPostEntity> bloglist = new ArrayList<>();
	
	private List<Link> links = new ArrayList<>();

//	public List<BlogPostEntity> getBloglist() {
//		return bloglist;
//	}
//
//	public void setBloglist(List<BlogPostEntity> bloglist) {
//		this.bloglist = bloglist;
//	}
	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLinks(String title, URI uri) {
			
			Link link = new Link();
			link.setReference(title);
			link.setUri(uri);
			links.add(link);
			
		}
	

}
