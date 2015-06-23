package be.occam.colloseum.kuleuven.application.run;

import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class RunKULeuven_Production extends JTest {
	
	public RunKULeuven_Production() {
		
		super( "/", 8091, ConfigurationProfiles.PRODUCTION );
		
	}
	
	@Test
	public void doIt() throws Exception {
		
		Thread.sleep( 1200000 );
		
	}
	
}
