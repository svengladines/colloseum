package be.occam.colloseum.soccer.club.hats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.match.actions.Plan;

public class SpokesPerson extends Hat {
	
	@Resource
	Fixer fixer;
	
	@Resource
	CuteGirlFromTheLeague cuteGirlFromTheLeague;
	
	public List<Match> whatAreThePlannedMatchesAfter( Date date ) {
		
		List<Plan> planned
			= this.fixer.whatAreThePlannedMatchesAfter( date );
		
		List<Match> matches
			= new ArrayList<Match>();
		
		for ( Plan plan : planned ) {
			
		}
		
		return null;
	}
	
}
