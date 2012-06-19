package be.occam.colloseum.model;

import java.util.List;

public class Object {

	protected List<Event<?>> autoSpawnEvents;
	
	protected Object wakeUp() {
		
		return this;
		
	}
	
	protected Object sleep() {
		
		return this;
		
	}
	
}
