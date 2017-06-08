package org.cisco.cmad.BloggingApp.api;

import java.util.List;

public interface BlogPost {
	
	public void createBlogpost(BlogPostEntity blogpost,String userid);
    public boolean deleteBlogpost(String blogpostid);
    public BlogPostEntity getBlogpost(String blogpostid);
    public List<BlogPostEntity> getallBlogPosts();
    public Comments postComments(Comments comment,String blogid);
	public List<Comments> getallComments(String blogid);

}
