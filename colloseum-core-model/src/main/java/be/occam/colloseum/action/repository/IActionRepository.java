package be.occam.colloseum.action.repository;

import java.util.List;

import be.occam.colloseum.action.ActionDTO;

public interface IActionRepository {

	public ActionDTO findOne( String id );
	
	public List<ActionDTO> findAll( );
	
	public ActionDTO persist( ActionDTO persist );
	
	public List<ActionDTO> findByActor( String person );

}
