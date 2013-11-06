package be.occam.colloseum.publisher.youtube.jtests;

import org.junit.Ignore;
import org.junit.Test;

import be.occam.test.jtest.JTest;

@Ignore
public class TestYouTubeIndex extends JTest {
	
	public TestYouTubeIndex() {
		
		super( "/colloseum-publisher-youtube" );
		this.forcePort = 8091;
		
	}
	
	@Test
	public void testIndex() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
