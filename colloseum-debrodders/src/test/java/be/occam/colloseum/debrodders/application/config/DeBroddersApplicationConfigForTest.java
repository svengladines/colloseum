package be.occam.colloseum.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import be.occam.colloseum.core.credential.repository.impl.FileSystemCredentialRepository;
import be.occam.colloseum.credential.repository.ICredentialRepository;
import be.occam.colloseum.publish.publit.repository.impl.FileSystemPublitRepository;
import be.occam.colloseum.publit.repository.IPublitRepository;
import be.occam.colloseum.util.ConfigurationProfiles;

@Configuration
@Import( {DeBroddersApplicationConfig.class, MvcConfig.class } )
@PropertySource( value={"classpath:/colloseum-core.properties"})
public class DeBroddersApplicationConfigForTest {

	final static String BASE_PKG = "be.occam.colloseum.debrodders";

	@Configuration
	@ComponentScan(BASE_PKG)
	public static class WebConfig {
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.TEST})
	@PropertySource("classpath:colloseum-publisher-rs.properties")
	static class RepositoryConfigForTest {
		
		@Bean
		IPublitRepository publitRepository() {
			
			return new FileSystemPublitRepository();
			
		}
		
		@Bean
		ICredentialRepository credentialRepository() {

			return new FileSystemCredentialRepository();
			
		}
		
	}
	
}
