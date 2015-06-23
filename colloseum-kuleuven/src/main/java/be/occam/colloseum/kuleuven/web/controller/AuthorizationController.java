package be.occam.colloseum.kuleuven.web.controller;

import static be.occam.utils.spring.web.Controller.response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import be.occam.utils.oauth.OAuthor;
import be.occam.utils.spring.web.RedirectException;

@Controller
@RequestMapping()
public class AuthorizationController {
	
	private final static Logger logger = LoggerFactory.getLogger( AuthorizationController.class );
	
	@Resource
	OAuthor oAuthor;

	@RequestMapping( value="/authorized.html", method = { RequestMethod.GET } )
	public ModelAndView authorized( HttpServletRequest request, @RequestParam(required=false) String state, @RequestParam(required=false) String returnUrl ) {
		
		logger.info( "authorized.html" );
		
		Map<String,Object> map	
			= new HashMap<String,Object>();
		

		String consent
			= request.getParameter("consent");
		
		if ( consent != null ) {
			map.put( "authorized", "in progress" );
			this.oAuthor.authorize( 
					"kuleuven-football@gmail.com", 
					"https://www.googleapis.com/auth/calendar.readonly", 
					"https://kuleuven-football.appspot.com/authorized.html", 
					state );
		}
		else { 
				String error
					= request.getParameter("error_description");
		
			if ( error != null ) {
			
				logger.info( "error_description is [{}]", error );
				map.put( "authorized", "false" );
				map.put( "error", error );
				
			}
			else {
			
					
				String code
					 = request.getParameter("code");
				
				if ( code !=null ) {
					
					logger.info( "CODE is [{}]", code );
					
					this.oAuthor.authorized( "kuleuven.football@gmail.com", code );
					
					map.put( "authorized", "authorized" );
					try {
						map.put( "code", URLEncoder.encode( code, "utf-8" ) );
						map.put( "returnUrl", state );
					}
					catch( UnsupportedEncodingException ignore ) {}
					
				}
				else {
				
					String tc
					 	= request.getParameter("token-code");
				
					if ( tc !=null ) {
						
						map.put( "authorized", "request access token" );
						
						String token
							= this.oAuthor.requestAccessToken( "kuleuven.football@gmail.com", tc, "https://kuleuven-football.appspot.com/authorized.html", returnUrl );
						
						map.put( "token", token );
						
					}
					else {
						String t = request.getParameter("token");
						if ( t != null ) {
							this.oAuthor.setAccessToken( t );
						}
					}
				}
			}
			
		}
		
		ModelAndView mv
			= new ModelAndView( "authorized", map );
		
		return mv;
		
	}
	
	// SGL| generic response handler for all controllers ? in mvcconfig ?
	@ExceptionHandler(RedirectException.class)
	public ResponseEntity<String> handleRedirectException(RedirectException e, HttpServletRequest servletRequest ) {
		
		logger.info("redirecting to [{}]", e.getRedirectUrl() );
		
		Map<String,String> headers
			= new HashMap<String,String>();
		headers.put( "Location", e.getRedirectUrl() );
		
		ResponseEntity<String>response
			= response( e.getMessage(), HttpStatus.TEMPORARY_REDIRECT, headers );
		
		return response;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlException(Exception e, HttpServletRequest servletRequest ) {
		
		logger.warn("error", e);
		
		ResponseEntity<String>response
			= response( e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR );
		
		return response;
		
	}
	
}
