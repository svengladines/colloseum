package be.occam.colloseum.publisher.rs.resources;

import java.net.URI;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.publisher.core.IPublisher;
import be.occam.colloseum.publisher.core.Publit;
import be.occam.colloseum.publisher.core.storage.IPublitStorage;
import be.occam.colloseum.publisher.core.storage.impl.FileStorage;
import be.occam.colloseum.publisher.registry.PublisherRegistry;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("publits")
@Component
@Scope("singleton")
public class PublitsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( PublitsResource.class );
	
	protected final Publit[] array 
		= new Publit[] {};
	
	@Resource
	protected PublisherRegistry publisherRegistry;
	
	protected IPublitStorage publitStorage
		= new FileStorage();
	
	@GET
	public Response get(@Context HttpContext httpContext ) {
		
		this.logger.info( "get, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		return Response.ok().build();
		
	}
	
	@POST
	@Consumes( { MediaType.MULTIPART_FORM_DATA } )
	public Response post( FormDataMultiPart form, @Context HttpContext httpContext ) {
		
		try {
			
			FormDataBodyPart filePart  
				= form.getField( "file" );
			
			Publit publit
				= new Publit();
			
			byte[] content 
				= null;
			
			if ( filePart != null ) {
		
				content
					= filePart.getEntityAs( byte[].class );
			
				logger.debug( "content bytes: #[{}]", content.length );
				
				publit.setContent( content );
				publit.setUrl( new StringBuilder("file://").append( filePart.getFormDataContentDisposition().getFileName() ).toString() );
				
			}
			else {
				
				FormDataBodyPart dataPart
					= form.getField( "data" );
			
				String data
					= dataPart != null ? dataPart.getValue() : null;
					
				publit.setData( data );
				
			}
			
			FormDataBodyPart idPart
				= form.getField( "id" );
			
			String id
				= idPart != null ? idPart.getValue() : null;
			
			FormDataBodyPart titlePart
				= form.getField( "title" );
			
			String title
				= titlePart != null ? titlePart.getValue() : null;
			
			FormDataBodyPart descriptionPart
				= form.getField( "description" );
		
			String description
				= descriptionPart != null ? descriptionPart.getValue() : null;
			
			publit
					.setId( id )
					.setTitle( title )
					.setDescription( description );
			
			Response response
				= null;
			
			IPublisher[] publishers 
				= this.publisherRegistry.list();
			
			for ( IPublisher publisher : publishers ) {
				
				if ( publisher.accept( publit , httpContext) ) {
					
					publit.setId( UUID.randomUUID().toString() );
					
					StringBuilder b
						= new StringBuilder( httpContext.getUriInfo().getRequestUri().toString() ).append( "/" ).append( publit.getId() );
					
					Publit published 
						= publisher.publish( publit, httpContext );
					
					if ( published != null ) {
					
						this.publitStorage.persist( published );
						
						response
							= Response
								.created( URI.create( b.toString() ) )
								.entity( published )
								.build();
						
					}
					
					break;
				
				}
				
			}
			
			if ( response == null ) {
				
				response
					= Response.status( Status.NOT_ACCEPTABLE ).build();
				
			}
			
			return response;
			
			
		}
		catch( Exception e ) {
			logger.warn( "post failed", e );
			throw new WebApplicationException( e );
		}
			
	}
	
}
