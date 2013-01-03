package be.occam.colloseum.soccer.match.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.soccer.match.Match;

public interface IMatchRepository {
	
	public Match findOne( String id );
	
	public Set<Match> findAll( Providers providers );
	
	public Match persist( Match id, Providers providers );

}
