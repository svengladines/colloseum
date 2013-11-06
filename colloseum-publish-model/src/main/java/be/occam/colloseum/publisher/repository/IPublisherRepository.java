package be.occam.colloseum.publisher.repository;

import java.net.URI;
import java.util.List;
import java.util.Set;

import be.occam.colloseum.publisher.PublisherDTO;

public interface IPublisherRepository {
	
	public PublisherDTO findOne( String id );
	
	public List<PublisherDTO> findAll( );
	
	public List<PublisherDTO> findByAcceptance( URI acceptance );
	
	public PublisherDTO persist( PublisherDTO publisher );

}
