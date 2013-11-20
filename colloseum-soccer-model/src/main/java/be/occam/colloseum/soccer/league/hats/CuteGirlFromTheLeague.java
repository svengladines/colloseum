package be.occam.colloseum.soccer.league.hats;

import java.util.List;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.soccer.ranking.Ranking;
import be.occam.colloseum.soccer.team.Team;

public abstract class CuteGirlFromTheLeague extends Hat {
	
	public Team whatsThatTeamThatsCalled( String name ) {
		
		return new Team().setName( name );
		
	}
	
	public abstract List<Ranking> whatAreTheRankings();

}
 