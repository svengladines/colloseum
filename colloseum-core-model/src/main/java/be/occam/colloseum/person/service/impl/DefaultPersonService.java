package be.occam.colloseum.person.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.person.repository.IPersonRepository;
import be.occam.colloseum.person.service.IPersonService;

@Component
@Scope( "singleton" )
public class DefaultPersonService implements IPersonService {
	
	@Resource
	protected IPersonRepository personRepository; 
	
	@Override
	public Person findOne(String id) {
		
		return this.personRepository.findOne( id );
		
	}

}
