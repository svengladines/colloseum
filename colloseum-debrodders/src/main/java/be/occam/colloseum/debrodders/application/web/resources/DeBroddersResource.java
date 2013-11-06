package be.occam.colloseum.debrodders.application.web.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class DeBroddersResource {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@RequestMapping( method=RequestMethod.GET )
	public String get() {
		
		logger.debug( "debrodders GET" );
		
		return "debrodders";
		
	}
	
}
