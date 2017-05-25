package org.cisco.cmad.BloggingApp.RestResources;

import java.net.HttpCookie;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.cisco.cmad.BloggingApp.api.BlogUsers;
import org.cisco.cmad.BloggingApp.service.ProfileMgmt;
import org.cisco.cmad.BloggingApp.service.Users;
import org.glassfish.jersey.server.Uri;

@Path("Blogusers")

public class GetUsers {
	
		ProfileMgmt users = new ProfileMgmt();
	
		@GET
		@Produces(MediaType.APPLICATION_JSON)
				
		public List<BlogUsers> getAllUsers() {
							return users.getallUsers();
		}
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/userid={id}")
		public Response getUser(@PathParam("id") Long id, @Context UriInfo baseuri) {
			        BlogUsers user = users.getUser(id);
			        //String uri = baseuri.getBaseUriBuilder().path(GetUsers.class).path(Long.toString(user.getId())).build().toString();
			        String uri=baseuri.getAbsolutePathBuilder().build().toString();
      		        user.addLinks(uri, "Self");
			        return Response.status(Status.ACCEPTED).entity(user).build();
					//return new ProfileMgmt().getUser(id);
		}
		
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/adduser")
		public Response addUser(BlogUsers user, @Context UriInfo urinf) {
				    BlogUsers adduser = users.addUser(user);
				    String id=String.valueOf(adduser.getId());
				    URI uri = urinf.getAbsolutePathBuilder().path(id).build();
					//return Response.status(Status.CREATED).entity(adduser).build();
				       return Response.created(uri).entity(adduser).build();
		
		}
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/update/userid={id}")
		public BlogUsers updateUser(@PathParam("id") long id,BlogUsers user) {
				   user.setId(id);
					return users.updateUser(id, user);
		
		}
		
		@DELETE
		@Path("/userid={id}")
		public void removeUSer(@PathParam("id") long id) {
					users.deleteUser(id);
			
		}
		
		@GET
		@Path("/context")
		@Produces(MediaType.TEXT_PLAIN)
		public String getcontext(@Context UriInfo uri, @Context HttpHeaders header) {
						String path = uri.getAbsolutePath().toString();
						String reqheader=header.getMediaType().toString();
						return "URI:"+path+"    "+"HTPP Header:"+reqheader;
			
		}
		
		
		@Path("/comments")
		public CommentResource getComments() {
			
			return new CommentResource();
		}
		
		
}
