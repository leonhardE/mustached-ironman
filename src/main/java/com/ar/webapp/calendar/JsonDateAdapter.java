/**
 * 
 */
package com.ar.webapp.calendar;

/**
 * @author Alexander Rettner
 *
 */
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class JsonDateAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String dateString) throws Exception {
		return new Date(Long.parseLong(dateString));
	}

	@Override
	public String marshal(Date date) throws Exception {
		if (date == null)
			return null;
		return String.valueOf(date.getTime());
	}

}