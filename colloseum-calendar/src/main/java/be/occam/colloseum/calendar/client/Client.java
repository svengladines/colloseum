package be.occam.colloseum.calendar.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpRequest;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final String key
			= "AIzaSyArT1oGU4l11kEeRidnMejgCw8DybNlMVw";
		
		Logger logger
			= LoggerFactory.getLogger( Client.class );
			
		try {

			HttpTransport httpTransport 
				= new NetHttpTransport();
			
		    JacksonFactory jsonFactory 
		    	= new JacksonFactory();
		    
		    JsonHttpRequestInitializer init
		    	= new JsonHttpRequestInitializer() {
					
					@Override
					public void initialize(JsonHttpRequest jsonHttpRequest ) throws IOException {
						CalendarRequest request 
							= (CalendarRequest)jsonHttpRequest;
						request.setKey( key );
					}
					
				};
		    
		    GenericUrl url
		    	= Calendar
		    		.builder(httpTransport, jsonFactory)
		    		.getBaseUrl();
		    
		    logger.info( "url: [{}]", url );
		    
		    Calendar service 
		    	= Calendar
		    		.builder(httpTransport, jsonFactory)
		    		.setJsonHttpRequestInitializer( init )
		    		.setApplicationName("be.occam.colloseum")
		    		.build();
		    
		    
		    Events events 
		    	= service.events().list( "debrodders@gmail.com" ).execute();
		    
		    while (true) {
		      for (Event event : events.getItems()) {
		        System.out.println(event.getSummary());
		      }
		      String pageToken = events.getNextPageToken();
		      if (pageToken != null && !pageToken.isEmpty()) {
		        events = service.events().list("primary").setPageToken(pageToken).execute();
		      } else {
		        break;
		      }
		    }

		    
		}
		catch ( Exception e ) {
			
			LoggerFactory.getLogger( Client.class ).warn( "d'oh", e ) ;
			
		}

		
	}

}
