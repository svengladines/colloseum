package be.occam.colloseum.soccer.club.hats;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;

import be.occam.colloseum.calendar.CalendarGuy;
import be.occam.colloseum.event.Event;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.Match.RegistrationStatus;
import be.occam.colloseum.soccer.match.actions.Plan;
import be.occam.colloseum.soccer.team.Team;
import be.occam.colloseum.util.Timing;

public class Fixer extends ClubHat {
	
	// TODO: standard start hour of match -> deviant
	
	@Resource
	CalendarGuy calendarGuy;
	
	@Resource
	CuteGirlFromTheLeague cuteGirlFromTheLeague;
	
	public Fixer( Tag clubTag ) {
		super( clubTag );
	}
	
	public List<Plan> whatAreThePlannedMatchesAfter( Date from, Date untill ) {
		
		List<Event> events 
			= this.calendarGuy.listEvents( from );
		
		List<Plan> planned
			= new ArrayList<Plan>();
		
		Date now
			= new Date();
		
		Match first
			= null;
		
		for ( Event event : events ) {
			
			Match match
				= this.mapTo( event );
			
			if ( ( untill != null ) && ( match.getStarts().after( untill ) ) ) {
				// too far in the future
				continue;
			}
			
			if ( ( first == null ) && ( match.getStarts().after( now ) ) ) {
				match.setFirstToCome( true );
				first = match;
			}
			
			Plan plan
				= new Plan( match );
			plan.setPlanned( event.getStart() );
			plan.setActor( this.person );
			
			planned.add( plan );
			
		}
		
		return planned;
		
	}
	
	public List<Match> whatMatchesArePlannedAt( Date date ) {
		
		List<Event> events 
			= this.calendarGuy.listEvents( date );
		
		Date now
			= new Date();
		
		Match first
			= null;
		
		List<Match> matches
			= new LinkedList<Match>();
		
		for ( Event event : events ) {
			
			if ( ! sameDay( event.getStart(), date ) ) {
				continue;
			}
			
			Match match 
				= mapTo( event );
			
			if ( match != null ) {
				matches.add( match );
			}
			
		}
		
		return matches;
		
	}
	
	protected RegistrationStatus registrationStatus( Match m ) {
		
		RegistrationStatus status
			= null;
		
		GregorianCalendar starts = new GregorianCalendar();
		starts.setTime( m.getStarts() );
		
		GregorianCalendar now = new GregorianCalendar();
		now.setTime( new Date() );
		
		if ( now.after( starts ) ) {
			status = RegistrationStatus.Neutral;
		}
		else {
			GregorianCalendar offset = new GregorianCalendar( );
			
			offset.setTime( now.getTime() );
			offset.add( GregorianCalendar.DAY_OF_MONTH, 1);
			
			if ( offset.after( starts ) ) {
				status = m.getPlayers() < 12 ? RegistrationStatus.Critical : RegistrationStatus.OK ;
			}
			else {
				offset.setTime( now.getTime() );
				offset.add( GregorianCalendar.DAY_OF_MONTH, 2);
				
				if ( offset.after( starts ) ) {
					status = m.getPlayers() < 12 ? RegistrationStatus.Low : RegistrationStatus.OK ;
				}	
			}
		}
		
		return status;
		
	}
	
	protected boolean sameDay( Date one, Date two ) {
		
		Calendar cOne = new GregorianCalendar();
		cOne.setTime( one );
		
		Calendar cTwo = new GregorianCalendar();
		cTwo.setTime( two );
		
		return ( cOne.get(Calendar.YEAR) == cTwo.get(Calendar.YEAR) ) && ( cOne.get(Calendar.DAY_OF_YEAR) == cTwo.get(Calendar.DAY_OF_YEAR) );
		
	}
	
	protected Match mapTo( Event event ) {
		
		Date now
			= new Date();
		
		String subject = event.getSubject();
		
		String[] split = subject.split( "-" );
		
		if ( split.length < 2 ) {
			return null;
		}
		
		String home = split[0].trim();
		String away = split[1].trim();
		
		Team homeTeam = this.cuteGirlFromTheLeague.whatsThatTeamThatsCalled( home );
		Team awayTeam = this.cuteGirlFromTheLeague.whatsThatTeamThatsCalled( away );
		
		
		Match match
			= new Match()
			.setHomeTeam( homeTeam )
			.setAwayTeam( awayTeam )
			.setName( subject )
			.setStarts( event.getStart() );
	
		if ( match.getStarts().after( now ) ) {
			match.setYetToCome( true );
		}

		if ( now.after( match.getStarts() ) ) {
			
			String description 
				= event.getDescription();
			
			if ( description != null ) {
				String[] score
					= description.split("-");
				
				match.setHomeTeamScore( Integer.valueOf( score[0].trim() ) );
				match.setAwayTeamScore( Integer.valueOf( score[1].trim() ) );
			}
			
		}
		
		Calendar startCal
			= GregorianCalendar.getInstance();
		
		startCal.setTime( match.getStarts() );
		startCal.setTimeZone( TimeZone.getTimeZone("Europe/Brussels" ) );
		
		logger.info( "hour = [{}]", startCal.get( Calendar.HOUR_OF_DAY ) );
		logger.info( "minute = [{}]", startCal.get( Calendar.MINUTE) );
		
		// TODO
		if ( ( startCal.get( Calendar.HOUR_OF_DAY ) != 20 ) || ( startCal.get( Calendar.MINUTE ) != 0 ) ) {
			
			match.setDeviantKickOff( true );
			
		}
		
		return match;
		
	}
	
	public Match whatIsTheNextMatch( ) {

		Date now
			= new Date();
		
		List<Event> events 
			= this.calendarGuy.listEvents( now );
		
		List<Plan> planned
			= new ArrayList<Plan>();
		
		
		Match first
			= null;
		
		for ( Event event : events ) {
			
			Match match
				= this.mapTo( event );

			if ( match != null ) {
				first = match;
				break;
			}
			
		}
		
		return first;
		
	}
	
	/**
	 * For now, ID = date-string (is KULeuven prop, but for now do it here)
	 */
	public Match whichMatchHasID( String id ) {

		Date date
			= Timing.date( id );

		List<Match> list
			= this.whatMatchesArePlannedAt( date );
		
		if ( list.isEmpty() ) {
			logger.warn( "no match at date [{}]", date );
			return null;
		}
		
		Match m
			= list.get( 0 );
		
		// TODO, id stuff
		m.setId( id );
		
		return m;
		
	}

}
