package be.occam.colloseum.kuleuven.hats;

import java.util.ArrayList;
import java.util.List;

import be.occam.colloseum.soccer.league.hats.LeagueManager;
import be.occam.colloseum.soccer.ranking.Ranking;

public class KULeuvenLeagueManager extends LeagueManager {

	@Override
	public List<Ranking> whatAreTheRankings() {
		return new ArrayList<Ranking>();
	}

}
