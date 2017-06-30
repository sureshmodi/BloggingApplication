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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

import org.cisco.cmad.BloggingApp.Database.JPABlogAppDAO;
import org.cisco.cmad.BloggingApp.api.BlogPost;
import org.cisco.cmad.BloggingApp.api.BlogPostEntity;
import org.cisco.cmad.BloggingApp.api.BlogPostList;
import org.cisco.cmad.BloggingApp.api.BlogPostNotCreatedExcepion;
import org.cisco.cmad.BloggingApp.api.BlogPostNotFoundException;
import org.cisco.cmad.BloggingApp.api.BlogUser;
import org.cisco.cmad.BloggingApp.api.Comments;
import org.cisco.cmad.BloggingApp.api.CommentsList;
import org.cisco.cmad.BloggingApp.api.ErrorMsg;
import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.JWTAuthentication;
import org.cisco.cmad.BloggingApp.api.UserDetails;
import org.cisco.cmad.BloggingApp.jwt.JWTImpl;
import org.cisco.cmad.BloggingApp.service.CmadBlogPost;
import org.cisco.cmad.BloggingApp.service.CmadBlogUser;

import io.jsonwebtoken.SignatureException;

@Path("blogging")
public class BlogRestController {
	
	private static BlogUser bloguser = new CmadBlogUser();
	private static BlogPost blogpost = new CmadBlogPost();
	private static JWTAuthentication jwtauth = new JWTImpl();
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/user/signup")
	public Response addUser(UserDetails user) {
			
			if(user != null && user.getUserid() != null && !((user.getUserid().contentEquals("")))) {
				bloguser.createUser(user);
				return Response.status(Status.CREATED).entity(user).build();
			}	else {
				throw new InvalidUserCredentialsException("Missing mandatory user details");
			}
			   
	
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost/userid/{userid}")
	public Response createBlogpost(BlogPostEntity recvblogpost, @PathParam("userid") String userid,
			   @Context UriInfo uriinfo,@Context HttpHeaders headers) throws BlogPostNotCreatedExcepion
	{

		if(userid != null) {
			MultivaluedMap<String,String> headervalues = headers.getRequestHeaders();
			String jwttoken = null;
			
			try {
			jwttoken = headervalues.get(AUTHORIZATION).get(0);
			} catch (NullPointerException e) {
			throw new InvalidUserCredentialsException("No Authorization header present in HTTP Request");
			}	  
			
			jwtauth.parseJwtToken(userid, jwttoken);	
			
			BlogPostEntity blogpostdb = blogpost.createBlogpost(recvblogpost, userid);
			String id = String.valueOf(blogpostdb.getBlogpostid());
			
			URI uri = uriinfo.getBaseUriBuilder()
				  .path(BlogRestController.class)
				  .path("blogpost").path(id).build();
			
			blogpostdb.addLinks(uri, recvblogpost.getBlogpostid());
			return Response.created(uri).entity(blogpostdb).build();
		} else {
			
			throw new InvalidUserCredentialsException("Missing mandatory user details");
			
		}
	
	
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
			URI uri = uriinfo.getAbsolutePathBuilder().build();
			blog.addLinks(uri,"Blog Comments");
			return Response.status(Status.OK).entity(blog).build();
			
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})

