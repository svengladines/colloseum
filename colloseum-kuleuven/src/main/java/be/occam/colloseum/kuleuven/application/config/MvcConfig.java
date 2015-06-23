package be.occam.colloseum.kuleuven.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import be.occam.colloseum.kuleuven.web.controller.AuthorizationController;
import be.occam.colloseum.kuleuven.web.controller.MatchesController;
import be.occam.colloseum.kuleuven.web.controller.PageController;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.soccer.club.hats.WebMaster;

@EnableAsync
@EnableWebMvc
@Configuration
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
	public static class ControllerConfiguration {
		
		@Bean
		PageController pageController() {
			return new PageController();
		}
		
		@Bean
		MatchesController matchesController() {
			return new MatchesController();
		}
		
		@Bean
		AuthorizationController authorizationController() {
			return new AuthorizationController();
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
