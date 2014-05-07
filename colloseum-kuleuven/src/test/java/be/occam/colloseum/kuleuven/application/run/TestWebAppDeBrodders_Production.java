package be.occam.colloseum.kuleuven.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class TestWebAppDeBrodders_Production extends JTest {
	
	public TestWebAppDeBrodders_Production() {
		
		super( "/colloseum-debrodders", 8091 );
		
		ConfigurationProfiles.activateTestProfile();
		
	}
	
	@Test
	public void testIndex() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
