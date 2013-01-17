package be.occam.colloseum.model;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import be.occam.colloseum.person.Person;

@XmlRootElement
@XmlSeeAlso(Person.class)
public class Event<S,O> {
	
	protected S subject;
	
	protected O object;
	
	protected Date epoch;
	protected Location location;
	
	protected Set<String> tags;
	
	public S getSubject() {
		return subject;
	}
	
	public Event<S,O> setSubject(S subject) {
		this.subject = subject;
		return this;
	}
	
	public O getObject() {
		return object;
	}
	
	public Event<S,O> setObject(O object) {
		this.object = object;
		return this;
	}
	
	public Date getEpoch() {
		return epoch;
	}
	
	public Event<S,O> setEpoch(Date epoch) {
		this.epoch = epoch;
		return this;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public Event<S,O> setLocation(Location location) {
		this.location = location;
		return this;
	}

	public Set<String> getTags() {
		return tags;
	}
	
	public Event<S,O> tag( String tag ) {
		
		this.tags.add( tag );
		
		return this;
	}
	
	
	
}
