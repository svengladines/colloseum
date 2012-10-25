package be.occam.colloseum.core.event.events.store;

import java.util.List;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.model.Event;
import be.occam.colloseum.model.filter.EventFilter;

public interface IEventStore {
	
	public List<Event<?,?>> filtered( Providers providers, EventFilter... eventFilters );

}
