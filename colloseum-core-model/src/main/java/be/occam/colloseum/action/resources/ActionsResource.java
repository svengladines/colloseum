package be.occam.colloseum.action.resources;

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

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.repository.IActionRepository;

@Path( "actions" )
public class ActionsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Action<?>[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected IActionRepository actionRepository;
	
	protected MessageBodyWriter<Action<?>> writer;
	
	@GET
	public Action<?>[] get( ) {
		
		Set<Action<?>> clubs 
			= this.actionRepository.findAll( this.providers );
		
		return clubs.toArray( this.array );
		
	}
	
	@POST
	public Response post( Action<?> club ) {
		
		String id
			= club.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			club.setId( id );
		}
		
		this.actionRepository.persist( club , providers );
		
		return Response.ok().entity( club ).build();
		
	}
	
}
