package be.occam.colloseum.soccer.league.hats;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.soccer.team.Team;

public class CuteGirlFromTheLeague extends Hat {
	
	public Team whatsThatTeamThatsCalled( String name ) {
		
		return new Team().setName( name );
		
	}

}
 