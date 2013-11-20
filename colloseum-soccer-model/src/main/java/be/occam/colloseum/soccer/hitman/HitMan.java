package be.occam.colloseum.soccer.hitman;

import be.occam.colloseum.soccer.player.Player;

public class HitMan {
	
	public HitMan( Player player, int hits ) {
		this.player = player;
		this.hits = hits;
	}
	
	protected Player player;
	protected int hits;
	
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	

}
