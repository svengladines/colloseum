package be.occam.colloseum.kuleuven.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.occam.colloseum.credential.resources.CredentialResource;
import be.occam.colloseum.credential.resources.CredentialsResource;
import be.occam.colloseum.kuleuven.application.web.resources.KULeuvenResource;
import be.occam.colloseum.kuleuven.application.web.resources.ScenarioResource;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.person.resources.PersonResource;
import be.occam.colloseum.person.resources.PersonsResource;
import be.occam.colloseum.soccer.club.hats.WebMaster;
import be.occam.colloseum.soccer.hitman.resources.HitMenResource;
import be.occam.colloseum.soccer.match.resources.MatchesResource;
import be.occam.colloseum.soccer.player.resources.PlayersResource;
import be.occam.colloseum.soccer.ranking.resources.RankingsResource;
import be.occam.colloseum.soccer.registration.resources.RegistrationResource;
import be.occam.colloseum.soccer.registration.resources.RegistrationsResource;

@EnableAsync
@EnableWebMvc
@Configuration
@Import({KULeuvenApplicationConfig.class})
public class MvcConfig {
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver
			= new InternalResourceViewResolver();
		resolver.setPrefix( "/WEB-INF/jsp/" );
		resolver.setSuffix( ".jsp" );
		return resolver;
	}
	
	@Configuration
	public static class ResourcesConfiguration {
		
		@Bean
		MatchesResource matchesResource() {
			
			return new MatchesResource();
			
		}
		
		@Bean
		RankingsResource rankingsResource() {
			
			return new RankingsResource();
			
		}
		
		@Bean
		HitMenResource hitMenResource() {
			
			return new HitMenResource();
			
		}
		
		@Bean
		CredentialsResource credentialsResource() {
			
			return new CredentialsResource();
			
		}
		
		@Bean
		CredentialResource credentialResource() {
			
			return new CredentialResource();
			
		}
		
		@Bean
		KULeuvenResource kuleuvenResource() {
			
			return new KULeuvenResource();
					
		}
		
		@Bean
		PersonsResource personsResource() {
			
			return new PersonsResource();
			
		}
		
		@Bean
		PersonResource personResource() {
			
			return new PersonResource();
			
		}
		
		@Bean
		ScenarioResource scenarioResource() {
			
			return new ScenarioResource();
			
		}
		
		@Bean
		RegistrationsResource registrationsResource() {
			
			return new RegistrationsResource();
			
		}
		
		@Bean
		RegistrationResource registrationResource() {
			
			return new RegistrationResource();
			
		}
		
		@Bean
		PlayersResource playersResource() {
			
			return new PlayersResource();
			
		}
		
		@Bean
		@Scope("singleton")
		public WebMaster webMaster() {
			return new WebMaster( new Tag( "kuleuven" ) );
		}
		
	}
	
	@Configuration
	public static class LocaleConfiguration {
		
		@Bean
		public LocaleResolver localeResolver() {
			
			return new FixedLocaleResolver( java.util.Locale.UK );
			
		}
		
	}

}
