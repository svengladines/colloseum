package be.occam.colloseum.model;

import java.util.Set;

import be.occam.colloseum.person.Person;

public class User {
	
	protected Set<Person> roles;
	
	public User role( Person role ) {
		
		this.roles.add( role );
		
		return this;
		
	}

}
