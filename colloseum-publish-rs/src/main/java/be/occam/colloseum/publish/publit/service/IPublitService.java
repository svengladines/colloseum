package be.occam.colloseum.publish.publit.service;

import java.util.Collection;

import be.occam.colloseum.publit.Publit;

public interface IPublitService {
	
	public Collection<Publit> findAll();
	
	public Publit create( Publit publit );
	public Publit update( Publit publit );
	
	public Publit delete( String id );

}
