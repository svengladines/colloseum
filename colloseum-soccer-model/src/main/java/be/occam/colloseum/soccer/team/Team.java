package be.occam.colloseum.soccer.team;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.model.Tag;
import be.occam.colloseum.model.Thing;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Team extends Thing {
	
	protected String id;
	protected String name;
	// should probably be club, but until we need that...
	protected Tag clubTag;

	public String getId() {
		
		return id;
		
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Team setName( String name ) {
		this.name = name;
		return this;
	}
	
	public Tag getClubTag() {
		return clubTag;
	}
	
}
