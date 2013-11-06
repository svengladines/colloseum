package be.occam.colloseum.rs.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

public class Resource {
	
	public static ResponseBuilder response( Status status, Object entity ) {
		
		return Response
			.status( status )
			
			.header("Access-Control-Allow-Origin","*")
			.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
			.header("Access-Control-Allow-Headers", "Accept,Content-Type")
			
			.entity( entity );
		
	}
	
	public static ResponseBuilder response( Status status ) {
		
		return Response
			.status( status )
			
			.header("Access-Control-Allow-Origin","*")
			.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
			.header("Access-Control-Allow-Headers", "Accept,Content-Type");
			
	}
	
	public static ResponseBuilder response( ResponseBuilder builder ) {
		
		return builder
			
			.header("Access-Control-Allow-Origin","*")
			.header("Access-Control-Allow-Methods", "GET,POST,PUT,OPTIONS")
			.header("Access-Control-Allow-Headers", "Accept,Content-Type");
			
	}

}
