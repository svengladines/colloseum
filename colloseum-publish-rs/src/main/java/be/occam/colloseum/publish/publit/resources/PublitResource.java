package be.occam.colloseum.publish.publit.resources;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.application.ProvidersRegistry;
import be.occam.colloseum.publit.Publit;
import be.occam.colloseum.publit.repository.IPublitRepository;

import com.sun.jersey.api.core.HttpContext;

@Path("publits/{id}")
@Component
@Scope("singleton")
public class PublitResource {
	
	private final Logger logger
		= LoggerFactory.getLogger( PublitResource.class );
	
	@Resource
	protected IPublitRepository publitRepository;
	
	@OPTIONS
	public Response options() {
		
		return Response.ok()
			.header("Access-Control-Allow-Origin","*")
			.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
			.header("Access-Control-Allow-Headers", "Accept,Content-Type")
			.build();
		
	}
	
	@GET
	public Publit get( @PathParam( "id" ) String id, @Context HttpContext httpContext ) {
		
		this.logger.info( "get, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		Publit publit
			= this.publitRepository.findOne( id );
		
		return publit;
		
	}
	
	@PUT
	public Response put(@PathParam( "id" ) String id, Publit publit, @Context HttpContext httpContext ) {
		
		this.logger.info( "put, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		/*
		Publit stored
			= this.get( id, httpContext );
		
		if ( stored == null ) {
			throw new WebApplicationException( Status.NOT_FOUND );
		}
		*/
		
		// publit.setId( id );
		// publit.setUrl( URIUtil.url( httpContext, publit ).toString() );
				
		this.publitRepository.save( publit );
		
		return Response.ok()
			.header("Access-Control-Allow-Origin","*")
			.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
			.header("Access-Control-Allow-Headers", "Accept,Content-Type")
		.entity( publit ).build();
		
	}
	
	@DELETE
	public Publit delete(@PathParam( "id" ) String id, @Context HttpContext httpContext ) {
		
		this.logger.info( "delete, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		Publit publit
			= this.get( id , httpContext );
		
		// this.publitRepository.delete( publit );
		
		return publit;
		
	}
	
	@Context
	public void setProviders(Providers providers) {
		
		ProvidersRegistry.register( providers );
		
	}
	
}
