package be.occam.colloseum.soccer.match.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.LoggerFactory;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.event.Event;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.team.Team;

/**
 * 
 * A match is plannned.
 * 
 * Action-plan:
 * 
 * 1. add to the team's agenda
 * 2. 
 * 
 * @author sven
 *
 */
@XmlRootElement
public class Plan extends Action<Match>{
	
	public Plan(Match t) {
		super(t);
	}

	protected Date planned;
	
	@Override
	public Action<Match> execute() {
		
		// 1. add to the team's agenda
		Match match
			= this.subject;
		
		String title
			= new StringBuilder( match.getHomeTeam().getName() ).append( " - " ).append( match.getAwayTeam().getName() ).toString();
		
		GregorianCalendar end 
			= new GregorianCalendar();
		end.setTime( this.planned );
		end.add( Calendar.HOUR, 2 );
		
		Event event
			= new Event()
				.setStart( this.planned )
				.setEnd( end.getTime() )
				.setSubject( title );
		
		new CalendarGuy().createEvent( event );
		
		return this;
		
	}
	
	public Date getPlanned() {
		return planned;
	}

	public Plan setPlanned(Date planned) {
		this.planned = planned;
		return this;
	}

	public static void main( String[] args ) {
		
		try {
		
			Team kuleuven
				= new Team().setName("KU Leuven");
			
			Team rsca
				= new Team().setName("RSC Anderlecht");
			
			Match match 
				= new Match().setHomeTeam( kuleuven ).setAwayTeam( rsca );
			
			Plan plan
				= new Plan( match ).setPlanned( new SimpleDateFormat( "dd-MM-yyyy" ).parse( "14-05-2013" ) );
			
			plan.execute();
			
		}
		catch( Exception e ){
			LoggerFactory.getLogger( Plan.class ).error( "bugger", e );
		}
		
	}

}
