/**
 * 
 */
package com.ar.webapp.calendar;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Alexander Rettner
 *
 */
public class MyResourceTest {

	/**
	 * Test method for {@link com.ar.webapp.calendar.MyResource#getIt()}.
	 */
	@Test
	public final void testGetIt() {
		MyResource mr = new MyResource();
		String actual = mr.getIt();
		Assert.assertEquals("Got it!", actual);
	}

	/**
	 * Test method for {@link com.ar.webapp.calendar.MyResource#takeIt()}.
	 */
	@Test
	@Ignore
	public final void testTakeIt() {
		fail("Not yet implemented"); // TODO
	}

}
