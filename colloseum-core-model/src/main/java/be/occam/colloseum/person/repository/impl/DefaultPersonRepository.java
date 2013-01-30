package be.occam.colloseum.person.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import be.occam.colloseum.application.ProvidersRegistry;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.person.repository.IPersonRepository;

@Repository
@Scope("singleton")
public class DefaultPersonRepository implements IPersonRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Person[] array 
		= {};
	
	protected MessageBodyWriter<Person> messageBodyWriter;
	
	protected MessageBodyReader<Person> messageBodyReader;
	
	@Value("${colloseum.persons.directory}")
	protected String directory;
	
	@Override
	public Person findOne(String id) {
		
		if ( this.messageBodyReader == null ) {
			
			this.messageBodyReader
				= ProvidersRegistry.registered().getMessageBodyReader( Person.class, Person.class, null, MediaType.APPLICATION_JSON_TYPE );
		}
		 
		try {
		
			File directory 
				= new File( this.directory );
		
			Person found
				= null;
			
			File[] directories 
				= directory.listFiles( );
			
			for ( File pdirectory : directories ) {
				
				if ( pdirectory.isDirectory() ) {
					
					this.logger.debug( "person directory [{}]", pdirectory.getCanonicalPath() );
					
					// TODO, fix matching
					if ( pdirectory.getName().endsWith( id ) ) {
				
						File[] ps
							= pdirectory.listFiles( );
						
						for ( File p : ps ) {
							
							if ( ! p.isDirectory() ) {
								
								if ( p.getName().endsWith(".json" ) ) {
									
									FileInputStream fis
										= new FileInputStream( p );
									
									Person person 
										= this.messageBodyReader.readFrom( 
											Person.class,
											Person.class,
											new Annotation[] {},
											MediaType.APPLICATION_JSON_TYPE,
											null,
											fis );
									
									found = person;
									
								}
							}
							
						}
					}
				}
					
			}
			
			return found;
		}
		catch( Exception e) {
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public Set<Person> findAll( Providers providers ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= providers.getMessageBodyReader( Person.class, Person.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			File directory 
				= new File( this.directory );
			
			Set<Person> set 
				= new HashSet<Person>();
			
			File[] directories 
				= directory.listFiles( );
			
			for ( File pdirectory : directories ) {
				
				if ( pdirectory.isDirectory() ) {
					
					this.logger.debug( "person directory [{}]", pdirectory.getCanonicalPath() );
				
					File[] ps
						= pdirectory.listFiles( );
					
						for ( File p : ps ) {
							
							if ( ! p.isDirectory() ) {
							
								if ( p.getName().endsWith(".json" ) ) {
									
									FileInputStream fis
										= new FileInputStream( p );
									
									Person club 
										= this.messageBodyReader.readFrom( 
											Person.class,
											Person.class,
											new Annotation[] {},
											MediaType.APPLICATION_JSON_TYPE,
											null,
											fis );
									
									set.add( club );
									
								}
							}
							
						}
				}
					
			}
			
			return set;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

	@Override
	public Person persist( Person person) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= ProvidersRegistry.registered().getMessageBodyWriter( Person.class, Person.class, null, MediaType.APPLICATION_JSON_TYPE );
				
			}
			
			StringBuilder b
				= new StringBuilder( this.directory )
					.append( File.separator )
					.append( person.getId() );
			
			File directory
				= new File( b.toString() );
			
			if ( ! directory.exists() ) {
				directory.mkdirs();
			}
			
			b.append( File.separator );
			b.append( person.getId() );
			b.append( ".json" );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( person, Person.class, person.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			logger.info( "person written to [{}]", b.toString() );
			return person;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

}
