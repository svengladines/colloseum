package be.occam.colloseum.soccer.club.hats;

import java.util.List;

import javax.annotation.Resource;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.soccer.league.hats.CuteGirlFromTheLeague;
import be.occam.colloseum.soccer.ranking.Ranking;

public class Ranker extends Hat {
	
	protected String us;
	
	public Ranker( String us ) {
		this.us = us;
	}
	
	@Resource
	CuteGirlFromTheLeague cuteGirlFromTheLeague;
	
	public List<Ranking> whatAreTheCurrentRankings( ) {
		
		// assumption the cute girl returns them sorted (champs first)!
		List<Ranking> rankings
			= this.cuteGirlFromTheLeague.whatAreTheRankings();
		
		for ( Ranking ranking : rankings ) {
			
			if ( ranking.getTeam().getName().equals( us ) ) {
				ranking.setItIsUs( true );
				break;
			}
			
		}
		
		return rankings;
		
	}
	
}
