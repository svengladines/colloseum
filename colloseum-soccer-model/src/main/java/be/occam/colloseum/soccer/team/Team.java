package be.occam.colloseum.soccer.team;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Team {
	
	protected String id;
	protected String name;

	public String getId() {
		
		return id;
		
	}

	public Team setId(String id) {
		
		this.id = id;
		return this;
		
	}

	public String getName() {
		return name;
	}

	public Team setName( String name ) {
		this.name = name;
		return this;
	}
	
}
