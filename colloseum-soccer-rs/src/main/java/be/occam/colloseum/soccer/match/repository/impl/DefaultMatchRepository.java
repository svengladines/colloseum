package be.occam.colloseum.soccer.match.repository.impl;

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

import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.repository.IMatchRepository;

@Repository
public class DefaultMatchRepository implements IMatchRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Match[] array 
		= {};
	
	protected MessageBodyWriter<Match> messageBodyWriter;
	
	protected MessageBodyReader<Match> messageBodyReader;
	
	@Value("${colloseum.soccer.teams.directory}")
	protected String directory;
	
	@Override
	public Match findOne(String id) {
		return null;
	}
	
	@Override
	public Set<Match> findAll( Providers providers ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= providers.getMessageBodyReader( Match.class, Match.class, null, MediaType.APPLICATION_JSON_TYPE );
				
			}
			
			File directory 
				= new File( this.directory );
			
			Set<Match> set 
				= new HashSet<Match>();
			
			
			File[] files 
				= directory.listFiles( );
			
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Match team 
					= this.messageBodyReader.readFrom( 
						Match.class,
						Match.class,
						new Annotation[] {},
						MediaType.APPLICATION_JSON_TYPE,
						null,
						fis );
				
				set.add( team );
				
			}
			
			return set;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

	@Override
	public Match persist( Match match, Providers providers ) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= providers.getMessageBodyWriter( Match.class, Match.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			StringBuilder b
				= new StringBuilder( this.directory ).append( File.separator ).append( match.getId() );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( match, Match.class, match.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			return match;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

}
