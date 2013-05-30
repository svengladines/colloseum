package be.occam.colloseum.action;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ActionDTO {
	
	protected String clz;
	
	protected String id;
	
	protected String actor;
	
	protected String subject;
	
	@XmlTransient
	protected Date date;

	public String getId() {
		return id;
	}

	public ActionDTO setId(String id) {
		this.id = id;
		return this;
	}

	public String getActor() {
		return actor;
	}

	public ActionDTO setActor( String actor) {
		this.actor = actor;
		return this;
	}

	public String getSubject() {
		return subject;
	}

	public ActionDTO setSubject( String subject ) {
		this.subject = subject;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public ActionDTO setDate(Date date) {
		this.date = date;
		return this;
	}

	public String getClz() {
		return clz;
	}

	public ActionDTO setClz(String clz) {
		this.clz = clz;
		return this;
	}
	
}
