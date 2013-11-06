package be.occam.colloseum.publish.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import be.occam.colloseum.publish.publit.resources.PublitsResource;

@Configuration
public class PublishRsApplicationConfig {
	
	@Configuration
	static class ConfigurationFilesForProduction {
		
	}
	
	@Configuration
	static class ResourceConfiguration {
		
		@Bean
		PublitsResource publitsResource() {
			return new PublitsResource();
		}
		
	}
	
}
