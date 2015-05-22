/**
 * 
 */
package com.ar.webapp.calendar;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Alexander Rettner
 *
 */
public class CalendarResourceTest
// extends JerseyTest
{

	/**
	 * Test method for {@link com.ar.webapp.calendar.CalendarResource#getData()}
	 * .
	 * 
	 * @throws JAXBException
	 * 
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	@Test
	@Ignore
	public final void testGetData() throws JAXBException {
		final JAXBContext context = JAXBContext.newInstance(MyJaxbBean.class);

		final MyJaxbBean addr = new MyJaxbBean("Minotaurus", 32);

		final StringWriter writer = new StringWriter();
		// this is where we convert the object to XML
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
				"application/json");
		marshaller.marshal(addr, writer);

		System.out.println(writer.toString());

		// this is where we convert the XML to object
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setProperty(MarshallerProperties.MEDIA_TYPE,
				"application/json");
		final MyJaxbBean fromXML = (MyJaxbBean) unmarshaller
				.unmarshal(new StringReader(writer.toString()));

		System.out.println(fromXML.toString());

		assertEquals(addr, fromXML);
	}

}
