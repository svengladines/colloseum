package be.occam.colloseum.publisher.rs.resources;

import javax.annotation.Resource;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.publisher.core.Publit;
import be.occam.colloseum.publisher.core.storage.IPublitStorage;

import com.sun.jersey.api.core.HttpContext;

@Path("publits/{id}")
@Component
@Scope("singleton")
public class PublitResource {
	
	private final Logger logger
		= LoggerFactory.getLogger( PublitResource.class );
	
	@Resource
	private IPublitStorage publitStorage;
	
	@GET
	public Publit get(@PathParam( "id" ) String id, @Context HttpContext httpContext ) {
		
		this.logger.info( "get, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		Publit publit
			= this.publitStorage.load( id );
		
		return publit;
		
	}
	
	@PUT
	public Publit put(@PathParam( "id" ) String id, @Context HttpContext httpContext, Publit publit ) {
		
		this.logger.info( "put, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		Publit stored
			= this.get( id, httpContext );
		
		if ( stored == null ) {
			throw new WebApplicationException( Status.NOT_FOUND );
		}
		
		publit.setId( id );
				
		this.publitStorage.persist( publit );
		
		return publit;
		
	}
	
	@DELETE
	public Publit delete(@PathParam( "id" ) String id, @Context HttpContext httpContext ) {
		
		this.logger.info( "delete, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		Publit publit
			= this.get( id , httpContext );
		
		this.publitStorage.delete( publit );
		
		return publit;
		
	}
	
}
