package com.ar.webapp.calendar;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * 
 * @author rettnera
 *
 */
@Path("/rest")
public class UserService {
	@Path("/users")
	@POST
	public Response addUser(@QueryParam("firstName") String firstName,
			@QueryParam("lastName") String lastName) {
		// vaildate first name
		if (firstName == null || firstName.isEmpty()) {
			return Response.ok().entity("First name is mandatory dude").build();
		}
		// vaildate last name
		if (lastName == null || lastName.isEmpty()) {
			return Response.ok()
					.entity("Don't you have any last name? I will keep secret")
					.build();
		}
		// Add user and return the response
		return Response
				.ok()
				.entity("User " + firstName
						+ " added through JAX-RS JavaScript API").build();
	}
}