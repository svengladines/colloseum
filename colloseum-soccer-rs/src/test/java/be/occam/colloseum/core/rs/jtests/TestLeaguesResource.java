package be.occam.colloseum.core.rs.jtests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import be.occam.colloseum.soccer.league.League;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestLeaguesResource extends JTest {
	
	/*
	
	private final String path
		= "leagues";
	
	public TestLeaguesResource() {
		super( "/colloseum-soccer-rs" );
	}
	
	@Test
	public void testGet() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );

		ClientResponse response
			= resource
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.get( ClientResponse.class );
	
		assertEquals( 200, response.getStatus() );
		
		String json
			= response.getEntity( String.class );
		
		logger.debug( "teams: [{}]", json );
		
	}
	
	@Test
	public void testPost() {
		
		String url 
			= this.baseResourceUrl().append( this.path ).toString();
	
		WebResource resource
			= this.client.resource( url );
		
		logger.debug( "url :[{}]", url );
		
		final String id
			= "KBLVB-3A";
		
		League league
			= new League().setId( id ).setName( "KBLVB 3" );

		ClientResponse response
			= resource
				.entity( league, MediaType.APPLICATION_JSON_TYPE )
				.accept( MediaType.APPLICATION_JSON_TYPE )
				.post( ClientResponse.class );
	
		assertEquals( 200, response.getStatus() );
		
		String json
			= response.getEntity( String.class );
		
		logger.debug( "json: [{}]", json );
		
		
	}
	
	*/
	
}
