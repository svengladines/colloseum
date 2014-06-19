package be.occam.colloseum.kuleuven.cron.scenarios;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.email.MailMan;
import be.occam.colloseum.soccer.club.hats.Fixer;
import be.occam.colloseum.soccer.club.hats.SpokesPerson;
import be.occam.colloseum.soccer.club.hats.TeamManager;
import be.occam.colloseum.soccer.club.hats.WebMaster;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.player.Player;
import be.occam.colloseum.soccer.registration.Registration;

/**
 * - ask fixer for the next match
 * - ask team-manager for available players
 * - for these players, ask team manager for registration status
 * - ask spokesperson to prepare registration e-mails
 * - ask mailman to deliver e-mails
 */
public class InvitePlayersToRegister {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	Fixer fixer;
	
	@Resource
	TeamManager teamManager;
	
	@Resource
	SpokesPerson spokesPerson;
	
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
			
			List<Player> availablePlayers
				= this.teamManager.whichPlayersAreAvailableForMatch( match );
			
			List<Player> toInvitePlayers
				= new LinkedList<Player>();
			
			for ( Player player : availablePlayers ) {
				
				logger.info( "check registration for [{}]", player.getEmail() );
				
				Registration registration
					= this.teamManager.whatIsTheRegistrationForPlayerForMatch( player, match );
				
				if ( registration == null ) {
					
					logger.info( "[{}] did not yet register", player.getEmail() );
					toInvitePlayers.add( player );
					
				}
				
			}
			
			List<MimeMessage> emails
				= this.spokesPerson.writeInvitationEmails( toInvitePlayers , match, webMaster );
			
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
