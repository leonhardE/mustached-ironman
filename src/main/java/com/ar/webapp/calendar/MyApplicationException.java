/**
 * 
 */
package com.ar.webapp.calendar;

import java.io.Serializable;

/**
 * @author Alexander Rettner
 *
 */
public class MyApplicationException extends RuntimeException implements
		Serializable {
	private static final long serialVersionUID = -5796908645533623184L;

	public MyApplicationException() {
		super();
	}

	public MyApplicationException(String msg) {
		super(msg);
	}

	public MyApplicationException(String msg, Exception e) {
		super(msg, e);
	}
}