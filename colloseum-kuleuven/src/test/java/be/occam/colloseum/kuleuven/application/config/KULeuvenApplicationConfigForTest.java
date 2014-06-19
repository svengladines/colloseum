package be.occam.colloseum.kuleuven.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import be.occam.colloseum.core.credential.repository.impl.FileSystemCredentialRepository;
import be.occam.colloseum.credential.repository.ICredentialRepository;
import be.occam.colloseum.person.repository.IPersonRepository;
import be.occam.colloseum.person.repository.impl.FileSystemPersonRepository;
import be.occam.colloseum.util.ConfigurationProfiles;

@Configuration
@PropertySource( value={"classpath:/colloseum-core.properties"})
@Profile( ConfigurationProfiles.TEST )
public class KULeuvenApplicationConfigForTest {

	final static String BASE_PKG = "be.occam.colloseum.debrodders";

	@Configuration
	@Profile({ConfigurationProfiles.TEST})
	@PropertySource("classpath:colloseum-publisher-rs.properties")
	static class RepositoryConfigForTest {
		
		/*
		
		@Bean
		IPublitRepository publitRepository() {
			
			return new FileSystemPublitRepository();
			
		}
		*/
		
		@Bean
		ICredentialRepository credentialRepository() {

			return new FileSystemCredentialRepository();
			
		}
		
		@Bean
		IPersonRepository personRepository() {

			return new FileSystemPersonRepository();
			
		}
		
	}
	
}
