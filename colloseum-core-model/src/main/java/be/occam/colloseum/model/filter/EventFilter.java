package be.occam.colloseum.model.filter;

import be.occam.colloseum.model.Event;

public abstract class EventFilter {
	
	public abstract boolean pass( Event<?,?> event );

}
