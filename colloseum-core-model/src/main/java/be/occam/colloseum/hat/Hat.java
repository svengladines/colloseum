package be.occam.colloseum.hat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.occam.colloseum.person.Person;

public abstract class Hat {
	
	protected final Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected Person person;
	
	// public abstract Hat doYourThing(); 

}
