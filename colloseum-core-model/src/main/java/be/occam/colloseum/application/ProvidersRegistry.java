package be.occam.colloseum.application;

import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProvidersRegistry {
	
	private static final Logger logger = LoggerFactory.getLogger( ProvidersRegistry.class );
	
	private static Providers registered;
	
	public static void register( Providers providers ) {
		
		logger.info( "providers registered: [{}]", providers );
		
		registered = providers;
		
	}
	
	public static Providers registered( ) {
		
		return registered;
		
	}

}
