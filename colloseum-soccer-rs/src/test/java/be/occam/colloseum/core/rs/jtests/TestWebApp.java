package be.occam.colloseum.core.rs.jtests;

import org.junit.Ignore;
import org.junit.Test;

import be.occam.test.jtest.JTest;

@Ignore
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
