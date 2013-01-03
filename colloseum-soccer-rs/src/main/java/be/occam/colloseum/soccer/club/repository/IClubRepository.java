package be.occam.colloseum.soccer.club.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.soccer.club.Club;

public interface IClubRepository {
	
	public Club findOne( String id );
	
	public Set<Club> findAll( Providers providers );
	
	public Club persist( Club id, Providers providers );

}
