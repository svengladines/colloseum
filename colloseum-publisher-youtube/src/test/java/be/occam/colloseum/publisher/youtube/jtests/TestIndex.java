package be.occam.colloseum.publisher.youtube.jtests;

import org.junit.Test;

import be.occam.test.jtest.JTest;

public class TestIndex extends JTest {
	
	public TestIndex() {
		
		super( "/colloseum-publisher-youtube" );
		this.forcePort = 8091;
		
	}
	
	@Test
	public void testIndex() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
