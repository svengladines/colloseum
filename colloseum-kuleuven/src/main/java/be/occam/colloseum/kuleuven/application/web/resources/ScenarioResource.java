package be.occam.colloseum.kuleuven.application.web.resources;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import be.occam.colloseum.kuleuven.cron.scenarios.InvitePlayersToRegister;
import be.occam.colloseum.soccer.club.hats.WebMaster;

@Controller
@RequestMapping( value="/scenarios/{id}" )
public class ScenarioResource {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	InvitePlayersToRegister invitePlayersToRegister;
	
	@Resource
	WebMaster webMaster;
	
	@RequestMapping( method=RequestMethod.GET )
	@ResponseBody
	public String get( @PathVariable String id, HttpServletRequest request, WebRequest r ) {
		
		try {
			invitePlayersToRegister.play( webMaster );
		}
		catch( Throwable e ) {
			logger.warn( "scenario threw exception", e );
			return e.getMessage();
		}
		
		return "ok";
		
	}
	
	@ExceptionHandler(Exception.class)
	public void exception( Exception e ) {
		logger.warn( "request failed", e );
	}
	
	protected String baseUrl( HttpServletRequest request ) {
		
		StringBuilder b
			= new StringBuilder();
		
		b.append( request.getScheme() );
		b.append( "://" );
		b.append( request.getServerName() );
		b.append( ":" );
		b.append( request.getLocalPort() );
		b.append( request.getContextPath() );
		
		return b.toString();
		
	}
	
}
