package be.occam.colloseum.publish.publit.resources;

import static be.occam.colloseum.rs.util.Controller.response;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publit.Publit;

@Controller
@RequestMapping("/publits")
@Scope("singleton")
public class PublitsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( PublitsResource.class );
	
	protected final Publit[] array 
		= new Publit[] {};
	
	@Resource
	protected IPublitService publitService;
	
	@RequestMapping( method= { RequestMethod.GET })
	public ResponseEntity<Publit[]> get(  ) {
		
		this.logger.info( "get" );
		
		Collection<Publit> publits
			=  this.publitService.findAll();
		
		return response( sorted( publits ).toArray( this.array ), HttpStatus.OK );
			
	}
	
	@RequestMapping( method= { RequestMethod.POST })
	public ResponseEntity<Publit> post( @RequestBody Publit publit ) {
		
		this.logger.info( "post" );
		
		String id
			= UUID.randomUUID().toString();
		
		publit.setId( id  );
		
		this.publitService.create( publit );
			
		return response( publit, HttpStatus.CREATED );
			
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleServiceException(Exception e, WebRequest webRequest ) {
		logger.warn("server error", e);
		return response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
	}
	
	protected Collection<Publit> sorted( Collection<Publit> unsorted ) {
		
		List<Publit> sorted
			= new LinkedList<Publit>();
		
		sorted.addAll( unsorted );
		
		Collections.sort( sorted, touched() );
		
		return sorted;
		
	}
	
	protected Comparator<Publit> touched( ) {
		
		return new Comparator<Publit>() {

			@Override
			public int compare(Publit o1, Publit o2) {
				
				// -1 if o1 more recent than o2
				// no date is verrrrry old
				
				int result = 0;
				
				if ( o1.getTouched() != null ) { 
					
					if ( o2.getTouched() != null ) {
						// reversed (oldest first)
						result = o2.getTouched().compareTo( o1.getTouched() );
					}
					else {
						result = -1;
					}
					
				}
				else {
					result = 1;
				}
				
				return result;
				
			}
		};
		
	}
}
