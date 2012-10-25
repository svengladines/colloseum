package be.occam.colloseum.model.events;

import be.occam.colloseum.model.Event;
import be.occam.colloseum.model.god.events.GivesLife;
import be.occam.colloseum.model.match.events.MatchScheduledEvent;

public class Events {
	
	public static final GivesLife BORN
		= new GivesLife();
	
	public static final MatchScheduledEvent MATCH_SCHEDULED
		= new MatchScheduledEvent();
	
	public static Event<?,?>[] events
		= new Event[] {
		
		BORN,
		MATCH_SCHEDULED
		
	};
	
	@SuppressWarnings("unchecked")
	public static <X,Y> Event<X,Y> event( String type ) {
		
		try { 
		
			Class<Event> clz 
				= (Class<Event>) Class.forName( type );
		
			Event<X,Y> event
				= clz.newInstance();
			
			return event;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	public static String[] tags( String tags ) {
		return tags.trim().split(",");
	}
	
}
