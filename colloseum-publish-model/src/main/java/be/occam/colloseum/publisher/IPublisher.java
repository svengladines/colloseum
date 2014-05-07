package be.occam.colloseum.publisher;

import be.occam.colloseum.publit.Publit;

public interface IPublisher {
	
	public boolean accept( Publit publit );
	
	public Publit publish( Publit publit );

	public Publit get( String id );
	
}
