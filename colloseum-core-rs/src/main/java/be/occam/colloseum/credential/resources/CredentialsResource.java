package be.occam.colloseum.credential.resources;

import static be.occam.utils.spring.web.Controller.response;

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
@RequestMapping( value="/credentials" )
public class CredentialsResource {
	
	@Resource
	ICredentialRepository credentialRepository;
	
	protected Credential[] array 
		= new Credential[] {};
	
	@RequestMapping( method=RequestMethod.GET )
	public ResponseEntity<Credential[]> get() {
		
		return response( new Credential[] {}, HttpStatus.OK );
		
	}
	
	@RequestMapping( method=RequestMethod.POST )
	public ResponseEntity<Credential> post( @RequestBody Credential credential ) {
		
		Credential stored
			= this.credentialRepository.findByReference( credential.getReference() );
		
		if ( stored.getPassWord().equals( credential.getPassWord() ) ) {
			return response( stored, HttpStatus.OK );
		}
		else {
			return response( HttpStatus.UNAUTHORIZED );
		}
		
		
	}

}
