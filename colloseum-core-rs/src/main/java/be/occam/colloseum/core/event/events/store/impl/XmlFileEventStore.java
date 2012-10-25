package be.occam.colloseum.core.event.events.store.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import be.occam.colloseum.core.event.events.store.IEventStore;
import be.occam.colloseum.model.Event;
import be.occam.colloseum.model.filter.EventFilter;

@Component
public class XmlFileEventStore implements IEventStore {
	
	@Value(value="src/test/resources/events")
	protected String directory;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Event<?, ?>> filtered( Providers providers, EventFilter... eventFilters ) {
		
		try {
			
			List<Event<?,?>> events
				= new ArrayList<Event<?,?>>();
		
			Event event 
				= new Event();
			
			MessageBodyReader<Event> reader
				= providers.getMessageBodyReader( Event.class, event.getClass() , null, MediaType.TEXT_XML_TYPE );
			
			File dir
				= new File( this.directory );
			
			File[] files
				= dir.listFiles();
			
			for ( File file : files ) {
				
				FileInputStream fis
					= new FileInputStream( file );
				
				Event marshalled
					= reader.readFrom( Event.class, event.getClass(), Event.class.getAnnotations(), MediaType.TEXT_XML_TYPE, null, fis );
				
				events.add( marshalled );
				
			}
			
			return events;
			
			
		} catch( Exception e ) {
			
			throw new RuntimeException( e );
			
		}
		
	}

}
