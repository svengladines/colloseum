package be.occam.colloseum.soccer.match.resources;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.repository.IMatchRepository;

@Component
@Scope("singleton")
@Path( "matches" )
public class MatchesResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Match[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected IMatchRepository matchRepository;
	
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
	
}
