package be.occam.colloseum.core.rs.data;

import be.occam.colloseum.person.Person;
import be.occam.colloseum.soccer.club.Club;
import be.occam.colloseum.soccer.team.Team;

public class Springfield {
	
	public static class Clubs {
		
		public static Club rscs() {
			
			return new Club() {
				
				public Team peewee() {
					return new Team().setId( "rscs.peewee" ).setName("Peewee Soccer Team");
				}
				
			}.setId("rscs").setName( "Royal Sporting Club Springfield" );
			
		}
		
	}
	
	public static class People {
		
		public static Person homer () {
			
			return new Person()
				.setId( "homer.simpson" )
				.setEmail( "homer.simpson@springfield.net" );
			
		}
		
	}

}
