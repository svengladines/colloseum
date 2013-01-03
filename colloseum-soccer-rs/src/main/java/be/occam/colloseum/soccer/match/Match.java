package be.occam.colloseum.soccer.match;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.soccer.team.Team;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Match {
	
	protected String id;
	protected String name;
	
	protected Team homeTeam;
	protected Team awayTeam;

	public String getId() {
		
		return id;
		
	}

	public Match setId(String id) {
		
		this.id = id;
		return this;
		
	}

	public String getName() {
		return name;
	}

	public Match setName( String name ) {
		this.name = name;
		return this;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Match setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
		return this;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public Match setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
		return this;
	}
	
}
