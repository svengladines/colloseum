package be.occam.colloseum.publit.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import be.occam.colloseum.application.ProvidersRegistry;
import be.occam.colloseum.publit.Publit;
import be.occam.colloseum.publit.repository.IPublitRepository;

@Repository
@Scope("singleton")
public class DefaultPublitRepository implements IPublitRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Publit[] array 
		= {};

	protected MessageBodyWriter<Publit> messageBodyWriter;
	
	protected MessageBodyReader<Publit> messageBodyReader;
	
	@Value("${colloseum.publit.publits.directory}")
	protected String directory;
	
	@Override
	public Publit findOne(String id) {
		return null;
	}
	
	@Override
	public List<Publit> findAll( ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= ProvidersRegistry.registered().getMessageBodyReader( Publit.class, Publit.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			File directory 
				= new File( this.directory );
			
			List<Publit> list 
				= new LinkedList<Publit>();
			
			
			File[] files 
				= directory.listFiles( );
						
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Publit publit 
					= this.messageBodyReader.readFrom( 
							Publit.class,
							Publit.class,
						new Annotation[] {},
						MediaType.APPLICATION_JSON_TYPE,
						null,
						fis );
				
				logger.info( "publit marshalled", publit );
				
				if ( publit.getCanRender() ) {
					list.add( publit );
				}
				
			}
			
			Collections.sort( list , this.comparator() );
			
			return list;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

	@Override
	public Publit persist( Publit action ) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= ProvidersRegistry.registered().getMessageBodyWriter( Publit.class, Publit.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			StringBuilder b
				= new StringBuilder( this.directory )
					.append( File.separator )
					.append( action.getId() )
					.append( ".json" );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( action, Publit.class, action.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			return action;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

	@Override
	public List<Publit> findByTrack(String track) {
		
		List<Publit> all = this.findAll();
		
		List<Publit> byTrack = new ArrayList<Publit>( all.size() );
		
		for ( Publit publit : all ) {
			
			if ( track.equals( publit.getTrack() ) ) {
				byTrack.add( publit );
			}
			
		}
		
		return byTrack;
		
	}
	
	protected Comparator<Publit> comparator() {
		
		return new Comparator<Publit>() {

			@Override
			public int compare(Publit o1, Publit o2) {
			
				return (int) ( o2.getTimestamp() - o1.getTimestamp() );  
				
			}
			
			
			
		};
		
	}
	
		
}
