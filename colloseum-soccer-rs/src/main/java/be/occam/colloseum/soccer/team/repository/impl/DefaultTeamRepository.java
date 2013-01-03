package be.occam.colloseum.soccer.team.repository.impl;

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

import be.occam.colloseum.soccer.team.Team;
import be.occam.colloseum.soccer.team.repository.ITeamRepository;

@Repository
public class DefaultTeamRepository implements ITeamRepository {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	protected final Team[] array 
		= {};
	
	protected MessageBodyWriter<Team> messageBodyWriter;
	
	protected MessageBodyReader<Team> messageBodyReader;
	
	@Value("${colloseum.soccer.teams.directory}")
	protected String directory;
	
	@Override
	public Team findOne(String id) {
		return null;
	}
	
	@Override
	public Set<Team> findAll( Providers providers ) {
		
		try {
			
			if ( this.messageBodyReader == null ) {
		
				this.messageBodyReader
					= providers.getMessageBodyReader( Team.class, Team.class, null, MediaType.APPLICATION_JSON_TYPE );
				
			}
			
			File directory 
				= new File( this.directory );
			
			Set<Team> set 
				= new HashSet<Team>();
			
			
			File[] files 
				= directory.listFiles( );
			
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Team team 
					= this.messageBodyReader.readFrom( 
						Team.class,
						Team.class,
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
	public Team persist( Team team, Providers providers ) {
		
		try {
			
			if ( this.messageBodyWriter == null ) {
		
				this.messageBodyWriter
					= providers.getMessageBodyWriter( Team.class, Team.class, null, MediaType.APPLICATION_JSON_TYPE );
			}
			
			StringBuilder b
				= new StringBuilder( this.directory ).append( File.separator ).append( team.getId() );
			
			FileOutputStream fos
				= new FileOutputStream( b.toString() );
			
			this.messageBodyWriter.writeTo( team, Team.class, team.getClass(), new Annotation[] {}, MediaType.APPLICATION_JSON_TYPE, null, fos );
			
			return team;
			
		}
		catch( Exception e ) {
			throw new RuntimeException( e ); 
		}
		
	}

}
