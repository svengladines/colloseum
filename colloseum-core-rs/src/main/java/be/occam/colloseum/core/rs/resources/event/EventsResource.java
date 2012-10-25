package be.occam.colloseum.core.rs.resources.event;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import be.occam.colloseum.core.event.events.store.IEventStore;
import be.occam.colloseum.core.rs.Params;
import be.occam.colloseum.model.Event;
import be.occam.colloseum.model.God;
import be.occam.colloseum.model.Objects;
import be.occam.colloseum.model.User;
import be.occam.colloseum.model.events.Events;
import be.occam.colloseum.model.filter.EventFilter;
import be.occam.colloseum.model.filter.TemplateFilter;
import be.occam.colloseum.util.Timing;

import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("events")
@Component
@Scope("singleton")
public class EventsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( EventsResource.class );
	
	protected final Event<?,?>[] array 
		= new Event<?,?>[] {};
	
	@Resource
	protected IEventStore eventStore;
	
	@GET
	public Event<?,?>[] get( @Context HttpContext httpContext, @Context Providers providers ) {
		
		// return list of events that can be initiated by user
		
		this.logger.info( "get, uri =[{}]", httpContext.getUriInfo().getRequestUri() );
		
		User user
			= this.user( httpContext );
		
		List<EventFilter> filters
			= this.filters( httpContext );
		
		List<Event<?,?>> events
			= this.eventStore.filtered( providers, filters.toArray( new EventFilter[] {} ) );
				
		return events.toArray( this.array );
		
	}
	
	@POST
	@Consumes( { MediaType.MULTIPART_FORM_DATA } )
	public Response post(
			FormDataMultiPart form,
			@Context HttpContext httpContext,
			@Context Providers providers ) {
		
		try {
			
			FormDataBodyPart subjectPart  
				= form.getField( "subject" );
			
			FormDataBodyPart objectPart  
				= form.getField( "object" );
			
			FormDataBodyPart tagsPart  
				= form.getField( "tags" );
			
			FormDataBodyPart typePart
				= form.getField( "type" );
			
			FormDataBodyPart datePart
				= form.getField( "date" );
			
			FormDataBodyPart timePart
				= form.getField( "time" );
		
			
			if ( ( typePart == null ) || ( subjectPart == null ) || ( objectPart == null ) || ( datePart == null ) || ( timePart == null ) ) {
				return Response.status( Status.BAD_REQUEST ).build();
			}
			
			String subjectID
				= subjectPart.getValue();
		
			String objectID
				= objectPart.getValue();
		
			String tags
				= tagsPart.getValue();
			
			Object object
				= new Objects().object( objectID );
			
			Object subject
				= new Objects().object( subjectID );
			
			Event<Object,Object> event
				= Events.event( typePart.getValue() )
					.setEpoch( Timing.moment( datePart.getValue(), timePart.getValue() )  )
					.setObject(object)
					.setSubject( subject );
			
			MessageBodyWriter<Event> writer
				= providers.getMessageBodyWriter( Event.class, event.getClass() , null , MediaType.APPLICATION_XML_TYPE );
			
			String uuid
				= UUID.randomUUID().toString();
			
			File f
				= new File( new StringBuilder( "target/" ).append( uuid ).append( ".xml" ).toString() );
			
			FileOutputStream fos
				= new FileOutputStream( f );
			
			writer.writeTo( event, Event.class, event.getClass(), event.getClass().getAnnotations(), MediaType.APPLICATION_XML_TYPE, null, fos );
			
			return Response.status( Status.CREATED ).build();
			
			
		}
		catch( Exception e ) {
			logger.warn( "post failed", e );
			throw new WebApplicationException( e );
		}
			
	}
	
	protected User user( HttpContext httpContext ) {
		
		return new User().role( new God() ); 
		
	}
	
	protected List<EventFilter> filters( HttpContext context ) {
		
		List<EventFilter> filters
			= new ArrayList<EventFilter>();
		
		MultivaluedMap<String, String> params
			= context.getRequest().getQueryParameters();
		
		String template
			= params.getFirst( Params.PARAM_TEMPLATE );
		
		if ( template != null ) {
			
			filters.add( new TemplateFilter() );
			
		}
		else {
			
			
			
		}
		
		return filters;
		
	}
	
}
