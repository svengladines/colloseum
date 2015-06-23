package be.occam.colloseum.calendar.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.Builder;
import com.google.api.services.calendar.CalendarRequestInitializer;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

public class Client {
	
	protected final Logger logger
		= LoggerFactory.getLogger( Client.class );
	
	protected String key;
	
	final String calendarId;
	
	public Client( String calendarId, String key ) {
		this.calendarId = calendarId;
		this.key = key;
	}
	
	public List<be.occam.colloseum.event.Event> query( String token ) {
		
		List<be.occam.colloseum.event.Event> list
			= new LinkedList<be.occam.colloseum.event.Event>();
		
		try {
			
			Calendar.Events calEvents 
				= calendar().events();
				
			Events events = calEvents.list( calendarId ).setOauthToken( token ).execute();
			// Events events = calEvents.list( calendarId ).execute();
			
			 for (Event event : events.getItems()) {
				 
				 logger.debug( "[{}]", event.getStart() );
				 
				 /*
				 logger.info( "[{}], [{}], [{}]",
						 new Object[] { new SimpleDateFormat( ).format( new Date( event.getStart().getDateTime().getValue() ) ),
						 new SimpleDateFormat( ).format( new Date( event.getEnd().getDateTime().getValue() ) ),
						 event.getSummary() } );
				*/
				 
				 be.occam.colloseum.event.Event evt
				 	= this.mapTo( event );
				 
				 if ( evt != null ) {
				 
					 logger.info( "[{}], [{}], [{}]",
							 new Object[] { new SimpleDateFormat( ).format( evt.getStart() ),
							 new SimpleDateFormat( ).format( evt.getEnd() ),
							 evt.getSubject() } );
					 
					 list.add( evt );
					 
				 }
				 
			 }
			
		}
		catch( Exception e ) {
			
			logger.warn( "d'oh!", e );
			
		}
		
		return list;
	}
	
	public be.occam.colloseum.event.Event create( be.occam.colloseum.event.Event event ) {
		
		try {
			
			return mapTo( this.calendar().events().insert( calendarId, mapTo( event ) ).execute() );
			
		}
		catch( Exception e ) {
			
			throw new RuntimeException( e );
			
		}
		
	}
	
	protected Calendar calendar() {
		
		HttpTransport httpTransport 
			= new NetHttpTransport();
		
		JsonFactory jsonFactory
			= new JacksonFactory();
		
		Builder builder 
			= new Builder( httpTransport, jsonFactory, null );
		
		CalendarRequestInitializer calInit
			= new CalendarRequestInitializer( );
	    
	    Calendar service 
	    	=  builder
	    		.setApplicationName("kuleuven-football")
	    		.setCalendarRequestInitializer( calInit )
	    		.build();
	    
	   return service;
		
	}
	
	protected be.occam.colloseum.event.Event mapTo( Event from ) {
		
		be.occam.colloseum.event.Event to
			= null;
		
		// TODO, don't skip all-day events (date != null on all-day-events
		if ( from.getStart().getDate() == null ) {
		
			to = new be.occam.colloseum.event.Event();
			to.setStart( new Date( from.getStart().getDateTime().getValue() ) );
			to.setEnd( new Date( from.getEnd().getDateTime().getValue() ) );
			to.setSubject( from.getSummary() );
			to.setDescription( from.getDescription() );
			
			if ( from.getAttendees() != null ) {
			
				for ( EventAttendee ea : from.getAttendees() ) {
					
					if ( ea.getOrganizer() == null || ( ! ea.getOrganizer() )  ) {
						if ( "accepted".equalsIgnoreCase( ea.getResponseStatus() ) ) {
							logger.info( "[{}] will attend", ea.getEmail() );
							to.getAttendees().add( ea.getEmail() );
						}
					}
					
				}
			}
			
		}
		
		return to;
		
	}
	
	protected Event mapTo( be.occam.colloseum.event.Event from ) {
		
		Event to
			= new Event();
		
		to.setCreated( new DateTime( new Date() ) );
		to.setStart( new EventDateTime().setDateTime( new DateTime( from.getStart() ) ) );
		to.setEnd( new EventDateTime().setDateTime( new DateTime( from.getEnd() ) ) );
		to.setSummary( from.getSubject() );
		to.setDescription( from.getDescription() );
		
		return to;
		
	}

}
