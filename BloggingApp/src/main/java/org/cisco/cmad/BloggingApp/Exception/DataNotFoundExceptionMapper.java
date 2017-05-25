package org.cisco.cmad.BloggingApp.Exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.cisco.cmad.BloggingApp.api.ExceptionModel;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException excep) {
		
		ExceptionModel excepmodel = new ExceptionModel(excep.getMessage(), 404, "http://www.google.com");
		return Response.status(Status.NOT_FOUND).entity(excepmodel).build();
	}


}
