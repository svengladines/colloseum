package be.occam.colloseum.soccer.application.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.util.ConfigurationProfiles;

@Configuration
@EnableAsync
public class ApplicationConfig {

	final static String BASE_PKG = "be.occam.colloseum.soccer.event.resources";
	
	@Configuration
	@ComponentScan(BASE_PKG)
	public static class WebConfig {
		
	}
	
	@Configuration
	@Profile( ConfigurationProfiles.PRODUCTION )
	static class ConfigurationFilesForProduction {
		
		@Bean
		@Scope("singleton")
		public static PropertySourcesPlaceholderConfigurer properties(@Value("file:/usr/local/blackboard/config/toledodb.properties") Resource propertyResource) {
			PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
			p.setLocation(propertyResource);
			p.setIgnoreUnresolvablePlaceholders(true);
			return p;
		}
		
	}
	
	@Configuration
	@Profile({ConfigurationProfiles.PRODUCTION })
	static class SoccerConfigForProduction {
		
		@Bean
		public Client client() {
			return new Client();
		}
		
		@Bean
		public CalendarGuy calendarGuy() {
			return new CalendarGuy();
		}
		
	}
	
}
