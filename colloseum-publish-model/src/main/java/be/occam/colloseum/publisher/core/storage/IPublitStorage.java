package be.occam.colloseum.publisher.core.storage;

import be.occam.colloseum.publit.Publit;

public interface IPublitStorage {
	
	public IPublitStorage persist( Publit shareable );
	
	public Publit load( String id );
	
	public IPublitStorage delete( Publit publit );

}
