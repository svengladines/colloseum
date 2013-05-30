package be.occam.colloseum.utests.action.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.action.repository.IActionRepository;
import be.occam.colloseum.action.service.impl.DefaultActionService;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.person.service.IPersonService;

public class TestDefaultActionService {
	
	DefaultActionService service
		= new DefaultActionService();
	
	IActionRepository repo;
	IPersonService personService;
	
	@Before
	public void setup() throws Exception {
		
		repo
			= createMock( IActionRepository.class );
		
		personService
			= createMock( IPersonService.class );
		
		service.setActionRepository( repo );
		service.setPersonService( personService );
		
	}

	@Test
	public void doesItSmoke() throws Exception {
		
		Person homer
			= new Person().setId( "homer.simpson" );
		
		List<ActionDTO> list 
			= Arrays.asList( new ActionDTO().setActor( homer.getId() ) );
		
		// expect( repo.findByActor( homer.getId() ) ).andReturn( );
		
		//List<Action<?>> list
		//	= service.todosFor( homer );
		
		assertTrue( list != null );
		
	}
	
}
