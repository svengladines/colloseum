package be.occam.colloseum.soccer.club;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Club {
	
	protected String id;
	protected String name;

	public String getId() {
		
		return id;
		
	}

	public Club setId(String id) {
		
		this.id = id;
		return this;
		
	}

	public String getName() {
		return name;
	}

	public Club setName( String name ) {
		this.name = name;
		return this;
	}
	
}
