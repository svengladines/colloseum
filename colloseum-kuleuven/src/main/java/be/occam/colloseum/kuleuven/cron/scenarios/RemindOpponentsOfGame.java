package be.occam.colloseum.kuleuven.cron.scenarios;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.email.MailMan;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.club.hats.WebMaster;
import be.occam.colloseum.soccer.league.hats.LeagueManager;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.player.Player;
import be.occam.colloseum.soccer.registration.Registration;
import be.occam.colloseum.soccer.team.Team;

/**
 * - ask fixer for the next match
 * - ask league for spokesperson of oppontent
 * - ask our spokesperson to prepare reminder e-mail
 * - address to e-mail to the spokesperson
 * - ask mailman to deliver e-mails
 */
public class RemindOpponentsOfGame {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	Fixer fixer;
	
	@Resource
	LeagueManager leagueManager;
	
	@Resource
	Team team;
	
	@Resource
	MailMan mailMan;
	
	public void play( WebMaster webMaster ) {
		
		Match match = fixer.whatIsTheNextMatch();
		
		if ( match == null ) {
			return;
		}
		
		logger.info( "next match is [{}]", match.getName() );
		
		if ( isMatchThisWeek( match ) ) {
			
			logger.info( "the match is this week " );
			
			Team otherTeam 
				= match.getHomeTeam().equals( this.team ) ? match.getAwayTeam() : match.getHomeTeam() ;
			
			List<Person> spokesFolk
				= this.leagueManager.whoIsSpokesPersonForTeam( otherTeam );
			
			for ( Person spokesPerson : spokesFolk ) {
				
				logger.debug( "spokesperson [{}]", spokesPerson );
				
				
				
			}
			
			List<MimeMessage> emails = Arrays.asList( );
				//= this.spokesPerson.writeInvitationEmails( toInvitePlayers , match, webMaster );
			
			for ( MimeMessage email : emails ) {
				this.mailMan.deliver( email );
			}
			
		}
		
	}
	
	protected boolean isMatchThisWeek( Match match ) {
		
		Date now 
			= new Date();
		
		Date start
			= match.getStarts();
		
		GregorianCalendar nowCal
			= new GregorianCalendar();
		nowCal.setTime( now );
		
		GregorianCalendar startCal
			= new GregorianCalendar();
		startCal.setTime( start );
		
		int startDay 
			= startCal.get( Calendar.DAY_OF_YEAR );
		
		int nowDay
			= nowCal.get( Calendar.DAY_OF_YEAR );
		
		boolean thisWeek
			= ( ( startDay - nowDay )  < 7 ) || ( ( startDay + 365 - nowDay ) < 7 );
			
		return thisWeek;
		
	}
	

}
