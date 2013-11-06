package be.occam.colloseum.soccer.club.actions;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import be.occam.colloseum.soccer.club.Club;

@XmlRootElement
@XmlSeeAlso(Club.class)
public class Join { /*extends Action<Club> {

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
	*/

}
