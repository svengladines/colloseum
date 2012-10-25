package be.occam.colloseum.model;

import java.util.Set;

public class Thing {
	
	protected String id;
	protected Set<String> tags;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	public Thing tag( String tag ) {
		
		this.tags.add( tag );
		
		return this;
		
	}

}
