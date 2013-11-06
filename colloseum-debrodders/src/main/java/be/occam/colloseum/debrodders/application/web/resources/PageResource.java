package be.occam.colloseum.debrodders.application.web.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
