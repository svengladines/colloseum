package be.occam.colloseum.soccer.team.repository;

import java.util.Set;

import javax.ws.rs.ext.Providers;

import be.occam.colloseum.soccer.team.Team;

public interface ITeamRepository {
	
	public Team findOne( String id );
	
	public Set<Team> findAll( Providers providers );
	
	public Team persist( Team id, Providers providers );

}
