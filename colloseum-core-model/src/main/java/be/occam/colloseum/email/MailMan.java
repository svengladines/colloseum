package be.occam.colloseum.email;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.javamail.JavaMailSender;

import be.occam.colloseum.hat.Hat;

public class MailMan extends Hat {
	
	@Resource
	JavaMailSender javaMailSender;
	
	public void deliver( MimeMessage mimeMessage ) {
		try {
		
			// it sends, just checked. disable to prevent spam for poor wieland. 
			this.javaMailSender.send( mimeMessage );
		
			logger.info( "sent email to {}", (Object) mimeMessage.getRecipients(  RecipientType.TO ) );
			
		}
		catch( Exception e ) {
			logger.error( "could not send email", e );
			throw new RuntimeException( e );
		}
		
	}
	
}
