package be.occam.colloseum.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import be.occam.colloseum.person.Person;

public interface IPersonRepository extends JpaRepository<Person, String>{
	
	public Person findById( String id );

}
