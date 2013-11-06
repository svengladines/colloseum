package be.occam.colloseum.publisher.youtube;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YoutubeUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger( YoutubeUtils.class );
	
	public static final String PARAM_VIDEO 
		= "v=";
	
	public static String videoParam( URI uri ) {
		
		try {
			
			String param
				= null;
			
			String query 
				= uri.getQuery();
			
			String[] pairs
				= query.split("&");
			
			for ( String pair : pairs ) {
				
				int l
					= pair.indexOf( PARAM_VIDEO );
				
				if ( l != -1 ) {
					
					param = pair.substring( l + PARAM_VIDEO.length() );
					logger.info( "video = [{}]", param );
					break;
					
				}
				
			}
			
			return param;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
	}

}
