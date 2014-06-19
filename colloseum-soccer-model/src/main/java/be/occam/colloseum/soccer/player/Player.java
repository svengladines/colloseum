package be.occam.colloseum.soccer.player;

import static be.occam.colloseum.soccer.Tags.*;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.Tags;

@XmlRootElement
@Entity
@DiscriminatorValue(value="PL")
public class Player extends Person {
	
	protected String nickName;
	
	public Player() {
		super();
	}
	
	public Player( Person person ) {
		super( person );
		// just make sure we are a player
		if ( ! this.getTags().contains( PLAYER ) ) {
			this.getTags().add( tag( PLAYER ) );
		}
	}
	
	public Player( String nick ) {
		super();
		this.nickName = nick;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	/**
	@Override
	@Id
	public String getId() {
		return super.getId();
	}
	*/

}