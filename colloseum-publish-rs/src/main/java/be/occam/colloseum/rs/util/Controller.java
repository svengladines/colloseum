package be.occam.colloseum.rs.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Controller {
	
	public static <T> ResponseEntity<T> response( T t, HttpStatus status ) {
	
		HttpHeaders headers 
			= new HttpHeaders();
	
		headers.add("Access-Control-Allow-Origin", "*" ) ;
		headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS" );
		headers.add("Access-Control-Allow-Headers", "Content-Type");
		headers.add("Access-Control-Allow-Credentials","true");
		
		ResponseEntity<T> response;
		
		if ( t != null ) {
			 response 
				= new ResponseEntity<T>( t, headers, status );
		}
		else {
			response 
				= new ResponseEntity<T>( headers, status );
		}
		
		return response;
		
	}
	
	public static <T> ResponseEntity<T> response( HttpStatus status ) {
		
		return response( null, status );
	
	}
	
}