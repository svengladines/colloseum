package be.occam.colloseum.front.jtests;

import org.junit.Test;

import be.occam.test.jtest.JTest;


public class TestIndex extends JTest {
	
	public TestIndex() {
		
		super( "/colloseum-front" );
		this.forcePort = 8091;
		
		// this.context( "/colloseum-core-rs", "/home/sven/psx/projecten/colloseum/colloseum-core-rs/target/colloseum-core-rs-1.0-LATEST-SNAPSHOT.war" );
		
		this.context( "/colloseum-publisher-rs", "/home/sven/psx/projecten/colloseum/colloseum-publisher-rs/target/colloseum-publisher-rs-1.0-LATEST-SNAPSHOT.war" );
		this.context( "/colloseum-publisher-youtube", "/home/sven/psx/projecten/colloseum/colloseum-publisher-youtube/target/colloseum-publisher-youtube-1.0-LATEST-SNAPSHOT.war" );
		
	}
	
	@Test
	public void testIndex() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
