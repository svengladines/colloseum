package be.occam.colloseum.soccer.ranking;

import be.occam.colloseum.soccer.team.Team;

public class Ranking {

	protected Team team;
	
	protected int won;
	protected int lost;
	protected int tied;
	
	protected int scored;
	protected int against;
	
	protected boolean itIsUs;
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getWon() {
		return won;
	}
	public void setWon(int won) {
		this.won = won;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public int getTied() {
		return tied;
	}
	public void setTied(int tied) {
		this.tied = tied;
	}
	public int getScored() {
		return scored;
	}
	public void setScored(int scored) {
		this.scored = scored;
	}
	public int getAgainst() {
		return against;
	}
	public void setAgainst(int against) {
		this.against = against;
	}
	
	public int getPlayed() {
		
		return this.won + this.lost + this.tied; 
		
	}
	
	public int getPoints() {
		
		return ( this.won * 3 ) + this.tied;
		
	}
	
	public boolean getItIsUs() {
		return this.itIsUs;
	}
	
	public void setItIsUs(boolean itIsUs) {
		this.itIsUs = itIsUs;
	}
	
	
	
}
