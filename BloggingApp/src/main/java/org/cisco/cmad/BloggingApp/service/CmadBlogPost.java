package org.cisco.cmad.BloggingApp.service;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.cisco.cmad.BloggingApp.Database.BlogPostDAOInf;
import org.cisco.cmad.BloggingApp.Database.CommentsDAOInf;
import org.cisco.cmad.BloggingApp.Database.JPABlogAppDAO;
import org.cisco.cmad.BloggingApp.Rest.Errormsg;
import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.BlogPostNotFoundException;
import org.cisco.cmad.BloggingApp.api.Comments;

public class CmadBlogPost implements BlogPost {

	private BlogPostDAOInf blogdao = new JPABlogAppDAO();
	private CommentsDAOInf commentsdao = new JPABlogAppDAO();
	
	@Override
	public void createBlogpost(BlogPostEntity blogpost, String userid) {
					
			try {
				blogdao.createBlogpost(blogpost, userid);
			} catch (Exception ex) {
				System.out.println("Suresh,Caught Exception:"+ex.toString());
							
			}	

	}

	@Override
	public boolean deleteBlogpost(String blogpostid) {
		
			return blogdao.deleteBlogpost(blogpostid);
			
	}

	@Override
	public BlogPostEntity getBlogpost(String blogpostid) {
		
			BlogPostEntity blogpost = blogdao.retrieveBlogpost(blogpostid);
			
			if (blogpost != null) {
				return blogpost;
			} else {
				throw new BlogPostNotFoundException("BlogPost do not exist");
			}
	}

	@Override
	public List<BlogPostEntity> getallBlogPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comments postComments(Comments comment, String blogid) {
			Comments dbcomment = commentsdao.postComments(comment, blogid);
			return dbcomment;
	}

	@Override
	public List<Comments> getallComments(String blogid) {
		List<Comments> commentlist = commentsdao.getallComments(blogid);
		return 	commentlist;
	}

}