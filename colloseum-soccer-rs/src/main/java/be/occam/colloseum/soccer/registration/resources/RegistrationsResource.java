package be.occam.colloseum.soccer.registration.resources;

import static be.occam.colloseum.util.Resource.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.club.hats.TeamManager;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.player.Player;
import be.occam.colloseum.soccer.registration.RSVP;
import be.occam.colloseum.soccer.registration.Registration;

@Controller
@RequestMapping( {"/registrations"} )
public class RegistrationsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Match[] array = {};
	
	@Resource
	TeamManager teamManager;
	
	@Resource
	Fixer fixer;
	
	@RequestMapping( method = { RequestMethod.GET }, produces={"application/json","text/xml"} )
	@ResponseBody
	public ResponseEntity<List<Registration>> get(
				@RequestParam(required=false) String match,
				@RequestParam(required=false) String player,
				@RequestParam(required=false) String rsvp ) {
		
		List<Registration> registrations
			= new ArrayList<Registration>( 1 );
		
		Player p
			= this.teamManager.whoIsThePlayerWithID( player );
		
		Match m
			= this.fixer.whichMatchHasID( match );
		
		Registration registration
			= this.teamManager.whatIsTheRegistrationForPlayerForMatch( p, m );
		
		if ( registration != null ) {
			
			registration.setId( UUID.randomUUID().toString() );
			
			if ( rsvp != null ) {
				// filter on rsvp
				RSVP r = new RSVP( rsvp );
				if ( r.getAnswer().equals( registration.getRsvp().getAnswer() ) ){
					registrations.add( registration );			
				}
			}
			
		}
		
		return response( registrations, HttpStatus.OK );
		
	}
	
	@RequestMapping( method = { RequestMethod.POST } )
	@ResponseBody
	public ResponseEntity<Registration> post(
				@RequestBody Registration registration ) {
		
		Player p
			= registration.getPlayer();
		
		Match m
			= registration.getMatch();
		
		RSVP r
			= registration.getRsvp();
		
		Player player
			= this.teamManager.whoIsThePlayerWithID( p.getId() );
		
		Match match 
			= this.fixer.whichMatchHasID( m.getId() );
		
		registration.setPlayer( player );
		registration.setMatch( match );
		registration.setRsvp( r );
		
		this.teamManager.registerPlayerForMatch( registration );
		
		return response( registration, HttpStatus.OK );
		
	}
	
	@RequestMapping( method = { RequestMethod.GET }, produces= MediaType.TEXT_HTML_VALUE )
	public ModelAndView view(
				@RequestParam(required=false) String match,
				@RequestParam(required=false) String player,
				@RequestParam(required=false) String rsvp ) {
		
		ResponseEntity<List<Registration>> response
			= this.get( match, player, rsvp );
		
		Map<String,Object> map
			= new HashMap<String,Object>();
		
		map.put("registrations", response.getBody() );
		
		return new ModelAndView( "registration", map );
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException( Exception e ) {
		logger.warn( "exception thrown while processing request", e );
		return response( new StringBuilder("exception:").append( e.getMessage() ).toString(), HttpStatus.INTERNAL_SERVER_ERROR );
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
