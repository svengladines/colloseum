package be.occam.colloseum.soccer.club.repository.impl;

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

import be.occam.colloseum.soccer.club.Club;
import be.occam.colloseum.soccer.club.repository.IClubRepository;

@Repository
public class DefaultClubRepository implements IClubRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Club[] array 
		= {};
	
	protected MessageBodyWriter<Club> messageBodyWriter;
	
	protected MessageBodyReader<Club> messageBodyReader;
	
	@Value("${colloseum.soccer.clubs.directory}")
	protected String directory;
	
	@Override
	public Club findOne(String id) {
		return null;
	}
	
	@Override
	public Set<Club> findAll( Providers providers ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= providers.getMessageBodyReader( Club.class, Club.class, null, MediaType.APPLICATION_JSON_TYPE );
				
			}
			
			File directory 
				= new File( this.directory );
			
			Set<Club> set 
				= new HashSet<Club>();
			
			
			File[] files 
				= directory.listFiles( );
			
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Club club 
					= this.messageBodyReader.readFrom( 
						Club.class,
						Club.class,
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
	public Club persist( Club club, Providers providers ) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= providers.getMessageBodyWriter( Club.class, Club.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			StringBuilder b
				= new StringBuilder( this.directory ).append( File.separator ).append( club.getId() );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( club, Club.class, club.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			return club;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

}
