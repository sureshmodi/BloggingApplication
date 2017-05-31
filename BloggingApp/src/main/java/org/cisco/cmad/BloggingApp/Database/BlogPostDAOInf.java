package org.cisco.cmad.BloggingApp.Database;

import java.util.List;

import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.UserDetails;

public interface BlogPostDAOInf {
	
	public void createBlogpost(BlogPost blogpost,String userid,String blogid);
    public BlogPost deleteBlogpost(String blogpostid);
    public BlogPost retrieveBlogpost(String blogpostid,String userid);
    public List<BlogPost> listallBlogPosts();
	       
	
}
