package org.cisco.cmad.BloggingApp.RestResources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/")
public class CommentResource {
	
	@GET
	@Path("/user/comment")
	public String test1( @QueryParam("userid") long userid, @QueryParam("commentid") long comid) {
			return "Inside SubResource:"+" userid:"+userid+ " commentid:"+comid;
	}

}
