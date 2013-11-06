package be.occam.colloseum.publisher.core.registry.impl;

import java.net.URL;

import javax.ws.rs.core.MediaType;

import be.occam.colloseum.publisher.IPublisher;
import be.occam.colloseum.publit.Publit;

import com.sun.jersey.api.core.HttpContext;

public class RemarkPublisher implements IPublisher {

	@Override
	public boolean accept( Publit publit, HttpContext httpContext ) {
		
		return publit.getUrl() == null;
		
	}

	@Override
	public Publit publish( Publit publit, HttpContext httpContext ) {
		
		return publit; //.setMediaType( MediaType.TEXT_HTML_TYPE );
		
	}

	@Override
	public Publit get(String id) {
		return null;
	}

}
