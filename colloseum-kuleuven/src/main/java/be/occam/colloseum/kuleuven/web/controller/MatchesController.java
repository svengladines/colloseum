package be.occam.colloseum.kuleuven.web.controller;

import static be.occam.utils.spring.web.Controller.response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.actions.Plan;
import be.occam.utils.spring.web.RedirectException;
import be.occam.utils.timing.Timing;

@Controller
@RequestMapping( {"/matches"} )
public class MatchesController {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Match[] array = {};
	
	@Resource
	Fixer fixer;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<List<Match>> query(
				@RequestParam(required=false) String from,
				@RequestParam(required=false) String till,
				@RequestParam(required=false) String next ) {
		
		Date now = new Date();
		
		Date cutOff
			= from == null ? now : Timing.date( from ) ;
		
		Date untill
			= till == null ? null : Timing.date( till ) ;
		
		boolean nxt
			= next == null ? false : Boolean.parseBoolean( next );
		
		List<Match> matches
			= new ArrayList<Match>( );
		
		if ( nxt ) {
			
			Match nextMatch
				= this.fixer.whatIsTheNextMatch();
			
			if ( nextMatch != null ) {
				matches.add( nextMatch );
			}
			
		}
		else {
			
			List<Plan> planned
				= this.fixer.whatAreThePlannedMatchesAfter( cutOff, untill );
			
			for ( Plan p : planned ) {
				
				Match m 
					= p.getSubject();
				
				matches.add( m );
				
			}
		}
		
		return response( matches, HttpStatus.OK );
		
	}
	
	@RequestMapping( method = { RequestMethod.GET }, produces={ MediaType.TEXT_HTML_VALUE } )
	public ModelAndView view( 
			@RequestParam(required=false) String from,
			@RequestParam(required=false) String till,
			@RequestParam(required=false) String next ) {
		
		ResponseEntity<List<Match>> matches
			= this.query( from, till, next );
		
		Map<String,Object> model
			= new HashMap<String,Object>();
	
		model.put( "matches", matches.getBody() );
		
		final String view
			= "matches";
		
		return new ModelAndView( view, model );
		
	}
	
	// SGL| generic response handler for all controllers ? in mvcconfig ?
	@ExceptionHandler(RedirectException.class)
	public ResponseEntity<String> handleRedirectException(RedirectException e, HttpServletRequest servletRequest ) {
		
		logger.info("redirecting to consent page", e);
		
		Map<String,String> headers
			= new HashMap<String,String>();
		
		StringBuilder b
			= new StringBuilder( servletRequest.getRequestURL().toString() );
		
		if ( servletRequest.getQueryString() != null ) {
			b.append( "&" ).append( servletRequest.getQueryString() );	
		}
		
		try {
			 b = new StringBuilder( URLEncoder.encode( b.toString(), "utf-8" ) );
		}
		catch( UnsupportedEncodingException ignore ) {}
		
		StringBuilder c
			= new StringBuilder( e.getRedirectUrl() );
			
		headers.put( "Location", c.toString() );
		
		ResponseEntity<String>response
				= response( e.getMessage(), HttpStatus.TEMPORARY_REDIRECT, headers );
		
		return response;
		
	}
	
}
