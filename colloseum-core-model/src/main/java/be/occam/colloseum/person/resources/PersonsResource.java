package be.occam.colloseum.person.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.person.repository.IPersonRepository;

import com.sun.jersey.api.core.HttpContext;

@Path("persons")
public class PersonsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Person[] array
		= {};
	
	@Resource
	IPersonRepository personRepository;
	
	@GET
	public Person[] get( @Context HttpContext httpContext, @Context Providers providers ) {
		
		List<Person> persons
			= new ArrayList<Person>();
		
		return this.personRepository.findAll( providers ).toArray( this.array );
		
	}
	
	@POST
	public Response post(
			Person person,
			@Context HttpContext httpContext,
			@Context Providers providers ){
		
		String id
			= UUID.randomUUID().toString();
		
		person.setId( id );
		
		person 
			= this.personRepository.persist( person , providers );
		
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
