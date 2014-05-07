package be.occam.colloseum.kuleuven.application.web.resources;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping( value="{page}.html" )
public class PageResource {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@RequestMapping( method=RequestMethod.GET )
	public String get( @PathVariable String page ) {
		
		logger.debug( "GET for page [{}]", page );
		
		return page;
		
	}
	
}
