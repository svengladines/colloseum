package be.occam.colloseum.model.filter;

import be.occam.colloseum.model.Event;

public class TemplateFilter extends EventFilter {

	@Override
	public boolean pass( Event<?,?> event ) {
		
		return event.getEpoch() == null;
		
	}

}
