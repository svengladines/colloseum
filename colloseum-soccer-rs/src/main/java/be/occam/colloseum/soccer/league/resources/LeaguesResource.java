package be.occam.colloseum.soccer.league.resources;

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

import be.occam.colloseum.soccer.league.League;
import be.occam.colloseum.soccer.league.repository.ILeagueRepository;

@Component
@Scope("singleton")
@Path( "leagues" )
public class LeaguesResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected League[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected ILeagueRepository leagueRepository;
	
	@GET
	public League[] get( ) {
		
		Set<League> set 
			= this.leagueRepository.findAll( this.providers );
		
		return set.toArray( this.array );
		
	}
	
	@POST
	public Response post( League league ) {
		
		String id
			= league.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			league.setId( id );
		}
		
		this.leagueRepository.persist( league , providers );
		
		return Response.ok().entity( league ).build();
		
	}
	
}
