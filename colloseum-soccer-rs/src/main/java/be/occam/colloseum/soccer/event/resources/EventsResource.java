package be.occam.colloseum.soccer.event.resources;

import static be.occam.colloseum.util.Resource.response;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.event.Event;


@Controller
@RequestMapping( {"/events"} )
public class EventsResource {
	
	@Resource
	CalendarGuy calendarGuy;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<List<Event>> query() {
		
		List<Event> events
			= this.calendarGuy.listEvents( new Date() );
		
		return response( events, HttpStatus.OK );
		
	}
	
}
