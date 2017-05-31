package org.cisco.cmad.BloggingApp.RestResources;

import javax.transaction.SystemException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class BlogExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		
		System.out.println("Suresh : Caught exception on server side");
		exception.printStackTrace();
		return Response.status(Status.INTERNAL_SERVER_ERROR).build();
	}


}
