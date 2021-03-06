package be.occam.colloseum.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.core.credential.repository.impl.FileSystemCredentialRepository;
import be.occam.colloseum.credential.repository.ICredentialRepository;
import be.occam.colloseum.kblvb.application.config.CuteGirlFromTheKblvb;
import be.occam.colloseum.kblvb.application.config.KblvbApplicationConfig;
import be.occam.colloseum.person.service.IPersonService;
import be.occam.colloseum.person.service.impl.DefaultPersonService;
import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publish.publit.service.impl.DefaultPublitService;
import be.occam.colloseum.soccer.club.hats.Bookie;
import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.club.hats.Ranker;
import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
@EnableTransactionManagement
public class DeBroddersApplicationConfig {

	final static String BASE_PKG = "be.occam.colloseum.debrodders";

	@Configuration
	@ComponentScan(BASE_PKG)
	public static class WebConfig {
		
	}
	
	@Configuration
	static class PropertiesConfigurer {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer propertiesConfigurer() {
			PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
			p.setIgnoreUnresolvablePlaceholders(true);
			return p;
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION,ConfigurationProfiles.DEV})
	// @Import( PublishAppEngineApplicationConfig.class )
	static class RepositoryConfigForProduction {
	
		@Bean
		ICredentialRepository credentialRepository() {

			return new FileSystemCredentialRepository();
			
		}
		
	}

	@Configuration
	@Import( KblvbApplicationConfig.class )
	static class HatConfiguration {
		
		@Bean
		public Ranker ranker() {
			return new Ranker( "De Brodders Herselt");
		}
		
		@Bean
		public Bookie bookie() {
			return new Bookie();
		}
		
		@Bean
		public Fixer fixer() {
			return new Fixer();
		}
		
		@Bean
		public CuteGirlFromTheLeague cuteGirlFromTheLeague() {
			return new CuteGirlFromTheKblvb();
		}
		
		@Bean
		public CalendarGuy calendarGuy() {
			return new CalendarGuy( );
		}
		
		@Bean
		public Client client() {
			return new Client( "debrodders@gmail.com" );
		}
		
	}
	
	@Configuration
	static class ServiceConfiguration {
		
		@Bean
		public IPublitService publitService() {
			return new DefaultPublitService();
		}
		
		@Bean
		public IPersonService personService() {
			return new DefaultPersonService();
		}
		
	}
	
}
