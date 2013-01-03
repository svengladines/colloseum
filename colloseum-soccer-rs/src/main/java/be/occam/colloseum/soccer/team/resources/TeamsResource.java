package be.occam.colloseum.soccer.team.resources;

import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.soccer.club.Club;
import be.occam.colloseum.soccer.club.repository.IClubRepository;
import be.occam.colloseum.soccer.team.Team;
import be.occam.colloseum.soccer.team.repository.ITeamRepository;

@Component
@Scope("singleton")
@Path( "teams" )
public class TeamsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Team[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected ITeamRepository teamRepository;
	
	@GET
	public Team[] get( ) {
		
		Set<Team> clubs 
			= this.teamRepository.findAll( this.providers );
		
		return clubs.toArray( this.array );
		
	}
	
	@POST
	public Response post( Team team ) {
		
		String id
			= team.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			team.setId( id );
		}
		
		this.teamRepository.persist( team , providers );
		
		return Response.ok().entity( team ).build();
		
	}
	
}
