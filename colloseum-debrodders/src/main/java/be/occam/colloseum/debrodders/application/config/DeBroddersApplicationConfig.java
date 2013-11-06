package be.occam.colloseum.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publish.publit.service.impl.DefaultPublitService;
import be.occam.colloseum.soccer.club.hats.Fixer;
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
	static class RepositoryConfigForProduction {
		
		// here come the appengine repository
		
	}
	
	@Configuration
	static class HatConfiguration {
		
		@Bean
		public Fixer fixer() {
			return new Fixer();
		}
		
		@Bean
		public CuteGirlFromTheLeague cuteGirlFromTheLeague() {
			return new CuteGirlFromTheLeague();
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
		
	}
	
}
