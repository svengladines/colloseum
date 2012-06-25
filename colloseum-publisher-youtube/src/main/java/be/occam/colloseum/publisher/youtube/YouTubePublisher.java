package be.occam.colloseum.publisher.youtube;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import javax.annotation.Resource;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.publisher.youtube.YouTubeClient.Video;
import be.occam.colloseum.publisher.core.IPublisher;

import com.google.api.client.testing.json.AbstractJsonGeneratorTest.Feed;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.atom.rome.impl.provider.entity.AtomEntryProvider;
import com.sun.jersey.atom.rome.impl.provider.entity.AtomFeedProvider;


@Path( "publishers/youtube" )
public class YouTubePublisher implements IPublisher {

	private final Logger logger
		= LoggerFactory.getLogger( this.getClass() );

	protected final String PARAM_VIDEO 
		= "v=";

	@Resource
	IPublitStorage publitStorage;
	
	@Override
	public boolean accept( Publit publit, HttpContext httpContext ) {
	
		URI uri 
			= URI.create( publit.getData().toString() );
		
		String host
			= uri.getHost();
		
		if ( host == null ) {
			return false;
		}
		
		boolean accept
			= "www.youtube.com".equals( uri.getHost() );
		
		String id
			= this.videoParam( uri );
	
		return accept;
	
	}

	@Override
	public Publit get(String id) {
		
		return this.get( id, true, true );
		
	}

	public Publit get(String id, boolean loadContent, boolean loadPreview) {
		
		try {
			Publit publit
				= this.publitStorage.load( id );
		
			String uri
				= publit.getData();
		
			String html
			//	= this.html( uri );
			= "";
			return publit.setData( html ).setCanRender( true );
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}
	
	/*

	@Override
	public Publit publish( Publit publit, HttpContext context ) {
		
		try {
			
			byte[] data
				= publit.getData();
			
			String ref
				= new String( data, "utf-8" ) ;
			
			URI uri 
				= new URI( ref );
			
			String videoParam
				= this.videoParam( uri );
			
			String ytURI
				= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/").append( videoParam ).toString();
			
			Video video
				= this.videoYtClient( videoParam );
			
			/*
			String thumbUrl
				= video.group.thumbnail.get( 0 ).url;
			Client client
				= Client.create( new DefaultClientConfig() );
			
			WebResource resource
				= client.resource( thumbUrl );
			
			byte[] thumb
				= resource.accept( MediaType.APPLICATION_OCTET_STREAM_TYPE ).get( byte[].class );
			
			return new Publit().setPreview( thumb );
			
		}
		catch( Exception e ) {
			logger.warn( "publication failed", e );
			return null;
		}
		
	}
	
	*/

	@Override
	public Publit publish( Publit publit, HttpContext context ) {
		
		try {
			
			String ref
				= publit.getData();
			
			URI uri 
				= new URI( ref );
			
			String id
				= this.videoParam( uri );
			
			String preview
				= new StringBuilder( "http://img.youtube.com/vi/" ).append( id ).append( "/1.jpg" ).toString();
			
			publit.setPreview( preview );
			
			String dt
				= this.data( id );
			
			publit.setData( dt );
			
			String play
				= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/").append( id ).toString();
			
			publit.setPlay( play );
			
			String jsonp
				= new StringBuilder( "http://gdata.youtube.com/feeds/api/videos/")
					.append( id )
					.append( "?v=2&alt=json-in-script&format=5&callback=assemble").toString();
			
			publit.setDataScriptUrl( jsonp );
			
			publit.setAssembleScriptUrl( "http://localhost:8091/colloseum-publisher-youtube/assemble.js" );
		
			return publit;
			
		}
		catch( Exception e ) {
			logger.warn( "publication failed", e );
			return null;
		}
		
	}
	
	protected String videoParam( URI uri ) {
	
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

	protected Video videoJaxRs( String ytURI ) {
		
		ClientConfig config 
			= new DefaultClientConfig();
	
		config.getClasses().add( AtomFeedProvider.class );
		config.getClasses().add( AtomEntryProvider.class );
	
		Client client 
			= Client.create(config);
	
		WebResource resource
			= client.resource( ytURI );
	
		ClientResponse response
			= resource
			.accept( MediaType.valueOf( "application/atom+xml" ) )
			.get( ClientResponse.class );
	
		logger.info( "response: [{}]", response );
	
		Feed feed
			= response.getEntity( Feed.class );
	
		logger.info( "feed: [{}]", feed.toString() );
	
		return null;
		
	}

	protected Video videoYtClient( String id ) {
		
		Video video
			= new YouTubeClient().video( id );
		
		return video;
		
	}

	protected String html( String id ) {
		
		String uri
			= new StringBuilder( "http://www.youtube.com/v/" ).append( id ).toString();
		
		StringBuilder b = new StringBuilder()
			.append( "<object width=\"425\" height=\"355\">")
			.append(" <param name=\"movie\"")
		    .append("value=\"").append( uri ).append( "?version=3&autohide=1&showinfo=0\"></param>")
		    .append("<param name=\"allowScriptAccess\" value=\"always\"></param>")
		    .append("<embed src=\"").append( uri ).append( "?version=3&autohide=1&showinfo=0\"")
		    .append("type=\"application/x-shockwave-flash\"")
		    .append("allowscriptaccess=\"always\"")
		    .append("width=\"425\" height=\"355\"></embed>")
		    .append("</object>");
		
		// TODO, template this!
		
		return b.toString();
		
	}
	
	protected URI uri( String id, HttpContext httpContext ) {
		
		try {
			
			StringBuilder b
				= new StringBuilder(  )
				.append( httpContext.getUriInfo().getBaseUri().toString() )
				.append( "publishers/youtube?id=" ).append( id );
			
			return new URI( b.toString() );
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
		
	}
	
	protected String data( String id ) {
		
		try {
		 
			InputStreamReader is
				= new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( "script.txt" ) );
			
			BufferedReader br
				= new BufferedReader( is );
			
			StringBuilder d
				= new StringBuilder();
			
			String line
				= br.readLine();
			
			while ( line != null ) {
				
				d.append ( line ).append( "\n" );
				
				line = br.readLine();
				
			}
			
			br.close();
			d.append( "\n" );
			
			is
				= new InputStreamReader( Thread.currentThread().getContextClassLoader().getResourceAsStream( "jsonp.txt" ) );
		
			br
				= new BufferedReader( is );
		
			line
				= br.readLine();
		
			while ( line != null ) {
			
				d.append ( line ).append( "\n" );
			
			line = br.readLine();
			
			}
		
			br.close();
			
			return d.toString();
			
		}
		catch( Exception e ) {
			
			logger.warn( "could not build data string", e );
			return null;
			
		}
		
	}

}
