package be.occam.colloseum.soccer.club.hats;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import be.occam.colloseum.model.Tag;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.player.Player;
import be.occam.colloseum.soccer.registration.RSVP;
import be.occam.colloseum.soccer.registration.RSVP.Answer;
import be.occam.colloseum.soccer.registration.Registration;
import be.occam.colloseum.util.Timing;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class SpokesPerson extends ClubHat {
	
	@Resource
	JavaMailSender javaMailSender;
	
	String noReplyEmailAddress;
	
	public SpokesPerson(Tag clubTag, String emailAddress ) {
		super(clubTag);
		this.noReplyEmailAddress = emailAddress;
	}
	
	public List<MimeMessage> writeInvitationEmails( List<Player> players, Match match, WebMaster webMaster ) {
		
		List<MimeMessage> messages
			= new ArrayList<MimeMessage>( players.size() );
		
		Configuration cfg 
			= new Configuration();
		
		try {
			
			InputStream his
				= this.getClass().getResourceAsStream( "/templates/email.invitation.home.tmpl" );
			
			InputStream ais
				= this.getClass().getResourceAsStream( "/templates/email.invitation.away.tmpl" );

			Template template 
				= isHomeGame( match )  
						? new Template("invitation", new InputStreamReader( his ), cfg )
						: new Template("invitation", new InputStreamReader( ais ), cfg );
		
			for ( Player player : players ) {
				
				try {
			
					Map<String, Object> model = new HashMap<String, Object>();
					
					String yep
						= webMaster.whatIsTheURLFor( new Registration().setPlayer( player ).setMatch( match ).setRsvp( new RSVP( Answer.Yep ) ) ).toString();
					String nope
						= webMaster.whatIsTheURLFor( new Registration().setPlayer( player ).setMatch( match ).setRsvp( new RSVP( Answer.Nope ) ) ).toString();
				
					model.put( "player", player );
					model.put( "match", match );
					model.put( "date", Timing.date( match.getStarts() ) );
					model.put( "time", Timing.moment( match.getStarts() ) );
					model.put( "yep", yep );
					model.put( "nope", nope );
				
					StringWriter bodyWriter 
						= new StringWriter();
				
					template.process( model , bodyWriter );
					
					bodyWriter.flush();
					
					MimeMessage message = this.javaMailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message);
					
					helper.setFrom(this.noReplyEmailAddress);
					helper.setTo( player.getEmail() );
					helper.setSubject( match.getName() );
					
					String text
						= bodyWriter.toString();
					
					logger.info( "email text is [{}]", text );
					
					helper.setText(text, true);
					
					messages.add( message );
				
				}
				catch( Exception e ) {
					logger.warn( "could not write e-mail", e );
				}
				
			}
		}
		catch ( Exception e ) {
			throw new RuntimeException(e);
		}
		
		return messages;
		
	}
	
	protected boolean isHomeGame( Match match ) {
		
		return match.getTags().contains( this.clubTag );
		
	}
	
}
