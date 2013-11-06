package be.occam.colloseum.publisher.youtube.resources;

import static be.occam.colloseum.publisher.youtube.YoutubeUtils.videoParam;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.publit.Publit;

@Path("publit")
public class PublitResource {

	protected final Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@OPTIONS
	public Response options() {
		
		return Response.ok()
		.header("Access-Control-Allow-Origin","*")
		.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
		.header("Access-Control-Allow-Headers", "Accept,Content-Type")
		.build();
		
	}
	
	@GET
	public Response get( @QueryParam("uri") URI uri ) {
	
		try {
			
			String id
				= videoParam( uri );
			
			Publit publit = new Publit();
			
			// String preview
			//	= new StringBuilder( "http://img.youtube.com/vi/" ).append( id ).append( "/1.jpg" ).toString();
			
			// publit.setPreview( preview );
			
			// String play
			//	= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/").append( id ).toString();
			
			// publit.setPlay( play );
			
			String jsonp
				= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/")
					.append( id )
					.append( "?v=2&alt=json-in-script&format=5&callback=build").toString();
			
			publit.setDataScriptUrl( jsonp );
			
			publit.setAssembleScriptUrl( "http://localhost:8091/colloseum-publisher-youtube/assemble.js" );
		
			return Response.ok().header("Access-Control-Allow-Origin","*").header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS").entity( publit ).build();
			
		}
		catch( Exception e ) {
			logger.warn( "publication failed", e );
			return null;
		}
	}

}
