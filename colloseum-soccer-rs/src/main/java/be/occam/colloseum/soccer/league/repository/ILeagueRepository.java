package be.occam.colloseum.soccer.league.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.soccer.league.League;

public interface ILeagueRepository {
	
	public League findOne( String id );
	
	public Set<League> findAll( Providers providers );
	
	public League persist( League id, Providers providers );

}
