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

import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

/**
 * @author Alexander Rettner
 *
 */
public class CalendarResourceTest {

	/**
	 * Test method for
	 * {@link com.ar.webapp.books.BookService#getBook(com.ar.webapp.books.BookRequest)}
	 * .
	 */
	@Test
	public final void testGetBookByTitle() {
		Response response = given().contentType(ContentType.TEXT).expect()
				.contentType(ContentType.TEXT)
				.statusCode(Status.OK.getStatusCode()).when()
				.get("http://localhost:8080/calendar-webapp/webapi/data/text");

		String responseText = response.asString();

		assertEquals(new CalendarResource().getDataAsString(), responseText);
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
		final MyJaxbBean fromXML = (MyJaxbBean) unmarshaller
				.unmarshal(new StringReader(writer.toString()));

		System.out.println(fromXML.toString());

		assertEquals(addr, fromXML);
	}

}
