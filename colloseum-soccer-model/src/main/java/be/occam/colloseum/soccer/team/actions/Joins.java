package be.occam.colloseum.soccer.team.actions;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.team.Team;

public class Joins extends Action<Team> {

	@Override
	public Action<Team> execute() {
		
		Team t
			= this.subject;
		
		Person actor
			= this.actor;
		
		String teamId 
			= t.getId();
		
		actor.getTags().add( teamId );
		
		return this;
	}

}
