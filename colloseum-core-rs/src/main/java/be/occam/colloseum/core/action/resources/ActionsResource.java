package be.occam.colloseum.core.action.resources;

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

import be.occam.colloseum.action.Action;
import be.occam.colloseum.core.action.service.ActionService;

@Component
@Scope("singleton")
@Path( "actions" )
public class ActionsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Action<?>[] array = {};
	
	@Context
	protected Providers providers;
	
	@Resource
	protected ActionService actionService;
	
	protected MessageBodyWriter<Action<?>> writer;
	
	@GET
	public Action<?>[] get( ) {
		
		return this.array;
		
	}
	
	@POST
	public Response post( Action<?> action ) {
	
		String id
			= action.getId();
		
		if ( id == null ) {
			id
				= UUID.randomUUID().toString();
			action.setId( id );
		}
		
		this.actionService.consume( action, providers );
		
		return Response.ok().build();
		
	}
	
}
