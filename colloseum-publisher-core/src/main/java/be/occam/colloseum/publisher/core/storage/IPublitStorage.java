package be.occam.colloseum.publisher.core.storage;

import be.occam.colloseum.publisher.core.Publit;

public interface IPublitStorage {
	
	public IPublitStorage persist( Publit shareable );
	
	public Publit load( String id );
	
	public IPublitStorage delete( Publit publit );

}
