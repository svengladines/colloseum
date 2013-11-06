package be.occam.colloseum.debrodders.jtests;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class TestWebAppDeBrodders_Test extends JTest {
	
	public TestWebAppDeBrodders_Test() {
		
		super( "/colloseum-debrodders", 8091 );
		
		ConfigurationProfiles.activateTestProfile();
		
	}
	
	@Test
	public void testIndex() throws Exception {
		
		Thread.sleep( 12000000 );
		
	}
	
}
