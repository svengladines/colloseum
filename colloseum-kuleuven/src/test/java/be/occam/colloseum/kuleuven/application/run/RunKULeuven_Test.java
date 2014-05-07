package be.occam.colloseum.kuleuven.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunKULeuven_Test extends JTest {
	
	public RunKULeuven_Test() {
		
		super( "/colloseum-kuleuven", 8091 );
		
		ConfigurationProfiles.activateTestProfile();
		
	}
	
	@Test
	public void doIt() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
