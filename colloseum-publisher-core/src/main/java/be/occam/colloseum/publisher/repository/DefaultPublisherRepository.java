package be.occam.colloseum.publisher.repository;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import be.occam.colloseum.publisher.PublisherDTO;

public class DefaultPublisherRepository implements IPublisherRepository {

	@Override
	public PublisherDTO findOne(String id) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<PublisherDTO> findAll() {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public List<PublisherDTO> findByAcceptance(URI acceptance) {
		
		PublisherDTO dto = new PublisherDTO();
		
		return Arrays.asList( dto );
		
	}

	@Override
	public PublisherDTO persist(PublisherDTO publisher) {
		// TODO Auto-generated method stub
		return null;
	}

}
