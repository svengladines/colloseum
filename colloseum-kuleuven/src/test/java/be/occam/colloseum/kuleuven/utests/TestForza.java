package be.occam.colloseum.kuleuven.utests;

import static be.occam.test.jtest.JTestUtil.getHTML;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TestForza {
	
	protected final Logger logger
		= LoggerFactory.getLogger( this.getClass() );
	
	@Test
	public void testBasicAuthorization() throws Exception {
		
		String url
			= "https://www.kuleuven.be/voetbal/deelname/match2.php?datum=2013-11-29";
		
		Map<String,String> headers
			= new HashMap<String,String>();
		
		String plain
			= "soccer:PloegskE";
		
		byte[] encodedBytes
			= {};// Base64.encode( plain.getBytes( "utf-8") );
		
		String encoded
			= new String( encodedBytes, "utf-8" );
		
		assertEquals( "c29jY2VyOlBsb2Vnc2tF", encoded );
		
		String auth
			= new StringBuilder("Basic ").append( encoded ).toString();
		
		headers.put( "Authorization", auth );
		
		ResponseEntity<String> pageResponse
			= getHTML( url, headers );
		
		assertEquals( HttpStatus.OK, pageResponse.getStatusCode() );
		
		String page
			= pageResponse.getBody();
		
		logger.info( "html: [{}]", page );
		
	}

}
