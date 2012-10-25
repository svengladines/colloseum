package be.occam.colloseum.model;

import java.util.Set;

public class User {
	
	protected Set<Person> roles;
	
	public User role( Person role ) {
		
		this.roles.add( role );
		
		return this;
		
	}

}
