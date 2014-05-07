package be.occam.colloseum.kuleuven.jtests;

import static be.occam.test.jtest.JTestUtil.*;
import org.junit.Test;

import be.occam.test.jtest.JTest;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

public class TestKULeuvenFixer extends JTest {
	
	public TestKULeuvenFixer() {
		
		super( "/colloseum-debrodders" );
		
		ConfigurationProfiles.activateTestProfile();
		
	}
	
}
