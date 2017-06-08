package org.cisco.cmad.BloggingApp.Rest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.cisco.cmad.BloggingApp.Database.JPABlogAppDAO;
import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.BlogUser;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.CommentsList;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.service.CmadBlogPost;
import org.cisco.cmad.BloggingApp.service.CmadBlogUser;

@Path("blogging")
public class BlogRestController {
	
	private static BlogUser bloguser = new CmadBlogUser();
	private static BlogPost blogpost = new CmadBlogPost();
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/user/register")
	public Response addUser(UserDetails user) {
			
				bloguser.createUser(user);
				return Response.status(Status.CREATED).entity(user).build();
			   
	
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost")
	public Response createBlogpost(BlogPostEntity recvblogpost, @QueryParam("userid") String userid,
								   @Context UriInfo uriinfo)
	
	{
				System.out.println("Suresh: Absolute path: "+uriinfo.getAbsolutePath());
			    blogpost.createBlogpost(recvblogpost, userid);
			    String id = String.valueOf(recvblogpost.getBlogpostid());
			    URI uri = uriinfo.getAbsolutePathBuilder().path(id).build();
			    
			    recvblogpost.addLinks(uri, recvblogpost.getBlogpostid());
				return Response.created(uri).entity(recvblogpost).build();
			    //return Response.status(Status.CREATED).entity(recvblogpost).build();
			   
	
	}
	
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost/{postid}/postcomment")
	
    public Response postComment(@PathParam("postid") String postid,Comments comment) {
		
		Comments dbcomments = blogpost.postComments(comment,postid);
		return Response.status(Status.CREATED).entity(dbcomments).build();
    }
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost/{blogpostid}")
	public Response getBlogpost(@PathParam("blogpostid") String blogpostid,@Context UriInfo uriinfo) {
		
		BlogPostEntity blog = blogpost.getBlogpost(blogpostid);
			
		if(blog!=null) {
			URI uri = uriinfo.getAbsolutePathBuilder().path("comments").build();
			blog.addLinks(uri,"Blog Comments");
			return Response.status(Status.FOUND).entity(blog).build();
			
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})

	@Path("/user/login")
	public Response userLogin(UserDetails user,@Context UriInfo uriinfo) {
				
			UserDetails userdb = bloguser.userLogin(user);
		
			
			Set<String> keys = userdb.getBloglist().keySet();
							
			for (String key: keys) {
						URI uri = uriinfo.getBaseUriBuilder().path(BlogRestController.class)
									.path("blogpost")
									.path(userdb.getBloglist().get(key).getBlogpostid())
									.build();
						userdb.addLinks(uri,userdb.getBloglist().get(key).getBlogpostid());
			}
				       									
			return Response.status(Status.FOUND).entity(userdb).build();
							   
	
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/user/update")
	
	public Response updateUser(UserDetails user) {
	
		 	if (user != null) {
		 		
		 		UserDetails userdb = bloguser.updateUser(user);
				
				if (userdb != null) {
					return Response.status(Status.OK).entity(userdb).build();
				} else {
					return Response.status(Status.NOT_MODIFIED).build();
				}
		 	} else {
		 		    System.out.println("Suresh: Invalid user details");
		 			return Response.status(Status.BAD_REQUEST).build();
		 	}
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost/{blogpostid}/comments")
	
	public Response getallComments(@PathParam("blogpostid") String blogpostid) {
		
		List<Comments> commentslist = blogpost.getallComments(blogpostid);
		 		 			
	 	System.out.print("Size of the comments list: "+commentslist.size()+"\n");
		for(int i=0;i < commentslist.size();i++) {
			
			System.out.println("Comments: "+commentslist.get(i).getComment());
		}
		
		CommentsList commlist = new CommentsList();
		commlist.setCommentsList(commentslist);
			
		return Response.status(Status.OK).entity(commlist).build();
		
	}
	
	@DELETE
	@Produces({MediaType.TEXT_PLAIN})
	@Path("/blogpost/{blogpostid}")
	public Response deleteBlogpost(@PathParam("blogpostid") String blogpostid) {
			
			if (blogpost.deleteBlogpost(blogpostid)) {
				return Response.status(Status.OK).entity("Successfully deleted").build();
			} else {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("BlogPost not deleted").build();
			}
	}

}