package be.occam.colloseum.soccer.hitman.resources;

import static be.occam.colloseum.util.Resource.response;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.occam.colloseum.soccer.club.hats.Bookie;
import be.occam.colloseum.soccer.hitman.HitMan;
import be.occam.colloseum.soccer.ranking.Ranking;

@Controller
@RequestMapping( {"/hitmen"} )
public class HitMenResource {
	
	protected Logger logger 
		= LoggerFactory.getLogger( this.getClass() );
	
	protected HitMan[] array = {};
	
	protected final Comparator<HitMan> comparator
	 	= this.comparator();
	
	@Resource
	Bookie bookie;
	
	@RequestMapping( method = { RequestMethod.GET } )
	@ResponseBody
	public ResponseEntity<HitMan[]> query() {
		
		List<HitMan> hitMen
			= this.bookie.getHitMen();
		
		Collections.sort( hitMen, this.comparator );
		
		return response( hitMen.toArray( this.array ), HttpStatus.OK );
		
	}
	
	protected Comparator<HitMan> comparator() {
		
		return new Comparator<HitMan>() {

			@Override
			public int compare(HitMan o1, HitMan o2) {
				return o1.getHits() > o2.getHits() ? -1 : 1;
			}
			
		};
		
	}
	
}
