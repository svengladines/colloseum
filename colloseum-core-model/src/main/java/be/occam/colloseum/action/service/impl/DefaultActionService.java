package be.occam.colloseum.action.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.action.repository.IActionRepository;
import be.occam.colloseum.action.service.IActionService;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.person.service.IPersonService;

@Component
@Scope("singleton")
public class DefaultActionService implements IActionService {
	
	@Resource
	IPersonService personService;
	
	@Resource
	IActionRepository actionRepository;

	@Override
	public ActionDTO create( ActionDTO dto ) {
		
		Person person 
			= this.personService.findOne( dto.getActor() );
		
		if ( person == null ) {
			throw new RuntimeException( "person [" + dto.getActor() + "] not found" );
		}
		
		this.actionRepository.persist( dto );
		
		return dto;
		
	}

	@Override
	public ActionDTO findOne( String id ) {
		
		return new ActionDTO();
		
	}

	@Override
	public List<ActionDTO> findAll() {
		return this.actionRepository.findAll();
	}

	@Override
	public List<Action<?>> todosFor( Person person ) {
		
		List<ActionDTO> dtos
			= this.actionRepository.findByActor( person.getId() );
		
		List<Action<?>> actions
			= new ArrayList<Action<?>>();
		
		for ( ActionDTO dto : dtos ) {
			
			try {
				
				String clz
					= dto.getClz();
				
				@SuppressWarnings("unchecked")
				Class<Action<?>> clAz
					= (Class<Action<?>>) Class.forName( clz );
				
				Action<?> action 
					= clAz.newInstance();
				
				actions.add( action );
				
			}
			catch( Exception e ) {
				throw new RuntimeException( e );
			}
			
		}
		
		return actions;
		
	}

	public IPersonService getPersonService() {
		return personService;
	}

	public DefaultActionService setPersonService(IPersonService personService) {
		this.personService = personService;
		return this;
	}

	public IActionRepository getActionRepository() {
		return actionRepository;
	}

	public DefaultActionService setActionRepository(IActionRepository actionRepository) {
		this.actionRepository = actionRepository;
		return this;
	}

}
