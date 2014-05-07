package be.occam.colloseum.publisher.core.registry;

import java.util.HashSet;
import java.util.Set;

import be.occam.colloseum.publisher.IPublisher;

public class PublisherRegistry {
	
	protected final IPublisher[] array
		= new IPublisher[] {};
	
	private final Set<IPublisher> publishers
		= new HashSet<IPublisher>();
	
	protected static PublisherRegistry instance
		= null;
	
	public static PublisherRegistry getInstance() {
		
		if ( instance == null ) {
			
			instance = new PublisherRegistry();
			
		}
		
		return instance;
		
	}
	
	public static IPublisher[] list() {
		
		PublisherRegistry instance
			= getInstance();
		
		return instance.publishers.toArray( instance.array );
		
	}
	
	public static PublisherRegistry register( IPublisher publisher ) {
		
		PublisherRegistry instance
			= getInstance();
		
		instance.publishers.add( publisher );
		
		return instance;
		
	}

}
