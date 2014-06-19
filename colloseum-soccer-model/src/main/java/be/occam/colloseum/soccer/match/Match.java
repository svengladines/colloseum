package be.occam.colloseum.soccer.match;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.model.Tag;
import be.occam.colloseum.soccer.team.Team;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Match {
	
	public static enum RegistrationStatus {
		None, Neutral, Low, Critical, OK, Overdose
	}
	
	protected String id;
	protected String name;
	
	protected Team homeTeam;
	protected Team awayTeam;
	
	protected Date starts;
	
	protected int players;
	
	protected int homeTeamScore;
	protected int awayTeamScore;
	
	protected boolean yetToCome;
	protected boolean firstToCome;
	
	protected RegistrationStatus registrationStatus;
	
	protected boolean deviantKickOff;
	
	protected Set<Tag> tags
		= new HashSet<Tag>();

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

	public Date getStarts() {
		return starts;
	}

	public Match setStarts(Date starts) {
		this.starts = starts;
		return this;
	}

	public boolean isFirstToCome() {
		return firstToCome;
	}

	public Match setFirstToCome(boolean firstToCome) {
		this.firstToCome = firstToCome;
		return this;
	}

	public int getPlayers() {
		return players;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public Match setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
		return this;
	}

	public boolean getIsYetToCome() {
		return yetToCome;
	}

	public Match setYetToCome(boolean yetToCome) {
		this.yetToCome = yetToCome;
		return this;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public Match setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
		return this;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	public Match setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
		return this;
	}

	public boolean getIsDeviantKickOff() {
		return deviantKickOff;
	}

	public Match setDeviantKickOff(boolean deviantKickOff) {
		this.deviantKickOff = deviantKickOff;
		return this;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
}
