package be.occam.colloseum.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.occam.colloseum.person.Person;

public interface PersonRepository extends JpaRepository<Person, String>{
	
	public Person findById( String id );

}
