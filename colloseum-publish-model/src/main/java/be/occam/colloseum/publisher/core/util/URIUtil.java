package be.occam.colloseum.publisher.core.util;

import be.occam.colloseum.publit.Publit;

import com.sun.jersey.api.core.HttpContext;

public class URIUtil {
	
	public static StringBuilder url( HttpContext context, Publit publit ) {
		
		StringBuilder b
			= new StringBuilder( context.getUriInfo().getBaseUri().toString() ).append( "publits/" ).append( publit.getId() );
		
		return b;
		
	}
	
	public static String previewUrl( HttpContext context, Publit shareable ) {
		
		StringBuilder b
			= url( context, shareable ).append( "/preview" );
		
		return b.toString();
		
	}
	
	public static String dataUrl( HttpContext context, Publit shareable ) {
		
		StringBuilder b
			= url( context, shareable ).append( "/data" );
		
		return b.toString();
		
	}
	
	public static String editUrl( HttpContext context, Publit shareable ) {
		
		StringBuilder b
			= url( context, shareable ).append( "/edit" );
		
		return b.toString();
		
	}


}
