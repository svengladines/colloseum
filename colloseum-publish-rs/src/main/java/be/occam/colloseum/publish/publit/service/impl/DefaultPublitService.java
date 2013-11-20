package be.occam.colloseum.publish.publit.service.impl;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publit.Publit;
import be.occam.colloseum.publit.Publit.Status;
import be.occam.colloseum.publit.repository.IPublitRepository;

public class DefaultPublitService implements IPublitService {
	
	@Resource
	IPublitRepository publitRepository;
	
	@Override
	public Collection<Publit> findAll() {
		
		return this.publitRepository.findAll();
		
	}

	@Override
	public Publit create(Publit publit) {
		
		publit.setTouched( new Date() ).setStatus( Status.Draft );
		return this.publitRepository.save( publit );
		
	}

	@Override
	public Publit update(Publit publit) {
		
		return this.publitRepository.save( publit );
		
	}

	@Override
	public Publit delete( String id ) {
		
		Publit publit 
			= this.publitRepository.findOne( id );
		
		if ( publit != null ) {
			this.publitRepository.delete( publit );
		}
		
		return publit;
	}
	
	

}
