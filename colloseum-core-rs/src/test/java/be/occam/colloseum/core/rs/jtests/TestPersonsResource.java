package be.occam.colloseum.core.rs.jtests;

import org.junit.BeforeClass;
import org.junit.Test;

import be.occam.test.jtest.JTest;

public class TestPersonsResource extends JTest {
	
	private final String path
		= "persons";
	
	public TestPersonsResource() {
		
		super( "/colloseum-core-rs" );
		this.forcePort = 8090;
		
	}
	
	@BeforeClass
	public static void setup() {
		
	}
	
	@Test
	public void testPost() {
		
		/*
		
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
		
		Person person
			= new Person().setFirstName( first ).setName( name ).setNickName( nick );
		
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
		}
		
		*/
	}
	
}
