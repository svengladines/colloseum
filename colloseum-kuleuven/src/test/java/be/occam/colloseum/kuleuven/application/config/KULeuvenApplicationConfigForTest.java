package be.occam.colloseum.kuleuven.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import be.occam.colloseum.util.ConfigurationProfiles;

@Configuration
@PropertySource( value={"classpath:/colloseum-core.properties"})
@Profile( ConfigurationProfiles.TEST )
public class KULeuvenApplicationConfigForTest {
	
}
