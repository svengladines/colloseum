package be.occam.colloseum.core.rs.jtests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import be.occam.colloseum.model.Person;
import be.occam.colloseum.model.events.Events;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;

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
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		String first 
			= "Homer";
		
		String name
			= "Simpson";
		
		Person person
			= new Person().setFirstName( first ).setName( name );
		
		ClientResponse response
			= resource
				.entity( person, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 201, response.getStatus() );
		
		String publit
			= response.getEntity( String.class );
		
		logger.debug( "person: [{}]", publit );
		
		
	}
	
}
