package be.occam.colloseum.soccer.club.hats;

import java.util.Arrays;
import java.util.List;

import be.occam.colloseum.hat.Hat;
import be.occam.colloseum.model.Tag;
import be.occam.colloseum.soccer.hitman.HitMan;
import be.occam.colloseum.soccer.player.Player;

public class Bookie extends ClubHat {
	
	public Bookie(Tag clubTag) {
		super(clubTag);
	}

	public List<HitMan> getHitMen() {
		
		HitMan[] men
			= new HitMan[] {
				new HitMan( new Player("Dave"), 4 ),
				new HitMan( new Player("Svekke C"), 3 ),
				new HitMan( new Player("Svekke G"), 3 ),
				new HitMan( new Player("Steven"), 4 ),
				new HitMan( new Player("Zoran"), 2 ),
				new HitMan( new Player("Gijbels"), 3 ),
				new HitMan( new Player("Bart G"), 6 ),
				new HitMan( new Player("Ken"), 9 ),
				new HitMan( new Player("Bjorn"), 7 ),
				new HitMan( new Player("Glen"), 1 )
		};
		
		return Arrays.asList( men );		
	}

}
