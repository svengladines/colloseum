package be.occam.colloseum.credential.resources;

import static be.occam.utils.spring.web.Controller.response;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.occam.colloseum.credential.Credential;
import be.occam.colloseum.credential.repository.ICredentialRepository;

@Controller
@RequestMapping( value="/credentials/{reference}" )
public class CredentialResource {
	
	@Resource
	ICredentialRepository credentialRepository;
	
	@RequestMapping( method=RequestMethod.GET )
	public ResponseEntity<Credential> get() {
		
		return response( new Credential(), HttpStatus.OK );
		
	}
	
	@RequestMapping( method=RequestMethod.PUT )
	public ResponseEntity<Credential> put( @PathVariable String reference, @RequestBody Credential credential ) {
		
		Credential stored
			= this.credentialRepository.findByReference( reference );
		// Credential saved = this.credentialRepository.save( credential );
		
		if ( stored.getPassWord().equals( credential.getPassWord() ) ) {
			
			Map<String,String> headers
				= new HashMap<String, String>();
			
			headers.put( "Set-Cookie", cookie( credential ) );
			return response( stored, HttpStatus.OK, headers );	
		}
		else {
			return response( HttpStatus.UNAUTHORIZED );
		}
		
		
	}
	
	protected String cookie( Credential credential ) {
		
		StringBuilder builder
			= new StringBuilder();
		
		builder.append( "cusr=" ).append( "svekke" ).append( "; Path=/; Expires=Wed, 09 Jun 2021 10:18:14 GMT");
		
		return builder.toString();
		
	}

}
