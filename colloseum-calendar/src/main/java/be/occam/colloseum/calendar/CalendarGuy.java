package be.occam.colloseum.calendar;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import be.occam.colloseum.calendar.client.Client;
import be.occam.colloseum.event.Event;
import be.occam.colloseum.hat.Hat;
import be.occam.utils.oauth.OAuthor;
import be.occam.utils.spring.web.RedirectException;

@Component
public class CalendarGuy extends Hat {
	
	@Resource
	Client client;
	
	@Resource
	OAuthor oAuthor;

	public CalendarGuy createEvent( Event event ) {
		
		try {
			
			this.client.create( event );
			
		}
		catch( Exception e ) {
			
			logger.warn( "could not create event in calendar", e );
			
		}
		
		return this;
		
	}
	
	public List<Event> listEvents( Date start ) {
		
		if ( ! this.oAuthor.isAuthorized() ) {
			logger.info( "no ouath token, authorize ... ");
			// TODO, throw authexception
			throw new RedirectException( "https://kuleuven-football.appspot.com/authorized.html?consent=true" );
		}
		
		try {
			
			logger.info( "list with ouath token [{}]", this.oAuthor.getAccessToken( ) );
			
			List<Event> list = this.client.query( this.oAuthor.getAccessToken( ) );
			
			List<Event> selected = new LinkedList<Event>();
			
			for ( Event event : list ) {
				
				if ( start.before( event.getStart() ) ) {
					
					selected.add( event );
					
				}
				
			}
			
			Collections.sort( selected, new Comparator<Event>( ) {

				@Override
				public int compare(Event o1, Event o2) {
					
					return o1.getStart().before( o2.getStart() ) ? -1 : 1;
					
				}
				
			});
			
			return selected;
			
		}
		catch( Exception e ) {
			
			logger.warn( "could not list events", e );
			throw new RuntimeException( e ); 
			
		}
		
	}

}
