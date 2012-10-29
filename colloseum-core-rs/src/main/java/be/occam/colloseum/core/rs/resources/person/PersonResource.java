package be.occam.colloseum.core.rs.resources.person;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.occam.colloseum.core.person.IDorm;
import be.occam.colloseum.core.rs.util.PersonUtil;
import be.occam.colloseum.model.Person;

import com.sun.jersey.api.core.HttpContext;

@Path("persons/{id}")
@Component
public class PersonResource {
	
	protected IDorm dorm;
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );

	protected final Person[] array
		= {};

	@Value( "${colloseum.person.persons.directory}" )
	String directory;
	
	@GET
	public Person get(
			@PathParam("id") String id,
			@Context HttpContext httpContext,
			@Context Providers providers ) {
		
		String file
			= new StringBuilder( id ).append( ".xml" ).toString();
		
		Person p
			= PersonUtil.load( this.directory, file, providers );
		
		return p;
		
	}

}
