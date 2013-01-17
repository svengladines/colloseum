package be.occam.colloseum.soccer.player;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.match.Match;


public class Player extends Person {

	public Match whatIsTheNextMatch() {
		
		return new Match();
	
	}
	
}