package be.occam.colloseum.publisher.rs.jtests;

import org.junit.BeforeClass;
import org.junit.Ignore;

import be.occam.test.jtest.JTest;

@Ignore
public class TestPublitsResource extends JTest {
	
	private final String path
		= "publits";
	
	
	public TestPublitsResource() {
		
		super( "/colloseum-publisher-rs" );
		this.forcePort = 8090;
		
	}
	
	@BeforeClass
	public static void setup() {
		
		// PublisherRegistry.getInstance().register( new YouTubePublisher() ).register( new RemarkPublisher()  );
		
	}
	
	/*
	
	@Test
	public void testPost() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		String title
			= "You'll neeever walk alone";
	
		Publit publit
			= new Publit().setTitle( title ).setPlay( "http://www.youtube.com/watch?v=WfP2ABaKZIY" ).setTrack( "t-1" );

		ClientResponse response
			= resource
				.entity( publit, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 201, response.getStatus() );
		
		ClientResponse getResponse
			= resource
			.accept( MediaType.APPLICATION_JSON_TYPE )
			.get( ClientResponse.class );
		
		// String created
		//	= response.getEntity( String.class );
		
		// logger.debug( "publit: [{}]", publit );
		
	}
	
	public void testPostRemark() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		String title
			= "Homer says";
		
		String description
			= "One of the most intriging remarks Homer ever made";
		
		String data 
			= "Whenever I learn something new, it pushes something old out of my brain";
	
		FormDataMultiPart formDataMultiPart 
			= new FormDataMultiPart();
		
		formDataMultiPart.field( "title", title, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "data", data, MediaType.TEXT_PLAIN_TYPE );
		
		formDataMultiPart.field( "description", description, MediaType.TEXT_PLAIN_TYPE );

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
	*/
	
}
