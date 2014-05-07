package be.occam.colloseum.kuleuven.application.web.resources;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.colloseum.kuleuven.cron.scenarios.InvitePlayersToRegister;

@Controller
@RequestMapping( value="/scenarios/{id}" )
public class ScenarioResource {
	
	protected Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Resource
	InvitePlayersToRegister invitePlayersToRegister;
	
	@RequestMapping( method=RequestMethod.GET )
	@ResponseBody
	public String get( @PathVariable String id ) {
		
		try {
			invitePlayersToRegister.play();
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
	
}
