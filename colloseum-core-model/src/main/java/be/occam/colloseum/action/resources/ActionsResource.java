package be.occam.colloseum.action.resources;

import java.util.Arrays;
import java.util.List;
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
import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.action.service.IActionService;
import be.occam.colloseum.application.ProvidersRegistry;

@Path( "actions" )
public class ActionsResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Providers providers;
	
	protected MessageBodyWriter<Action<?>> writer;
	
	@Resource
	protected IActionService actionService;
	
	@GET
	public List<ActionDTO> get( ) {
	
		List<ActionDTO> actions 
			= Arrays.asList( );
		
		return actions;
		
	}
	
	@POST
	public Response post( ActionDTO action ) {
		
		String id
			= action.getId();
		
		if ( id == null ) {
			id = UUID.randomUUID().toString();
			action.setId( id );
		}
		
		ActionDTO created
			= this.actionService.create( action );
		
		return Response.ok().entity( created ).build();
		
	}

	@Context
	public void setProviders(Providers providers) {
		
		ProvidersRegistry.register( providers );
		
	}
	
}
