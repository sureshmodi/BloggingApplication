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
import org.cisco.cmad.BloggingApp.api.BlogPostNotCreatedExcepion;
import org.cisco.cmad.BloggingApp.api.BlogPostNotFoundException;
import org.cisco.cmad.BloggingApp.api.Comments;

public class CmadBlogPost implements BlogPost {

	private BlogPostDAOInf blogdao = new JPABlogAppDAO();
	private CommentsDAOInf commentsdao = new JPABlogAppDAO();
	
	@Override
	public BlogPostEntity createBlogpost(BlogPostEntity blogpost, String userid) throws BlogPostNotCreatedExcepion {
					
			try {
				blogdao.createBlogpost(blogpost, userid);
			} catch (Exception ex) {
				System.out.println("Suresh,Caught Exception:"+ex.toString());
							
			}	
			
			BlogPostEntity blogpostdb = blogdao.retrieveBlogpost(blogpost.getBlogpostid());
			
			if (blogpostdb != null) {
				return blogpostdb;
			} else {
				throw new BlogPostNotCreatedExcepion("BlogPost Not Created");
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
	public List<Object[]> getallBlogPosts() {
		
		List<Object[]> blogpostlist = blogdao.listallBlogPosts();
		
		return blogpostlist;
					
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

	@Override
	public String getblogUserId(String blogpostid) {
		
		String userid = blogdao.blogUserid(blogpostid);
		return userid;
	}

	@Override
	public List<Object[]> searchBlogPosts(String searchtext) {
		
		List<Object[]> blogpostlist = blogdao.searchBlogPosts(searchtext);
		
		return blogpostlist;
	}

}
