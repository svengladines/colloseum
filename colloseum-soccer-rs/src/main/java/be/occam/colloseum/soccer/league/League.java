package be.occam.colloseum.soccer.league;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class League {
	
	protected String id;
	protected String name;

	public String getId() {
		
		return id;
		
	}

	public League setId(String id) {
		
		this.id = id;
		return this;
		
	}

	public String getName() {
		return name;
	}

	public League setName( String name ) {
		this.name = name;
		return this;
	}
	
}
