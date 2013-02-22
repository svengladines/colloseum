package be.occam.colloseum.publisher;

import be.occam.colloseum.publit.Publit;

import com.sun.jersey.api.core.HttpContext;

public interface IPublisher {
	
	public boolean accept( Publit publit, HttpContext httpContext );
	
	public Publit publish( Publit publit, HttpContext httpContext );

	public Publit get( String id );
	
}
