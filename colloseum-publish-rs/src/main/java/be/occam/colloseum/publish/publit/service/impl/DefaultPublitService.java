package be.occam.colloseum.publish.publit.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import be.occam.colloseum.publish.publit.service.IPublitService;
import be.occam.colloseum.publit.Publit;
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
		
		return publit;
		
	}

	@Override
	public Publit update(Publit publit) {
		
		return null;
		
	}

}
