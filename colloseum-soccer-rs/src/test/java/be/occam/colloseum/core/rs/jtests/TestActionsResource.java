package be.occam.colloseum.core.rs.jtests;

import static be.occam.colloseum.core.rs.data.Springfield.People.homer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.club.Club;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestActionsResource extends JTest {
	
	/*
	private final String path
		= "actions";
	
	public TestActionsResource() {
		
		super( "/colloseum-soccer-rs" );
		this.forcePort = 8090;
		
	}
	
	@BeforeClass
	public static void setup() {
		
	}
	
	@Test
	public void testPost() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		Person actor
			= homer();
		
		Club club
			= new Club().setId("rscs");
		
		ActionDTO action
			= new ActionDTO().setActor( actor.getId() ).setSubject( club.getId() );
		
		ClientResponse response
			= resource
				.entity( action, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		// TODO: make this 201 when resource is ready
		assertEquals( 200, response.getStatus() );
		
		String created
			= response.getEntity( String.class );
		
		logger.debug( "created: [{}]", created );
		
		ClientResponse getResponse
			= resource
			.accept( MediaType.APPLICATION_JSON_TYPE )
			.get( ClientResponse.class );
		
		assertEquals( 200, getResponse.getStatus() );
		
		ActionDTO[] actions
			= getResponse.getEntity( ActionDTO[].class );
		
		assertTrue( actions.length > 0 );
		
		this.logger.info( "actions.#[{}]", actions.length );
		
	}
	*/
	
}
