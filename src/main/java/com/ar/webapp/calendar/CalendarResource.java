/**
 * 
 */
package com.ar.webapp.calendar;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

/**
 * @author Alexander Rettner
 *
 */
@Path("data")
public class CalendarResource {

	@GET
	@Path(value = "text")
	@Produces(MediaType.TEXT_PLAIN)
	public String getDataAsString() {
		return "Foo";
	}

	@GET
	@Path(value = "/jsons")
	@Produces(MediaType.APPLICATION_JSON)
	public Entry[] getData() {
		Entry[] values = new Entry[] { new Entry(new Date(), "today"),
				new Entry(null, "Friday") };
		return values;
	}

	@POST
	@Path(value = "/jsons")
	@Consumes
	// {"date":"2015-05-17T13:08:17.084Z","summary":"date"}
	@Produces(MediaType.TEXT_PLAIN)
	public String createData(Entry entry, @Context Request request,
			@Context HttpServletResponse response) {
		// throw new MyApplicationException("foo");
		return "{entry: \"" + entry.toString() + "\"}";
	}

	@GET
	@Path(value = "/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Entry getDatum() {
		return new Entry(new Date(), "today");
	}

	@GET
	@Path(value = "/jaxb")
	@Produces(MediaType.APPLICATION_JSON)
	public MyJaxbBean[] getMyJaxBeans() {
		return new MyJaxbBean[] { new MyJaxbBean("Minotaurus", 32),
				new MyJaxbBean("Zentaurus", 19) };
	}

}
