package be.occam.colloseum.publisher.resources;

import java.net.URI;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import be.occam.colloseum.publisher.repository.IPublisherRepository;

@Path("publishers")
public class PublishersResource {
	
	@Resource
	IPublisherRepository publisherRepository;
	
	@GET
	public Response get( @QueryParam("acceptance") String acceptance ) {
		
		try {
		
			Response response 
				= Response.ok().entity( this.publisherRepository.findByAcceptance( new URI( acceptance ) )).build();
			
			return response;
		}
		catch( Exception e ) {
			throw new WebApplicationException( e );
		}
		
	}

}
