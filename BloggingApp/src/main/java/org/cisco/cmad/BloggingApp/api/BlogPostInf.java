package org.cisco.cmad.BloggingApp.api;

import java.util.List;

public interface BlogPostInf {
	
	public void createBlogpost();
    public BlogPost deleteBlogpost(String blogpostid);
    public BlogPost retrieveBlogpost(String blogpostid);
    public List<BlogPost> listallBlogPosts();
    
    
	
}