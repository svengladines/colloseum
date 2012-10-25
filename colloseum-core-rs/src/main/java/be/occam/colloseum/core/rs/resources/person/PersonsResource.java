package be.occam.colloseum.core.rs.resources.person;

import java.net.URI;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import be.occam.colloseum.model.Person;

import com.sun.jersey.api.core.HttpContext;

@Path("persons")
public class PersonsResource {
	
	protected final Person[] array
		= {};
	
	@GET
	public Person[] get( @Context HttpContext httpContext ) {
		
		return this.array;
		
	}
	
	@POST
	public Response post( Person person, @Context HttpContext httpContext ){
		
		String id
			= UUID.randomUUID().toString();
		
		person.setId( id );
		
		return Response
			.created( this.uri( person, httpContext) )
			.entity( person ).build();
		
	}
	
	protected URI uri( Person person, HttpContext httpContext ) {
		
		URI uri 
			= UriBuilder.fromUri( httpContext.getUriInfo().getRequestUri() ).replaceQuery( null ).build( );
	
		String url
			= new StringBuilder( uri.toString() )
				.append( "/" )
				.append( person.getId() ).toString();
		
		try {
			return new URI( url );
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}

}
