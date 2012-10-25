package be.occam.colloseum.model;

public interface IEventConsumer {
	
	public boolean accepts( Event<?,?> event );
	
	public IEventConsumer consume( Event<?,?> event );

}
