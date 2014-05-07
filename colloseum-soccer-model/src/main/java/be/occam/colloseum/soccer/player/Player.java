package be.occam.colloseum.soccer.player;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.match.Match;


public class Player extends Person {
	
	public Player() {
		super();
	}
	
	public Player( Person person ) {
		super( person );
	}
	
	public Player( String nick ) {
		super();
		this.nickName = nick;
	}

}