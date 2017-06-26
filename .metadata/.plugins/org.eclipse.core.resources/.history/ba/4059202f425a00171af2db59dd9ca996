package org.cisco.cmad.BloggingApp.api;

import java.util.List;

public interface BlogPost {
	
	public BlogPostEntity createBlogpost(BlogPostEntity blogpost,String userid);
    public boolean deleteBlogpost(String blogpostid);
    public BlogPostEntity getBlogpost(String blogpostid);
    public List<Object[]> getallBlogPosts();
    public Comments postComments(Comments comment,String blogid);
	public List<Comments> getallComments(String blogid);

}
