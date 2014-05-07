package be.occam.colloseum.soccer.registration;

import be.occam.colloseum.soccer.match.Match;
import be.occam.colloseum.soccer.player.Player;

public class Registration {
	
	protected String id;
	Player player;
	Match match;
	RSVP rsvp;
	
	
	public Player getPlayer() {
		return player;
	}
	
	public Registration setPlayer(Player player) {
		this.player = player;
		return this;
	}
	
	public Match getMatch() {
		return match;
	}
	
	public Registration setMatch(Match match) {
		this.match = match;
		return this;
	}
	
	public RSVP getRsvp() {
		return rsvp;
	}
	
	public Registration setRsvp(RSVP rsvp) {
		this.rsvp = rsvp;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
