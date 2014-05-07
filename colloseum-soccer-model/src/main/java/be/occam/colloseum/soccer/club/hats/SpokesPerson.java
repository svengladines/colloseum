package be.occam.colloseum.soccer.club.hats;

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
	
	public List<MimeMessage> writeInvitationEmails( List<Player> players, Match match ) {
		
		List<MimeMessage> messages
			= new ArrayList<MimeMessage>( players.size() );
		
		Configuration cfg 
			= new Configuration();
		
		try {

			Template template 
				= isHomeGame( match )  
						? new Template("invitation", new InputStreamReader( ClassLoader.getSystemResourceAsStream( "templates/email.invitation.home.tmpl") ), cfg )
						: new Template("invitation", new InputStreamReader( ClassLoader.getSystemResourceAsStream( "templates/email.invitation.away.tmpl") ), cfg );
		
			for ( Player player : players ) {
				
				try {
			
					Map<String, Object> model = new HashMap<String, Object>();
				
					model.put( "player", player );
					model.put( "match", match );
				
					StringWriter bodyWriter 
						= new StringWriter();
				
					template.process( model , bodyWriter );
					
					bodyWriter.flush();
					
					MimeMessage message = this.javaMailSender.createMimeMessage();
					MimeMessageHelper helper = new MimeMessageHelper(message);
					
					helper.setFrom(this.noReplyEmailAddress);
					helper.setTo( player.getEmail() );
					helper.setSubject( match.getName() );
					helper.setText(bodyWriter.toString(), true);
					
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
