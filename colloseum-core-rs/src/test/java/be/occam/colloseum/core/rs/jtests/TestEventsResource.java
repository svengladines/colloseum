package be.occam.colloseum.core.rs.jtests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import be.occam.colloseum.model.events.Events;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataMultiPart;

public class TestEventsResource extends JTest {
	
	private final String path
		= "events";
	
	
	public TestEventsResource() {
		
		super( "/colloseum-core-rs" );
		this.forcePort = 8090;
		
	}
	
	@BeforeClass
	public static void setup() {
		
	}
	
	@Test
	public void testPostEventByForm() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		String object
			= "god";
		
		String subject
			= "homer.simpson";
		
		String tags
			= "waitforit,cowabunga";
		
		String date = "28.08.2012";
		
		String time = "11:00";
		
		String type
			= Events.BORN.getClass().getCanonicalName();
	
		FormDataMultiPart formDataMultiPart 
			= new FormDataMultiPart();
		
		formDataMultiPart.field( "type", type, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "object", object, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "subject", subject, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "tags", tags, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "date", date, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "time", time, MediaType.TEXT_PLAIN_TYPE );

		ClientResponse response
			= resource
				.entity( formDataMultiPart, MediaType.MULTIPART_FORM_DATA_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 201, response.getStatus() );
		
		String publit
			= response.getEntity( String.class );
		
		logger.debug( "publit: [{}]", publit );
		
		
	}
	
	public void testPostFile() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		String title
			= "Homer says";
		
		String data 
			= "The X File";
	
		FormDataMultiPart formDataMultiPart 
			= new FormDataMultiPart();
		
		formDataMultiPart.field( "title", title, MediaType.TEXT_PLAIN_TYPE );
		
		FormDataContentDisposition fdcd
			= FormDataContentDisposition
				.name( "file" )
				.fileName( "thebookofevil.pdf" )
				.build();
		
		formDataMultiPart.field( "file", data, MediaType.valueOf( "application/pdf" ) );
		
		formDataMultiPart.getField( "file" ).setContentDisposition( fdcd );

		ClientResponse response
			= resource
				.entity( formDataMultiPart, MediaType.MULTIPART_FORM_DATA_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 201, response.getStatus() );
		
		String publit
			= response.getEntity( String.class );
		
		logger.debug( "publit: [{}]", publit );
		
		
	}
	
	public void testIndex() throws Exception {
		Thread.sleep( 1200000 );
	}
	
}
