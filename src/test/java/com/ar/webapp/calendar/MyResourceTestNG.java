package com.ar.webapp.calendar;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class MyResourceTestNG {

	@Test
	public void testTakeIt() {
		MyResource mr = new MyResource();
		String actual = mr.takeIt();
		assertEquals("OK", actual);
	}
}
