package be.occam.colloseum.action.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.repository.IActionRepository;

@Repository
public class DefaultActionRepository implements IActionRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Action<?>[] array 
		= {};
	
	protected MessageBodyWriter<Action> messageBodyWriter;
	
	protected MessageBodyReader<Action> messageBodyReader;
	
	@Value("${colloseum.soccer.clubs.directory}")
	protected String directory;
	
	@Override
	public Action findOne(String id) {
		return null;
	}
	
	@Override
	public Set<Action<?>> findAll( Providers providers ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= providers.getMessageBodyReader( Action.class, Action.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			File directory 
				= new File( this.directory );
			
			Set<Action<?>> set 
				= new HashSet<Action<?>>();
			
			
			File[] files 
				= directory.listFiles( );
			
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Action club 
					= this.messageBodyReader.readFrom( 
						Action.class,
						Action.class,
						new Annotation[] {},
						MediaType.APPLICATION_JSON_TYPE,
						null,
						fis );
				
				set.add( club );
				
			}
			
			return set;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

	@Override
	public Action<?> persist( Action<?> action, Providers providers ) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= providers.getMessageBodyWriter( Action.class, Action.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			StringBuilder b
				= new StringBuilder( this.directory ).append( File.separator ).append( action.getId() );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( action, Action.class, action.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			return action;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

}
