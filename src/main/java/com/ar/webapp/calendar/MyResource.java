package com.ar.webapp.calendar;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "books" path)
 */
@Path("books")
public class MyResource {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "[\n"
				+ " {\"id\":0,\"title\":\"1984\",\"author\":\"George Orwel\"},\n"
				+ " {\"id\":1,\"title\":\"Die Stadt und die Sterne\",\"author\":\"Arthur C. Clarke\"}\n"
				+ " ]";
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String takeIt() {
		return "OK";
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, String> getJson() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("title", "War and peace");
		m.put("author", "Leo Tolstoy");
		m.put("price", String.valueOf(49));
		m.put("id", String.valueOf(1));
		return m;
	}
}
