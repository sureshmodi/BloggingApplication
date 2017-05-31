package org.cisco.cmad.BloggingApp.RestResources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.cisco.cmad.BloggingApp.Database.JPABlogPostDAO;
import org.cisco.cmad.BloggingApp.Database.JPACommentsDAO;
import org.cisco.cmad.BloggingApp.Database.JPAUserDAO;
import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.UserDetails;

@Path("Blogging")
public class RestUserDetails {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/register")
	public Response addUser(UserDetails user) {
				JPAUserDAO userdao = new JPAUserDAO();
				try {
					userdao.createUser(user);
				} catch (Exception ex) {
					System.out.println("Suresh,Caught Exception:"+ex.toString());
					Errormsg msg = new Errormsg("USer "+user.getUserid()+" already exists");
					return Response.status(Status.CONFLICT).entity(msg).build();
				}	
			    user.setPassword("xxx");
				return Response.status(Status.CREATED).entity(user).build();
			   
	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/createblogpost")
	public Response createBlogpost(BlogPost blogpost, @QueryParam("userid") String userid,@QueryParam("blogid") String blogid) {
				JPABlogPostDAO blogdao = new JPABlogPostDAO();
				
				try {
					blogdao.createBlogpost(blogpost, userid, blogid);
				} catch (Exception ex) {
					System.out.println("Suresh,Caught Exception:"+ex.toString());
					Errormsg msg = new Errormsg("USer "+blogpost.getBlogpostid()+" already exists");
					return Response.status(Status.CONFLICT).entity(msg).build();
				}	
			    
				return Response.status(Status.CREATED).build();
			   
	
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/postcomment/blogpost")
	
    public Response postComment(@QueryParam("postid") String postid,Comments comment) {
		JPACommentsDAO commentdao = new JPACommentsDAO();
		commentdao.postComment(comment, postid);
		
		Comments comm1= new Comments();
		comm1.setComment(comment.getComment());
		comm1.setCommentdate(comment.getCommentdate());
		comm1.setCommentid(comment.getCommentid());
		//comm1.setBlogpost(comment.getBlogpost());
		//System.out.println("BlogID: "+comment.getBlogpost().getBlogpostid());				
        return Response.status(Status.CREATED).entity(comm1).build();
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/retrieve/blogpost")
	public Response retrieveBlogpost(@QueryParam("blogpostid") String blogpostid,
									 @QueryParam("userid") String userid) {
		
		JPABlogPostDAO blogdao = new JPABlogPostDAO();
		BlogPost blogpost = blogdao.retrieveBlogpost(blogpostid, userid);
		
		System.out.println("blogpostid: "+blogpost.getBlogpostid());
		System.out.println("Blog Content: "+blogpost.getBlogContent());
		System.out.print("Blog Created date:"+blogpost.getDatecreated());
//		System.out.println("Blog Comments: "+blogpost.getCommentslist().get(0).getComment());
		
//		BlogPost blogpostnew = new BlogPost();
//		blogpostnew.setBlogContent(blogpost.getBlogContent());
//        blogpostnew.setBlogpostid(blogpost.getBlogpostid());
//        blogpostnew.setDatecreated(blogpost.getDatecreated());
//        blogpostnew.setCommentslist(blogpost.getCommentslist());
       
		
		if(blogpost!=null) {
			
			return Response.status(Status.FOUND).entity(blogpost).build();
			
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
	}

}