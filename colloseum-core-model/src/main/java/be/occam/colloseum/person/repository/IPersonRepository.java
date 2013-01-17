package be.occam.colloseum.person.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.person.Person;

public interface IPersonRepository {
	
	public Person findOne( String id );
	
	public Set<Person> findAll( Providers providers );
	
	public Person persist( Person id, Providers providers );

}
