package be.occam.colloseum.core.rs.jtests;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import be.occam.colloseum.soccer.league.League;
import be.occam.test.jtest.JTest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TestWebApp extends JTest {
	
	private final String path
		= "leagues";
	
	public TestWebApp() {
		super( "/colloseum-front" );
		this.forcePort = 8099;
	}
	
	@Test
	public void doesItSmoke() throws Exception {
		
		Thread.sleep( 1000000 );
		
	}
	
}
