package be.occam.colloseum.soccer.club.resources;

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

@Component
@Scope("singleton")
@Path( "clubs" )
public class ClubsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Club[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected IClubRepository clubRepository;
	
	protected MessageBodyWriter<Club> writer;
	
	@GET
	public Club[] get( ) {
		
		Set<Club> clubs 
			= this.clubRepository.findAll( this.providers );
		
		return clubs.toArray( this.array );
		
	}
	
	@POST
	public Response post( Club club ) {
		
		String id
			= club.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			club.setId( id );
		}
		
		this.clubRepository.persist( club , providers );
		
		return Response.ok().entity( club ).build();
		
	}
	
}
