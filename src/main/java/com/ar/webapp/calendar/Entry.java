package com.ar.webapp.calendar;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Entry {
	@XmlElement
	@XmlJavaTypeAdapter(JsonDateAdapter.class)
	private Date date;
	@XmlElement
	private String summary;

	public Entry() {
	}

	public Entry(Date date, String summary) {
		this.date = date;
		this.summary = summary;
	}

	public Date getDate() {
		return date;
	}

	public String getSummary() {
		return summary;
	}

	@Override
	public String toString() {
		return "Entry [date=" + date + ", summary=" + summary + "]";
	}

}
