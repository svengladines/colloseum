package be.occam.colloseum.core.rs.jtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import be.occam.colloseum.person.Person;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestPersonsResource extends JTest {
	
	/*
	
	private final String path
		= "persons";
	
	public TestPersonsResource() {
		
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
		
		String first 
			= "Homer";
		
		String name
			= "Simpson";
		
		String nick
			= "Jay";
		
		String email
			= "homer.simpson@springfield.net";
		
		Person person
			= new Person().setFirstName( first ).setName( name ).setNickName( nick ).setEmail( email );
		
		ClientResponse response
			= resource
				.entity( person, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 201, response.getStatus() );
		
		String created
			= response.getEntity( String.class );
		
		logger.debug( "person: [{}]", created );
		
		ClientResponse getResponse
			= resource
			.accept( MediaType.APPLICATION_JSON_TYPE )
			.get( ClientResponse.class );
			
		
		assertEquals( 200, getResponse.getStatus() );
		
		Person [] persons
			= getResponse.getEntity( Person[].class );
		
		assertTrue( persons.length > 0 );
		
		for ( Person p : persons ) {
			logger.debug( "id = [{}]", p.getId() );
			logger.debug( "email = [{}]", p.getEmail() );
		}
		
	}
	
	*/
	
}
