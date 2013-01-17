package be.occam.colloseum.core.rs.util;

import java.io.File;
import java.io.FileInputStream;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.person.Person;

public class PersonUtil {
	
	protected static Logger logger
		= LoggerFactory.getLogger( PersonUtil.class );
	
	public static Person load( String directory, String file, Providers providers ) {
		
		try {
			
			Person person
				= new Person();
		
			MessageBodyReader<Person> reader
				= providers.getMessageBodyReader( Person.class, person.getClass() , null , MediaType.APPLICATION_XML_TYPE );
		
			File f
				= new File( new StringBuilder( directory ).append( File.separator ).append( file ).toString() );
			
			logger.debug( "load from file [{}]", f.getCanonicalPath() );
		
			FileInputStream fis
				= new FileInputStream( f );
		
			person = 
				reader.readFrom( Person.class, person.getClass(), person.getClass().getAnnotations(), MediaType.APPLICATION_XML_TYPE, null, fis );
			
			return person;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e );
		}
		
	}

}
