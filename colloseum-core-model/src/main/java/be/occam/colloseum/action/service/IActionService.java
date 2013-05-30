package be.occam.colloseum.action.service;

import java.util.List;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.person.Person;

public interface IActionService {
	
	ActionDTO create( ActionDTO dto );
	
	ActionDTO findOne( String id );
	
	List<ActionDTO> findAll();
	
	List<Action<?>> todosFor( Person person );
	
}
