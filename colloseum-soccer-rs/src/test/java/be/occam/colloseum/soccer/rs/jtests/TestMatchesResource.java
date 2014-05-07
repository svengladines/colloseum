package be.occam.colloseum.soccer.rs.jtests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import be.occam.colloseum.soccer.match.Match;
import be.occam.test.jtest.JTest;

public class TestMatchesResource extends JTest {
	
	private final String path
		= "/rs/matches";
	
	public TestMatchesResource() {
		super( "/colloseum-soccer-rs" );
	}
	
	@Test
	public void testGet() {
		
		String url 
			= this.baseUrl().append( this.path ).toString();
	
		ResponseEntity<Match[]> response 
			= this.template.getForEntity(url, Match[].class );

		assertEquals( 200, response.getStatusCode().value()  );
		
	}
	
	/*
	@Test
	public void testPost() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		final String id
			= "rsca";
		
		Team team
			= new Team().setId( id ).setName( "Royal Sporting Club Anderlecht" );

		ClientResponse response
			= resource
				.entity( team, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 200, response.getStatus() );
		
		String json
			= response.getEntity( String.class );
		
		logger.debug( "json: [{}]", json );
		
		
	}
	*/
	
}
