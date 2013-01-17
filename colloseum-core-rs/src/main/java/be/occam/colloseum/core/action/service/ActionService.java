package be.occam.colloseum.core.action.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.ext.Providers;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.action.repository.IActionRepository;

public class ActionService {
	
	@Resource
	protected IActionRepository actionRepository;
	
	public List<Action<?>> consume( Action<?> action, Providers providers ) {
		
		this.actionRepository.persist( action, providers );
		
		List<Action<?>> actions
			= new LinkedList<Action<?>>( );
		
		actions.add( action );
		
		return actions;
		
	}

}
