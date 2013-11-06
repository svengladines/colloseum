package be.occam.colloseum.event;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {

	protected Date start;
	protected Date end;
	protected String subject;
	protected String description; 
	
	public Date getStart() {
		return start;
	}
	
	public Event setStart(Date start) {
		this.start = start;
		return this;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public Event setEnd(Date end) {
		this.end = end;
		return this;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public Event setSubject(String subject) {
		this.subject = subject;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
