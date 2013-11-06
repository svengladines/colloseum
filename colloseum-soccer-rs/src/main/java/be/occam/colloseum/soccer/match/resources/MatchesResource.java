package be.occam.colloseum.soccer.match.resources;

import static be.occam.colloseum.util.Resource.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.actions.Plan;
import be.occam.colloseum.soccer.match.repository.IMatchRepository;
import be.occam.colloseum.util.Timing;

@Controller
@RequestMapping( {"/matches"} )
public class MatchesResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Match[] array = {};
	
	@Context
	protected Providers providers;
	
	// @Resource
	protected IMatchRepository matchRepository;
	
	@Resource
	Fixer fixer;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<List<Match>> query(
				@RequestParam(required=false) String cut ) {
		
		Date now = new Date();
		
		Date cutOff
			= cut == null ? now : Timing.date( cut ) ;
		
		List<Plan> planned
			= this.fixer.whatAreThePlannedMatchesAfter( cutOff );
		
		List<Match> matches
			= new ArrayList<Match>( planned.size() );
		
		for ( Plan p : planned ) {
			
			Match m 
				= p.getSubject();
			
			matches.add( m );
			
		}
		
		return response( matches, HttpStatus.OK );
		
	}
	
	/*
	@GET
	public Match[] get( ) {
		
		Set<Match> set 
			= this.matchRepository.findAll( this.providers );
		
		return set.toArray( this.array );
		
	}
	
	@POST
	public Response post( Match match ) {
		
		String id
			= match.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			match.setId( id );
		}
		
		this.matchRepository.persist( match , providers );
		
		return Response.ok().entity( match ).build();
		
	}
	*/
	
}
