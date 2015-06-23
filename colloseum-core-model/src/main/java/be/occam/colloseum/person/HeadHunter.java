package be.occam.colloseum.person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.person.repository.PersonRepository;

public class HeadHunter extends Hat {

	@Resource
	PersonRepository personRepository;
	
	public Person whoHasId( String id ) {
		
		Person person
			= this.personRepository.findById( id );
		
		logger.debug( "the universe does {}contain a person with ID [{}]",  person == null ? "not " : "", id );
		
		return person;
			
	}
	
	public List<Person> whoAreThePersonsWithTags( Tag... tags ) {
		
		List<Person> persons
			= new ArrayList<Person>();
		
		List<Person> all 
			= this.personRepository.findAll();
		
		persons.addAll( all );
		
		logger.info( "the universe contains [{}] persons", persons.size() );
		
		Iterator<Person> iterator
			= persons.iterator();
		
		while ( iterator.hasNext() ) {
			
			Person person 
				= iterator.next();
			
			for ( Tag tag : tags ) {
				
				if ( ! person.getTags().contains( tag ) ) {
					iterator.remove();
					break;
				}
				
			}
			
		}
		
		logger.info( "in the universe, [{}] person(s) have tags [{}]", persons.size(), tags );
		
		return persons;
		
	}
	
	public List<Person> whichPersonsHaveNames( String family, String given ) {
		
		List<Person> persons
			= new ArrayList<Person>();
	
		List<Person> all 
			= this.personRepository.findAll();
	
		logger.debug( "the universe contains [{}] persons", all.size() );
		
		for ( Person person : all ) {
			
			if ( person.getFamilyName().equals( family ) ) {
				
				if ( ( given == null ) || ( person.getGivenName().equals( given ) ) ) {
					persons.add( person );
				}
				
			}
			
		}
	
		logger.debug( "in the universe, [{}] person(s) have names [{},{}]", persons.size(), family, given );
	
		return persons;
		
	}
	
	public Person giveBirth( Person person ) {
		
		if ( person.getId() == null ) {
			person.setId( UUID.randomUUID().toString() );
		}
		
		return this.personRepository.saveAndFlush( person );
		
	}
	
}
