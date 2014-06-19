package be.occam.colloseum.kuleuven.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import be.occam.colloseum.appengine.application.config.AppEngineConfig;
import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.email.MailMan;
import be.occam.colloseum.kuleuven.cron.scenarios.InvitePlayersToRegister;
import be.occam.colloseum.kuleuven.hats.KULeuvenLeagueManager;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.person.HeadHunter;
import be.occam.colloseum.person.service.IPersonService;
import be.occam.colloseum.person.service.impl.DefaultPersonService;
import be.occam.colloseum.soccer.club.hats.Bookie;
import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.club.hats.Ranker;
import be.occam.colloseum.soccer.club.hats.SpokesPerson;
import be.occam.colloseum.soccer.club.hats.TeamManager;
import be.occam.colloseum.soccer.league.hats.LeagueManager;
import be.occam.utils.spring.configuration.ConfigurationProfiles;

@Configuration
@EnableTransactionManagement
public class KULeuvenApplicationConfig {

	final static String BASE_PKG = "be.occam.colloseum.kuleuven";

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
	@Import( AppEngineConfig.class )
	static class RepositoryConfigForProduction {
	
		/*
		@Bean
		ICredentialRepository credentialRepository() {

			return new FileSystemCredentialRepository();
			
		}
		*/
		
	}

	@Configuration
	static class HatConfiguration {
		
		final Tag kuleuvenTag = new Tag( "kuleuven", "kuleuven" );
		
		@Bean
		public Ranker ranker() {
			return new Ranker( kuleuvenTag );
		}
		
		@Bean
		public Bookie bookie() {
			return new Bookie( kuleuvenTag );
		}
		
		@Bean
		public Fixer fixer() {
			return new Fixer( kuleuvenTag );
		}
		
		@Bean
		public TeamManager teamManager() {
			return new TeamManager( kuleuvenTag );
		}
		
		@Bean
		public SpokesPerson spokesPerson() {
			return new SpokesPerson( kuleuvenTag, "kuleuven-foot@kuleuven.be" );
		}
		
		@Bean
		public HeadHunter headHunter() {
			return new HeadHunter( );
		}
		
		@Bean
		public CalendarGuy calendarGuy() {
			return new CalendarGuy( );
		}
		
		@Bean
		public Client client() {
			return new Client( "forza.sedes@gmail.com" );
		}
		
		@Bean
		public MailMan mailMan() {
			return new MailMan();
		}
		
		@Bean
		LeagueManager cuteGirlFromTheLeague() {
			return new KULeuvenLeagueManager();
		}
		
	}
	
	@Configuration
	static class ServiceConfiguration {
		
		@Bean
		public IPersonService personService() {
			return new DefaultPersonService();
		}
		
	}
	
	@Configuration
	// @Profile( ConfigurationProfiles.PRODUCTION )
	@PropertySource("classpath:kuleuven.properties")
	public static class EmailConfigForProduction {
		
		@Bean
		JavaMailSender javaMailSender(@Value("${email.smtp.host}") String smtpHost, @Value("${email.smtp.port}") Integer smtpPort ) {
			
			JavaMailSenderImpl sender
				= new JavaMailSenderImpl();
			
			sender.setHost( smtpHost );
			sender.setPort( smtpPort );
			
			return sender;
				
		}
		
	}
	
	@Configuration
	public static class ScenariosConfig {
		
		@Bean
		InvitePlayersToRegister invitePlayersToRegister() {
			
			return new InvitePlayersToRegister();
				
		}
		
	}
	
}
