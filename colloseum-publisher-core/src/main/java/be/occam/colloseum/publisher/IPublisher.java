package be.occam.colloseum.publisher.core;

import java.net.URL;

import com.sun.jersey.api.core.HttpContext;

public interface IPublisher {
	
	public boolean accept( Publit publit, HttpContext httpContext );
	
	public Publit publish( Publit publit, HttpContext httpContext );

	public Publit get( String id );
	
}
