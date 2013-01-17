package be.occam.colloseum.action.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.action.Action;

public interface IActionRepository {
	
	public Action<?> findOne( String id );
	
	public Set<Action<?>> findAll( Providers providers );
	
	public Action<?> persist( Action<?> id, Providers providers );

}
