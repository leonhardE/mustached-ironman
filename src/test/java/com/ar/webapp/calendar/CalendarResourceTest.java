/**
 * 
 */
package com.ar.webapp.calendar;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

/**
 * @author Alexander Rettner
 *
 */
public class CalendarResourceTest {

	private WeldContainer weld;

	@Before
	public void setUp() throws Exception {
		weld = new Weld().initialize();
		RequestContext requestContext = weld.instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();
		requestContext.activate();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.ar.webapp.books.BookService#getBook(com.ar.webapp.books.BookRequest)}
	 * .
	 * 
	 * Doesn't work without injection
	 */
	@Test
	public final void testGetBookByTitle() {
		final CalendarResource resource = weld.instance().select(CalendarResource.class).get();
		Response response = given().contentType(ContentType.TEXT).expect().contentType(ContentType.TEXT)
				.statusCode(Status.OK.getStatusCode()).when().get("/calendar-webapp/webapi/data/text");

		String responseText = response.asString();

		assertEquals(resource.getDataAsString(), responseText);
	}

	/**
	 * Test method for {@link com.ar.webapp.calendar.CalendarResource#getData()}
	 * .
	 * 
	 * @throws JAXBException
	 */
	@Test
	@Ignore
	public final void testGetData() throws JAXBException {
		final JAXBContext context = JAXBContext.newInstance(MyJaxbBean.class);

		final MyJaxbBean addr = new MyJaxbBean("Minotaurus", 32);

		final StringWriter writer = new StringWriter();
		// this is where we convert the object to XML
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(addr, writer);

		System.out.println(writer.toString());

		// this is where we convert the XML to object
		Unmarshaller unmarshaller = context.createUnmarshaller();
		final MyJaxbBean fromXML = (MyJaxbBean) unmarshaller.unmarshal(new StringReader(writer.toString()));

		System.out.println(fromXML.toString());

		assertEquals(addr, fromXML);
	}

}
