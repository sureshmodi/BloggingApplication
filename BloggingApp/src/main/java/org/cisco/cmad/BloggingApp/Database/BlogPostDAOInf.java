package org.cisco.cmad.BloggingApp.Database;

import java.util.List;

import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.UserDetails;

public interface BlogPostDAOInf {
	
	public void createBlogpost(BlogPostEntity blogpost,String userid);
    public boolean deleteBlogpost(String blogpostid);
    public BlogPostEntity retrieveBlogpost(String blogpostid);
    public List<BlogPostEntity> listallBlogPosts();
	       
	
}
