package be.occam.colloseum.debrodders.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.occam.colloseum.credential.resources.CredentialResource;
import be.occam.colloseum.credential.resources.CredentialsResource;
import be.occam.colloseum.debrodders.application.web.resources.DeBroddersResource;
import be.occam.colloseum.person.resources.PersonResource;
import be.occam.colloseum.person.resources.PersonsResource;
import be.occam.colloseum.publish.publit.resources.PublitResource;
import be.occam.colloseum.publish.publit.resources.PublitsResource;
import be.occam.colloseum.soccer.hitman.resources.HitMenResource;
import be.occam.colloseum.soccer.match.resources.MatchesResource;
import be.occam.colloseum.soccer.ranking.resources.RankingsResource;

@EnableAsync
@EnableWebMvc
@Configuration
@Import({DeBroddersApplicationConfig.class})
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
		DeBroddersResource deBroddersResource() {
			
			return new DeBroddersResource();
					
		}
		
		@Bean
		PublitsResource publitsResource() {
			
			return new PublitsResource();
			
		}
		
		@Bean
		PublitResource publitResource() {
			
			return new PublitResource();
			
		}
		
		@Bean
		PersonsResource personsResource() {
			
			return new PersonsResource();
			
		}
		
		@Bean
		PersonResource personResource() {
			
			return new PersonResource();
			
		}
		
		
	}

}