	@Path("/user/login")
	public Response userLogin(UserDetails user,@Context UriInfo uriinfo) {
			
		UserDetails userdb = null;
		ErrorMsg errormsg= new ErrorMsg();
		//String authorization = "Authorization";
				
		if (user.getUserid()!=null && user.getPassword()!=null) {
			userdb = bloguser.userLogin(user);
					
			Set<String> keys = userdb.getBloglist().keySet();
													
			for (String key: keys) {
						String blogid = userdb.getBloglist().get(key).getBlogpostid();
						String blogtitle = userdb.getBloglist().get(key).getTitle();
						URI uri = uriinfo.getBaseUriBuilder().path(BlogRestController.class)
									.path("blogpost")
									.path(userdb.getBloglist().get(key).getBlogpostid())
									.build();
						/*userdb.addLinks(uri,userdb.getBloglist().get(key).getBlogpostid());*/
						userdb.addLinks(uri,blogid,blogtitle);
			}
			
			String token = jwtauth.generateJwtToken(user.getUserid(),uriinfo.getAbsolutePath().toString(),user.getUserid(),1000000);
			System.out.println("Suresh: Generated Token: "+token);
				       									
			return Response.status(Status.OK).entity(userdb).header(AUTHORIZATION,token).build();
						
		} else {
			System.out.println("Suresh: Inside userlogin method");
			errormsg.setErrormsg("UserID/Password is empty");
			errormsg.setErrorcode(400);
			return Response.status(Status.BAD_REQUEST).entity(errormsg).build();	
		}
							   
	
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/user/{userid}/updateprofile")

	public Response updateUser(UserDetails user,@Context HttpHeaders headers,@PathParam("userid") String userid) {
	
		 	if (user != null) {
		 		 		 		  				
		 		MultivaluedMap<String,String> headervalues = headers.getRequestHeaders();
		      	String jwttoken = null;
			  
		      	try {
				  jwttoken = headervalues.get(AUTHORIZATION).get(0);
		      	} catch (NullPointerException e) {
				  throw new InvalidUserCredentialsException("No Authorization header present in HTTP Request");
		      	}	  
			 
		      	 jwtauth.parseJwtToken(userid, jwttoken);	
		 		  		 		  	 		 				 			  
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
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON })
	@Path("/blogpost/{blogpostid}")
	public Response deleteBlogpost(@PathParam("blogpostid") String blogpostid,@Context HttpHeaders headers) {
			
			MultivaluedMap<String,String> headervalues = headers.getRequestHeaders();
	      	String jwttoken = null;
	      	String userid = null;
		  
	      	try {
			  jwttoken = headervalues.get(AUTHORIZATION).get(0);
	      	} catch (NullPointerException e) {
			  throw new InvalidUserCredentialsException("No Authorization header present in HTTP Request");
	      	}	  
		 	      		      	
	      	userid = blogpost.getblogUserId(blogpostid);
	      	
	      	if (userid != null) {
	      			jwtauth.parseJwtToken(userid, jwttoken);
	      			System.out.println("Suresh: User Authenticated successfully while deleting blogpost");
	      	} else {
	      			System.out.println("Not received userid from DB");
	      	}
	      	
			if (blogpost.deleteBlogpost(blogpostid)) {
				return Response.status(Status.OK).entity("Successfully deleted").build();
			} else {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity("BlogPost not deleted").build();
			}
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blogpost/all")
	public Response getallBlogposts(@Context UriInfo uriinfo) {
		
		List<Object[]> dbbloglist = blogpost.getallBlogPosts();
			
		if(dbbloglist!=null) {
			//URI uri = uriinfo.getAbsolutePathBuilder().path("comments").build();
			//blog.addLinks(uri,"Blog Comments");
			
			BlogPostList bloglist = new BlogPostList();
			//bloglist.setBloglist(dbbloglist);
						
			for (int i=0;i<dbbloglist.size();i++) {
				URI uri = uriinfo.getBaseUriBuilder().path(BlogRestController.class)
							.path("blogpost")
							.path((String) dbbloglist.get(i)[0])
							.build();
				
				bloglist.addLinks((String) dbbloglist.get(i)[1],uri);
			}	
		
			return Response.status(Status.OK).entity(bloglist).build();
			
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		
	}

	@GET
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})

	@Path("/user/{userid}/home")
	public Response userDetails(@PathParam("userid") String userid,@Context UriInfo uriinfo,@Context HttpHeaders headers) {
			
		UserDetails userdb = null;
		ErrorMsg errormsg= new ErrorMsg();
						
		if (userid!=null) {
			
			MultivaluedMap<String,String> headervalues = headers.getRequestHeaders();
	      	String jwttoken = null;
		  
	      	try {
			  jwttoken = headervalues.get(AUTHORIZATION).get(0);
	      	} catch (NullPointerException e) {
			  throw new InvalidUserCredentialsException("No Authorization header present in HTTP Request");
	      	}	  
		 
	      	jwtauth.parseJwtToken(userid, jwttoken);	
			userdb = bloguser.getUserDetails(userid);
					
			Set<String> keys = userdb.getBloglist().keySet();
													
			for (String key: keys) {
						String blogid = userdb.getBloglist().get(key).getBlogpostid();
						String blogtitle = userdb.getBloglist().get(key).getTitle();
						URI uri = uriinfo.getBaseUriBuilder().path(BlogRestController.class)
									.path("blogpost")
									.path(userdb.getBloglist().get(key).getBlogpostid())
									.build();
						/*userdb.addLinks(uri,userdb.getBloglist().get(key).getBlogpostid());*/
						userdb.addLinks(uri,blogid,blogtitle);
			}
						
			return Response.status(Status.OK).entity(userdb).build();
						
		} else {
			errormsg.setErrormsg("UserID is empty");
			errormsg.setErrorcode(400);
			return Response.status(Status.BAD_REQUEST).entity(errormsg).build();	
		}
							   
	
	}
	
	@POST
	//@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.TEXT_PLAIN,MediaType.APPLICATION_JSON})

	@Path("/user/{userid}/logout")
	public Response userLogout(@PathParam("userid") String userid, @Context UriInfo uriinfo, 
							   @Context HttpHeaders headers) {
			
		MultivaluedMap<String,String> headervalues = headers.getRequestHeaders();
		ErrorMsg errormsg= new ErrorMsg();
		String jwttoken = null;
				
		try {
			  jwttoken = headervalues.get(AUTHORIZATION).get(0);
		  	  System.out.println("Suresh: Authorization header value: "+jwttoken);
			  
		} catch (NullPointerException e) {
			  throw new InvalidUserCredentialsException("No Authorization header present in HTTP Request");
		}	 
						
		if (userid!=null) {
			try {
				jwtauth.parseJwtToken(userid, jwttoken);
				jwtauth.deleteJwtToken(userid);
				return Response.status(Status.OK).entity("User Successfully logged out").build();
			} catch (InvalidUserCredentialsException e) {
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Caught Exception:"+e.toString());
				return Response.status(Status.OK).entity("User Successfully logged out").build();
			}	
						
		} else {
			System.out.println("Suresh: Inside userlogout method");
			/*errormsg.setErrormsg("UserID is empty");
			errormsg.setErrorcode(400);
			return Response.status(Status.BAD_REQUEST).entity(errormsg).build();	*/
			return Response.status(Status.BAD_REQUEST).entity("Bad Request").build();
			
		}
							   
	
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Path("/blog/search/{search}")
	public Response searchBlogposts(@Context UriInfo uriinfo,@PathParam("search") String searchtext) {
		
		System.out.println("Suresh: Received Blog Search text: "+searchtext);
		List<Object[]> dbbloglist = blogpost.searchBlogPosts(searchtext);
			
		if(!dbbloglist.isEmpty() && dbbloglist != null) {
						
			BlogPostList bloglist = new BlogPostList();
									
			for (int i=0;i<dbbloglist.size();i++) {
				
				System.out.println("Suresh: Inside searchBlogPost: blogid: "+dbbloglist.get(i)[0]);
				System.out.println("Suresh: Inside searchBlogPost: blogtitle: "+dbbloglist.get(i)[1]);
				
				URI uri = uriinfo.getBaseUriBuilder().path(BlogRestController.class)
							.path("blogpost")
							.path((String) dbbloglist.get(i)[0])
							.build();
				
				bloglist.addLinks((String) dbbloglist.get(i)[1],uri);
				
			
			}	
		
			return Response.status(Status.OK).entity(bloglist).build();
			
		} else {
			throw new BlogPostNotFoundException("No results found. Try entering different keywords.");
			//return Response.status(Status.NOT_FOUND).build();
		}
		
		
	}
	
	
}
