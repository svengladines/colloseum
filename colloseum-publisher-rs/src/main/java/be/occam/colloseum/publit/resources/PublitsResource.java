package be.occam.colloseum.publit.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.application.ProvidersRegistry;
import be.occam.colloseum.publisher.core.util.URIUtil;
import be.occam.colloseum.publit.Publit;
import be.occam.colloseum.publit.repository.IPublitRepository;

import com.sun.jersey.api.core.HttpContext;

@Path("publits")
@Component
@Scope("singleton")
public class PublitsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( PublitsResource.class );
	
	protected final Publit[] array 
		= new Publit[] {};
	
	protected Providers providers;
	
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
	public Response get(@Context HttpContext httpContext, @QueryParam("track") String track, @QueryParam("pick") Boolean pick ) {
		
		this.logger.info( "get, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		List<Publit> publits = new ArrayList<Publit>();
		
		if ( track == null ) {
			publits.addAll( this.publitRepository.findAll() );
		}
		else {
			publits.addAll( this.publitRepository.findByTrack( track ) );
		}
		
		if ( pick != null && pick.booleanValue() && (! publits.isEmpty() ) ) {
			
			Publit picked = publits.get( new Random().nextInt( publits.size() ) );
			publits.clear();
			publits.add( picked );
			
		}
		
		return Response.ok()
		.header("Access-Control-Allow-Origin","*").header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
		.entity( publits.toArray( this.array ) ).build();
		
	}
	
	@POST
	public Response post( Publit publit, @Context HttpContext httpContext, @Context Providers providers ) {
		
		try {
			
			this.logger.info( "post" );
			
			setProviders( providers );
			
			String id
				= UUID.randomUUID().toString();
			
			publit.setId( id  ); 
			publit.setUrl( URIUtil.url( httpContext, publit ).toString() );
			publit.setTimestamp( System.currentTimeMillis() );
			
			publit = this.publitRepository.persist( publit );
				
			Response response
				= Response.created( new URI( publit.getUrl() ) )
				.header("Access-Control-Allow-Origin","*").header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
				.entity( publit ).build();
			
			return response;
			
			
		}
		catch( Exception e ) {
			logger.warn( "post failed", e );
			throw new WebApplicationException( e );
		}
			
	}
	
	@Context
	public void setProviders(Providers providers) {
		
		this.logger.info( "setproviders" );
		
		ProvidersRegistry.register( providers );
		
	}
	
}
