package be.occam.colloseum.model;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Thing {
	
	protected String id;
	
	protected Set<Tag> tags
		= new HashSet<Tag>();
	
	@XmlAttribute
	public String getId() {
		return id;
	}
	
	public Thing setId(String id) {
		this.id = id;
		return this;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public boolean equals(Object obj) {
		
		boolean equals
			= false;
		
		if ( obj instanceof Thing ) {
			
			if ( ((Thing) obj).getId().equals( this.id ) ) {
				equals = true;
			}
			
		}
		
		return equals;
	}
	
	

}
