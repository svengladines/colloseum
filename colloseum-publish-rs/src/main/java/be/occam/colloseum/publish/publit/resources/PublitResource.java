package be.occam.colloseum.publish.publit.resources;

import static be.occam.colloseum.rs.util.Controller.response;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publit.Publit;

@Controller
@RequestMapping("/publits/{id}")
@Scope("singleton")
public class PublitResource {
	
	private final Logger logger
		= LoggerFactory.getLogger( PublitResource.class );
	
	@Resource
	protected IPublitService publitService;
	
	@RequestMapping( method= { RequestMethod.PUT })
	public ResponseEntity<Publit> put( @RequestBody Publit publit ) {
		
		this.logger.info( "[{}]; put", publit );
		
		publit = this.publitService.update( publit );
			
		return response( publit, HttpStatus.CREATED );
			
	}
	
	@RequestMapping( method= { RequestMethod.DELETE })
	public ResponseEntity<Publit> delete( @PathVariable String id) {
		
		this.logger.info( "[{}]; delete", id );
		
		Publit publit 
			= this.publitService.delete( id );
			
		return response( publit, HttpStatus.OK );
			
	}
	
}
