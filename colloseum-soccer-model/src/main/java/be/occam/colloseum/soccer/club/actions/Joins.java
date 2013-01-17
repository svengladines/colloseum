package be.occam.colloseum.soccer.club.actions;

import be.occam.colloseum.action.Action;
import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.club.Club;

public class Joins extends Action<Club> {

	@Override
	public Action<Club> execute() {
		
		Club c 
			= this.subject;
		
		Person actor
			= this.actor;
		
		String clubId 
			= c.getId();
		
		actor.getTags().add( clubId );
		
		return this;
	}

}
