/**
 * 
 */
package com.ar.webapp.calendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * @author rettnera
 *
 */
@Named
@RequestScoped
public class Bean {

	/**
	 * 
	 */
	public Bean() {
		// TODO Auto-generated constructor stub
	}

	public String speak() {
		return "Foo";
	}

}
