package be.occam.colloseum.soccer.application.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.colloseum.soccer.match.repository.IMatchRepository;
import be.occam.colloseum.soccer.match.repository.impl.DefaultMatchRepository;
import be.occam.colloseum.util.ConfigurationProfiles;

@Configuration
@EnableAsync
public class ApplicationConfig {

	@Configuration
	@ComponentScan("be.occam.colloseum.soccer.match.resources")
	public static class WebConfig {
		
	}
	
	@Configuration
	@Profile( ConfigurationProfiles.PRODUCTION )
	static class ConfigurationFilesForProduction {
		
	}
	
	@Configuration
	//@Profile({ConfigurationProfiles.PRODUCTION })
	static class CalendarConfigForProduction {
		
		@Bean
		public Client client() {
			return new Client("");
		}
		
	}
	
	@Configuration
	//@Profile({ConfigurationProfiles.PRODUCTION })
	static class RepositoryConfigForProduction {
		
		@Bean
		public IMatchRepository matchRepository() {
			return new DefaultMatchRepository();
		}
		
	}
	
}
