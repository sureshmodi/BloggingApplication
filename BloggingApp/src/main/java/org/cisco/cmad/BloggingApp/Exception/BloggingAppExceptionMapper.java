package org.cisco.cmad.BloggingApp.Exception;

import javax.json.stream.JsonParsingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.cisco.cmad.BloggingApp.api.BlogPostNotFoundException;
import org.cisco.cmad.BloggingApp.api.ErrorMsg;
import org.cisco.cmad.BloggingApp.api.InvalidUserCredentialsException;
import org.cisco.cmad.BloggingApp.api.UserAlreadyExistsException;
import org.cisco.cmad.BloggingApp.api.UserNotFoundException;
import org.cisco.cmad.BloggingApp.api.UserRegistrationFailedException;
import org.cisco.cmad.BloggingApp.api.UserUpdateFailedException;

@Provider
@SuppressWarnings("serial")
public class BloggingAppExceptionMapper implements ExceptionMapper<Throwable> {

	ErrorMsg errormsg = new ErrorMsg();
	
	@Override
	
	public Response toResponse(Throwable exception) {
		exception.printStackTrace();
		
		if (exception instanceof UserNotFoundException) {
			
				errormsg.setErrormsg("Unknown USer");
				errormsg.setErrorcode(401);
				return Response.status(Status.UNAUTHORIZED).entity(errormsg).build();
				
		} else if (exception instanceof WebApplicationException) {
			
				errormsg.setErrormsg("Invalid Blog Content");
				errormsg.setErrorcode(500);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errormsg).build();
				
		} else if(exception instanceof BlogPostNotFoundException) {
				
				errormsg.setErrormsg(exception.getMessage());
				errormsg.setErrorcode(404);
				return Response.status(Status.NOT_FOUND).entity(errormsg).build();
				
		} else if(exception instanceof InvalidUserCredentialsException) {
			
				errormsg.setErrormsg(exception.getMessage());
				errormsg.setErrorcode(401);
				return Response.status(Status.UNAUTHORIZED).entity(errormsg).build();
				
		} else if(exception instanceof UserRegistrationFailedException) {
				errormsg.setErrormsg(exception.getMessage());
				errormsg.setErrorcode(503);
				return Response.status(Status.SERVICE_UNAVAILABLE).entity(errormsg).build();
				
		} else if(exception instanceof UserAlreadyExistsException) {
				
				errormsg.setErrormsg(exception.getMessage());
				errormsg.setErrorcode(409);
				return Response.status(Status.CONFLICT).entity(errormsg).build();
			
		} else if(exception instanceof UserUpdateFailedException) {
			
			errormsg.setErrormsg(exception.getMessage());
			errormsg.setErrorcode(304);
			return Response.status(Status.NOT_MODIFIED).entity(errormsg).build();		
				
		} else {
				
				System.out.println("Suresh: Caught exception Class:"+exception.getClass());
				errormsg.setErrormsg(exception.getMessage());
				errormsg.setErrorcode(500);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errormsg).build();
		}
		
		
	}
	


}
