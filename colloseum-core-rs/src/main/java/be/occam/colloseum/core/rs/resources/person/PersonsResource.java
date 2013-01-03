package be.occam.colloseum.core.rs.resources.person;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.occam.colloseum.core.rs.util.PersonUtil;
import be.occam.colloseum.model.Person;

import com.sun.jersey.api.core.HttpContext;

@Path("persons")
@Component
public class PersonsResource {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Person[] array
		= {};
	
	@Value( "${colloseum.person.persons.directory}" )
	String directory; 
	
	@GET
	public Person[] get( @Context HttpContext httpContext, @Context Providers providers ) {
		
		List<Person> persons
			= new ArrayList<Person>();
		
		List<String> files
			= this.files( this.directory );
		
		for ( String file : files ) {
			
			Person p
				= PersonUtil.load( this.directory, file, providers );
			
			if ( "template".equals( p.getId() ) ) {
				continue;
			}
			
			persons.add( p );
			
			logger.debug( "[{}]; in list ", p.getId() );
		}
		
		return persons.toArray( this.array );
		
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
			= this.persist( person, providers );
		
		return Response
			.created( this.uri( person, httpContext) )
			.entity( person ).build();
		
	}
	
	protected List<String> files( String directory ) {
		
		List<String> files
			= new ArrayList<String>();
		
		File d
			= new File( directory );
		
		if ( ! d.exists() ) {
			throw new RuntimeException( "there is no file at path [" + directory + "]" );
		}
		
		if ( ! d.isDirectory() ) {
			throw new RuntimeException( "the file with path [" + directory + "] is not a directory" );
		}
		
		File[] list
			= d.listFiles();
		
		for ( File file : list ) {
			
			if ( file.getName().endsWith( ".xml" ) ) {
				
				files.add( file.getName() );
				
			}
			
		}
		
		return files;
		
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
	
	protected Person persist( Person person, Providers providers ) {
		
		try {
		
			MessageBodyWriter<Person> writer
				= providers.getMessageBodyWriter( Person.class, person.getClass() , null , MediaType.APPLICATION_XML_TYPE );
		
			File f
				= new File( new StringBuilder( this.directory ).append( File.separator ).append( person.getId() ).append( ".xml" ).toString() );
		
			FileOutputStream fos
				= new FileOutputStream( f );
		
			writer.writeTo( person, Person.class, person.getClass(), person.getClass().getAnnotations(), MediaType.APPLICATION_XML_TYPE, null, fos );
			
			logger.info( "[{}]; written to file [{}]", person.getId(), f.getAbsolutePath() );
			
			return person;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}

}
