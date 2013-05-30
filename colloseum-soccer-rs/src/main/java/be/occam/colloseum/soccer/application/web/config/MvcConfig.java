package be.occam.colloseum.soccer.application.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAsync
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "be.occam.colloseum.soccer.event.resources" } )
@Import({ApplicationConfig.class})
public class MvcConfig {
	
	public class WebConfig {
	}

}
