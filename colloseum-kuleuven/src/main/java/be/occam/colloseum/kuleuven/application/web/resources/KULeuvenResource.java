package be.occam.colloseum.kuleuven.application.web.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class KULeuvenResource {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@RequestMapping( method=RequestMethod.GET )
	public String get() {
		
		logger.debug( "kuleuven GET" );
		
		return "kuleuven";
		
	}
	
}
