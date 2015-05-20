/**
 * 
 */
package com.ar.webapp.calendar;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Alexander Rettner
 *
 */
@Provider
public class MyApplicationExceptionHandler implements
		ExceptionMapper<MyApplicationException> {

	@Override
	public Response toResponse(MyApplicationException exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(exception.getMessage()).build();
	}
}