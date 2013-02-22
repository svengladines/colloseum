package be.occam.colloseum.publit.repository;

import java.util.List;

import be.occam.colloseum.action.ActionDTO;
import be.occam.colloseum.publit.Publit;

public interface IPublitRepository {

	public Publit findOne( String id );
	
	public List<Publit> findAll( );
	
	public List<Publit> findByTrack( String track );
	
	public Publit persist( Publit persist );
	
}
